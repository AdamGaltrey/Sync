package couk.Adamki11s.Managers;

import couk.Adamki11s.Cache.SyncCache;
import couk.Adamki11s.Cryptography.SyncCryptography;
import couk.Adamki11s.IO.SyncIO;
import couk.Adamki11s.Plugins.SyncPluginData;
import couk.Adamki11s.Web.SyncWeb;

public class SyncControl {
	
	private SyncCache syncCache = new SyncCache();
	private SyncCryptography syncCryptography = new SyncCryptography();
	private SyncIO syncIO = new SyncIO();
	private SyncPluginData syncPluginData = new SyncPluginData();
	private SyncWeb syncWeb = new SyncWeb();
	
	public SyncCache getSyncCache() {
		return syncCache;
	}
	public SyncCryptography getSyncCryptography() {
		return syncCryptography;
	}
	public SyncIO getSyncIO() {
		return syncIO;
	}
	public SyncPluginData getSyncPluginData() {
		return syncPluginData;
	}
	public SyncWeb getSyncWeb() {
		return syncWeb;
	}

}
