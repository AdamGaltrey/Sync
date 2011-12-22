package couk.Adamki11s.Managers;

import java.io.File;

import couk.Adamki11s.IO.EncryptedIOStream;
import couk.Adamki11s.IO.IOStream;
import couk.Adamki11s.IO.LocationIOStream;

public class IOManager {
	
	SyncManager sm;

	public IOManager(SyncManager sm) {
		this.sm = sm;
	}
	
	/**
	 * Creates a new IOStream Instance
	 * @param f The File which will be read and written to.
	 * @return The IOStream
	 */
	public IOStream getIOStream(File f){
		return new IOStream(f);
	}
	
	public LocationIOStream getLocationIOStream(File f){
		return new LocationIOStream(f);
	}
	
	public EncryptedIOStream getEncryptedIOStream(File f, String password){
		return new EncryptedIOStream(f, password);
	}
	
	
}
