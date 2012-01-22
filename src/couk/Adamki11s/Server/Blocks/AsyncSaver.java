package couk.Adamki11s.Server.Blocks;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

import org.bukkit.World;
import org.bukkit.block.Block;

import couk.Adamki11s.Exceptions.SizeExceededException;
import couk.Adamki11s.IO.Blocks.BlockIOStream;
import couk.Adamki11s.IO.Blocks.SyncBlockIO;
import couk.Adamki11s.IO.Blocks.jnbt.ByteArrayTag;
import couk.Adamki11s.IO.Blocks.jnbt.IntTag;
import couk.Adamki11s.IO.Blocks.jnbt.Tag;
import couk.Adamki11s.IO.Blocks.jnbt.CompoundTag;

public class AsyncSaver implements Runnable {

	private World w;
	private LinkedList<Block> blocks = new LinkedList<Block>();
	private int x1, y1, z1, x2, y2, z2, width, height, length;
	private boolean working = true;
	private SyncBlockIO io;
	private final SyncBlockIO output;

	private int bufferSize = 10000;
	byte[] blockID;
	byte[] blockData;

	public AsyncSaver(SyncBlockIO f, World w, int x1, int y1, int z1, int x2, int y2, int z2) throws SizeExceededException {
		this.output = f;
		this.w = w;
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		width = x2 - x1;
		height = y2 - y2;
		length = z2 - z1;
		int arraySize = width * height * length;
		if (width > 256 || height > 256 || length > 256) {
			throw new SizeExceededException(Math.max(length, Math.max(width, height)));
		}
		blockID = new byte[arraySize];
		blockData = new byte[arraySize];
	}

	@Override
	public void run() {
		HashMap<String, Tag> tags = new HashMap<String, Tag>();
		long start = System.nanoTime();
		int index = 0;
		while (this.working) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					for (int z = 0; z < length; z++) {
						Block b = w.getBlockAt(this.x1 + x, this.y1 + y, this.z1 + z);
						blockID[index] = (byte) b.getTypeId();
						blockData[index] = (byte) b.getData();
						index++;
					}
				}
			}
			this.working = false;
		}

		tags.put("BlockID", new ByteArrayTag("BlockID", blockID));
		tags.put("Data", new ByteArrayTag("Data", blockData));
		tags.put("StartX", new IntTag("StartX", x1));
		tags.put("StartY", new IntTag("StartY", y1));
		tags.put("StartZ", new IntTag("StartZ", z1));
		tags.put("XSize", new IntTag("XSize", width));
		tags.put("YSize", new IntTag("YSize", height));
		tags.put("ZSize", new IntTag("ZSize", length));
		CompoundTag compoundTag = new CompoundTag("SyncBackup", tags);
		this.output.reopenOutputStream();
		this.output.writePayload(compoundTag);
		this.output.closeOutputStream();
	}

	public boolean isWorking() {
		return this.working;
	}

	public LinkedList<Block> getBlockList() {
		return this.blocks;
	}

}
