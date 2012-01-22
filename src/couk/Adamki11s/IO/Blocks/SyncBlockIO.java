package couk.Adamki11s.IO.Blocks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import couk.Adamki11s.Exceptions.SizeExceededException;
import couk.Adamki11s.IO.Blocks.jnbt.Tag;
import couk.Adamki11s.Server.Blocks.AsyncSaver;
import couk.Adamki11s.Sync.Sync;
import couk.Adamki11s.IO.Blocks.jnbt.ByteArrayTag;
import couk.Adamki11s.IO.Blocks.jnbt.IntTag;

public class SyncBlockIO {
	
	private final File f;
	private BlockIOStream stream;
	
	public SyncBlockIO(File f){
		this.f = f;
		try {
			stream = new BlockIOStream(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reopenOutputStream(){
		this.stream.reopenOutputStream();
	}
	
	public void reopenInputStream(){
		this.stream.reopenInputStream();
	}
	
	public void closeOutputStream(){
		this.stream.closeOutputStream();
	}
	
	public void closeInputStream(){
		this.stream.closeInputStream();
	}
	
	public void saveBlockList(World w, Location corner1, Location corner2) throws SizeExceededException {
		this.reopenOutputStream();
		int x1 = Math.min(corner1.getBlockX(), corner2.getBlockX()), y1 = Math.min(corner1.getBlockY(), corner2.getBlockY()), z1 = Math.min(corner1.getBlockZ(),
				corner2.getBlockZ()), x2 = Math.max(corner2.getBlockX(), corner2.getBlockX()), y2 = Math.max(corner2.getBlockY(), corner2.getBlockY()), z2 = Math.max(
				corner2.getBlockZ(), corner2.getBlockZ());
		saveBlocks(w, x1, y1, z1, x2, y2, z2);
	}
	
	private void saveBlocks(World w, int x1, int y1, int z1, int x2, int y2, int z2) throws SizeExceededException {
		AsyncSaver Saver = new AsyncSaver(this, w, x1, y1, z1, x2, y2, z2);
		Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Sync.plugin, Saver, 0L);
	}
	
	public void writePayload(Tag tag){
		this.stream.write(tag);
	}
	
	private Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected) {
		Tag tag = items.get(key);
		return tag;
	}
	
	public List<Block> loadBlockListFromWorld(World w){
		List<Block> list = new ArrayList<Block>();
		Map<String, Tag> tags = this.stream.read();
		int StartX = (Integer) getChildTag(tags, "StartX", IntTag.class).getValue();
		int StartY = (Integer) getChildTag(tags, "StartY", IntTag.class).getValue();
		int StartZ = (Integer) getChildTag(tags, "StartZ", IntTag.class).getValue();

		int width = (Integer) getChildTag(tags, "XSize", IntTag.class).getValue();
		int height = (Integer) getChildTag(tags, "YSize", IntTag.class).getValue();
		int length = (Integer) getChildTag(tags, "ZSize", IntTag.class).getValue();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					Block b = w.getBlockAt(StartX + x, StartY + y, StartZ + z);
					list.add(b);
				}
			}
		}
		return list;
	}
	
	public int getStartX(){
		Map<String, Tag> tags = this.stream.read();
		int StartX = (Integer) getChildTag(tags, "StartX", IntTag.class).getValue();
		getChildTag(tags, "StartY", IntTag.class).getValue();
		getChildTag(tags, "StartZ", IntTag.class).getValue();

		getChildTag(tags, "XSize", IntTag.class).getValue();
		getChildTag(tags, "YSize", IntTag.class).getValue();
		getChildTag(tags, "ZSize", IntTag.class).getValue();
		return StartX;
	}
	
	public int getStartY(){
		Map<String, Tag> tags = this.stream.read();
		getChildTag(tags, "StartX", IntTag.class).getValue();
		int StartY = (Integer) getChildTag(tags, "StartY", IntTag.class).getValue();
		getChildTag(tags, "StartZ", IntTag.class).getValue();

		getChildTag(tags, "XSize", IntTag.class).getValue();
		getChildTag(tags, "YSize", IntTag.class).getValue();
		getChildTag(tags, "ZSize", IntTag.class).getValue();
		return StartY;
	}
	
	public int getStartZ(){
		Map<String, Tag> tags = this.stream.read();
		int StartZ = (Integer) getChildTag(tags, "StartZ", IntTag.class).getValue();
		return StartZ;
	}
	
	public int getWidth(){
		Map<String, Tag> tags = this.stream.read();
		int width = (Integer) getChildTag(tags, "XSize", IntTag.class).getValue();
		return width;
	}
	
	public int getHeight(){
		Map<String, Tag> tags = this.stream.read();
		int height = (Integer) getChildTag(tags, "YSize", IntTag.class).getValue();
		return height;
	}
	
	public int getLength(){
		Map<String, Tag> tags = this.stream.read();
		int length = (Integer) getChildTag(tags, "ZSize", IntTag.class).getValue();
		return length;
	}
	
	public List<Integer> loadBlockListTypeIds(){
		List<Integer> list = new ArrayList<Integer>();
		Map<String, Tag> tags = this.stream.read();

		int width = (Integer) getChildTag(tags, "XSize", IntTag.class).getValue();
		int height = (Integer) getChildTag(tags, "YSize", IntTag.class).getValue();
		int length = (Integer) getChildTag(tags, "ZSize", IntTag.class).getValue();

		byte[] blocks = (byte[]) getChildTag(tags, "BlockID", ByteArrayTag.class).getValue();

		int index = 0;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					list.add((int)blocks[index]);
					index++;
				}
			}
		}
		return list;
	}
	
	public List<Byte> loadBlockListData(){
		List<Byte> list = new ArrayList<Byte>();
		Map<String, Tag> tags = this.stream.read();

		int width = (Integer) getChildTag(tags, "XSize", IntTag.class).getValue();
		int height = (Integer) getChildTag(tags, "YSize", IntTag.class).getValue();
		int length = (Integer) getChildTag(tags, "ZSize", IntTag.class).getValue();

		byte[] blockData = (byte[]) getChildTag(tags, "Data", ByteArrayTag.class).getValue();

		int index = 0;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					list.add(blockData[index]);
					index++;
				}
			}
		}
		return list;
	}
	
	public void loadBlockListToWorld(World w){
		Map<String, Tag> tags = this.stream.read();
		int StartX = (Integer) getChildTag(tags, "StartX", IntTag.class).getValue();
		int StartY = (Integer) getChildTag(tags, "StartY", IntTag.class).getValue();
		int StartZ = (Integer) getChildTag(tags, "StartZ", IntTag.class).getValue();

		int width = (Integer) getChildTag(tags, "XSize", IntTag.class).getValue();
		int height = (Integer) getChildTag(tags, "YSize", IntTag.class).getValue();
		int length = (Integer) getChildTag(tags, "ZSize", IntTag.class).getValue();

		byte[] blocks = (byte[]) getChildTag(tags, "BlockID", ByteArrayTag.class).getValue();
		byte[] blockData = (byte[]) getChildTag(tags, "Data", ByteArrayTag.class).getValue();

		int index = 0;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				for (int z = 0; z < length; z++) {
					Block b = w.getBlockAt(StartX + x, StartY + y, StartZ + z);
					b.setTypeId((int) blocks[index]);
					b.setData(blockData[index]);
					index++;
				}
			}
		}
	}

}
