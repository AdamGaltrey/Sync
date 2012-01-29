package couk.Adamki11s.Exceptions;

import org.bukkit.plugin.Plugin;

public class UnsupportedTrackingException extends Exception  {

	private static final long serialVersionUID = -4572066862731966173L;
	
	public UnsupportedTrackingException(Plugin p){
		super("Plugin " + p.getDescription().getName() + " tried to register a Statistic service but Sync Statistic tracking has been disabled by the host.");
	}

}
