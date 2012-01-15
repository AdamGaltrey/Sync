package couk.Adamki11s.Scheduler;

import couk.Adamki11s.Scheduler.AsyncChecks.CacheChecker;

public class CoreAsyncThread implements Runnable {

	@Override
	public void run() {
		CacheChecker.checkAll();
	}
	
}
