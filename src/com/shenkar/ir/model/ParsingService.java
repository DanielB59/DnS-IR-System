package com.shenkar.ir.model;

import java.io.*;
import java.util.*;

import com.shenkar.ir.entities.*;
import com.shenkar.ir.optimizations.Algorithms;

public class ParsingService implements Service {
	
	private List<String> lines = new ArrayList<>();
	public List<String> words = new ArrayList<>();
	
	@SuppressWarnings("serial")
	public static final Set<String> stopList = new HashSet<String>() {{
		add("a");		add("all");		add("and");		add("any");		add("at");
		add("be");		add("do");		add("me");		add("her");		add("how");
		add("if");		add("is");		add("many");	add("not");		add("see");
		add("the");		add("thier");	add("when");	add("why");		add("i");
		add("we");		add("am");		add("so");		add("it");		add("its");
		add("it's");	add("to");		add("did");		add("where");	add("this");
	}};
	
	public ParsingService() {
		super();
	}

	public void readFile(File document) throws IOException {
		String regex = "[ ]*[)( \n\r\\/,.\"?!][ ]*";
		FileInputStream fis = new FileInputStream(document);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		
		try {
			String input = null;
			while (null != (input = reader.readLine()))
				lines.add(input);
		} catch (EOFException e) {
			e.printStackTrace();
		}
		
		for (String line : lines) {
			String[] split = line.split(regex);
			for (String word : split) {
				if (stopList.contains(word))
					continue;
//				words.add(Algorithms.soundex(Algorithms.stem(word)));
				words.add(word);
			}
		}
		System.out.println(words);
		reader.close();
	}
	
	public static void transform(List<String> words) {
		for (int i = 0; i < words.size(); ++i)
			words.set(i, Algorithms.soundex(Algorithms.stem(words.get(i))));
	}
	
	public static List<Term> toTerms(List<String> words) {
		List<Term> terms = new ArrayList<>();
		for (String word : words)
			terms.add(new Term(word));
		return terms;
	}
}
