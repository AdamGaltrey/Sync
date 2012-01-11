package couk.Adamki11s.IO.Branches;

import java.util.ArrayList;

public class Root extends BranchData {
	
	public Root(String key, Object value) {
		super(key, value);
	}
	private ArrayList<Branch> branches = new ArrayList<Branch>();
	private ArrayList<BranchData> data = new ArrayList<BranchData>();

}
