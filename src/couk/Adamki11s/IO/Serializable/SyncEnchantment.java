package couk.Adamki11s.IO.Serializable;

import java.io.Serializable;

import org.bukkit.enchantments.Enchantment;

public class SyncEnchantment implements Serializable {

	private static final long serialVersionUID = 1400673419716003438L;
	
	private int id, level;
	
	public SyncEnchantment(Enchantment e){
		this.id = e.getId();
		this.level = e.getMaxLevel();
	}

	public int getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}

}
