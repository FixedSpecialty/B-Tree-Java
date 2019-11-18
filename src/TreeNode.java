
public class TreeNode {

	private static int parent; //byte offset of parent node
	private static int[] childPointer; //byte offset of child pointers
	private static TreeObject[] treeObject; //array of tree objects
	
	/**
	 * MetaData for TreeNode
	 */
	private static int n; //number of keys currently stored
	private static boolean leaf; //true if no left or right child
	private static int ownLocation; //finds location of Node for cache
	
	public int getN()
	{
		return n;
		
	}
	
	public void setN(int keyCount)
	{
		n = keyCount;
	}
	
	public int getParent()
	{
		return parent;
	}
	
	public void setParent(int setP)
	{
		parent = setP;
	}
}
