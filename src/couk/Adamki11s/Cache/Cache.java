package couk.Adamki11s.Cache;
import java.util.HashMap;

import org.bukkit.plugin.Plugin;


public class Cache {
	
	private HashMap<String, Object> contents = new HashMap<String, Object>();
	
	private Plugin p;
	
	public Cache(Plugin p){
		this.p = p;
	}
	
	public void add(String key, Object data){
		this.contents.put(key, data);
	}
	
	public void remove(String key){
		if(this.contents.containsKey(key)){
			this.contents.remove(key);
		}
	}
	
	public void erase(){
		this.contents.clear();
	}
	
	public HashMap<String, Object> getContents(){
		return this.contents;
	}
	
	public int getSize(){
		return this.contents.size();
	}

}
