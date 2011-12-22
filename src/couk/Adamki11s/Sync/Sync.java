package couk.Adamki11s.Sync;

import java.util.logging.Logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import couk.Adamki11s.Managers.SyncManager;

public class Sync extends JavaPlugin {
	
	public static Logger log = Logger.getLogger("Sync");
	public static String version;
	public static final String prefix = "[Sync]";

	@Override
	public void onDisable() {
		log.info(prefix + " Sync Version " + version + " un-loaded successfully.");
	}

	@Override
	public void onEnable() {
		this.version = this.getDescription().getVersion();
		//this.getServer().getServicesManager().register(PermissionManager.class, this.permissionsManager, this, ServicePriority.Normal);
		this.getServer().getServicesManager().register(Sync.class, this, this, ServicePriority.Normal);
		log.info(prefix + " Sync Version " + version + " loaded successfully.");
	}
	
	public SyncManager getSyncManager(Plugin p){
		return new SyncManager(p);
	}

}
