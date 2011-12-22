package couk.Adamki11s.IO;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationIOStream extends IOStream {

	public LocationIOStream(File f) {
		super(f);
	}
	
	@Override
	public void write(){
		HashMap<String, Object> newSet = new HashMap<String, Object>();
		for (Entry<String, Object> map : super.getData().entrySet()) {
			String key = map.getKey();
			Location raw = (Location)map.getValue();
			StringBuilder formatted = new StringBuilder();
			formatted.append(raw.getWorld().getName()).append(",").append(raw.getX()).append(",").append(raw.getY()).append(",").append(raw.getY());
			newSet.put(map.getKey(), formatted.toString());
		}
		super.setData(newSet);
		super.write();
	}

	@Override
	public void read() {
		super.read();
		HashMap<String, Object> newSet = new HashMap<String, Object>();
		for (Entry<String, Object> map : super.getData().entrySet()) {
			String key = map.getKey();
			String[] rawLocation = map.getValue().toString().split(",");
			Location location = new Location(Bukkit.getServer().getWorld(rawLocation[0]), Double.parseDouble(rawLocation[1]), Double.parseDouble(rawLocation[2]),
					Double.parseDouble(rawLocation[3]));
			newSet.put(key, location);
		}
		super.setData(newSet);
	}
}
