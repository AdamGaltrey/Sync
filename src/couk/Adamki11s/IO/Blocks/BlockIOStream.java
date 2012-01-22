package couk.Adamki11s.IO.Blocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	private NBTOutputStream stream;
	private NBTInputStream input;
	private boolean inputClosed = false, outputClosed = false;

	protected BlockIOStream(File f) throws Exception {
		this.f = f;
		if(!f.exists()){
			f.createNewFile();
		}
		stream = new NBTOutputStream(new FileOutputStream(f));
		input = new NBTInputStream(new GZIPInputStream(new FileInputStream(f)));
	}

	protected void write(Tag tag) {
		try {
			this.stream.writeTag(tag);
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}
	
	protected Map<String, Tag> read(){
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
	
	protected void reopenOutputStream(){
		if(!this.outputClosed){
			return;
		}
		try {
			stream = new NBTOutputStream(new FileOutputStream(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void reopenInputStream(){
		if(!this.inputClosed){
			return;
		}
		try {
			input = new NBTInputStream(new GZIPInputStream(new FileInputStream(f)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void closeOutputStream() {
		try {
			this.stream.close();
			this.outputClosed = true;
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}
	
	protected void closeInputStream(){
		try {
			this.input.close();
			this.inputClosed = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
