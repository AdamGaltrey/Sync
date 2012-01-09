package couk.Adamki11s.IO;

import java.io.File;
import java.io.IOException;

public class SyncIO {
	
	private File f;
	
	public SyncIO(File f){
		this.f = f;
	}
	
	public File getFile(){
		return this.f;
	}
	
	public boolean exists(){
		return f.exists();
	}
	
	public void createNewFile() throws IOException{
		f.createNewFile();
	}
	
	public void delete(){
		f.delete();
	}

}
