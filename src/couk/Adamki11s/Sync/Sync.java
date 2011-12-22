package couk.Adamki11s.Sync;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

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
		log.info(prefix + " Sync Version " + version + " loaded successfully.");
	}

}
