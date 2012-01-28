package couk.Adamki11s.Web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import couk.Adamki11s.Managers.SyncLog;

public class WebFile {

	public static synchronized void download(String website, File outputLocation) {
		URL url;
		try {
			url = new URL(website);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}

		ProgressDisplay disp = null;
		Thread t;

		try {
			SyncLog.logInfo("Downloading file at : " + website);
			URLConnection connection = url.openConnection();
			int fileSize = connection.getContentLength();
			disp = new ProgressDisplay(fileSize);
			t = new Thread(disp);
			t.start();
			BufferedInputStream in = new BufferedInputStream(url.openStream());
			FileOutputStream fos = new FileOutputStream(outputLocation);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] data = new byte[1024];
			int x = 0, total = 0;
			while ((x = in.read(data, 0, 1024)) >= 0) {
				total += x;
				disp.setProgress(total);
				bout.write(data, 0, x);
			}
			disp.run = false;
			bout.close();
			in.close();
		} catch (Exception ex) {
			if (ex instanceof IOException) {
				SyncLog.logSevere("Invalid file path for download location!");
			}
			disp.run = false;
			ex.printStackTrace();
		}
		SyncLog.logInfo("Download Completed!");
		SyncLog.logInfo("File downloaded to : " + outputLocation.getAbsolutePath());

	}

}
