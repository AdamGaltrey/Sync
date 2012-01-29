package couk.Adamki11s.Sync;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import couk.Adamki11s.Configuration.FileConfigurations;
import couk.Adamki11s.Configuration.FolderConfigurations;
import couk.Adamki11s.Configuration.GlobalConfiguration;
import couk.Adamki11s.Statistics.RegisterCycle;
import couk.Adamki11s.Updates.UpdateCycle;

public class Sync extends JavaPlugin {

	public static Logger log = Logger.getLogger("Sync");
	public static String version = "1.0.0";
	public static final String prefix = "[Sync]";
	public static Plugin plugin;
	private static int updateCycleTaskId, statisticCycleTaskId;

	@Override
	public void onDisable() {
		if (GlobalConfiguration.isCheckForUpdates()) {
			Bukkit.getServer().getScheduler().cancelTask(updateCycleTaskId);
			logGenericInfo("Update thread stopped.");
		}
		if(GlobalConfiguration.isAllowStatisticTracking()){
			Bukkit.getServer().getScheduler().cancelTask(statisticCycleTaskId);
		}
		logGenericInfo(" Sync Version " + version + " un-loaded successfully.");
	}

	@Override
	public void onEnable() {
		logGenericInfo("Loading Sync...");
		plugin = this;
		FolderConfigurations.folderChecks();
		FileConfigurations.createConfigurations();
		if (GlobalConfiguration.isCheckForUpdates()) {
			logGenericInfo("Checking for updates every " + GlobalConfiguration.getUpdateCycle() + " minutes.");
			updateCycleTaskId = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(this, new UpdateCycle(), 20L, (GlobalConfiguration.getUpdateCycle() * 60 * 20));
		} else {
			logGenericInfo("Sync Update checking disabled.");
		}
		if (GlobalConfiguration.isAllowStatisticTracking()) {
			logGenericInfo("Saving plugin statistics every " + GlobalConfiguration.getStatisticUpdateCycle() + " minutes.");
			statisticCycleTaskId = Bukkit.getServer().getScheduler()
					.scheduleAsyncRepeatingTask(this, new RegisterCycle(), 20L, (GlobalConfiguration.getStatisticUpdateCycle() * 60 * 20));
		} else {
			logGenericInfo("Sync Statistic tracking disabled.");
		}
		version = this.getDescription().getVersion();
		logGenericInfo(prefix + " Sync Version " + version + " loaded successfully.");
		logGenericInfo("Sync loaded successfully!");
	}

	public static void logGenericInfo(String message) {
		log.info(prefix + " " + message);
	}

	public static void logGenericWarning(String message) {
		log.warning(prefix + " " + message);
	}

}
