package couk.Adamki11s.IO.Serializable;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

import couk.Adamki11s.Exceptions.NullWorldException;

public class SyncBlock implements Serializable {

	private static final long serialVersionUID = -4625179989529077890L;
	
	private int id;
	private SyncLocation location;
	private byte data;
	
	/**
	 * A serializable implementation of Bukkit's Block class.
	 * @param bukkitBlock
	 */
	public SyncBlock(Block bukkitBlock){
		this.id = bukkitBlock.getTypeId();
		this.location = new SyncLocation(bukkitBlock.getLocation());
		this.data = bukkitBlock.getData();
	}
	
	/**
	 * Converts a SyncBlock object into a Bukkit Block object.
	 * 
	 * @param syncBlock
	 * @return Block
	 */
	public Block getBukkitBlock() {
		World w = Bukkit.getServer().getWorld(this.getLocation().getWorldName());
		if (w == null) {
			try {
				throw new NullWorldException(this.getLocation().getWorldName());
			} catch (NullWorldException e) {
				e.printStackTrace();
			}
		}
		Block b = w.getBlockAt((this.getLocation()).getBukkitLocation());
		return b;
	}

	public int getId() {
		return id;
	}

	public SyncLocation getLocation() {
		return location;
	}

	public byte getData() {
		return data;
	}

}
