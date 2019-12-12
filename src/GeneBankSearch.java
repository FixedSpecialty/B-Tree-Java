import java.io.File;
import java.io.IOException;
import java.io.* ;
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
    public static void main(String[] args) throws IOException{
    	Exception e = new Exception();
        try{
        	if (args.length < 3){
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
			if(args.length == 4){
				if(cacheBool == true) {
					cacheSize = Integer.parseInt(args[3]);
					if (cacheBool == false) {
						actualCache = new Cache(0);
					} else if (cacheBool == true) {
						actualCache = new Cache(cacheSize);
					}
				} else {
					debugLevel = Integer.parseInt(args[3]);
					if(debugLevel > 1 || debugLevel < 0)
					{
						System.out.println("Incorrect debug level. Must be 0 for NO, 1 for YES");
					}
				}
			}
			//args 3 & 4 check
			if(args.length == 5 )
			{
				debugLevel = Integer.parseInt(args[4]);
				if(debugLevel > 1 || debugLevel < 0)
				{
					System.out.println("Incorrect debug level. Must be 0 for NO, 1 for YES");
				}
				cacheSize = Integer.parseInt(args[3]);
				if(cacheBool == false){
					actualCache = new Cache(0);
				}
				else if(cacheBool == true){
					actualCache = new Cache(cacheSize);
				}
			}
        DataConversion data = new DataConversion();
        String[] parts = treeFile.split("\\.");
       // File filefile = new File(treeFile.substring(0, args[1].indexOf(".btree")));

		int sequence = Integer.parseInt(parts[4]);
		 degree = Integer.parseInt(parts[5]);
		//BTree bTree = new BTree(treeFile, sequence, degree, cacheBool, cacheSize, debugLevel);
		Scanner queryScan = new Scanner(new File(queryFile));
		TreeNode root = Read(0);
		if(cacheBool == true) {
			actualCache.addObject(root);
		}
	    StringBuilder str = new StringBuilder();
        String printname = new String(parts[0]+"_"+queryFile+"_"+"result");
			while(queryScan.hasNext()){
				String qLine = queryScan.nextLine().toLowerCase();
				long x=DataConversion.convertToLong(qLine);
				TreeNode foundQuery =search(root, x);
				
	          
				
				if(foundQuery!= null)
				{
					for(int i=0;i<foundQuery.keys.length;i++) {
						if(x==foundQuery.keys[i].getLongValue()) {
							if(cacheBool == true) {
								actualCache.addObject(foundQuery);
							}
	                    if(debugLevel == 1){
	                                str.append(DataConversion.convertFromLong(x, sequence)+": "+foundQuery.keys[i].getFrequency()+"\n");
	                               
	                            }
							System.out.println(DataConversion.convertFromLong(x, sequence)+": "+foundQuery.keys[i].getFrequency());
						}
						
					}
				}
	
			}//end of the while loop
            if(debugLevel == 1){
            
                BufferedWriter writer = new  BufferedWriter(new FileWriter(printname));
                writer.write(str.toString());
                writer.close();
            
            }

          
    
	}catch (Exception t){
			usage();
        	t.printStackTrace();
    	}
//        System.out.println(System.currentTimeMillis());
//        //1576191479243
//        //1576191542380
//        //1576191569219
    }//end of main
    
    
    
	private static TreeNode Read(long location) {
		TreeNode node=new TreeNode(degree,location);
		if(cacheBool == true) {
			for (int i = 0; i < actualCache.numberOfObject(); i++) {
				TreeNode n = (TreeNode) actualCache.get(i);
				if (location == n.ownLocation) {
					return n;
				}
			}
		}
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
			if(cacheBool == true) {
				actualCache.addObject(node);
			}
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
