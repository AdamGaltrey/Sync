package couk.Adamki11s.IO.Writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SyncWriter {

	private final File f;
	private final boolean append;
	private ArrayList<String> contents = new ArrayList<String>();
	private ArrayList<String> readContents = new ArrayList<String>();

	/**
	 * Basic Wrapper for the BufferedWriter
	 * 
	 * @param f
	 */
	public SyncWriter(File f) {
		this.f = f;
		this.append = false;
	}

	/**
	 * Basic Wrapper for the BufferedWriter
	 * 
	 * @param f
	 * @param append
	 *            - Whether to append to the file or not
	 */
	public SyncWriter(File f, boolean append) {
		this.f = f;
		this.append = append;
	}

	public void addString(String s) {
		this.contents.add(s);
	}

	public void removeString(String s) {
		this.contents.remove(s);
	}

	public void eraseContents() {
		this.contents.clear();
	}

	public ArrayList<String> getWritableContents() {
		return this.contents;
	}

	public ArrayList<String> getReadableContents() {
		return this.readContents;
	}

	public void write() {
		try {
			FileWriter fstream = new FileWriter(f, append);
			BufferedWriter fbw = new BufferedWriter(fstream);
			for (String s : this.contents) {
				fbw.write(s);
				fbw.newLine();
			}
			fbw.flush();
			fstream.flush();
			fbw.close();
			fstream.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

	public void read() {
		try {
			FileInputStream in = new FileInputStream(f);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				this.readContents.add(strLine);
			}
			br.close();
			in.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

}
