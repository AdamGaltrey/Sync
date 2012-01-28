package couk.Adamki11s.Exceptions;

import org.bukkit.plugin.Plugin;

public class MultipleUpdatePackageException extends Exception {

	private static final long serialVersionUID = -1869753165223230160L;

	public MultipleUpdatePackageException(Plugin p){
		super("The plugin " + p.getDescription().getName() + " has already registered an update service!");
	}
	
}
