

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class BTree {
	//we need to create instructor with the max degree
	int degree;
	public TreeNode root;
	int TreeNodeSize;
	File filename;
	String printName;
	boolean cacheBoolean;
	int cacheSize;
	int debugLevel;
	Cache cache;
	public int size=0;
	int sequencelength;
	BufferedWriter writer;
	StringBuilder StringtoWrite=new StringBuilder();
	public BTree(String filename,int seqLength,int t, boolean cacheBoolean, int cacheSize, int debugLevel) {
		try {
			if(cacheBoolean == false) {
				this.cache = new Cache(0);
			} else {
				this.cache = new Cache(cacheSize);
			}
			printName = filename;
			this.degree=t;
			sequencelength=seqLength;
			this.filename=new File(filename);
			this.cacheBoolean = cacheBoolean;
			this.cacheSize = cacheSize;
			this.debugLevel = debugLevel;
			root=new TreeNode(t,0);
			this.disk_write(root, root.ownLocation);
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public BTree(File filename,int seqLength,int t, boolean cacheBoolean, int cacheSize, int debugLevel) {
		try {
			if(cacheBoolean == false) {
				this.cache = new Cache(0);
			} else {
				this.cache = new Cache(cacheSize);
			}
			this.degree=t;
			sequencelength=seqLength;
			this.filename = filename;
			this.cacheBoolean = cacheBoolean;
			this.cacheSize = cacheSize;
			this.debugLevel = debugLevel;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public TreeNode getRoot()
	{
		return root;
	}
	public void insert(long k) {
		this.size++;
		TreeNode checkduplicate=this.search(root, k);
		if(checkduplicate!=null) {
			for(int i=0;i<checkduplicate.keys.length;i++) {
				if(checkduplicate.keys[i].getLongValue()==k) {
					checkduplicate.keys[i].incrementFrequency();
					this.disk_write(checkduplicate, checkduplicate.ownLocation);
					return;
				}
			}
		}
		TreeNode r=this.root;
		if(r.n==(2*this.degree-1)) {
			TreeNode s=new TreeNode(this.degree,this.getFileLength());
			this.disk_write(s, s.ownLocation);
			this.root.ownLocation=this.getFileLength();
			this.disk_write(root, root.ownLocation);
			this.root=s;
			s.leaf=false;
			s.n=0;
			s.children[0]=r.ownLocation;
			s.ownLocation=0;
			this.split(s, 0, r);
			this.insertNonFull(s, k);
		
		}else {
			this.insertNonFull(root, k);
		}
	}
	
	

	public void insertNonFull(TreeNode x, long k) {

		 int i=x.n-1;
		 if(x.leaf) {
			 while(i>=0 && k<x.keys[i].getLongValue()) {
				 x.keys[i+1]=new TreeObject(x.keys[i].getLongValue(),x.keys[i].getFrequency());
				 i--;
			 }
			 x.keys[i+1]=new TreeObject(k);
			 x.n++;
			 this.disk_write(x, x.ownLocation);
			 
		 }else {
			 while(i>=0 && k < x.keys[i].getLongValue()) {
				 i--;
			 }
			 i++;
			 TreeNode node;
			 if(x.children[i]!=-1) {
				 node=this.readFile(x.children[i]);
				 if(node.n==(2*this.degree-1)) {
					 this.split(x, i, node);
					 if(k>x.keys[i].getLongValue()) {
						 i++;
					 }

				 }
				
				 this.insertNonFull(this.readFile(x.children[i]),k);
				 
			 }


		 }
	}
	public void split(TreeNode x, int i, TreeNode y) {
		TreeNode z=new TreeNode(this.degree,this.getFileLength());
		x.children[i]=y.ownLocation;
		z.leaf=y.leaf;
		z.n=this.degree-1;
		this.disk_write(z, z.ownLocation);
		
		
		for(int j=0;j<this.degree-1;j++) {
			z.keys[j]=new TreeObject(y.keys[j+this.degree].getLongValue(),y.keys[j+this.degree].getFrequency());	
			y.keys[j+this.degree]= new TreeObject();
		}
		if(!y.leaf) {
			for(int j=0;j<this.degree;j++) {
				z.children[j]=y.children[j+this.degree];
				y.children[j+this.degree]=-1;
			}
		}
		
		
		y.n=this.degree-1;
		for(int j=x.n;j>i;j--) {
			x.children[j+1]=x.children[j];
			x.children[j]=-1;
			
		}
		x.children[i+1]=z.ownLocation;
		for(int j=x.n-1;j>(i-1);j--) {
			x.keys[j+1]=new TreeObject(x.keys[j].getLongValue(),x.keys[j].getFrequency());
			
		}
		
		x.keys[i]=new TreeObject(y.keys[this.degree-1].getLongValue(),y.keys[this.degree-1].getFrequency());
		y.keys[this.degree-1]=new TreeObject();
		x.n++;
		

		this.disk_write(z, z.ownLocation);
		this.disk_write(y, y.ownLocation);
		this.disk_write(x, x.ownLocation);
		
		
	}
	public void disk_write(TreeNode node, long position) {
		try {
			RandomAccessFile writtingfile=new RandomAccessFile(filename,"rw");
			writtingfile.seek(position);
			writtingfile.writeBoolean(node.leaf);
			writtingfile.writeInt(node.n);
			writtingfile.writeLong(node.ownLocation);
			for(int i=0;i<node.keys.length;i++) {
				writtingfile.writeLong(node.keys[i].getLongValue());
				writtingfile.writeInt(node.keys[i].getFrequency());
				}
			
			for(int i=0;i<node.children.length;i++) {
				writtingfile.writeLong(node.children[i]);
				}


			cache.addObject(node);

			writtingfile.close();
			
		} catch (IOException e) {
				e.printStackTrace();
				}
				
				
		
		}
		/**
		 * 
		 * @param location of the node in the file, we Do not have TreeMetaData
		 */
		public TreeNode readFile(Long location){
			//should return TreeNode
			TreeNode node=new TreeNode(degree,location);
			for(int i = 0; i < cacheSize; i++){
				TreeNode n = (TreeNode)cache.get(i);
				if(location == n.ownLocation){
					return n;
				}
			}
			try {
				RandomAccessFile qfile=new RandomAccessFile(this.filename,"r");
				qfile.seek(location);
				node.leaf=qfile.readBoolean();
				node.n=qfile.readInt();
				//System.out.println(node.n);
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
		
		
	public TreeNode search(TreeNode node, long key) {
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
				returnNode=this.readFile(node.children[i]);
			}
			return search(returnNode,key);
			
			
		}

		public long getFileLength() {
			long x = 0;
			try {
				RandomAccessFile newfile=new RandomAccessFile(this.filename,"r");
				x= newfile.length();
				newfile.close();
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return x;

		}
		
	public void print(TreeNode node, int debug) throws IOException {
		
			
			int i;
			for(i=0; i < 2*this.degree-1; i++) {
				if (!node.leaf) {
					if(node.children[i] != -1L) {
						TreeNode n = readFile(node.children[i]);
						print(n, debug);
					}
				}
				TreeObject current = node.keys[i];
				if(current.getLongValue() != -1) {
					System.out.print(DataConversion.convertFromLong(current.getLongValue(), this.sequencelength)+": "+current.getFrequency()+"\n");
					if(debug == 1) {
						StringtoWrite.append(DataConversion.convertFromLong(current.getLongValue(), this.sequencelength)+": "+current.getFrequency()+"\n");
					}
				}
			}
			
			if (!node.leaf) {
				if(node.children[i] != -1L) {
					TreeNode n = this.readFile(node.children[i]);
					print(n, debug);
				}
			}
			
			//	Close writer
			String[] parts = printName.split("\\.");
			String file = parts[0];
			String btree = parts[1];
			String data = parts[2];
			String sequence = parts[3];
			String degree = parts[4];
			
			writer = new  BufferedWriter(new FileWriter(file+"."+btree+"."+"dump."+sequence+"."+degree));
			writer.write(StringtoWrite.toString());
			writer.close();


		}

	}