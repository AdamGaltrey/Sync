package couk.Adamki11s.IO.Serializable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class SyncItemStack implements Serializable {

	private static final long serialVersionUID = 4240813376190509679L;

	private int id, amount;
	private byte data;
	private short durability;
	private Map<SyncEnchantment, Integer> enchantments = new HashMap<SyncEnchantment, Integer>();

	/**
	 * A serializable implementation of Bukkit's ItemStack class.
	 * 
	 * @param bukkitItemStack
	 */
	public SyncItemStack(ItemStack bukkitItemStack) {
		this.id = bukkitItemStack.getTypeId();
		this.amount = bukkitItemStack.getAmount();
		this.data = bukkitItemStack.getData().getData();
		this.durability = bukkitItemStack.getDurability();
		if (bukkitItemStack.getEnchantments() != null && !bukkitItemStack.getEnchantments().isEmpty() && bukkitItemStack.getEnchantments().size() > 0) {
			for (Entry<Enchantment, Integer> e : bukkitItemStack.getEnchantments().entrySet()) {
				if (e.getKey() != null) {
					this.enchantments.put(new SyncEnchantment(e.getKey()), e.getValue());
				}
			}
		}
	}
	
	/**
	 * Converts a SyncItemStack object into a Bukkit ItemStack object.
	 * 
	 * @param syncItemStack
	 * @return ItemStack
	 */
	public ItemStack getBukkitItemStack() {
		ItemStack is = new ItemStack(this.getId(), this.getAmount());
		is.getData().setData(this.getData());
		is.setDurability(this.getDurability());
		if(this.getEnchantments() != null && !this.getEnchantments().isEmpty() && this.getEnchantments().size() > 0){
		for (Entry<SyncEnchantment, Integer> e : this.getEnchantments().entrySet()) {
			if (e.getKey() != null) {
				Enchantment ench = Enchantment.getById(e.getKey().getId());
				is.addEnchantment(ench, e.getValue());
			}
		}
		}
		return is;
	}

	public int getId() {
		return id;
	}

	public int getAmount() {
		return amount;
	}

	public byte getData() {
		return data;
	}

	public short getDurability() {
		return durability;
	}

	public Map<SyncEnchantment, Integer> getEnchantments() {
		return enchantments;
	}

}
