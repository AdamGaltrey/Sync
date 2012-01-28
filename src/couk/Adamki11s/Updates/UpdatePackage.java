package couk.Adamki11s.Updates;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.bukkit.plugin.Plugin;

public class UpdatePackage {
	
	private URL link;
	private boolean autoDownloadUpdates, reloadAfterUpdate;
	private File f;
	private Plugin plugin;
	
	public UpdatePackage(Plugin plugin, String link, boolean autoDownloadUpdates, boolean reloadAfterUpdate, File f) {
		if(reloadAfterUpdate){
			if(!f.getAbsoluteFile().toString().endsWith("plugins" + File.separator + "Update" + File.separator + plugin.getDescription().getName() + ".jar")){	
				throw new IllegalArgumentException("Plugin can only reload after update if the path is plugins/Update/" + plugin.getDescription().getName()  +".jar");
			}
		}
		this.plugin = plugin;
		try {
			this.link = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		this.autoDownloadUpdates = autoDownloadUpdates;
		this.reloadAfterUpdate = reloadAfterUpdate;
		this.f = f;
	}

	public URL getLink() {
		return link;
	}

	public void setLink(URL link) {
		this.link = link;
	}

	public boolean isAutoDownloadingUpdates() {
		return autoDownloadUpdates;
	}

	public void setAutoDownloadUpdates(boolean autoDownloadUpdates) {
		this.autoDownloadUpdates = autoDownloadUpdates;
	}

	public boolean isReloadingAfterUpdate() {
		return reloadAfterUpdate;
	}

	public void setReloadAfterUpdate(boolean reloadAfterUpdate) {
		this.reloadAfterUpdate = reloadAfterUpdate;
	}

	public File getFile() {
		return this.f;
	}

	public void setFile(File f) {
		this.f = f;
	}
	
	public Plugin getPlugin(){
		return this.plugin;
	}
	

}
