package couk.Adamki11s.IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class IOStream extends GenericIO {

	protected void write(File f, LinkedHashMap<String, Object> data) throws IOException {
		FileWriter fileStream = new FileWriter(f);
		BufferedWriter writer = new BufferedWriter(fileStream);
		writer.write(super.getHeader());
		writer.newLine();
		for (Entry<String, Object> entry : data.entrySet()) {
			String[] nodes = entry.getKey().split("\\.");
			for (int tree = 1; tree <= nodes.length; tree++) {
				if ((tree) != nodes.length) {
					writer.write(super.getTab(tree) + super.getTerminatingKey(nodes[tree - 1]) + "");
				} else {
					writer.write(super.getTab(tree) + super.getKey(nodes[tree - 1]) + entry.getValue());
				}
				writer.newLine();
			}
		}
		writer.flush();
		writer.close();
	}

	protected LinkedHashMap<String, Object> read(File f) {
		return null;
	}

	public static void main(String[] args) {
		LinkedHashMap<String, Object> base = new LinkedHashMap<String, Object>();
		File f = new File("C:" + File.separator + "Sync" + File.separator + "Data.syn");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SyncIO io = new SyncIO(f);
		base.put("test.tree2.end", "Tedst");
		try {
			io.write(f, base);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
