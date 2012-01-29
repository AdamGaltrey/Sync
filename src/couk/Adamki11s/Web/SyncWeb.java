package couk.Adamki11s.Web;

import java.io.File;
import java.net.URL;

public class SyncWeb {
	
	/**
	 * Download a file from the Internet to the specified file.
	 * @param website
	 * @param outputLocation
	 */
	public void download(String link, File outputLocation){
		WebFile.download(link, outputLocation);
	}
	
	/**
	 * Get the source code from a webpage.
	 * @param website
	 * @return
	 */
	public String fetchSource(URL website){
		return WebSource.fetchSource(website);
	}

}
