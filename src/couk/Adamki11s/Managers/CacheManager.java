package couk.Adamki11s.Managers;

import couk.Adamki11s.Cache.Cache;

public class CacheManager {
	
	SyncManager sm;
	
	public CacheManager(SyncManager sm){
		this.sm = sm;
	}
	
	public Cache getPluginCache(){
		return new Cache(sm.getPlugin());
	}

}
