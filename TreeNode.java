
import java.util.Arrays;
import java.util.LinkedList;


public class TreeNode {

	public int parent; //byte offset of parent node
	public long[] children; //array of tree objects
	public TreeObject[] keys; //array of tree keys
	
	/**
	 * MetaData for TreeNode
	 */
	public  int n; //number of keys currently stored
	public int childcount;
	public  boolean leaf; //true if no left or right child
	public  long  ownLocation; //finds location of Node for cache
	
	public TreeNode(int t, long ownLocation)
	{
		n = 0;
		leaf=true;
		this.ownLocation=ownLocation;
		childcount=0;
		this.keys = new TreeObject[(2*t-1)];
		Arrays.fill(keys, new TreeObject(-1L));
		
		this.children = new long[(2*t)];
		Arrays.fill(children, -1L);
		
	}

	public void print() {
		System.out.println("File Position: " + ownLocation);
		System.out.println("Is Leaf: " + leaf);
		System.out.println("Number of Keys: " + n);
		System.out.println("Keys: ");
		for (int i = 0; i < keys.length; ++i) {
			System.out.print(keys[i].getLongValue() + ", "+keys[i].getFrequency()+" $  ");
		}
		System.out.println();
		System.out.println("Children: ");
		
		for(int j = 0; j < children.length; j++) {
			System.out.print(children[j] + ", ");
		}
		System.out.println();

	}
}
