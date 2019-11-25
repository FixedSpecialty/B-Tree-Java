import java.util.LinkedList;

public class TreeNode {

	private static int parent; //byte offset of parent node
	private static LinkedList<Integer> children; //array of tree objects
	private static LinkedList<TreeObject> keys; //array of tree keys
	
	/**
	 * MetaData for TreeNode
	 */
	private static int n; //number of keys currently stored
	private static boolean leaf; //true if no left or right child
	private static int ownLocation; //finds location of Node for cache
	
	public TreeNode()
	{
		n = 0;
		leaf=false;
		ownLocation=0;
		keys = new LinkedList<TreeObject>();
		children = new LinkedList<Integer>();
		
	}
	public int getN()
	{
		return n;
		
	}
	public void setN(int keyCount)
	{
		n = keyCount;
	}
	public void setLocation(int location)
	{
		ownLocation = location;
	}
	public int getLocation()
	{
		return ownLocation;
	}
	
	public int getParent()
	{
		return parent;
	}
	
	public void setParent(int setP)
	{
		parent = setP;
	}
	
	//for adding first child
	public void addChild(int x)
	{
		children.add(x);
	}
	
		
	//add specific for adding in an already populated node
	public void addChild(int x, int i)
	{
		children.add(i, x);
	}
	
	//return the child at the specified index of node
	public int getChild(int i)
	{
		return children.get(i);
	}
	
	//removes the child at the specified index of node
	public int removeChild(int i)
	{
		return children.remove(i);
	}
	
	//boolean to check if node is a leaf or not
	public boolean leaf()
	{
		return leaf;
	}
	
	//sets the boolean leaf value
	public void setLeaf(boolean leaf)
	{
		this.leaf = leaf;
	}
	
	//adds a treeObject to the node 
	public void addKey(TreeObject x)
	{
		keys.add(x);
	}
	
	//adds a treeObject at specific index of node
	public void addKey(TreeObject x, int i)
	{
		keys.add(i, x);
	}
	
	//returns the index if the key 
	public TreeObject getKey(int i)
	{
		return keys.get(i);
	}
	
	//removes the key
	public TreeObject removeKey(int i)
	{
		return keys.remove(i);
	}
}
