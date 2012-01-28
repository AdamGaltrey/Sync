package couk.Adamki11s.Updates;

import java.io.File;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import couk.Adamki11s.Sync.Sync;
import couk.Adamki11s.Web.WebFile;

public class UpdateCycle implements Runnable {

	@Override
	public void run() {
		HashSet<UpdatePackage> services = UpdateService.getServices();
		if (services.size() != 0) {
			Sync.logGenericInfo("Checking updates for " + services.size() + " plugins");
		}
		for (UpdatePackage pack : services) {
			SyncVersionData svd = SyncUpdater.getSyncVersionData(pack.getLink().toString());
			Plugin p = pack.getPlugin();
			if (!p.getDescription().getVersion().equalsIgnoreCase(svd.getVersion())) {
				if (pack.isAutoDownloadingUpdates()) {
					Sync.logGenericInfo("Updating plugin " + p.getDescription().getName() + " from v" + p.getDescription().getVersion() + " >> v" + svd.getVersion() + "...");
					WebFile.download(svd.getDownloadLink().toString(), pack.getFile());
				} else {
					Sync.logGenericWarning("Outdated plugin : " + p.getDescription().getName() + ". Running version " + p.getDescription().getVersion() + ". Newest version " + svd.getVersion());
				}
				if(pack.isReloadingAfterUpdate()){
					Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "rl");
				}
			}
		}
		if (services.size() != 0) {
			Sync.logGenericInfo("Update checking completed!");
		}
	}

}
