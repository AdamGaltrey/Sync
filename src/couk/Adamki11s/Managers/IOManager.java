package couk.Adamki11s.Managers;

import java.io.File;

import couk.Adamki11s.Data.IOStream;

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
	
	
}
