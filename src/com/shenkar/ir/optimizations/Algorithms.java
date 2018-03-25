package com.shenkar.ir.optimizations;

import java.util.HashMap;

public class Algorithms {
	private Algorithms() {};
	//probably visitor design pattern
	
	@SuppressWarnings("serial")
	public static final HashMap<String, String> dictionary = new HashMap<String, String>(){{
		//put("AEHIOUWY","0");
		put("BFPV","1");
		put("CGJKQSXZ","2");
		put("DT","3");
		put("L","4");
		put("MN","5");
		put("R","6");
	}};
	
	public static String soundex(String term) {
		StringBuilder builder = new StringBuilder("");
		String result = null, code = null;
		if (null == term || term.equals("") || term.equals("\n")) return term;
		term = term.toUpperCase();
		builder.append(term.charAt(0));
		
		char[] letters = term.toCharArray();
		String prev = "";
		
		for (int i = 1; i < letters.length; ++i) {
			for (String key : dictionary.keySet()) {
				if (0 <= key.indexOf(letters[i])) {
					code = dictionary.get(key);
					if (null != code && prev != code) {
						prev = code;
						builder.append(code);
					}
				}
			}
		}
		
		//result = builder.toString().replace("0", "");
		builder.append("000");
		result = builder.substring(0,4);
		
		return result;
	}
	
	private static Stemmer stemmer = new Stemmer();
	
	public static String stem(String term) {
		char[] arr = term.toCharArray();
		
		stemmer.reset();
		stemmer.add(arr, arr.length);
		stemmer.stem();
		
		return stemmer.toString();
	}
}
