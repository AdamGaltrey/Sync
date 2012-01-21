package couk.Adamki11s.IO;

import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import couk.Adamki11s.Exceptions.NullWorldException;
import couk.Adamki11s.IO.Serializable.SyncBlock;
import couk.Adamki11s.IO.Serializable.SyncEnchantment;
import couk.Adamki11s.IO.Serializable.SyncItemStack;
import couk.Adamki11s.IO.Serializable.SyncLocation;

public class Convertors {

	public static SyncLocation getSyncLocation(Location bukkitLocation) {
		return new SyncLocation(bukkitLocation);
	}

	public static SyncBlock getSyncBlock(Block bukkitBlock) {
		return new SyncBlock(bukkitBlock);
	}

	public static SyncItemStack getSyncItemStack(ItemStack bukkitItemStack) {
		return new SyncItemStack(bukkitItemStack);
	}

	public static SyncEnchantment getSyncEnchantment(Enchantment bukkitEnchantment) {
		return new SyncEnchantment(bukkitEnchantment);
	}

	public static Location getBukkitLocation(SyncLocation syncLocation) {
		World w = Bukkit.getServer().getWorld(syncLocation.getWorldName());
		if (w == null) {
			try {
				throw new NullWorldException(syncLocation.getWorldName());
			} catch (NullWorldException e) {
				e.printStackTrace();
			}
		}
		return new Location(w, syncLocation.getX(), syncLocation.getY(), syncLocation.getZ(), syncLocation.getYaw(), syncLocation.getPitch());
	}

	public static Block getBukkitBlock(SyncBlock syncBlock) {
		World w = Bukkit.getServer().getWorld(syncBlock.getLocation().getWorldName());
		if (w == null) {
			try {
				throw new NullWorldException(syncBlock.getLocation().getWorldName());
			} catch (NullWorldException e) {
				e.printStackTrace();
			}
		}
		Block b = w.getBlockAt(getBukkitLocation(syncBlock.getLocation()));
		return b;
	}

	public static ItemStack getBukkitItemStack(SyncItemStack syncItemStack) {
		ItemStack is = new ItemStack(syncItemStack.getId(), syncItemStack.getAmount());
		is.getData().setData(syncItemStack.getData());
		is.setDurability(syncItemStack.getDurability());	
		for(Entry<SyncEnchantment, Integer> e : syncItemStack.getEnchantments().entrySet()){
			Enchantment ench = Enchantment.getById(e.getKey().getId());
			is.addEnchantment(ench, e.getValue());
		}
		return is;
	}

	public static boolean doesCompile(String regex, String checking) {
		Pattern p = Pattern.compile(regex);
		Matcher match = p.matcher(checking);
		return match.find();
	}

}
