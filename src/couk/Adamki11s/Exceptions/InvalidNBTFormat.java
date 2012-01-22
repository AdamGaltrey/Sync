package couk.Adamki11s.Exceptions;

public class InvalidNBTFormat extends Exception {
	
	private static final long serialVersionUID = -891602678311772399L;
	
	String region, expectedTag, retrievedTag;
	
	public InvalidNBTFormat(String region, String expectedTag, String retrievedTag){
		this.region = region;
		this.expectedTag = expectedTag;
		this.retrievedTag = retrievedTag;
	}
	
	@Override
	public void printStackTrace() {
		System.out.println("------------------------------");
		System.out.println("[Regios][Exception] Got NBT Tag : " + this.retrievedTag + ", expected tag : " + this.expectedTag + " for region : " + this.region + "!");
		System.out.println("------------------------------");
		super.printStackTrace();
		System.out.println("------------------------------");
	}

}
