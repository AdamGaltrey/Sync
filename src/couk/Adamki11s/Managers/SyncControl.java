package couk.Adamki11s.Managers;

import java.io.File;

import couk.Adamki11s.Cache.SyncCache;
import couk.Adamki11s.Cryptography.SyncCryptography;
import couk.Adamki11s.IO.Objects.SyncObjectIO;
import couk.Adamki11s.IO.Standard.SyncIO;
import couk.Adamki11s.SQL.SyncSQL;
import couk.Adamki11s.Updates.SyncUpdater;
import couk.Adamki11s.Web.SyncWeb;

public class SyncControl {

	public SyncCache getSyncCache() {
		return new SyncCache();
	}

	public SyncCryptography getSyncCryptography() {
		return new SyncCryptography();
	}

	public SyncIO getSyncIO(File f) {
		return new SyncIO(f);
	}
	
	public SyncObjectIO getSyncObjectIO(File f){
		return new SyncObjectIO(f);
	}

	public SyncWeb getSyncWeb() {
		return new SyncWeb();
	}

	public SyncSQL getSyncSQLite(File f) {
		return new SyncSQL(f);
	}

	public SyncSQL getSyncMySQL(String host, String database, String username, String password) {
		return new SyncSQL(host, database, username, password);
	}

	public SyncUpdater getSyncUpdater() {
		return new SyncUpdater();
	}

}
