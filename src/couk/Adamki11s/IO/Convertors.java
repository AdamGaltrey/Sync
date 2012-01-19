package couk.Adamki11s.IO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import couk.Adamki11s.Exceptions.InvalidFormatException;
import couk.Adamki11s.Exceptions.NullWorldException;

public class Convertors {
	
	private static final String locationRegex = "(^Location):(\\w+),(\\d+.?\\d+),(\\d+.?\\d+),(\\d+.?\\d+),(\\d+.?\\d+),(\\d+.?\\d+):";

	public static String locationToString(Location l){
		return "Location:" + l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ() + "," + l.getYaw() + "," + l.getPitch() + ":";
	}
	
	public static String blockToString(Block b){
		Location l = b.getLocation();
		return "Block:" + l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ() + "|" + b.getTypeId() + "," + b.getData() + ":";
	}
	
	public static String itemStackToString(ItemStack is){
		return "ItemStack:" + is.getTypeId() + "," + is.getData().getData() + "," + is.getAmount() + ":";
	}
	
	public static Location stringToLocation(String location){
		if(!doesCompile(locationRegex, location)){
			try {
				throw new InvalidFormatException(location, ConversionTypes.LOCATION);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
		} else {
			String parts[] = location.split(":");
			String core = parts[1];
			String[] locData = core.split(",");
			World w = Bukkit.getServer().getWorld(locData[0].substring(1));
			if(w == null){
				try {
					throw new NullWorldException(locData[0].substring(1));
				} catch (NullWorldException e) {
					e.printStackTrace();
				}
			}
			double x = 0, y = 0, z = 0; float yaw = 0, pitch = 0;
			try{
				x = Double.parseDouble(locData[1]);
				y = Double.parseDouble(locData[2]);
				z = Double.parseDouble(locData[3]);
				yaw = Float.parseFloat(locData[4]);
				pitch = Float.parseFloat(locData[5]);
			} catch (NumberFormatException nfex){
				nfex.printStackTrace();
			}
			return new Location(w, x, y, z, yaw, pitch);
		}
		return null;
	}
	
	public static boolean doesCompile(String regex, String checking){
		Pattern p = Pattern.compile(regex);
		Matcher match = p.matcher(checking);
		return match.find();
	}

}
