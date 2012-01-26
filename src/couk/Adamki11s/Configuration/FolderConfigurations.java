package couk.Adamki11s.Configuration;

import java.io.File;

import couk.Adamki11s.Managers.SyncLog;

public class FolderConfigurations {

	public static final File root = new File("plugins" + File.separator + "Sync"), pluginStatistics = new File(root + File.separator + "Plugin Statistics"),
			syncConfiguration = new File(root + File.separator + "Sync Configuration"), syncBackups = new File(root + File.separator + "Backups"), syncUpdates = new File(root
					+ File.separator + "Plugin Updates");

	public static void folderChecks() {
		if (!root.exists()) {
			SyncLog.logInfo("Sync Root folder created.");
			root.mkdir();
		}
		if (!pluginStatistics.exists()) {
			SyncLog.logInfo("Sync Plugin Statistics folder created.");
			pluginStatistics.mkdir();
		}
		if (!syncConfiguration.exists()) {
			SyncLog.logInfo("Sync Configuration folder created.");
			syncConfiguration.mkdir();
		}
		if (!syncBackups.exists()) {
			SyncLog.logInfo("Sync Backup folder created.");
			syncBackups.mkdir();
		}
		if (!syncUpdates.exists()) {
			SyncLog.logInfo("Sync Plugin Updates folder created.");
			syncUpdates.mkdir();
		}
	}

}
