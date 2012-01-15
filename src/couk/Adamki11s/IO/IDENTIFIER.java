package couk.Adamki11s.IO;

public class IDENTIFIER {
	
	public static enum ID{
		NEWLINE,
		COMMENT;
	}
	
	private ID id;
	private String comment;
	
	public IDENTIFIER(ID id){
		this.id = id;
	}
	
	public IDENTIFIER(ID id, String comment){
		this.id = id;
		this.comment = comment;
	}
	
	public boolean isNewLine(){
		return this.id == ID.NEWLINE;
	}
	
	public String getComment(){
		return this.comment;
	}

}
