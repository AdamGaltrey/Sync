package couk.Adamki11s.Cryptography;

public class SyncCryptography {
	
	private HashEncoder hashEncoder = new HashEncoder();
	private RSAEncoder rsaEncoder = new RSAEncoder();
	
	public HashEncoder getHashEncoder(){
		return this.hashEncoder;
	}
	
	public RSAEncoder getRSAEncoder(){
		return this.rsaEncoder;
	}
	
	

}
