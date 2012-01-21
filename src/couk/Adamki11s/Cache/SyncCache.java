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
	
	public boolean exists(String id){
		return this.reference.containsKey(id);
	}
	
	public Object getObject(String key){
		return this.reference.get(key);
	}

	public HashMap<String, Object> getCache() {
		return this.reference;
	}

}
