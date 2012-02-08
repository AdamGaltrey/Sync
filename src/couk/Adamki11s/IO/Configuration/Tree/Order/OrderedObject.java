package couk.Adamki11s.IO.Configuration.Tree.Order;

public class OrderedObject {
	
	private final String parent, key;
	private final Object value;
	private final int depth;
	private boolean read = false;
	
	public OrderedObject(String parent, String key, int depth){
		this.parent = parent;
		this.key = key;
		this.depth = depth;
		this.value = null;
	}
	
	public OrderedObject(String parent, String key, Object value, int depth){
		this.parent = parent;
		this.key = key;
		this.value = value;
		this.depth = depth;
	}

	public String getParent(){
		return this.parent;
	}
	
	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public int getDepth() {
		return depth;
	}
	
	public boolean hasBeenRead(){
		return this.read;
	}
	
	public void read(){
		this.read = true;
	}
	
	public boolean doesHaveValue(){
		return (!(this.value == null));
	}

}
