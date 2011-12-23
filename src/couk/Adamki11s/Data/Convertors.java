package couk.Adamki11s.Data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Convertors {
	
	public static LinkedHashMap<String, Object> convertToLinkedHashMap(HashMap<String, Object> set){
		LinkedHashMap<String, Object> lhm = new LinkedHashMap<String, Object>();
		for(Entry<String, Object> map : set.entrySet()){
			lhm.put(map.getKey(), map.getValue());
		}
		return lhm;
	}

}
