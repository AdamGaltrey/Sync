package couk.Adamki11s.IO.Serializable;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class SyncItemStack implements Serializable {

	private static final long serialVersionUID = 4240813376190509679L;

	private int id, amount;
	private byte data;
	private short durability;
	private Map<SyncEnchantment, Integer> enchantments;

	/**
	 * A serializable implementation of Bukkit's ItemStack class.
	 * @param bukkitItemStack
	 */
	public SyncItemStack(ItemStack bukkitItemStack) {
		this.id = bukkitItemStack.getTypeId();
		this.amount = bukkitItemStack.getAmount();
		this.data = bukkitItemStack.getData().getData();
		this.durability = bukkitItemStack.getDurability();
		for (Entry<Enchantment, Integer> e : bukkitItemStack.getEnchantments().entrySet()) {
			this.enchantments.put(new SyncEnchantment(e.getKey()), e.getValue());
		}
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
