package couk.Adamki11s.Exceptions;

import couk.Adamki11s.IO.ConversionTypes;

public class InvalidFormatException extends Exception {

	private static final long serialVersionUID = 5693656617065047061L;

	public InvalidFormatException(String format, ConversionTypes type){
		super("INVALID FORMAT FOR (" + type.toString() + ") : " + format);
	}

}
