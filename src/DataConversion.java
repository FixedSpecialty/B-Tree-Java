

//Dont quite know if these are right
//I need to test more, the string/long concat was very weird

public class DataConversion {

	public static long convertToLong(String dnaString) {
		char[] array = dnaString.toCharArray();
		StringBuilder str = new StringBuilder();
		for(int i=0; i<array.length; i++) {
			char atcg = Character.toUpperCase(array[i]);
			switch(atcg) {
			case 'A':
				str.append("00");
				break;
			case 'T':
				str.append("11");
				break;
			case 'C':
				str.append("01");
				break;
			case 'G':
				str.append("10");
				break;
			}
		}

			return Long.parseLong(str.toString(),2);
	}
	public static String convertFromLong(long dnaLongValue, int length) {
		String temp = Long.toString(dnaLongValue,2);// the long converted to string w/o leading 0's
		StringBuilder dnaString = new StringBuilder();// will hold the long converted to a string w/ leading 0's
		int k2 = length*2;// number of total bits expected
		int zerosNeeded = k2-temp.length();// number of leading 0's missing
		
		for(int i=0; i<zerosNeeded; i++) {
			dnaString.append("0");// add leading 0's
		}
		
		dnaString.append(temp);
		
		StringBuilder retVal = new StringBuilder();// will hold final resulting chars a, t, c, g
		int i=0;
		while(i<k2) { 
			
			String letter = ""+dnaString.charAt(i)+dnaString.charAt(i+1); // two bits per char
			switch(letter) {
			case "00":
				retVal.append("a");
				break;
				
			case "01":
				retVal.append("c");
				break;
				
			case "10":
				retVal.append("g");
				break;
				
			case "11":
				retVal.append("t");
				break;
			}
			
			i+=2;
		}
		
		return retVal.toString();
	}
}

