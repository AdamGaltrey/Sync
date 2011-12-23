package couk.Adamki11s.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.UUID;

import couk.Adamki11s.Sync.Sync;

public class IOStream {

	private LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();

	private File f;

	public IOStream(File f) {
		this.f = f;
	}

	public void add(String key, Object data){
		this.data.put(key, data);
	}
	
	public void addComment(String comment){
		data.put(comment, IDENTIFIERS.COMMENT);
	}
	
	public void addNewLine(){
		data.put(UUID.randomUUID().toString(), IDENTIFIERS.NEWLINE);
	}
	
	public void remove(String key){
		if(contains(key)){
			this.data.remove(key);
		}
	}
	
	public boolean contains(String key){
		return this.data.containsKey(key);
	}
	
	public void modify(String key, Object newValue){
		if(contains(key)){
			data.put(key, newValue);
		}
	}
	
	public void erase(){
		this.data.clear();
	}
	
	public HashMap<String, Object> getData(){
		return this.data;
	}
	
	public void setData(LinkedHashMap<String, Object> dataSet){
		this.data = dataSet;
	}
	
	public Object getObject(String key){
		return this.data.get(key);
	}
	
	public String getString(String key){
		return getObject(key).toString();
	}
	
	public int getInt(String key){
		return Integer.parseInt(getString(key));
	}
	
	public double getDouble(String key){
		return Double.parseDouble(getString(key));
	}
	
	public boolean getBoolean(String key){
		return Boolean.parseBoolean(getString(key));
	}
	
	public void write() {
		GenericIO.write(this.f, this.data);
	}

	public void read() {
		this.data = GenericIO.read(this.f);
	}

}
