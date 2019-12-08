

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.File;
import java.util.*;


public class GeneBankCreateBTree {
	public static int cache;
	public static boolean cacheBool;
	public static int degree;
	public static int sequenceLength;
	public static int cacheSize;
	public static int debugLevel;

	public static void usage() {

		System.out.println("java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequence length>\n"
				+ "[<cache size>] [<debug level>]");

	}

	private static int optimalDegree() {
		double optimalDegree;
		int metaData = 9;
		int pointers = 4;
		int objects = 12;
		double diskSize = 4096;
		optimalDegree = diskSize + objects;
		optimalDegree = optimalDegree - metaData;
		optimalDegree = optimalDegree - pointers;
		optimalDegree = optimalDegree / ((pointers * 2) + (objects * 2));

		return (int) Math.floor(optimalDegree);
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 4) {
			usage();
		}
		// args 0 check
		try {
			cache = Integer.parseInt(args[0]);
			if (cache == 0) {
				cacheBool = false;
			} else if (cache == 1) {
				cacheBool = true;
			} else
				System.out.println("Incorrect cache input. Must be 0 for NO, 1 for YES.");
			// args 1 check
			degree = Integer.parseInt(args[1]);
			if (degree == 0) {
				degree = optimalDegree();
			}
			// args 3 check
			sequenceLength = Integer.parseInt(args[3]);
			if (sequenceLength > 31 || sequenceLength < 0) {
				System.out.println("Incorrect sequence length. Must be between 1 and 31 inclusive. ");
				usage();
			}
			// args 4+5 check
			if (args.length > 4) {
				if (cacheBool) {
					cacheSize = Integer.parseInt(args[4]);
				}
			}
			if (args.length > 4 && !cacheBool) {
				debugLevel = Integer.parseInt(args[5]);
			if(debugLevel > 1 || debugLevel < 0)
			{
				System.out.println("Incorrect debug level. Must be 0 for NO, 1 for YES");
			}
		}
		//args 2 check
		File filename = new File(args[2]);
		String gbkFile = (filename + ".btree.data."+sequenceLength+"."+degree);
		BTree bTree = new BTree(gbkFile, sequenceLength, degree, cacheBool, cacheSize, debugLevel);
		LinkedList<String> fileParsed = FileParse.fileParse(sequenceLength, filename);

		for(int i=0;i<fileParsed.size();i++) {
			String s=fileParsed.get(i);
			if(s.length()==0) {
				System.out.println("error empty string");
			}
				long x=DataConversion.convertToLong(s);
				bTree.insert(x);
			
		}		
		bTree.print(bTree.root, 1);
		System.out.println(bTree.size);	
	}catch(Exception e)
	{
		usage();
		e.printStackTrace();
	}
}
}
