import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GeneBankSearch {
    private static int cache;
    private static boolean cacheBool;
    private static String treeFile;
    private static String queryFile;
    private static int cacheSize;
    private static int debugLevel;
    private static int degree;
    public static Cache actualCache;

    public static void usage() 
	{
		
		System.out.println("java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<cache size>]\n" + 
				"[<debug level>]");
		
	}
    public static void main(String[] args) throws IOException
    {
    	Exception e = new Exception();
        try{
        if (args.length < 3)
        {
           throw e;
        }
        //args 0 check
        cache = Integer.parseInt(args[0]);
			if(cache == 0)
			{
				cacheBool = false;
			}
			else if (cache == 1)
			{
				cacheBool = true;
			}
            else System.out.println("Incorrect cache input. Must be 0 for NO, 1 for YES.");
        //args 1 check
        treeFile = args[1];
        System.out.println("the file is "+treeFile);
        //args 2 check
        queryFile = args[2];

        //args 3 check
        if(args.length > 3 && args.length < 5)
        {
            cacheSize = Integer.parseInt(args[3]);
            actualCache = new Cache(cacheSize);
        }
        //args 4 check
        if(args.length == 5 && !cacheBool)
		{
			debugLevel = Integer.parseInt(args[4]);
			if(debugLevel > 1 || debugLevel < 0)
			{
				System.out.println("Incorrect debug level. Must be 0 for NO, 1 for YES");
			}
        }
        
        DataConversion data = new DataConversion();
        String[] parts = treeFile.split("\\.");
       // File filefile = new File(treeFile.substring(0, args[1].indexOf(".btree")));

		int sequence = Integer.parseInt(parts[4]);
		 degree = Integer.parseInt(parts[5]);
		System.out.println(treeFile);
		//BTree bTree = new BTree(treeFile, sequence, degree, cacheBool, cacheSize, debugLevel);
		Scanner queryScan = new Scanner(new File(queryFile));
		TreeNode root = Read(0);
		System.out.println(root.childcount);
		
		while(queryScan.hasNext())
		{
			String qLine = queryScan.nextLine().toLowerCase();
			long x=DataConversion.convertToLong(qLine);
			TreeNode foundQuery =search(root, x);
			
			
			
			if(foundQuery!= null)
			{
				for(int i=0;i<foundQuery.keys.length;i++) {
					if(x==foundQuery.keys[i].getLongValue()) {
						System.out.println(DataConversion.convertFromLong(x, sequence)+": "+foundQuery.keys[i].getFrequency());
					}
					
				}
			}

		}
    }catch (Exception t)
    {
        usage();
        t.printStackTrace();
    }
    }
	private static TreeNode Read(long location) {
		TreeNode node=new TreeNode(degree,location);
		try {
			RandomAccessFile qfile=new RandomAccessFile(new File(treeFile),"rwd");
			qfile.seek(location);
			node.leaf=qfile.readBoolean();
			node.n=qfile.readInt();
			node.ownLocation=qfile.readLong();
			for(int i=0;i<node.keys.length;i++) {

				long val=qfile.readLong();
				int freq=qfile.readInt();
				node.keys[i]=new TreeObject(val,freq);
			}
			for(int i=0;i<node.children.length;i++) {
				node.children[i]=qfile.readLong();
			}
			qfile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return node;
	}
	public static TreeNode search(TreeNode node, long key) {
		int i =0;
		TreeNode returnNode=null;

		while(i< node.n && key>node.keys[i].getLongValue()){
			i++;
		}
		if (i<node.n && key == node.keys[i].getLongValue()) {
			return node;
		}
		if(node.leaf) {
			return null;
		}
		if(node.children[i]!=-1) {
			returnNode=GeneBankSearch.Read(node.children[i]);
		}
		return GeneBankSearch.search(returnNode,key);


	}
}
