package couk.Adamki11s.Statistics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class BitlyTracker {

	private HashMap<String, URL> urls = new HashMap<String, URL>();

	public void addURL(String tag, URL url) {
		this.urls.put(tag, url);
	}

	public void removeURL(String tag) {
		this.urls.remove(tag);
	}

	public URL getURL(String tag) {
		return this.urls.get(tag);
	}

	public HashMap<String, URL> getURLS() {
		return this.urls;
	}

	public boolean doesURLExist(String tag) {
		return this.urls.containsKey(tag);
	}

	public void pingURL(String tag) {
		if (this.doesURLExist(tag)) {
			try {
				URL url = this.getURL(tag);
				HttpURLConnection con = (HttpURLConnection) (url.openConnection());
				System.setProperty("http.agent", "");
				con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/12.0.742.100 Safari/534.30");
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				while ((in.readLine()) != null);
				in.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
