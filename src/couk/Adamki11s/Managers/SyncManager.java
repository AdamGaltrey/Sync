package couk.Adamki11s.Managers;

import org.bukkit.plugin.Plugin;

public class SyncManager {
	
	private Plugin plugin;
	
	private CacheManager cacheManager;
	private IOManager ioManager;
	private SQLManager sqlManager;
	private WebManager webManager;
	
	public SyncManager(Plugin plugin){
		this.plugin = plugin;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public IOManager getIoManager() {
		return ioManager;
	}

	public SQLManager getSqlManager() {
		return sqlManager;
	}

	public WebManager getWebManager() {
		return webManager;
	}

}
