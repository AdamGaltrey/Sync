package couk.Adamki11s.IO.Configuration.Tree;

import java.io.File;

import couk.Adamki11s.IO.GenericIO;

public class SyncTreeConfiguration extends GenericIO {
	
	private final File f;
	private final boolean append;
	
	public SyncTreeConfiguration(File f){
		this.f = f;
		this.append = false;
	}
	
	public SyncTreeConfiguration(File f, boolean append){
		this.f = f;
		this.append = append;
	}

}
