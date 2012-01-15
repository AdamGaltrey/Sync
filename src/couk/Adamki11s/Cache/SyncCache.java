package couk.Adamki11s.Cache;

import java.util.HashMap;

public class SyncCache {

	private HashMap<String, CacheObject> reference = new HashMap<String, CacheObject>();
	private long lastCheck;
	
	private int updateRate = 1;
	private boolean runChecks = true;
	
	public SyncCache(int updateRate){
		this.updateRate = updateRate;
		this.runChecks = true;
	}
	
	public SyncCache(int updateRate, boolean runChecks){
		this.updateRate = updateRate;
		this.runChecks = runChecks;
	}
	
	public boolean isRunningChecks(){
		return this.runChecks;
	}
	
	public int getUpdateRate(){
		return this.updateRate;
	}
	
	public void setTimeCheck(){
		this.lastCheck = System.currentTimeMillis();
	}
	
	public boolean canBeChecked(){
		return ((System.currentTimeMillis() / 1000) - (this.lastCheck / 1000)) >= this.updateRate;
	}

	public void add(String id, Object data) {
		this.reference.put(id, new CacheObject(data, 0));
	}

	public void add(String id, Object data, int persistanceDuration) {
		this.reference.put(id, new CacheObject(data, persistanceDuration));
	}

	public void remove(String id) {
		this.reference.remove(id);
	}
	
	public boolean exists(String id){
		return this.reference.containsKey(id);
	}
	
	public long getTimeUntilErased(String id){
		return (!exists(id) ? 0 : this.reference.get(id).getTimeUntilErased());
	}

	public HashMap<String, CacheObject> getCache() {
		return this.reference;
	}

}
