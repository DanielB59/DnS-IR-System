package com.shenkar.ir.entities;

import java.util.*;

import com.shenkar.ir.model.ParsingService;

public class Index  {
	
	private HashMap<Term, Integer> index = new HashMap<>();
	private Document document;

	public Index() {}
	
	public Index(List<String> words) {
		Set terms = index.keySet();
		Collections.sort(words);
		System.out.println(words);
		ParsingService.transform(words);
		System.out.println(words);
		for (String word : words) {
			terms.add(word);
		}
		
		for (Term term : ParsingService.toTerms(words)) {
			if (index.containsKey(term)) {
				index.put(term, index.get(term));
			}
			else{
				index.put(term, 0);
			}
		}
		System.out.println(index);
	}
}
