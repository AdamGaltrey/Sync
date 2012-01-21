package couk.Adamki11s.Sync;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import couk.Adamki11s.Managers.SyncControl;
import couk.Adamki11s.Scheduler.CoreAsyncThread;
import couk.Adamki11s.Scheduler.CoreSyncThread;

public class Sync extends JavaPlugin {
	
	public static Logger log = Logger.getLogger("Sync");
	public static String version = "1.0.0";
	public static final String prefix = "[Sync]";

	@Override
	public void onDisable() {
		log.info(prefix + " Sync Version " + version + " un-loaded successfully.");
	}

	@Override
	public void onEnable() {
		logGenericInfo("***** SYNC *****");
		version = this.getDescription().getVersion();
		logGenericInfo(prefix + " Sync Version " + version + " loaded successfully.");
		logGenericInfo("***** SYNC *****");
	}
	
	public static void logGenericInfo(String message){
		log.info(prefix + " " + message);
	}
	
	public SyncControl getSyncControl(){
		return new SyncControl();
	}

}
