package couk.Adamki11s.IO.Blocks;

import java.io.File;
import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import couk.Adamki11s.Exceptions.SizeExceededException;
import couk.Adamki11s.Server.Blocks.AsyncSaver;
import couk.Adamki11s.Sync.Sync;

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
	
	public void saveBlockList(World w, Location corner1, Location corner2) throws SizeExceededException {
		int x1 = Math.min(corner1.getBlockX(), corner2.getBlockX()), y1 = Math.min(corner1.getBlockY(), corner2.getBlockY()), z1 = Math.min(corner1.getBlockZ(),
				corner2.getBlockZ()), x2 = Math.max(corner2.getBlockX(), corner2.getBlockX()), y2 = Math.max(corner2.getBlockY(), corner2.getBlockY()), z2 = Math.max(
				corner2.getBlockZ(), corner2.getBlockZ());
		saveBlocks(w, x1, y1, z1, x2, y2, z2);
	}
	
	private void saveBlocks(World w, int x1, int y1, int z1, int x2, int y2, int z2) throws SizeExceededException {
		AsyncSaver Saver = new AsyncSaver(this.f, w, x1, y1, z1, x2, y2, z2);
		Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Sync.plugin, Saver, 0L);
	}

}
