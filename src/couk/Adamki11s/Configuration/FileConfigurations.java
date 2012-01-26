package couk.Adamki11s.Configuration;

import java.io.File;
import java.io.IOException;

import couk.Adamki11s.IO.Standard.SyncIO;

public class FileConfigurations {
	
	public static File updatesConfiguration = new File(FolderConfigurations.syncConfiguration + File.separator + "Updates.config");
	
	public static void createConfigurations(){
		updatesConfiguration();
	}
	
	private static void updatesConfiguration(){
		if(!updatesConfiguration.exists()){
			try {
				updatesConfiguration.createNewFile();
				SyncIO io = new SyncIO(updatesConfiguration);
				io.add("CheckForUpdates", true);
				io.addComment("Whether to check for plugin updates for Sync alone. Other plugins which use Sync should have their own configurations.");
				io.add("AutoDownloadUpdates", true);
				io.addComment("Whether Sync will automatically download the newest version of itself");
				io.add("DownloadPath", FolderConfigurations.syncUpdates.getAbsoluteFile());
				io.addComment("The File path where Sync will download the newest version to.");
				io.write();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		SyncIO io = new SyncIO(updatesConfiguration);
		io.read();
		boolean checkForUpdates = io.getBoolean("CheckForUpdates"), autoDownload = io.getBoolean("AutoDownloadUpdates");
		File downloadPath = new File(io.getString("DownloadPath").replace("\\", File.separator));
	}

}
