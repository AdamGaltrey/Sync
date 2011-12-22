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
		this.cacheManager = new CacheManager(this);
		this.ioManager = new IOManager(this);
		this.sqlManager = new SQLManager(this);
		this.webManager = new WebManager(this);
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public IOManager getIOManager() {
		return ioManager;
	}

	public SQLManager getSqlManager() {
		return sqlManager;
	}

	public WebManager getWebManager() {
		return webManager;
	}

}
