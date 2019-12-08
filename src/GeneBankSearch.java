import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GeneBankSearch {
    private static int cache;
    private static boolean cacheBool;
    private static String treeFile;
    private static String queryFile;
    private static int cacheSize;
    private static int debugLevel;

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
        //args 2 check
        queryFile = args[2];

        //args 3 check
        if(args.length > 3 && args.length < 5)
        {
            cacheSize = Integer.parseInt(args[3]);
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
        File filefile = new File(treeFile.substring(0, args[1].indexOf(".btree")));
//		String file = parts[0]+"."+parts[1];
//		String btree = parts[2];
//		String dat = parts[3];
		int sequence = Integer.parseInt(parts[4]);
		int degree = Integer.parseInt(parts[5]);
		System.out.println(treeFile);
		BTree bTree = new BTree(treeFile, sequence, degree, cacheBool, cacheSize, debugLevel);
		Scanner queryScan = new Scanner(new File(queryFile));
		TreeNode root = bTree.readFile(bTree.getRoot().ownLocation);
		System.out.println(root.childcount);
		
		while(queryScan.hasNext())
		{
			String qLine = queryScan.nextLine().toLowerCase();
		//	System.out.println(qLine);
				long x=DataConversion.convertToLong(qLine);
			//System.out.println(x);
			TreeObject treeO = new TreeObject(x,qLine.length());
			TreeNode foundQuery = bTree.search(bTree.getRoot(), x);
			//System.out.println(foundQuery);
			
			
			if(foundQuery!= null)
			{
				System.out.println("xxxxxxxxx");
				treeO.incrementFrequency();
//				System.out.println(foundQuery.toString() );
//				System.out.println(DataConversion.convertToLong(foundQuery.toString()));
				System.out.println(foundQuery.toString() + ": "+treeO.getFrequency());
			}
			//System.out.println(foundQuery.toString() + ": "+treeO.getFrequency());
		}
    }catch (Exception t)
    {
        usage();
        t.printStackTrace();
    }
    }
}
