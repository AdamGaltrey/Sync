package couk.Adamki11s.IO.Configuration.Tree.Order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderMaster {

	public final String separator = ".";
	public final String ROOT = "SyncTreeConfigROOT";

	// private ArrayList<String> orderedData = new ArrayList<String>();
	LinkedList<String> orderedData = new LinkedList<String>();

	private void writeKey(OrderedKey ok) {
		this.orderedData.add(ok.getKey() + ";");
	}

	private void writeValue(OrderedValue ov) {
		this.orderedData.add(ov.getKey() + ":" + ov.getValue().toString());
	}

	public synchronized LinkedList<String> orderData(Map<String, Object> data) {
		orderedData.clear();
		ArrayList<OrderedObject> structuredObjects = getStructure(data);
		/*
		 * Order Pseudo
		 * 
		 * 1) Process all depth level 1 Values first - Remove all processed
		 * objects from the list
		 * 
		 * 2) Check through keys, only process keys with depth 1 and follow the
		 * chain.
		 * 
		 * 3) Check all depth 2 objects, process objects first.
		 * 
		 * 4) If parent key matches objects parent key then add to chain. -
		 * Remove object from list when processed. Repeat until all depth 2
		 * objects for the parent have been processed.
		 * 
		 * 5) Process next depth level, checking chain the same way as before.
		 * Repeat until all depths have been checked and written.
		 */

		LinkedList<String> orderedData = orderData(structuredObjects);
		return orderedData;
	}

	private int getMaxDepthLevel(List<OrderedObject> list) {
		int maxDepth = 0;
		for (OrderedObject oo : list) {
			if (oo.getDepth() > maxDepth) {
				maxDepth = oo.getDepth();
			} else {
				continue;
			}
		}
		return maxDepth;
	}

	private int getObjectsAtDepthLevel(List<OrderedObject> list, int depthLevel) {
		int objects = 0;
		for (OrderedObject oo : list) {
			if (oo.getDepth() == depthLevel) {
				objects++;
			} else {
				continue;
			}
		}
		return objects;
	}

	private int getObjectsAtDepthLevelWithParent(List<OrderedObject> list, int depthLevel, String parent) {
		int objects = 0;
		for (OrderedObject oo : list) {
			if (oo.getParent().equalsIgnoreCase(parent) && oo.getDepth() == depthLevel) {
				objects++;
			} else {
				continue;
			}
		}
		return objects;
	}

	private int getParentObjects(List<OrderedObject> list, String parent) {
		int objects = 0;
		for (OrderedObject oo : list) {
			if (oo.getParent().equalsIgnoreCase(parent)) {
				objects++;
			} else {
				continue;
			}
		}
		return objects;
	}

	int processedObjects = 0;
	int processableObjects;

	int[] depthLevelCounts;

	private void processOrderList(List<OrderedObject> structuredObjects, int depth) {
		for (OrderedObject oo : structuredObjects) {
			if (!oo.hasBeenRead() && oo.getDepth() == depth) {
				oo.read();
				int objectsToRead = (this.getObjectsAtDepthLevelWithParent(structuredObjects, oo.getDepth() + 1, oo.getKey())), objectsRead = 0;
				for(OrderedObject ooV : structuredObjects){
					if(ooV instanceof OrderedValue && ooV.getDepth() == depth && ooV.getParent().equalsIgnoreCase(oo.getKey())){
						ooV.read();
						objectsRead++;
						this.writeValue((OrderedValue) ooV);
					}
				}
				if(objectsToRead == objectsRead){
					processOrderList(structuredObjects, 0);
				}
				for(OrderedObject ooK : structuredObjects){
					if(ooK instanceof OrderedKey && ooK.getDepth() == depth && ooK.getParent().equalsIgnoreCase(oo.getKey())){
						ooK.read();
						objectsRead++;
						this.writeKey((OrderedKey) ooK);
					}
				}
			}
		}
	}
	
	private void processBranches(List<OrderedObject> structuredObjects, String parent, int depth){
		
	}

	private LinkedList<String> orderData(List<OrderedObject> structuredObjects) {

		processedObjects = 0;
		processableObjects = structuredObjects.size();
		depthLevelCounts = new int[getMaxDepthLevel(structuredObjects)];

		for (int i = 0; i < depthLevelCounts.length; i++) {
			depthLevelCounts[i] = getObjectsAtDepthLevel(structuredObjects, i);
		}

		return orderedData;
	}

	private ArrayList<OrderedObject> getStructure(Map<String, Object> data) {
		ArrayList<OrderedObject> structuredObjects = new ArrayList<OrderedObject>();
		for (Entry<String, Object> entry : data.entrySet()) {
			String[] parts = entry.getKey().split(separator);
			if (parts.length > 1) {
				int maxDepth = parts.length;
				for (int d = 0; d < maxDepth; d++) {
					if (d != maxDepth) {
						if (d != 0) {
							OrderedKey ok = new OrderedKey(parts[d - 1], parts[d], d);
							if (isDuplicateKey(ok, structuredObjects)) {
								// duplicate key, depth, parent exception
								continue;
							} else {
								structuredObjects.add(ok);
							}
							continue;
						} else {
							OrderedKey ok = new OrderedKey(ROOT, parts[d], d);
							if (isDuplicateKey(ok, structuredObjects)) {
								// duplicate key, depth, parent exception
								continue;
							} else {
								structuredObjects.add(ok);
							}
							continue;
						}
					} else {
						OrderedValue ov = new OrderedValue(parts[d - 1], parts[d], entry.getValue(), d);
						if (isDuplicateKey(ov, structuredObjects)) {
							// duplicate key, depth, parent exception
							continue;
						} else {
							structuredObjects.add(ov);
						}
						continue;
					}
				}
			} else {
				OrderedValue ov = new OrderedValue(ROOT, parts[0], entry.getValue(), 0);
				if (isDuplicateKey(ov, structuredObjects)) {
					// duplicate key, depth, parent exception
					continue;
				} else {
					structuredObjects.add(ov);
				}
				continue;
			}
		}
		return structuredObjects;
	}

	private boolean isDuplicateKey(OrderedObject o1, List<OrderedObject> list) {
		for (OrderedObject o2 : list) {
			if (o1.getKey().equalsIgnoreCase(o2.getKey()) && o1.getDepth() == o2.getDepth() && o1.getParent().equalsIgnoreCase(o2.getParent())) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}

}
