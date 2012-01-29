package couk.Adamki11s.IO.Objects;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SyncObjectIO extends ObjectIOStream {

	private File f;
	private ArrayList<SyncWrapper> writeableData = new ArrayList<SyncWrapper>();
	private ArrayList<SyncWrapper> readableData = new ArrayList<SyncWrapper>();

	public SyncObjectIO(File f) {
		this.f = f;
	}

	public File getFile() {
		return this.f;
	}

	public boolean exists() {
		return f.exists();
	}

	public void createNewFile() throws IOException {
		f.createNewFile();
	}

	public void delete() {
		f.delete();
	}

	/**
	 * Add a SyncWrapper holding an object to be serialized upon invoking the
	 * write() method.
	 * 
	 * @param wrapper
	 */
	public void add(SyncWrapper wrapper) {
		this.writeableData.add(wrapper);
	}

	/**
	 * Remove a SyncWrapper from the data to be written upon invokation of the
	 * write() method.
	 * 
	 * @param wrapper
	 */
	public void remove(SyncWrapper wrapper) {
		this.writeableData.remove(wrapper);
	}

	/**
	 * Erases all objects in the stream.
	 */
	public void erase() {
		this.readableData.clear();
		this.writeableData.clear();
	}

	/**
	 * Serialize and Write all data to file.
	 */
	public void write() {
		super.write(this.f, this.writeableData);
	}

	/**
	 * Deserialize and load in data from the file.
	 */
	public void read() {
		this.readableData = super.read(this.f);
	}

	/**
	 * Manually insert a list of SyncWrappers
	 * @param dataSet
	 */
	public void insertWriteableData(ArrayList<SyncWrapper> dataSet) {
		this.writeableData = dataSet;
	}

	public ArrayList<SyncWrapper> getReadableData() {
		return this.readableData;
	}

	public ArrayList<SyncWrapper> getWriteableData() {
		return this.writeableData;
	}

	/**
	 * Check if an object can be found with the corresponding tag.
	 * @param tag
	 * @return boolean
	 */
	public boolean doesObjectExist(String tag) {
		return (!(getObject(tag) == null));
	}

	/**
	 * Get the object linked to a tag. doesObjectExist() should be checked before to prevent NullPointers.
	 * @param tag
	 * @return Object
	 */
	public Object getObject(String tag) {
		for (SyncWrapper wrapper : this.readableData) {
			if (wrapper.getTag().equalsIgnoreCase(tag)) {
				return wrapper.getObject();
			}
		}
		return null;
	}

}
