
public class BTree extends TreeNode {

	
	public <T> Object search(TreeNode target, long x)
	{
		TreeObject treeO = new TreeObject(x);
		int i = 1;
		while(i<target.getN() && treeO.equals(target.getKey(i)) > 0)
		{
			i++;
		}
		if(i<target.getN() && treeO.equals(target.getKey(i)) == 0)
		{
			return target.getKey(i);
		}
		if(target.leaf())
		{
			return null;
			
		}
		else
		{
		int childOffset = target.getChild(i);
		}
		return search(,x);
	}
	public void insertNonFull(TreeNode x, long k)
	{
		int i = x.getN();
		TreeObject treeO = new TreeObject(k);
		if(x.leaf())
		{
			while(i>0 && treeO.equals(x.getKey(i-1)) < 0)
			{
				i--;
			}
		}
	}
}
