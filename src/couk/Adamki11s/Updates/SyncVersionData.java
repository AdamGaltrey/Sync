package couk.Adamki11s.Updates;

import java.net.MalformedURLException;
import java.net.URL;

public class SyncVersionData {
	
	private URL downloadLink;
	private String version, changelog;
	
	public SyncVersionData(String downloadLink, String version, String changelog){
		this.version = version;
		this.changelog = changelog;
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
	
	public String getChangelog(){
		return this.changelog;
	}

}
