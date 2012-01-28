package couk.Adamki11s.Cache;

import java.util.HashMap;

public class SyncCache {

	private HashMap<String, Object> reference = new HashMap<String, Object>();

	public void add(String id, Object data) {
		this.reference.put(id, data);
	}

	public void remove(String id) {
		this.reference.remove(id);
	}

	public boolean exists(String id) {
		return this.reference.containsKey(id);
	}

	public Object getObject(String key) {
		return this.reference.get(key);
	}

	public String getString(String key) {
		return getObject(key).toString();
	}

	public int getInteger(String key) {
		int i = 0;
		try {
			i = Integer.parseInt(getString(key));
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return i;
	}
	
	public double getDouble(String key) {
		double i = 0;
		try {
			i = Double.parseDouble(getString(key));
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return i;
	}
	
	public float getFloat(String key) {
		float i = 0;
		try {
			i = Float.parseFloat(getString(key));
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return i;
	}
	
	public boolean getBoolean(String key){
		return Boolean.parseBoolean(getString(key));
	}

	public HashMap<String, Object> getCache() {
		return this.reference;
	}
	
	public void erase(){
		this.reference.clear();
	}

}
