package couk.Adamki11s.IO.Branches;

import java.util.ArrayList;

public class SubBranch extends BranchData {
	
	public SubBranch(String key, Object value) {
		super(key, value);
	}

	private ArrayList<BranchData> data = new ArrayList<BranchData>();

}
