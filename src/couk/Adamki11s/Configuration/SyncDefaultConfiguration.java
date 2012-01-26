package couk.Adamki11s.Configuration;

import java.io.File;

import couk.Adamki11s.IO.Standard.SyncIO;

public class SyncDefaultConfiguration {

	private final File f;
	
	private boolean autoDownloadUpdates = true, checkForUpdates = true, reloadAfterUpdate = false;
	private String outputPath = FolderConfigurations.syncUpdates.getAbsolutePath();
	
	public SyncDefaultConfiguration(File f){
		this.f = f;
	}

	public void setAutoDownloadUpdates(boolean autoDownloadUpdates) {
		this.autoDownloadUpdates = autoDownloadUpdates;
	}

	public void setCheckForUpdates(boolean checkForUpdates) {
		this.checkForUpdates = checkForUpdates;
	}

	public void setReloadAfterUpdate(boolean reloadAfterUpdate) {
		this.reloadAfterUpdate = reloadAfterUpdate;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	
	public void write(){
		SyncIO stream = new SyncIO(this.f);
		stream.add("CheckForUpdates", this.checkForUpdates);
		stream.add("AutoDownloadUpdates", this.autoDownloadUpdates);
		stream.add("ReloadAfterUpdate", this.reloadAfterUpdate);
		stream.add("DownloadPath", this.outputPath);
		stream.write();
	}
	
	public SyncConfigurationData read(){
		SyncIO stream = new SyncIO(this.f);
		stream.read();
		boolean a = stream.getBoolean("AutoDownloadUpdates"), b = stream.getBoolean("CheckForUpdates"), c = stream.getBoolean("ReloadAfterUpdate");
		String o = stream.getString("DownloadPath");
		return new SyncConfigurationData(a, b, c, o);
	}

}

class SyncConfigurationData {
	
	private final boolean autoDownloadUpdates, checkForUpdates, reloadAfterUpdate;
	private final String outputPath;
	
	public SyncConfigurationData(boolean autoDownloadUpdates, boolean checkForUpdates, boolean reloadAfterUpdate, String outputPath) {
		this.autoDownloadUpdates = autoDownloadUpdates;
		this.checkForUpdates = checkForUpdates;
		this.reloadAfterUpdate = reloadAfterUpdate;
		this.outputPath = outputPath;
	}
	
	public boolean isAutoDownloadingUpdates(){
		return this.autoDownloadUpdates;
	}
	
	public boolean isCheckingForUpdates(){
		return this.checkForUpdates;
	}
	
	public boolean isReloadingAfterUpdate(){
		return this.reloadAfterUpdate;
	}
	
	public String getOutputPath(){
		return this.outputPath;
	}
	
	public File getOutputFile(){
		return new File(this.outputPath.replace("\\", File.separator));
	}
	
}
