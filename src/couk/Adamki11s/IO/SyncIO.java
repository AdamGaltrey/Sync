package couk.Adamki11s.IO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SyncIO extends IOStream {
	
	private File f;
	private HashMap<String, Object> writeableData = new HashMap<String, Object>();
	private HashMap<String, Object> readableData = new HashMap<String, Object>();
	
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
	
	public void write(){
		super.write(this.f, this.writeableData);
	}
	
	public void read(){
		this.readableData = super.read(this.f);
	}

}
