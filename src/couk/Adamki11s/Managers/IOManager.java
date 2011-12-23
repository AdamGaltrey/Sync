package couk.Adamki11s.Managers;

import java.io.File;
import java.io.IOException;

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
	
	public void createPluginFolder(){
		File f = new File("plugins" + File.separator + sm.getPlugin().getDescription().getName());
		if(!f.exists()){
			f.mkdir();
		}
	}
	
	public void createPluginConfiguration(){
		File f = new File("plugins" + File.separator + sm.getPlugin().getDescription().getName() + File.separator + "Configuration.yml");
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
