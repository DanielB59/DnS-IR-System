package com.shenkar.ir.model;

import java.io.*;
import java.util.List;

public class QueryService {
	
	private static File searchQuery = new File("searchQuery.txt");
	public static List<String> queryWords = null;
	
	static {
		try {
			searchQuery.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void processQuery(String input) throws IOException {
		FileOutputStream fos = new FileOutputStream(searchQuery);
		DataOutputStream dos = new DataOutputStream(fos);
		dos.write(input.getBytes());
		dos.close();
		ParsingService parser = new ParsingService();
		parser.readFile(searchQuery);
		queryWords = parser.words;
	}
	
	public static void optimizeQuery() throws IOException {
		ParsingService.transform(queryWords);
	}
}
