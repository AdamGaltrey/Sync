package couk.Adamki11s.IO.Objects;

import java.io.Serializable;
import java.util.ArrayList;

import couk.Adamki11s.Cache.SyncCache;

public class SyncHolder implements Serializable {
	
	private static final long serialVersionUID = 8992406766288051772L;
	
	private ArrayList<SyncWrapper> wrappers = new ArrayList<SyncWrapper>();
	
	public void addWrapper(SyncWrapper wrapper){
		this.wrappers.add(wrapper);
	}
	
	public void setWrappers(ArrayList<SyncWrapper> wrappers){
		this.wrappers = wrappers;
	}
	
	public ArrayList<SyncWrapper> getWrappers(){
		return this.wrappers;
	}

}
