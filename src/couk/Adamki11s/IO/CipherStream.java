package couk.Adamki11s.IO;

import java.io.File;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

class CipherStream extends EncryptedIOStream {

	char[] PASSWORD;
	private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, };

	public CipherStream(File f, String password) {
		super(f, password);
		this.PASSWORD = password.toCharArray();
	}
	
	public void setPassword(String password){
		this.PASSWORD = password.toCharArray();
	}

	String encrypt(String property) {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
			pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
			return base64Encode(pbeCipher.doFinal(property.getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private String base64Encode(byte[] bytes) {
		return new BASE64Encoder().encode(bytes);
	}

	String decrypt(String property) {
		try {
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
			Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
			pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
			return new String(pbeCipher.doFinal(base64Decode(property)));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private byte[] base64Decode(String property) {
		try {
			return new BASE64Decoder().decodeBuffer(property);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
