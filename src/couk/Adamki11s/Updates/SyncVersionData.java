package couk.Adamki11s.Updates;

import java.net.MalformedURLException;
import java.net.URL;

public class SyncVersionData {
	
	private URL downloadLink;
	private String version;
	
	public SyncVersionData(String downloadLink, String version){
		this.version = version;
		try {
			this.downloadLink = new URL(downloadLink);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public URL getDownloadLink() {
		return downloadLink;
	}

	public String getVersion() {
		return version;
	}

}
