import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode node=new TreeNode(3,0);
		try {
			RandomAccessFile qfile=new RandomAccessFile(new File("test3.gbk.btree.data.7.3"),"rwd");
			qfile.seek(0);
			node.leaf=qfile.readBoolean();
			node.n=qfile.readInt();
			System.out.println(node.n);
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
	}

}
