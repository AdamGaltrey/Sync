package couk.Adamki11s.Managers;

import java.util.logging.Logger;

public class SyncLog {
	
	private static final Logger log = Logger.getLogger("Sync");
	private static final String prefix = "[Sync] ";
	
	public static void logInfo(String message){
		log.info(prefix + message);
	}
	
	public static void logWarning(String message){
		log.warning(prefix + message);
	}
	
	public static void logSevere(String message){
		log.severe(prefix + message);
	}

}
