package couk.Adamki11s.IO.Serializable;

import java.io.Serializable;

import org.bukkit.block.Block;

import couk.Adamki11s.IO.Convertors;

public class SyncBlock implements Serializable {

	private static final long serialVersionUID = -4625179989529077890L;
	
	private int id;
	private SyncLocation location;
	private byte data;
	
	public SyncBlock(Block bukkitBlock){
		this.id = bukkitBlock.getTypeId();
		this.location = Convertors.getSyncLocation(bukkitBlock.getLocation());
		this.data = bukkitBlock.getData();
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
