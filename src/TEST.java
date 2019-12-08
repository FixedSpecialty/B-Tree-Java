

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
public class TEST {


	public static void main(String[] args) throws IOException {
		String res="result";
		int six = 6;
		int three =3;
		boolean fal = false;

		BTree tree=new BTree("result",6,3, false, 0, 0);
		LinkedList<String> l=FileParse.fileParse(6, new File("test1.gbk"));
	//System.out.println(DataConversion.convertFromLong(46523, 7));
		int q=0;
		for(int i=0;i<l.size();i++) {
			q++;
			String s=l.get(i);
				long x=DataConversion.convertToLong(s);
				tree.insert(x);			

			
						
		}		
		System.out.println(q);	
		tree.print(tree.root, 1);
		System.out.println(tree.size);
		
	}
}