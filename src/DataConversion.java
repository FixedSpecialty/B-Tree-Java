//Dont quite know if these are right
//I need to test more, the string/long concat was very weird

public class DataConversion {

	public static long convertToLong(String dnaString) {
		String dnaStringLow = dnaString.toUpperCase();
		long dnaLong = 0L;

		for(int i = 0; i < dnaString.length(); i++) {
			dnaLong = dnaLong << 2;
			switch (dnaStringLow.charAt(i)) {
				case 'A':
					dnaLong = dnaLong | (00);
					break;
				case 'T':
					dnaLong = dnaLong | (11);
					break;
				case 'C':
					dnaLong = dnaLong | (01);
					break;
				case 'G':
					dnaLong = dnaLong | (10);
					break;
			}
		}
		return dnaLong;
	}

	public static String convertFromLong(long dnaLong, int length) {
		String out = "";

		for(int i = 0; i < length; i++) {
			char twoBitSequence = (char)(dnaLong & (11));
			dnaLong = dnaLong >> 2;
			switch (twoBitSequence) {
				case 00:
					out += "A";
					break;
				case 11:
					out += "T";
					break;
				case 01:
					out += "C";
					break;
				case 10:
					out += "G";
					break;
			}
		}
		return out;
	}
}

