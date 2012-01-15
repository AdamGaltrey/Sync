package couk.Adamki11s.Cache;

public class CacheObject {
	
	private long registrationTimeStamp;
	private Object data;
	private int persistanceDuration;
	
	public CacheObject(Object o, int persistanceDuration){
		this.registrationTimeStamp = System.currentTimeMillis();
		this.data = o;
		this.persistanceDuration = persistanceDuration;
	}
	
	public long getRegistrationTimeStamp(){
		return this.registrationTimeStamp;
	}
	
	public Object getData(){
		return this.data;
	}
	
	public int getPersistanceDuration(){
		return this.persistanceDuration;
	}
	
	public boolean canBeErased(){
		return (this.persistanceDuration == 0 ? true : ((System.currentTimeMillis() / 1000) - (this.registrationTimeStamp / 1000)) >= this.persistanceDuration);
	}
	
	public long getTimeUntilErased(){
		return (this.persistanceDuration <= 0 ? 0 : this.persistanceDuration - ((System.currentTimeMillis() / 1000) - (this.registrationTimeStamp / 1000)));
	}
	
	public void dispose(){
		this.registrationTimeStamp = 0;
		this.data = null;
		this.persistanceDuration = 0;
	}

}
