package couk.Adamki11s.IO;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationIOStream {

	private HashMap<String, Object> data = new HashMap<String, Object>();

	private File f;

	public LocationIOStream(File f) {
		this.f = f;
	}

	public void add(String key, Object data) {
		this.data.put(key, data);
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

	public void setData(HashMap<String, Object> dataSet) {
		this.data = dataSet;
	}

	public Location getLocation(String key) {
		return (Location) this.data.get(key);
	}

	public void write() {
		HashMap<String, Object> newSet = new HashMap<String, Object>();
		for (Entry<String, Object> map : this.data.entrySet()) {
			String key = map.getKey();
			Location raw = (Location) map.getValue();
			StringBuilder formatted = new StringBuilder();
			formatted.append(raw.getWorld().getName()).append(",").append(raw.getX()).append(",").append(raw.getY()).append(",").append(raw.getY());
			newSet.put(map.getKey(), formatted.toString());
		}
		GenericIO.write(this.f, newSet);
	}

	public void read() {
		HashMap<String, Object> newSet = GenericIO.read(this.f);
		for (Entry<String, Object> map : this.data.entrySet()) {
			String key = map.getKey();
			String[] rawLocation = map.getValue().toString().split(",");
			Location location = new Location(Bukkit.getServer().getWorld(rawLocation[0]), Double.parseDouble(rawLocation[1]), Double.parseDouble(rawLocation[2]),
					Double.parseDouble(rawLocation[3]));
			newSet.put(key, location);
		}
		this.data = newSet;
	}
	
}
