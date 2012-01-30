package couk.Adamki11s.IO.Serializable;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import couk.Adamki11s.Exceptions.NullWorldException;

public class SyncLocation implements Serializable {

	private static final long serialVersionUID = -6458678781007361236L;
	
	private String worldName;
	private double x, y, z;
	private float yaw, pitch;
	

	/**
	 * A serializable implementation of Bukkit's Location class.
	 * @param bukkitLocation
	 */
	public SyncLocation(Location bukkitLocation){
		this.worldName = bukkitLocation.getWorld().getName();
		this.x = bukkitLocation.getX();
		this.y = bukkitLocation.getY();
		this.z = bukkitLocation.getZ();
		this.yaw = bukkitLocation.getYaw();
		this.pitch = bukkitLocation.getPitch();
	}
	
	/**
	 * Converts a SyncLocation object into a Bukkit Location object.
	 * 
	 * @param syncLocation
	 * @return Location
	 */
	public Location getBukkitLocation() {
		World w = Bukkit.getServer().getWorld(this.getWorldName());
		if (w == null) {
			try {
				throw new NullWorldException(this.getWorldName());
			} catch (NullWorldException e) {
				e.printStackTrace();
			}
		}
		return new Location(w, this.getX(), this.getY(), this.getZ(), this.getYaw(), this.getPitch());
	}

	public String getWorldName() {
		return worldName;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public float getYaw() {
		return yaw;
	}

	public float getPitch() {
		return pitch;
	}
	
	

}
