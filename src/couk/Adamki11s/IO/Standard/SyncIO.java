package couk.Adamki11s.IO.Standard;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;
import couk.Adamki11s.IO.IDENTIFIER;
import couk.Adamki11s.IO.IDENTIFIER.ID;

public class SyncIO extends IOStream {
	
	private File f;
	private LinkedHashMap<String, Object> writeableData = new LinkedHashMap<String, Object>();
	private LinkedHashMap<String, Object> readableData = new LinkedHashMap<String, Object>();
	
	public SyncIO(File f){
		this.f = f;
	}
	
	public File getFile(){
		return this.f;
	}
	
	public boolean exists(){
		return f.exists();
	}
	
	public void createNewFile() throws IOException{
		f.createNewFile();
	}
	
	public void deleteFile(){
		f.delete();
	}
	
	public void erase(){
		this.writeableData.clear();
		this.readableData.clear();
	}
	
	public void add(String key, Object data){
		this.writeableData.put(key, data);
	}
	
	public void addComment(String comment){
		this.writeableData.put(UUID.randomUUID() + "", new IDENTIFIER(ID.COMMENT, comment));
	}
	
	public void addNewLine(){
		this.writeableData.put(UUID.randomUUID() + "",  new IDENTIFIER(ID.NEWLINE));
	}
	
	public void edit(String key, Object data){
		add(key, data);
	}
	
	public void remove(String key){
		this.writeableData.remove(key);
	}
	
	public void write(){
		try {
			super.write(this.f, this.writeableData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read(){
		this.readableData = super.read(this.f);
	}
	
	public void insertWriteableData(LinkedHashMap<String, Object> dataSet){
		this.writeableData = dataSet;
	}
	
	public LinkedHashMap<String, Object> getReadableData(){
		return this.readableData;
	}
	
	public LinkedHashMap<String, Object> getWriteableData(){
		return this.writeableData;
	}
	
	public Object getObject(String key){
		return this.readableData.get(key);
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

}
