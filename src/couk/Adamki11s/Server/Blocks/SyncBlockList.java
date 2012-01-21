package couk.Adamki11s.Server.Blocks;

import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import couk.Adamki11s.Sync.Sync;

public class SyncBlockList {

	public LinkedList<Block> getBlockList(World w, Location corner1, Location corner2) {
		int x1 = Math.min(corner1.getBlockX(), corner2.getBlockX()), y1 = Math.min(corner1.getBlockY(), corner2.getBlockY()), z1 = Math.min(corner1.getBlockZ(),
				corner2.getBlockZ()), x2 = Math.max(corner2.getBlockX(), corner2.getBlockX()), y2 = Math.max(corner2.getBlockY(), corner2.getBlockY()), z2 = Math.max(
				corner2.getBlockZ(), corner2.getBlockZ());
		return getBlocks(w, x1, y1, z1, x2, y2, z2);
	}

	private LinkedList<Block> getBlocks(World w, int x1, int y1, int z1, int x2, int y2, int z2) {
		AsyncObtainer obtainer = new AsyncObtainer(w, x1, y1, z1, x2, y2, z2);
		Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Sync.plugin, obtainer, 0L);
		return obtainer.getBlockList();
	}

}