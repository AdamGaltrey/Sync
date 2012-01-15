package couk.Adamki11s.Managers;

import java.io.File;

import org.bukkit.plugin.Plugin;

import couk.Adamki11s.Cache.SyncCache;
import couk.Adamki11s.Cryptography.SyncCryptography;
import couk.Adamki11s.IO.SyncIO;
import couk.Adamki11s.Plugins.SyncPluginData;
import couk.Adamki11s.Web.SyncWeb;

public class SyncControl {
	
	public SyncCache getSyncCache(int updateRate) {
		return new SyncCache(updateRate);
	}
	public SyncCryptography getSyncCryptography() {
		return new SyncCryptography();
	}
	public SyncIO getSyncIO(File f) {
		return new SyncIO(f);
	}
	public SyncPluginData getSyncPluginData() {
		return new SyncPluginData();
	}
	public SyncWeb getSyncWeb() {
		return new SyncWeb();
	}

}
