

public class BTreeObject {
    private String dnaString;
    private int frequency;

    public BTreeObject(String dnaString) {
        this.dnaString = dnaString;
        this.frequency = 1;
    }

    public long getLongValue(){
        return DataConversion.convertToLong(dnaString);
    }

    public int getFrequency(){
        return this.frequency;
    }

    public void incrementFrequency() {
        frequency++;
    }

    public String getDnaString()
    {
        return this.dnaString;
    }

    @Override
    public String toString() {
        return "Frequency: " + frequency + ", Sequence: " + dnaString;
    }
}
