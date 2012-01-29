package couk.Adamki11s.Cache;

import java.util.HashMap;

public class SyncCache {

	private HashMap<String, Object> reference = new HashMap<String, Object>();

	/**
	 * Adds an object to the cache with the corresponding Id.
	 * @param id Reference
	 * @param data Object
	 */
	public void add(String id, Object data) {
		this.reference.put(id, data);
	}

	/**
	 * Removes the Object with the corresponding Id from the cache.
	 * @param id Reference
	 */
	public void remove(String id) {
		this.reference.remove(id);
	}

	/**
	 * Check if there is an object linked to an Id.
	 * @param id
	 * @return Whether an Object linked to the specified String exists.
	 */
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

	/**
	 * Get all objects from the Cache.
	 * @return Cache Objects
	 */
	public HashMap<String, Object> getCache() {
		return this.reference;
	}
	
	/**
	 * Erase the contents of the Cache.
	 */
	public void erase(){
		this.reference.clear();
	}

}
