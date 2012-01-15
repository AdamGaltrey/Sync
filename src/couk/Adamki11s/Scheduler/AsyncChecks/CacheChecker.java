package couk.Adamki11s.Scheduler.AsyncChecks;

import java.util.HashSet;
import java.util.Map.Entry;

import couk.Adamki11s.Cache.CacheObject;
import couk.Adamki11s.Cache.SyncCache;

public class CacheChecker {
	
	public static HashSet<SyncCache> caches = new HashSet<SyncCache>();
	
	public static void checkAll(){
		for(SyncCache cache : caches){
			if(!cache.isRunningChecks()){
				continue;
			} else {
				if(cache.getUpdateRate() == 1 || cache.canBeChecked()){
					cache.setTimeCheck();
					updateCache(cache);
				}
			}
		}
	}
	
	private static void updateCache(SyncCache cache){
		HashSet<String> toDispose = new HashSet<String>();
		for(Entry<String, CacheObject> entry : cache.getCache().entrySet()){
			if(entry.getValue().canBeErased()){
				toDispose.add(entry.getKey());
				entry.getValue().dispose();
			} else {
				continue;
			}
		}
		for(String s : toDispose){
			cache.getCache().remove(s);
		}
	}

}
