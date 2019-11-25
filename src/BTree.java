import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class BTree extends TreeNode {
	//we need to create instructor with the max degree
	int degree;
	TreeNode root;
	int cursor;
	int TreeNodeSize;
	private RandomAccessFile file;
<<<<<<< HEAD


	public BTree(int seqLength,int degree) {
=======
	private int maxNodeSize;
	
	
	public BTree(String filename,int seqLength,int degree) {
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
		try {
			file=new RandomAccessFile(new File(filename), "rw");
			cursor=0;
			root=new TreeNode();
			this.maxNodeSize=12*(2*degree-1)+4*(2*degree+1)+9;
			this.disk_write(root, root.getLocation());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
	//	public <T> Object search(TreeNode target, long x)
	//	{
	//		TreeObject treeO = new TreeObject(x);
	//		int i = 1;
	//		while(i<target.getN() && treeO.equals(target.getKey(i)) > 0)
	//		{
	//			i++;
	//		}
	//		if(i<target.getN() && treeO.equals(target.getKey(i)) == 0)
	//		{
	//			return target.getKey(i);
	//		}
	//		if(target.leaf())
	//		{
	//			return null;
	//			
	//		}
	//		else
	//		{
	//		int childOffset = target.getChild(i);
	//		}
	//		return search(,x);
	//	}


	//	search backwards rather than forwards
	//	i=n[x]
	//	if leaf[x] //can insert new key
	//	     then while i>=1 and k<keyi[x] // keep searching until you find the bigger key
	//	               do keyi+1[x] = keyi[x]
	//	                i--;
	//	       keyi+1[x]=k
	//	       h[x]++
	//	       disk-write(x);
	//	else //if not a leaf node
	//	    while i>=1 and k<keyi[x]
	//	       do i--;
	//	  i++;
	//	disk-read(Ci[x]);
	//	if n[Ci[x] = 2t-1
	//	     then BTree_split-child(x,i,Ci[x]);
	//	         if k>keyi[x]
	//	            then i++;
	//	BTree-insert-nonfull(Ci[x], k);
	public void insertNonFull(TreeNode x, long k)
	{
		int i = x.getN();
		TreeObject treeO = new TreeObject(k);
		if(x.leaf())
		{
			while(i>0 && k<x.getKey(i).getLongValue())
			{

				treeO = x.getKey(i+1);
				i--;
			}
			treeO = x.getKey(i);
		}
		else
		{
			while (i>0 && k<x.getKey(i).getLongValue())
			{
				i--;
			}
			i++;
		}
	//	disk-read(x.getChild(i));
//		int y = x.getChild(i).getN();
		
		if( == ((2*(degree)-1)))
				{
				split(x,i,x);
				if(k>x.getKey(i).getLongValue())
				{
					i++;
				}
				}
		
		insertNonFull(C[x], long k);
	}

=======
	
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
	/*
	 * the child node =x in the pseudo code
	 * the parent node = y in the pseudo code
	 */
	public void split(TreeNode x, int index, TreeNode y){

		TreeNode z=new TreeNode();
		z.setLeaf(y.leaf());
		z.setParent(y.getParent());
		for(int i=0;i<degree-1;i++) {
			z.addKey(y.removeKey(i));
			z.setN(z.getN()+1);
			y.setN(y.getN()-1);
		}
		if(!y.leaf()) {
			for(int i=0;i<degree-1;i++) {
				//it is either degree or i
				z.addChild(y.removeChild(i));
			}
<<<<<<< HEAD

=======
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
		}

		x.addKey(y.removeKey(degree-1), index);
		x.setN(x.getN()+1);
		y.setN(y.getN()-1);

		//write to the disk
		if(x==root && x.getN()==1) {
			disk_write(y,cursor);
			cursor+=this.maxNodeSize;
			z.setLocation(cursor);
			x.addChild(z.getLocation());
			disk_write(z,cursor);
			disk_write(x,0);
			cursor+=this.maxNodeSize;
		}else {
<<<<<<< HEAD
			//disk_write(y,y.getLocation);
			//z.setLocation(Location(offSet));
			//disk_write(z,Location);
			//x.addChild(z.getLocation, index+1);
			//disk_write(x,x.getLocation);
			//Location+=sizeof TreeNode

=======
			disk_write(y,y.getLocation());
			z.setLocation(cursor);
			disk_write(z,z.getLocation());
			x.addChild(z.getLocation(), index+1);
			disk_write(x,x.getLocation());
			cursor+=this.maxNodeSize;
			
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
		}


	}
	public void Insert(long k) {
		TreeNode r=this.root;
		
		if(r.getN()==(2*degree-1)) {
			
			//handle duplicates
			//call serchMethod and if the returns are equal then increase the numofDup
			//we need TreeNode constructor with Long
			//TreeNode s=new TreeNode(k);
			TreeNode s=new TreeNode();

			this.root=s;
			s.setLeaf(false);
			s.setN(0);
			s.setLocation(cursor);
			cursor+=this.maxNodeSize;
			s.addChild(r.getLocation());
			this.split(s, 0, r);
<<<<<<< HEAD
			//	this.insertNonFull(s, k);
=======
			this.insertNonFull(s, k);
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
		}else {
			this.insertNonFull(r, k);
		}

	}
<<<<<<< HEAD
	public void readNode(int i)
	{
=======

	public void disk_write(TreeNode node, int offset){
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
		
	}


	public void disk_write(TreeNode node, int offset)
	{

		try{			
<<<<<<< HEAD


=======
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
			/**
			 * write MetaData is going to be in the sequece of the
			 * 
			 * 		WriteBoolean
			 * 		WriteInt number of keys
			 * 		WrriteInt the location
			 */
			file.seek(offset);
			file.writeBoolean(node.leaf());
			file.writeInt(node.getN());
			file.writeInt(node.getParent());
			for (int i = 0; i < node.getN(); i++)
			{
<<<<<<< HEAD
				file.writeLong(node.getKey(i).getLongValue());
				file.writeInt(node.getKey(i).getFrequency());
				file.writeInt(node.getChild(i));
=======
					file.writeInt(node.getChild(i));
					file.writeLong(node.getKey(i).getLongValue());
					file.writeInt(node.getKey(i).getFrequency());
					
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
			}			
			file.writeInt(node.getChild(node.getN()+1));
		} 
		catch (Exception e) {
			System.out.println("Error in writing ");
			e.printStackTrace();
		}

	}
	/**
	 * 
	 * @param location of the node in the file, we Do not have TreeMetaData
	 */
	public TreeNode readFile(int location){
		//should return TreeNode
		TreeNode node=new TreeNode();
		try {
			file.seek(location);
			node.setLeaf(file.readBoolean());
			node.setN(file.readInt());
			node.setParent(file.readInt());
			for(int i=0;i<(2*degree)-1;i++) {
				node.addChild(file.readInt());
				TreeObject obj=new TreeObject(file.readLong(),file.readInt());
			
				node.addKey(obj);
				
			}
			return node;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		

<<<<<<< HEAD











=======
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
}
