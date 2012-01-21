package couk.Adamki11s.IO.Blocks;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.zip.GZIPOutputStream;

import org.bukkit.block.Block;

public class BlockIOStream {

	private final File f;
	private final FileOutputStream fos;
	private final DataOutputStream dos;

	public BlockIOStream(File f) throws Exception {
		this.f = f;
		fos = new FileOutputStream(this.f);
		dos = new DataOutputStream(new GZIPOutputStream(fos));
	}

	public void writeWorldHeader(String world) {
		try {
			dos.writeUTF(world);
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

	public void write(byte[] blocks) {
		try {
			System.out.println("Writing blocks : " + blocks.length);
			dos.write(blocks);
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

	public void closeStream() {
		try {
			dos.flush();
			dos.close();
		} catch (IOException iox) {
			iox.printStackTrace();
		}
	}

}
