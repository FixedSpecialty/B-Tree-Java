public class TreeObject {
	private long dnaLongValue;
	private int frequency;

<<<<<<< HEAD
	public TreeObject(long k) {
		this.dnaString = k;
		this.frequency = 1;
=======
	public TreeObject(long dnaLongValue,int freq) {
		this.dnaLongValue = dnaLongValue;
		this.frequency = freq;
>>>>>>> dc079353bb964884856517a6ade10a0e96b87534
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
		return "Frequency: " + frequency + ", Sequence: " + dnaLongValue;
	}
}

