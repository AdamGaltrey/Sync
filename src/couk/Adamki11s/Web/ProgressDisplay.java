package couk.Adamki11s.Web;

import couk.Adamki11s.Managers.SyncLog;

public class ProgressDisplay implements Runnable {

	private int progress, fileSize;

	public boolean run = true;

	ProgressDisplay(int fileSize) {
		this.fileSize = fileSize;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public void run() {
		while (run) {
			SyncLog.logInfo("Downloading... " + ((int) progress * 100 / fileSize) + "%");
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
