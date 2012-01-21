package couk.Adamki11s.IO.Objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ObjectIOStream {

	protected void write(File f, ArrayList<SyncWrapper> data) {
		SyncHolder holder = new SyncHolder();
		holder.setWrappers(data);
		try {
			FileOutputStream fileOut = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(holder);
			out.close();
			fileOut.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}

	protected ArrayList<SyncWrapper> read(File f) {
		SyncHolder holder = null;
		try {
			FileInputStream fileIn = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			holder = (SyncHolder) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return holder.getWrappers();
	}

}
