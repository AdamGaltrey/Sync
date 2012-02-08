package couk.Adamki11s.Web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import couk.Adamki11s.Managers.SyncLog;

public class WebSource {
	
	public static String fetchSource(URL website){
		
	    InputStream is = null;
	    DataInputStream dis = null;
	    String s, source = "";

		try {
			is = website.openStream();
		} catch (IOException ex) {
			ex.printStackTrace();
			SyncLog.logSevere("Error connecting to URL : '" + website.toString() + "'!");
		}
		
	    dis = new DataInputStream(new BufferedInputStream(is));
		BufferedReader br = new BufferedReader(new InputStreamReader(dis));
		
		try {
			while ((s = br.readLine()) != null) {
			    source += s;
			 }
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		try {
            is.close();
         } catch (IOException ioe) {
        	 ioe.printStackTrace();
         }
         
		return source;
	}

}
