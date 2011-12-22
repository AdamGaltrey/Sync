package couk.Adamki11s.Sync;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Sync extends JavaPlugin {
	
	private Logger log = Logger.getLogger("Sync");
	private String version;

	@Override
	public void onDisable() {
		log.info("[Sync] Sync Version " + version + " un-loaded successfully.");
	}

	@Override
	public void onEnable() {
		this.version = this.getDescription().getVersion();
		log.info("[Sync] Sync Version " + version + " loaded successfully.");
	}

}
