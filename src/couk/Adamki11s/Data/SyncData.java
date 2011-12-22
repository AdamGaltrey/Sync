package couk.Adamki11s.Data;

import org.bukkit.plugin.Plugin;

public class SyncData {
	
	private Plugin plugin;
	private SyncManager syncManager;
	
	public SyncData(Plugin plugin){
		this.plugin = plugin;
	}
	
	public Plugin getPlugin(){
		return this.plugin;
	}

}
