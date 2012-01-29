package couk.Adamki11s.Exceptions;

import org.bukkit.plugin.Plugin;

public class MultipleStatisticServiceException extends Exception {

	private static final long serialVersionUID = 1866714226079793807L;

	public MultipleStatisticServiceException(Plugin p){
		super("The plugin " + p.getDescription().getName() + " has already registered an statistic service!");
	}
	
}
