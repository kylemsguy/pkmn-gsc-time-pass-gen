package com.kylemsguy.pkmngsctimepass;

public class Utils {
	public static int genGSCTimePass(String name, int cash, int train_id) throws Exception {
		int pCash = cash % 65535;
		int pID = train_id % 65535;

		pCash = pCash / 256 + pCash % 256;
		pID = pID / 256 + pID % 256;

		int limit = name.length();
		int nval = 0; // value of name
		if (limit > 5) {
			limit = 5;
		}

		for (int i = 0; i < limit; i++) {
			nval += getCharVal(name.charAt(i));
		}

		return nval + pCash + pID;
	}

	private static int getCharVal(char character) throws Exception {
		int charcode = (int) character;
		if (character >= 'A' && character <= 'Z') {
			return (charcode - ((int) 'A') + 128);
		} else if (character >= 'a' && character <= 'z') {
			return (charcode - ((int) 'a') + 160);
		} else {
			switch (character) {
			case '-':
				return 227;
			case '?':
				return 230;
			case '/':
				return 243;
			case '.':
				return 232;
			case ',':
				return 244;
			case '(':
				return 154;
			case ')':
				return 155;
			case ':':
				return 156;
			case ';':
				return 157;
			case '[':
				return 158;
			case ']':
				return 159;
			case '+':
				return 225; // PK
			case '=':
				return 226; // MN
			case '!':
				return 231;
			case '*':
				return 241;
			}
		}
		throw new Exception("Invalid Character");
	}

}
