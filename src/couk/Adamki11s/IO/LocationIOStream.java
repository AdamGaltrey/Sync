package couk.Adamki11s.IO;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationIOStream {

	private LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();

	private File f;

	public LocationIOStream(File f) {
		this.f = f;
	}

	public void add(String key, Object data) {
		this.data.put(key, data);
	}
	
	public void addComment(String comment){
		data.put(comment, IDENTIFIERS.COMMENT);
	}
	
	public void addNewLine(){
		data.put(UUID.randomUUID().toString(), IDENTIFIERS.NEWLINE);
	}

	public void remove(String key) {
		if (contains(key)) {
			this.data.remove(key);
		}
	}

	public boolean contains(String key) {
		return this.data.containsKey(key);
	}

	public void modify(String key, Object newValue) {
		if (contains(key)) {
			data.put(key, newValue);
		}
	}

	public void erase() {
		this.data.clear();
	}

	public HashMap<String, Object> getData() {
		return this.data;
	}

	public void setData(LinkedHashMap<String, Object> dataSet) {
		this.data = dataSet;
	}

	public Location getLocation(String key) {
		return (Location) this.data.get(key);
	}

	public void write() {
		LinkedHashMap<String, Object> newSet = new LinkedHashMap<String, Object>();
		for (Entry<String, Object> map : this.data.entrySet()) {
			if(map.getValue() instanceof IDENTIFIERS){
				newSet.put(map.getKey(), map.getValue());
				continue;
			}
			String key = map.getKey();
			Location raw = (Location) map.getValue();
			StringBuilder formatted = new StringBuilder();
			formatted.append(raw.getWorld().getName()).append(",").append(raw.getX()).append(",").append(raw.getY()).append(",").append(raw.getY());
			newSet.put(map.getKey(), formatted.toString());
		}
		GenericIO.write(this.f, newSet);
	}

	public void read() {
		LinkedHashMap<String, Object> newSet = GenericIO.read(this.f);
		for (Entry<String, Object> map : this.data.entrySet()) {
			if(map.getValue() instanceof IDENTIFIERS){
				continue;
			}
			String key = map.getKey();
			String[] rawLocation = map.getValue().toString().split(",");
			Location location = new Location(Bukkit.getServer().getWorld(rawLocation[0]), Double.parseDouble(rawLocation[1]), Double.parseDouble(rawLocation[2]),
					Double.parseDouble(rawLocation[3]));
			newSet.put(key, location);
		}
		this.data = newSet;
	}
	
}
