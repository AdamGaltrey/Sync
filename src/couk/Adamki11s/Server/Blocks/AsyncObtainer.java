package couk.Adamki11s.Server.Blocks;

import java.io.File;
import java.util.LinkedList;

import org.bukkit.World;
import org.bukkit.block.Block;

import couk.Adamki11s.IO.Blocks.BlockIOStream;
import couk.Adamki11s.IO.Blocks.SyncBlockIO;

public class AsyncObtainer implements Runnable {

	private World w;
	private LinkedList<Block> blocks = new LinkedList<Block>();
	private int x1, y1, z1, x2, y2, z2;
	private boolean working = true;
	private BlockIOStream io;

	private int bufferSize = 10000;
	byte[] blockID;
	byte[] blockData;

	public AsyncObtainer(World w, int x1, int y1, int z1, int x2, int y2, int z2) {
		try {
			io = new BlockIOStream(new File("plugins" + File.separator + "blocks.syn"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.w = w;
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		System.out.println(x2 - x1);
		System.out.println(y2 - y1);
		System.out.println(z2 - z1);
		int arraySize = 100000000; // Math.abs(((x2 - x1) * (y2 - y1) * (z2 -
									// z1)));
		System.out.println("byte array size = " + arraySize);
		blockID = new byte[arraySize];
		blockData = new byte[arraySize];
		io.writeWorldHeader(w.getName());
	}

	@Override
	public void run() {
		long start = System.nanoTime();
		int index = 0;
		while (this.working) {
			for (int x = x1; x <= x2; x++) {
				for (int y = y1; y <= y2; y++) {
					for (int z = z1; z <= z2; z++) {
						Block b = w.getBlockAt(x, y, z);
						blockID[index] = (byte) b.getTypeId();
						blockData[index] = (byte) b.getData();
						index++;
					}
				}
			}
			this.working = false;
		}
		System.out.println("indexes checked");
		io.write(blockID);
		io.write(blockData);
		io.closeStream();
		System.out.println("response time = " + ((System.nanoTime() - start) / 1000000000F));
	}

	public boolean isWorking() {
		return this.working;
	}

	public LinkedList<Block> getBlockList() {
		return this.blocks;
	}

}
