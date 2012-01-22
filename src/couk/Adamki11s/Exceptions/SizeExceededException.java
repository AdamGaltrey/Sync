package couk.Adamki11s.Exceptions;

public class SizeExceededException extends Exception {

	private static final long serialVersionUID = -1350358655990724232L;

	public SizeExceededException(int size){
		super("Size cannot exceed 256 Blocks on X,Y or Z! Got size : " + size);
	}
	
}
