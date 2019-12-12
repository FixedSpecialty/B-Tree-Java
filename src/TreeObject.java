
public class TreeObject implements Comparable<TreeObject> {
	private long dnaLongValue;
	private int frequency;

	public TreeObject() {
		this.dnaLongValue = -1L;
		this.frequency = 1;
	}


	public TreeObject(long dnaLongValue) {
		this.dnaLongValue = dnaLongValue;
		this.frequency =1;
	
	}	
	
	public TreeObject(long dnaLongValue,int freq) {
		this.dnaLongValue = dnaLongValue;
		this.frequency = freq;

	}
	public void setLongValue(long x){
		dnaLongValue=x;
	}
	public long getLongValue(){
		return dnaLongValue;
	}

	public int getFrequency(){
		return this.frequency;
	}

	public void incrementFrequency() {
		frequency++;
	}

//	public String getDnaString()
//	{
//		return DataConversion.convertFromLong(dnaLongValue, length);
//	}

	@Override
	public String toString() {
		return dnaLongValue+":"+this.frequency+"$ ";
	}
	
	@Override
	public int compareTo(TreeObject treeO) {
		int retVal=0;
		if(treeO.getLongValue() > this.getLongValue())
		{
			retVal=-1;
		}
		else if(treeO.getLongValue() < this.getLongValue())
		{
			retVal = 1;
		}
		return retVal;
	}
}
