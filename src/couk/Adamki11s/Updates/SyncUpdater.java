package couk.Adamki11s.Updates;

import java.io.File;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import couk.Adamki11s.IO.Convertors;
import couk.Adamki11s.Managers.SyncLog;
import couk.Adamki11s.Web.WebFile;
import couk.Adamki11s.Web.WebSource;

public class SyncUpdater {
	
	//catch socket exceptions
	
	//Version Syntax >> (SyncV=x.y.z)
	private static final String regexVersionPattern = "(\\(Sync_Version=(\\d+)(\\.\\d+){1,}\\))",
			regexDownloadLinkPattern = "(\\(Sync_Download=(\\w+)(\\..*?){1,}\\))";

	public static SyncVersionData getSyncVersionData(String website) {
		String rawVersion = "", rawDLink = "", finalVersion = "", finalDLink = "";
		URL url = null;
		try {
			url = new URL(website);
		} catch (MalformedURLException e) {
			SyncLog.logSevere("Invalid URL!");
			e.printStackTrace();
		}
		
		String source = WebSource.fetchSource(url);
		
		Pattern p = Pattern.compile(regexVersionPattern);
		Matcher match = p.matcher(source);
		
		int versionMatches = 0;
		int dlMatches = 0;
		
		while(match.find()){
			rawVersion = match.group();
			versionMatches++;
		}
		
		if(!Convertors.doesCompile(regexVersionPattern, source)){
			SyncLog.logSevere("No Sync Version headers found at URL! " + website);
			return null;
		}
		
		if(versionMatches == 1) {
			finalVersion = rawVersion.substring(rawVersion.indexOf("=") + 1, rawVersion.length() - 1);
		} else {
			SyncLog.logSevere("Found multiple Sync Version headers! Got " + versionMatches + ". Expected 1!");
			return null;
		}
		
		p = Pattern.compile(regexDownloadLinkPattern);
		match = p.matcher(source);
		
		while(match.find()){
			rawDLink = match.group();
			dlMatches++;
		}
		
		if(!Convertors.doesCompile(regexDownloadLinkPattern, source)){
			SyncLog.logSevere("No Sync Download headers found at URL! " + website);
			return null;
		}
		
		if(dlMatches == 1) {
			finalDLink = "http://" + rawDLink.substring(rawDLink.indexOf("=") + 1, rawDLink.length() - 1);
		} else {
			SyncLog.logSevere("Found multiple Sync Download headers! Got " + dlMatches + ". Expected 1!");
			return null;
		}
		SyncVersionData svd = new SyncVersionData(finalDLink, finalVersion);
		return svd;
	}
	
	public boolean doVersionsMatch(String v1, String v2){
		return v1.equalsIgnoreCase(v2);
	}

}
