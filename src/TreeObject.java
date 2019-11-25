public class TreeObject {
	private long dnaLongValue;
	private int frequency;

	public TreeObject(long dnaLongValue,int freq) {
		this.dnaLongValue = dnaLongValue;
		this.frequency = freq;
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

