package couk.Adamki11s.IO;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class EncryptedIOStream {

	CipherStream cipherStream;
	
	private HashMap<String, Object> data = new HashMap<String, Object>();

	private File f;

	public EncryptedIOStream(File f, String password) {
		this.cipherStream = new CipherStream(password);
		this.f = f;
	}
	
	public void add(String key, Object data){
		this.data.put(key, data);
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
	
	public void setData(HashMap<String, Object> dataSet){
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
		HashMap<String, Object> newSet = new HashMap<String, Object>();
		for (Entry<String, Object> map : this.data.entrySet()) {
			newSet.put(this.cipherStream.encrypt(map.getKey().toString()), this.cipherStream.encrypt(map.getValue().toString()));
		}
		GenericIO.write(this.f, newSet);
		newSet.clear();
	}

	public void read() {
		HashMap<String, Object> newSet = new HashMap<String, Object>();
		newSet = GenericIO.read(this.f);
		for (Entry<String, Object> map : this.data.entrySet()) {
			newSet.put(this.cipherStream.decrypt(map.getKey().toString()), this.cipherStream.decrypt(map.getValue().toString()));
		}
		setData(newSet);
	}

}
