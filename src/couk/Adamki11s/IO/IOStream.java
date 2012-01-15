package couk.Adamki11s.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class IOStream extends GenericIO {

	protected void write(File f, LinkedHashMap<String, Object> data) throws IOException {
		try {
			FileWriter fstream = new FileWriter(f);
			BufferedWriter fbw = new BufferedWriter(fstream);
			fbw.write(super.getHeader());
			fbw.newLine();
			for (Entry<String, Object> entry : data.entrySet()) {
						fbw.write(entry.getKey().trim() + ":" + entry.getValue().toString().trim());
			}
			fbw.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

	protected LinkedHashMap<String, Object> read(File f) {
		LinkedHashMap<String, Object> tempKeys = new LinkedHashMap<String, Object>();
		try {
			FileInputStream in = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
						String key = strLine.trim().substring(0, strLine.indexOf(":"));
						String value = strLine.trim().substring(strLine.indexOf(":") + 1);
						tempKeys.put(key, value);
				}
			in.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
		return tempKeys;
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
		io.read();
		System.out.println(io.getString("test.tree2.end"));
	}

}
