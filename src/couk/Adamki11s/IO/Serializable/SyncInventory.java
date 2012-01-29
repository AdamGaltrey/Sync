package couk.Adamki11s.IO.Serializable;

import java.io.Serializable;

import org.bukkit.inventory.ItemStack;

import couk.Adamki11s.IO.Convertors;

public class SyncInventory implements Serializable {

	private static final long serialVersionUID = -1281099042210796771L;
	
	private SyncItemStack[] inventory;

	/**
	 * A serializable implementation of Bukkit's ItemStack[] (Inventory) class.
	 * @param inventory
	 */
	public SyncInventory(ItemStack[] inventory){
		for(int pos = 0; pos < inventory.length; pos++){
			this.inventory[pos] = new SyncItemStack(inventory[pos]);
		}
	}
	
	public ItemStack[] getContents(){
		ItemStack[] stack = new ItemStack[this.inventory.length];
		for(int pos = 0; pos < inventory.length; pos++){
			stack[pos] = Convertors.getBukkitItemStack(this.inventory[pos]);
		}
		return stack;
	}

}
