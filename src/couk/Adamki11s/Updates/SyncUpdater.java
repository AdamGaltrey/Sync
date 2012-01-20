package couk.Adamki11s.Updates;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import couk.Adamki11s.IO.Convertors;
import couk.Adamki11s.Managers.SyncLog;
import couk.Adamki11s.Web.WebSource;

public class SyncUpdater {
	
	//Version Syntax >> (SyncV=x.y.z)
	private static final String regexVersionPattern = "\\(SyncV=(\\d+)(\\.\\d+){1,}\\)";

	public static String getSyncVersion(String website) {
		URL url = null;
		try {
			url = new URL(website);
		} catch (MalformedURLException e) {
			SyncLog.logSevere("Invalid URL!");
			e.printStackTrace();
		}
		String source = WebSource.fetchSource(url);
		System.out.println(source);
		System.out.println("Using pattern : " + regexVersionPattern);
		Pattern p = Pattern.compile(regexVersionPattern);
		Matcher match = p.matcher(source);
		while(match.find()){
			System.out.println(match.group());
		}
		if(!Convertors.doesCompile(regexVersionPattern, source)){
			System.out.println("Pattern did not compile");
		} else {
			System.out.println("Compiled!");
		}
		return ";";
	}
	
	public static void main(String[] args) {
		getSyncVersion("http://forums.bukkit.org/threads/fix-gen-misc-nightlight-v1-1-a-light-for-the-night-1337.25433/");
	}

}
