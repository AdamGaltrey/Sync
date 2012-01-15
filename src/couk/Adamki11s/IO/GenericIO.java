package couk.Adamki11s.IO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import couk.Adamki11s.Sync.Sync;

public class GenericIO {
	
	final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	private static final String TAB = "  ";
	
	protected String getHeader(){
		return ("Generated using Sync version : " + Sync.version + " Date : " + getDate());
	}
	
	protected String getKey(String key){
		return (key + ":");
	}
	
	private String getDate(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

}
