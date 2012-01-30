package couk.Adamki11s.IO.Serializable;

import java.io.Serializable;

import org.bukkit.inventory.ItemStack;

import couk.Adamki11s.IO.Convertors;

public class SyncInventory implements Serializable {

	private static final long serialVersionUID = -1281099042210796771L;

	private SyncItemStack[] inventory = new SyncItemStack[36];

	/**
	 * A serializable implementation of Bukkit's ItemStack[] (Inventory) class.
	 * 
	 * @param inventory
	 */
	public SyncInventory(ItemStack[] inventory) {
		for (int pos = 0; pos < inventory.length; pos++) {
			if (inventory[pos] != null && inventory[pos].getAmount() != 0 && inventory[pos].getTypeId() != 0) {
				this.inventory[pos] = new SyncItemStack(inventory[pos]);
			} else {
				continue;
			}
		}
	}

	/**
	 * Converts a SyncInventory object into a Bukkit ItemStack[] (Inventory)
	 * object.
	 * 
	 * @param syncInventory
	 * @return ItemStack[] (Inventory)
	 */
	public ItemStack[] getContents() {
		ItemStack[] stack = new ItemStack[this.inventory.length];
		for (int pos = 0; pos < inventory.length; pos++) {
			if (inventory[pos] != null && inventory[pos].getAmount() != 0 && inventory[pos].getId() != 0) {
				stack[pos] = (this.inventory[pos]).getBukkitItemStack();
			} else {
				continue;
			}
		}
		return stack;
	}

}
