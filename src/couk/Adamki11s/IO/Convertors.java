package couk.Adamki11s.IO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class Convertors {
	
	private static final String locationRegex = "(^L):(\\w+),(\\d+.?\\d+),(\\d+.?\\d+),(\\d+.?\\d+),(\\d+.?\\d+),(\\d+.?\\d+):";

	public static String locationToString(Location l){
		return "Location(" + l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ() + "," + l.getYaw() + "," + l.getPitch() + ")";
	}
	
	public static String blockToString(Block b){
		Location l = b.getLocation();
		return "Block(" + l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ() + "|" + b.getTypeId() + "," + b.getData() + ")";
	}
	
	public static String itemStackToString(ItemStack is){
		return "ItemStack(" + is.getTypeId() + "," + is.getData().getData() + "," + is.getAmount() + ")";
	}
	
	public static Location stringToLocation(String location){
		return null;
	}
	
	private static boolean doesCompile(String regex, String checking){
		Pattern p = Pattern.compile(regex);
		Matcher match = p.matcher(checking);
		return match.matches();
	}

}
