public class DataConversion {

	public static long convertToLong(String dnaString) {
		String dnaStringLow = dnaString.toUpperCase();
		long dnaLong = 0L;

		for(int i = 0; i < dnaString.length(); i++) {
			dnaLong = dnaLong << 2;
			switch (dnaStringLow.charAt(i)) {
				case 'A':
					dnaLong = dnaLong | (0b00);
					break;
				case 'T':
					dnaLong = dnaLong | (0b11);
					break;
				case 'C':
					dnaLong = dnaLong | (0b01);
					break;
				case 'G':
					dnaLong = dnaLong | (0b10);
					break;
			}
		}
		return dnaLong;
	}

	public static StringBuilder convertFromLong(long dnaLong, int length) {
		String out = "";

		for(int i = 0; i < length; i++) {
			char twoBitSequence = (char)(dnaLong & (0b11));
			dnaLong = dnaLong >> 2;
			switch (twoBitSequence) {
				case 0b00:
					out += "A";
					break;
				case 0b11:
					out += "T";
					break;
				case 0b01:
					out += "C";
					break;
				case 0b10:
					out += "G";
					break;
			}
		}
		return new StringBuilder(out).reverse();
	}
}
