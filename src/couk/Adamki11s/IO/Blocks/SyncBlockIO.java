package couk.Adamki11s.IO.Blocks;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import couk.Adamki11s.Exceptions.InvalidNBTFormat;
import couk.Adamki11s.Exceptions.SizeExceededException;
import couk.Adamki11s.IO.Blocks.jnbt.Tag;
import couk.Adamki11s.Server.Blocks.AsyncSaver;
import couk.Adamki11s.Sync.Sync;
import couk.Adamki11s.IO.Blocks.jnbt.ByteArrayTag;
import couk.Adamki11s.IO.Blocks.jnbt.CompoundTag;
import couk.Adamki11s.IO.Blocks.jnbt.IntTag;
import couk.Adamki11s.IO.Blocks.jnbt.NBTInputStream;

public class SyncBlockIO {

	private final File f;

	public SyncBlockIO(File f) {
		this.f = f;
	}

	/**
	 * Saves all blocks inside the bounds of the two locations given to a file.
	 * 
	 * @param w
	 *            Bukkit World object.
	 * @param corner1
	 *            Location 1
	 * @param corner2
	 *            Location 2
	 * @throws SizeExceededException
	 *             Size cannot exceed 256 blocks in any plain (x, y, z).
	 */
	public void saveBlockList(World w, Location corner1, Location corner2) throws SizeExceededException {
		int x1 = Math.min(corner1.getBlockX(), corner2.getBlockX()), y1 = Math.min(corner1.getBlockY(), corner2.getBlockY()), z1 = Math.min(corner1.getBlockZ(),
				corner2.getBlockZ()), x2 = Math.max(corner2.getBlockX(), corner2.getBlockX()), y2 = Math.max(corner2.getBlockY(), corner2.getBlockY()), z2 = Math.max(
				corner2.getBlockZ(), corner2.getBlockZ());
		saveBlocks(w, x1, y1, z1, x2, y2, z2);
	}

	private void saveBlocks(World w, int x1, int y1, int z1, int x2, int y2, int z2) throws SizeExceededException {
		AsyncSaver Saver = new AsyncSaver(this.f, w, x1, y1, z1, x2, y2, z2);
		Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(Sync.plugin, Saver, 0L);
	}

	private int startX, startY, startZ, width, height, length;
	private byte[] blocks, blockData;

	/**
	 * Loads the saved block data from the file to memory.
	 */
	public void loadBlockData() {
		try {
			FileInputStream fis = new FileInputStream(f);
			NBTInputStream nbt = new NBTInputStream(new GZIPInputStream(fis));

			CompoundTag backuptag = (CompoundTag) nbt.readTag();
			Map<String, Tag> tagCollection = backuptag.getValue();

			if (!backuptag.getName().equals("SyncBackup")) {
				throw new InvalidNBTFormat("UNKNOWN", "SyncBackup", backuptag.getName());
			}

			this.startX = (Integer) getChildTag(tagCollection, "StartX", IntTag.class).getValue();
			this.startY = (Integer) getChildTag(tagCollection, "StartY", IntTag.class).getValue();
			this.startZ = (Integer) getChildTag(tagCollection, "StartZ", IntTag.class).getValue();

			this.width = (Integer) getChildTag(tagCollection, "XSize", IntTag.class).getValue();
			this.height = (Integer) getChildTag(tagCollection, "YSize", IntTag.class).getValue();
			this.length = (Integer) getChildTag(tagCollection, "ZSize", IntTag.class).getValue();

			this.blocks = (byte[]) getChildTag(tagCollection, "BlockID", ByteArrayTag.class).getValue();
			this.blockData = (byte[]) getChildTag(tagCollection, "Data", ByteArrayTag.class).getValue();

			fis.close();
			nbt.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
		Tag tag = items.get(key);
		return tag;
	}

	/**
	 * Get a list of all the current blocks in the world inside the location
	 * bounds specified when saving.
	 * 
	 * @param w
	 *            World
	 * @return List<Block>
	 */
	public List<Block> getBlockList(World w) {
		List<Block> list = new ArrayList<Block>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					Block b = w.getBlockAt(startX + x, startY + y, startZ + z);
					list.add(b);
				}
			}
		}
		return list;
	}

	/**
	 * Uses the block data from the saved file to add these blocks to the world.
	 * @param w
	 */
	public void copyBlockListToWorld(World w) {

		int index = 0;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					Block b = w.getBlockAt(startX + x, startY + y, startZ + z);
					if (b.getTypeId() != (int) blocks[index]) {
						b.setTypeId((int) blocks[index]);
					}
					if (b.getData() != blockData[index]) {
						b.setData(blockData[index]);
					}
					index++;
				}
			}
		}
	}

}
