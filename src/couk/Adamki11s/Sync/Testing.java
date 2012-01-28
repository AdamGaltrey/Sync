package couk.Adamki11s.Sync;

import java.io.File;
import java.io.Serializable;

import couk.Adamki11s.IO.Objects.SyncObjectIO;
import couk.Adamki11s.IO.Objects.SyncWrapper;
import couk.Adamki11s.Updates.SyncUpdater;
import couk.Adamki11s.Updates.SyncVersionData;

public class Testing {

	static File root = new File("C:" + File.separator + "Sync");

	public static void main(String[] args) {
		testSyncObjectIO(new File(root + File.separator + "Serialized.syn"));
		//testUpdater("http://forums.bukkit.org/threads/fix-gen-misc-nightlight-v1-1-a-light-for-the-night-1-0-1-r1.25433/");
	}

	public static void testUpdater(String url){
		SyncVersionData svd = SyncUpdater.getSyncVersionData(url);
		System.out.println("Version : " + svd.getVersion());
		System.out.println("Download Link : " + svd.getDownloadLink());
		System.out.println("Changelog : " + svd.getChangelog());
	}

	public static void testSyncObjectIO(File f) {
		SyncObjectIO io = new SyncObjectIO(f);
		io.add(new SyncWrapper("wrapper1", "testString"));
		testclass test = new testclass("test class string");
		io.add(new SyncWrapper("classWrapper", test));
		io.write();
		io.read();

		Object o1 = null, o2 = null, o3 = null;

		if (io.doesObjectExist("invalidwrapper")) {
			o2 = io.getObject("invalidwrapper");
		}
		if (io.doesObjectExist("wrapper1")) {
			o1 = io.getObject("wrapper1");
		}
		if (io.doesObjectExist("classWrapper")) {
			o3 = io.getObject("classWrapper");
		}

		if (o1 != null) {
			System.out.println("O1 Object = " + o1.toString());
		} else {
			System.out.println("O1 Object = NULL");
		}

		if (o2 != null) {
			System.out.println("O2 Object = " + o2.toString());
		} else {
			System.out.println("O2 Object = NULL");
		}

		if (o3 != null) {
			System.out.println("O3 Object = " + o3.toString());
			testclass conversion = (testclass) o3;
			System.out.println("Test class 3 string = " + conversion.getString());
		} else {
			System.out.println("O2 Object = NULL");
		}
	}

}

class testclass implements Serializable {

	private static final long serialVersionUID = -5407829961470248782L;

	private String s;

	public testclass(String s) {
		this.s = s;
	}

	public String getString() {
		return this.s;
	}

}
