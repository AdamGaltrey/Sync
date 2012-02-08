package couk.Adamki11s.IO.Configuration.Standard;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;
import couk.Adamki11s.IO.IDENTIFIER;
import couk.Adamki11s.IO.IDENTIFIER.ID;

public class SyncConfiguration extends IOStream {
	
	private File f;
	private LinkedHashMap<String, Object> writeableData = new LinkedHashMap<String, Object>();
	private LinkedHashMap<String, Object> readableData = new LinkedHashMap<String, Object>();
	private final boolean append;
	
	public SyncConfiguration(File f){
		this.f = f;
		this.append = false;
	}
	
	public SyncConfiguration(File f, boolean append){
		this.f = f;
		this.append = append;
	}
	
	public File getFile(){
		return this.f;
	}
	
	public void erase(){
		this.writeableData.clear();
		this.readableData.clear();
	}
	
	/**
	 * Add a key and data to be written tothe file upon invokation of the write() method.
	 * @param key
	 * @param data
	 */
	public void add(String key, Object data){
		this.writeableData.put(key, data);
	}
	
	/**
	 * Add a comment to the file. This will not be scanned when reading the file contents.
	 * @param comment
	 */
	public void addComment(String comment){
		this.writeableData.put(UUID.randomUUID() + "", new IDENTIFIER(ID.COMMENT, comment));
	}
	
	/**
	 * Insert a new line. For tidiness ;).
	 */
	public void addNewLine(){
		this.writeableData.put(UUID.randomUUID() + "",  new IDENTIFIER(ID.NEWLINE));
	}
	
	/**
	 * Edit existing data.
	 * @param key
	 * @param data
	 */
	public void edit(String key, Object data){
		add(key, data);
	}
	
	/**
	 * Check whether a key exists.
	 * @param key
	 * @return
	 */
	public boolean doesKeyExist(String key){
		return this.readableData.containsKey(key);
	}
	
	/**
	 * Remove existing data.
	 * @param key
	 */
	public void remove(String key){
		this.writeableData.remove(key);
	}
	
	/**
	 * Write all data to the file.
	 */
	public void write(){
		try {
			super.write(this.f, this.writeableData, this.append);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load data from the file.
	 */
	public void read(){
		this.readableData = super.read(this.f);
	}
	
	/**
	 * Manually insert data to write to the file upon invokation of the write() method.
	 * @param dataSet LinkedHashMap<String, Object>
	 */
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
