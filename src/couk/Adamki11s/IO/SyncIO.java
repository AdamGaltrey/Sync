package couk.Adamki11s.IO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SyncIO extends IOStream {
	
	private File f;
	private LinkedHashMap<String, Object> writeableData = new LinkedHashMap<String, Object>();
	private LinkedHashMap<String, Object> readableData = new LinkedHashMap<String, Object>();
	
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
		try {
			super.write(this.f, this.writeableData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read(){
		LinkedHashMap<String, Object> data = super.read(this.f);
	}

}
