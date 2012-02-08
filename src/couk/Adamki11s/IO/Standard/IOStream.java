package couk.Adamki11s.IO.Standard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import couk.Adamki11s.IO.GenericIO;
import couk.Adamki11s.IO.IDENTIFIER;

public class IOStream extends GenericIO {

	protected void write(File f, LinkedHashMap<String, Object> data, boolean append) throws IOException {
		try {
			FileWriter fstream = new FileWriter(f, append);
			BufferedWriter fbw = new BufferedWriter(fstream);
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

}
