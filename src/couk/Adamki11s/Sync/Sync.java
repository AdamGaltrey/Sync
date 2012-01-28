package couk.Adamki11s.Sync;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import couk.Adamki11s.Configuration.FileConfigurations;
import couk.Adamki11s.Configuration.FolderConfigurations;
import couk.Adamki11s.Configuration.GlobalConfiguration;
import couk.Adamki11s.Managers.SyncControl;
import couk.Adamki11s.Updates.UpdateCycle;

public class Sync extends JavaPlugin {

	public static Logger log = Logger.getLogger("Sync");
	public static String version = "1.0.0";
	public static final String prefix = "[Sync]";
	public static Plugin plugin;
	private static int updateCycleTaskId;

	@Override
	public void onDisable() {
		if (GlobalConfiguration.checkForUpdates) {
			Bukkit.getServer().getScheduler().cancelTask(updateCycleTaskId);
			logGenericInfo("Update thread stopped.");
		}
		logGenericInfo(" Sync Version " + version + " un-loaded successfully.");
	}

	@Override
	public void onEnable() {
		plugin = this;
		FolderConfigurations.folderChecks();
		FileConfigurations.createConfigurations();
		if (GlobalConfiguration.checkForUpdates) {
			logGenericInfo("Checking for updates every " + GlobalConfiguration.updateCycle + " minutes.");
			updateCycleTaskId = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new UpdateCycle(), 20L, (GlobalConfiguration.updateCycle * 60 * 20));
		} else {
			logGenericInfo("Not checking for updates");
		}
		logGenericInfo("***** SYNC *****");
		version = this.getDescription().getVersion();
		logGenericInfo(prefix + " Sync Version " + version + " loaded successfully.");
		logGenericInfo("***** SYNC *****");
	}

	public static void logGenericInfo(String message) {
		log.info(prefix + " " + message);
	}
	
	public static void logGenericWarning(String message) {
		log.warning(prefix + " " + message);
	}

	public SyncControl getSyncControl() {
		return new SyncControl();
	}

}
