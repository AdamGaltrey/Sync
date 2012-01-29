package couk.Adamki11s.Configuration;

import java.io.File;
import java.io.IOException;

import couk.Adamki11s.IO.Standard.SyncIO;

public class FileConfigurations {

	public static File updatesConfiguration = new File(FolderConfigurations.syncConfiguration + File.separator + "Updates.config"),
			commandConfiguration = new File(FolderConfigurations.syncConfiguration + File.separator + "PluginStatistics.config");

	public static void createConfigurations() {
		updatesConfiguration();
		statisticsConfiguration();
	}
	
	private static void statisticsConfiguration(){
		if (!commandConfiguration.exists()) {
			try {
				commandConfiguration.createNewFile();
				SyncIO io = new SyncIO(commandConfiguration);
				io.add("AllowStatisticTracking", true);
				io.addComment("Whether other plugins can register a statistic service with Sync.");
				io.add("StatisticUpdateCycle", 30);
				io.addComment("How often Sync will write all statistics for plugins registered with Sync to storage, in minutes. (Default = [30 mins])");
				io.write();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		SyncIO io = new SyncIO(commandConfiguration);
		io.read();
		boolean allowStatTracking = io.getBoolean("AllowStatisticTracking");
		int updateCycle = io.getInt("StatisticUpdateCycle");
		GlobalConfiguration.allowStatisticTracking = allowStatTracking;
		GlobalConfiguration.statisticUpdateCycle = updateCycle;
	}

	private static void updatesConfiguration() {
		if (!updatesConfiguration.exists()) {
			try {
				updatesConfiguration.createNewFile();
				SyncIO io = new SyncIO(updatesConfiguration);
				io.add("UpdateCheckCycle", 60);
				io.addComment("How often Sync will check for updates for all plugins registered with Sync, in minutes. (Default = 1 hour [60 mins])");
				io.add("CheckForUpdates", true);
				io.addComment("Whether to check for plugin updates. Other plugins which use Sync should have their own configurations.");
				io.add("AutoDownloadUpdates", true);
				io.addComment("Whether Sync will automatically download the newest version of itself");
				io.add("ReloadAfterUpdate", true);
				io.addComment("Whether Sync will force a server reload after updating. This is recommended only if you want to force the new version onto the server automatically in which case the Download Path should be set to plugins/Updates");
				io.add("DownloadPath", FolderConfigurations.syncUpdates.getAbsoluteFile());
				io.addComment("The File path where Sync will download the newest version to. Do not start with / Eg : '/plugins/AFolder'. Make sure it is : 'plugins/AFolder'");
				io.write();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		SyncIO io = new SyncIO(updatesConfiguration);
		io.read();
		boolean checkForUpdates = io.getBoolean("CheckForUpdates"), autoDownload = io.getBoolean("AutoDownloadUpdates"), reloadAfterUpdate = io.getBoolean("ReloadAfterUpdate");
		File downloadPath = new File(io.getString("DownloadPath").replace("\\", File.separator));
		int updateCycle = io.getInt("UpdateCheckCycle");
		GlobalConfiguration.checkForUpdates = checkForUpdates;
		GlobalConfiguration.autoDownloadUpdates = autoDownload;
		GlobalConfiguration.reloadAfterUpdate = reloadAfterUpdate;
		GlobalConfiguration.downloadPath = downloadPath;
		GlobalConfiguration.updateCycle = updateCycle;
	}

}
