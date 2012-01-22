package couk.Adamki11s.IO.Blocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.bukkit.ChatColor;

import couk.Adamki11s.Exceptions.InvalidNBTFormat;
import couk.Adamki11s.IO.Blocks.jnbt.NBTOutputStream;
import couk.Adamki11s.IO.Blocks.jnbt.Tag;
import couk.Adamki11s.IO.Blocks.jnbt.NBTInputStream;
import couk.Adamki11s.IO.Blocks.jnbt.CompoundTag;

public class BlockIOStream {

	private final File f;
	private final NBTOutputStream stream;
	private final NBTInputStream input;

	public BlockIOStream(File f) throws Exception {
		this.f = f;
		if(!f.exists()){
			f.createNewFile();
		}
		stream = new NBTOutputStream(new FileOutputStream(f));
		input = new NBTInputStream(new GZIPInputStream(new FileInputStream(f)));
	}

	public void write(Tag tag) {
		try {
			this.stream.writeTag(tag);
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}
	
	public Map<String, Tag> read(){
		CompoundTag backuptag = null;
		try {
			backuptag = (CompoundTag) input.readTag();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (!backuptag.getName().equals("SyncBackup")) {
			try {
				throw new InvalidNBTFormat("UNKNOWN", "SyncBackup", backuptag.getName());
			} catch (InvalidNBTFormat e) {
				e.printStackTrace();
			}
		}
		return backuptag.getValue();
	}

	public void closeStream() {
		try {
			this.stream.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

}
