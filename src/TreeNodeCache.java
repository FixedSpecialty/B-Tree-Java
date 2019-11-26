
public class TreeNodeCache 
{
	private TreeNode content;
	private int location;
	
	public TreeNodeCache(int location, TreeNode content)
	{
		this.content = content;
		this.location = location;
	}
	
	public TreeNode getContent()
	{
		return content;
	}
	public int getLocation()
	{
		return location;
	}

}
