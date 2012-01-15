package couk.Adamki11s.IO;

import java.util.UUID;

public class IDENTIFIER {
	
	public static enum ID{
		NEWLINE,
		COMMENT;
	}
	
	private ID id;
	private String comment;
	private UUID uuid;
	
	public IDENTIFIER(ID id){
		this.id = id;
		this.uuid = UUID.randomUUID();
	}
	
	public IDENTIFIER(ID id, String comment){
		this.id = id;
		this.comment = comment;
		this.uuid = UUID.randomUUID();
	}
	
	public boolean isNewLine(){
		return this.id == ID.NEWLINE;
	}
	
	public String getComment(){
		return this.comment;
	}
	
	public UUID getUUID(){
		return this.uuid;
	}

}
