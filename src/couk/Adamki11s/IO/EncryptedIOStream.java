package couk.Adamki11s.IO;

import java.io.File;
import java.util.HashMap;

public class EncryptedIOStream extends IOStream {
	
	CipherStream cipherStream;

	public EncryptedIOStream(File f, String password) {
		super(f);
		this.cipherStream = new CipherStream(f, password);
	}
	
	public void add(String key, Object data){
		super.add(key, data);
	}
	
	public void remove(String key){
		super.remove(key);
	}
	
	public boolean contains(String key){
		return super.contains(key);
	}
	
	public void modify(String key, Object newValue){
		super.modify(key, newValue);
	}
	
	public void erase(){
		super.erase();
	}
	
	public HashMap<String, Object> getData(){
		return super.getData();
	}
	
	public void write(){
		super.write();
	}
	
	public void read(){
		super.read();
	}

}
