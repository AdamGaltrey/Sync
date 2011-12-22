package couk.Adamki11s.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

import couk.Adamki11s.Sync.Sync;

public class GenericIO {
	
	public static void write(File f, HashMap<String, Object> data){
		try {
			FileWriter fstream = new FileWriter(f);
			BufferedWriter fbw = new BufferedWriter(fstream);
			fbw.write("Generated Using Sync, Version " + Sync.version + ". Author : Adamki11s");
			for (Entry<String, Object> entry : data.entrySet()) {
				fbw.newLine();
				fbw.write(entry.getKey().trim() + ":" + entry.getValue().toString().trim());
			}
			fbw.flush();
			fbw.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}
	
	public static HashMap<String, Object> read(File f){
		try {
			HashMap<String, Object> data = new HashMap<String, Object>();
			FileInputStream in = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				String[] property = strLine.split(":");
				data.put(property[0], property[1]);
			}
			in.close();
			return data;
		} catch (IOException iox) {
			iox.printStackTrace();
		}
		return null;
	}

}
