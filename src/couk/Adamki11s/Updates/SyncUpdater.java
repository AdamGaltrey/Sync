package couk.Adamki11s.Updates;

import java.net.MalformedURLException;
import java.net.URL;

import couk.Adamki11s.Managers.SyncLog;
import couk.Adamki11s.Web.WebSource;

public class SyncUpdater {
	
	private final String regexVersionPattern = "<ID=SyncV:Version=(.+)>";

	public static String getSyncVersion(String website) {
		URL url = null;
		try {
			url = new URL(website);
		} catch (MalformedURLException e) {
			SyncLog.logSevere("Invalid URL!");
			e.printStackTrace();
		}
		String source = WebSource.fetchSource(url);
		return ";";
	}

}
