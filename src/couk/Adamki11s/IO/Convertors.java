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
import couk.Adamki11s.IO.Serializable.SyncInventory;
import couk.Adamki11s.IO.Serializable.SyncItemStack;
import couk.Adamki11s.IO.Serializable.SyncLocation;

public class Convertors {

	/**
	 * Converts a Bukkit Location object into a serializable SyncLocation
	 * object.
	 * 
	 * @param bukkitLocation
	 * @return SyncLocation
	 */
	public static SyncLocation getSyncLocation(Location bukkitLocation) {
		return new SyncLocation(bukkitLocation);
	}

	/**
	 * Converts a Bukkit Block object into a serializable SyncBlock object.
	 * 
	 * @param bukkitBlock
	 * @return SyncBlock
	 */
	public static SyncBlock getSyncBlock(Block bukkitBlock) {
		return new SyncBlock(bukkitBlock);
	}

	/**
	 * Converts a Bukkit ItemStack object into a serializable SyncItemStack
	 * object.
	 * 
	 * @param bukkitItemStack
	 * @return SyncItemStack
	 */
	public static SyncItemStack getSyncItemStack(ItemStack bukkitItemStack) {
		return new SyncItemStack(bukkitItemStack);
	}

	/**
	 * Converts a Bukkit Enchantment object into a serializable SycnEnchantment
	 * object.
	 * 
	 * @param bukkitEnchantment
	 * @return SyncEnchantment
	 */
	public static SyncEnchantment getSyncEnchantment(Enchantment bukkitEnchantment) {
		return new SyncEnchantment(bukkitEnchantment);
	}

	/**
	 * Converts a Bukkit ItemStack[] (Inventory) object into a serializable
	 * SycnInventory object.
	 * 
	 * @param inventory
	 * @return SyncItemStack
	 */
	public static SyncInventory getSyncInventory(ItemStack[] inventory) {
		return new SyncInventory(inventory);
	}

	/**
	 * Converts a SyncLocation object into a Bukkit Location object.
	 * 
	 * @param syncLocation
	 * @return Location
	 */
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

	/**
	 * Converts a SyncBlock object into a Bukkit Block object.
	 * 
	 * @param syncBlock
	 * @return Block
	 */
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

	/**
	 * Converts a SyncItemStack object into a Bukkit ItemStack object.
	 * 
	 * @param syncItemStack
	 * @return ItemStack
	 */
	public static ItemStack getBukkitItemStack(SyncItemStack syncItemStack) {
		ItemStack is = new ItemStack(syncItemStack.getId(), syncItemStack.getAmount());
		is.getData().setData(syncItemStack.getData());
		is.setDurability(syncItemStack.getDurability());
		if(syncItemStack.getEnchantments() != null && !syncItemStack.getEnchantments().isEmpty() && syncItemStack.getEnchantments().size() > 0){
		for (Entry<SyncEnchantment, Integer> e : syncItemStack.getEnchantments().entrySet()) {
			if (e.getKey() != null) {
				Enchantment ench = Enchantment.getById(e.getKey().getId());
				is.addEnchantment(ench, e.getValue());
			}
		}
		}
		return is;
	}

	/**
	 * Converts a SyncInventory object into a Bukkit ItemStack[] (Inventory)
	 * object.
	 * 
	 * @param syncInventory
	 * @return ItemStack[] (Inventory)
	 */
	public static ItemStack[] getBukkitInventory(SyncInventory syncInventory) {
		return syncInventory.getContents();
	}

	public static boolean doesCompile(String regex, String checking) {
		Pattern p = Pattern.compile(regex);
		Matcher match = p.matcher(checking);
		return match.find();
	}

}
