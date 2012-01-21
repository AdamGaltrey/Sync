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

	public void add(SyncWrapper wrapper) {
		this.writeableData.add(wrapper);
	}

	public void remove(SyncWrapper wrapper) {
		this.writeableData.remove(wrapper);
	}

	public void write() {
		super.write(this.f, this.writeableData);
	}

	public void read() {
		this.readableData = super.read(this.f);
	}

	public void insertWriteableData(ArrayList<SyncWrapper> dataSet) {
		this.writeableData = dataSet;
	}

	public ArrayList<SyncWrapper> getReadableData() {
		return this.readableData;
	}

	public ArrayList<SyncWrapper> getWriteableData() {
		return this.writeableData;
	}

	public boolean doesWrapperExist(String tag) {
		return (!(getWrapper(tag) == null));
	}

	public SyncWrapper getWrapper(String tag) {
		for (SyncWrapper wrapper : this.readableData) {
			if (wrapper.getTag().equalsIgnoreCase(tag)) {
				return wrapper;
			}
		}
		return null;
	}

}
