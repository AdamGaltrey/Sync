package couk.Adamki11s.Configuration;

import java.io.File;

public class GlobalConfiguration {
	
	//Updates Configuration
	protected static boolean checkForUpdates, autoDownloadUpdates, reloadAfterUpdate;
	protected static File downloadPath;
	protected static int updateCycle;
	
	//Statistic Configuration
	protected static boolean allowStatisticTracking;
	protected static int statisticUpdateCycle;
	
	public static boolean isCheckForUpdates() {
		return checkForUpdates;
	}
	public static boolean isAutoDownloadUpdates() {
		return autoDownloadUpdates;
	}
	public static boolean isReloadAfterUpdate() {
		return reloadAfterUpdate;
	}
	public static File getDownloadPath() {
		return downloadPath;
	}
	public static int getUpdateCycle() {
		return updateCycle;
	}
	public static boolean isAllowStatisticTracking() {
		return allowStatisticTracking;
	}
	public static int getStatisticUpdateCycle() {
		return statisticUpdateCycle;
	}

}
