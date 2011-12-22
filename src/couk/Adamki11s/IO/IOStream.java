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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import couk.Adamki11s.Sync.Sync;

public class IOStream {

	private HashMap<String, Object> data = new HashMap<String, Object>();

	private File f;

	public IOStream(File f) {
		this.f = f;
	}

	
	/**
	 * Writes all data to the file.
	 */
	public void write() {
		try {
			FileWriter fstream = new FileWriter(this.f);
			BufferedWriter fbw = new BufferedWriter(fstream);
			fbw.write("Generated Using Sync, Version " + Sync.version + ". Author : Adamki11s");
			for (Entry<String, Object> entry : this.data.entrySet()) {
				fbw.newLine();
				fbw.write(entry.getKey().trim() + ":" + entry.getValue().toString().trim());
			}
			fbw.flush();
			fbw.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

	/**
	 * Reads all data from the file.
	 */
	public void read() {
		try {
			FileInputStream in = new FileInputStream(this.f);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				String[] property = strLine.split(":");
				this.data.put(property[0], property[1]);
			}
			in.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}
	
	/**
	 * Erases all data from the HashMap storage.
	 */
	public void erase(){
		this.data.clear();
	}
	
	/**
	 * 
	 * @return The HashMap containing all key data and their respective values.
	 */
	public HashMap<String, Object> getData(){
		return this.data;
	}

}
