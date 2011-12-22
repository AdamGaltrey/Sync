package couk.Adamki11s.IO;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

public class EncryptedIOStream extends IOStream {

	CipherStream cipherStream;

	public EncryptedIOStream(File f, String password) {
		super(f);
		this.cipherStream = new CipherStream(f, password);
	}

	@Override
	public void write() {
		HashMap<String, Object> newSet = new HashMap<String, Object>();
		for (Entry<String, Object> map : super.getData().entrySet()) {
			newSet.put(this.cipherStream.encrypt(map.getKey().toString()), this.cipherStream.encrypt(map.getValue().toString()));
		}
		super.setData(newSet);
		super.write();
	}

	@Override
	public void read() {
		super.read();
		HashMap<String, Object> newSet = new HashMap<String, Object>();
		for (Entry<String, Object> map : super.getData().entrySet()) {
			newSet.put(this.cipherStream.decrypt(map.getKey().toString()), this.cipherStream.decrypt(map.getValue().toString()));
		}
		super.setData(newSet);
	}

}
