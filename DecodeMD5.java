import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.security.*;

class DecodeMD5{

	final static String nyuEmail = "yx.shen@nyu.edu"; 
	final static String hasdedCode = "4b19af0100b9577f836af50f56d52082648206206aa38aa7cc292eab058c725ec10de4b2b9f2412a975af71aedcc5a581d681409b5511513a35f97eab77c8d04f91e1eac6152d5d8223c8695b0732fd43a0da0ac45353e3b2e97928c507389e125f1acaef7d3a0d6c3091c5c162763deed5b466b1bf12a0b21679ca39387d02df62d056b7f87a69c6f63ceb8d9eedca011ebcff03b1473ed8e45b01597761269ac0962aff946332a79181ff9ab1f4e1b2b5df0819061ba11847c2838d4845b1e180b41bab38e8933bd2cde41df46b266e4b4a861bfca814f7ecc83a9100246f020fbd8b26f55bd9d2db6f6a56c95a352f2c07d45427a7bac0d3508d640160a4b56d16abe8374778a750a6385362bd8fe8f44b190c9d6eb05c229dd16cd543f48334b652d0445024af6f4d7da7f8a578d9c3b1c86a7c36fdce257477a2e71f5b576a0c79cc8929b690324b89f2d495344d9174cd18f148fff1004bbb8fea5c58fbd1d24ed876dba9ad803770edce8ab8e4f1823b37c5b0fcb131a42c605cfc1c492a3bd9e503ee98f602eea453c4dde44e12d6dfa24a2548e42971be8a673eac72dce3cf2ae3248beb829725cedb6db558eb03f22ab7ca6fe49492d6387709461";

	public static void main (String args[]) throws Exception {
			String res = secretEmail(nyuEmail, hasdedCode);
	        System.out.println(res);
	}

	public static String secretEmail(String nyuEmail, String hasdedCode) throws Exception{
		//get MD5 of my email
		MessageDigest md = MessageDigest.getInstance("MD5");
	    md.reset();
 		md.update(nyuEmail.getBytes("UTF-8"));
	    BigInteger bigInt = new BigInteger(1, md.digest());
		String md5nyuEmail = bigInt.toString(16);

		//create the dict that contains all possible char
		char[] dict = new char[66];
		int index = 0;
		while (index < 52) {
			dict[index] = (char)('a' + index/2);
			index++;
			dict[index]= (char)('A' + index/2);
			index++;
		}

		while (index < 62) {
			dict[index] = (char)('0' + index - 52);
			index++;
		}

		dict[62] = '_';
		dict[63] = '.';
		dict[64] = '@';
		dict[65] = '+';

		//as MD5 is 32 char long, check all possible inputs until we get the correct one
		String secretEmail = "";
		int cnt = 0;
		for (int i = 0; i < hasdedCode.length() / 32; i++) {
			String curCode = hasdedCode.substring(i * 32, (i + 1) * 32);
			secretEmail = matchHasedCode(md5nyuEmail, curCode, dict, secretEmail);
		}
		return secretEmail;
	}

	public static String matchHasedCode(String md5nyuEmail, String curCode, char[] dict, String secretEmail) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		for (int i = 0; i < dict.length; i++) {
			for (int j = 0; j < dict.length; j++) {
				//get md5(s)
				String s =  secretEmail + String.valueOf(dict[i]) + String.valueOf(dict[j]);
	 			md.update(s.getBytes("UTF-8"));
		    	BigInteger bigInt = new BigInteger(1, md.digest());
				String md5secretEmail = bigInt.toString(16);
				//sometimes md5 has less than 32 char
				while (md5secretEmail.length() < 32) {
        			md5secretEmail = "0" + md5secretEmail;
        		}
        		//get md5(md5(e) + s + md5(s))
				String combo = md5nyuEmail + s + md5secretEmail;
	 			md.update(combo.getBytes("UTF-8"));
		    	bigInt = new BigInteger(1, md.digest());
				combo = bigInt.toString(16);
				while (combo.length() < 32) {
        			combo = "0" + combo;
        		}
				if (combo.equals(curCode)) {
					return s;
				}
			}
		}
		return "No email found";
	}


}