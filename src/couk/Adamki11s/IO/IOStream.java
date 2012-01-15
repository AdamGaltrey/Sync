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
			fbw.newLine();
			for (Entry<String, Object> entry : data.entrySet()) {
				if(entry.getValue() instanceof IDENTIFIER){
					IDENTIFIER id = (IDENTIFIER)entry.getValue();
					if(id.isNewLine()){
						fbw.newLine();
					} else {
						fbw.write("#" + id.getComment());
						fbw.newLine();
					}
					continue;
				}
				fbw.write(entry.getKey().trim() + ":" + entry.getValue().toString().trim());
				fbw.newLine();
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
				if (strLine.isEmpty() || strLine.length() < 2 || strLine.startsWith("#")) {
					System.out.println("Skipping " + strLine);
					continue;
				}
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
		File f = new File("C:" + File.separator + "Sync" + File.separator + "Data.syn");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SyncIO io = new SyncIO(f);
		io.add("test", "test value");
		io.addComment("TEST COMMENT");
		io.addNewLine();
		io.addComment("MULTIPLE COMMENT TEST");
		io.addNewLine();
		io.add("test2", "second value");
		io.write();
		io.read();
		System.out.println(io.getString("test"));
		System.out.println(io.getString("test2"));
	}

}
