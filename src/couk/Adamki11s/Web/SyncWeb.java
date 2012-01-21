package couk.Adamki11s.Web;

import java.io.File;
import java.net.URL;

public class SyncWeb {
	
	public void download(String website, File outputLocation){
		WebFile.download(website, outputLocation);
	}
	
	public String fetchSource(URL website){
		return WebSource.fetchSource(website);
	}

}
