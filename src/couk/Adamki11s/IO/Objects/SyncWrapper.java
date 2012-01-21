package couk.Adamki11s.IO.Objects;

import java.io.Serializable;

public class SyncWrapper implements Serializable {

	private static final long serialVersionUID = 5564823216286915043L;
	
	private Object object;
	
	private String tag;
	
	public SyncWrapper(String tag, Object object){
		this.tag = tag;
		this.object = object;
	}
	
	public String getTag(){
		return this.tag;
	}
	
	public Object getObject(){
		return this.object;
	}

}
