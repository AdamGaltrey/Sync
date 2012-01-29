package couk.Adamki11s.IO.Serializable;

import java.io.Serializable;

import org.bukkit.Location;

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
