package couk.Adamki11s.SQL;

public enum SCHEMA {
	
	MySQL,
	SQLite;
	
	@Override
	public String toString(){
		return this.toString().toUpperCase();
	}

}
