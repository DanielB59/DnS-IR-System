package com.shenkar.ir.entities;

import java.util.*;

import com.shenkar.ir.model.ParsingService;

public class Index  {
	
	public HashMap<Term, Integer> index = new HashMap<>();
	public Document document;

	public Index() {}
	
	public Index(List<String> words, Document doc) {
		document = doc;
		Collections.sort(words);
		ParsingService.transform(words);
		
		for (Term word : ParsingService.toTerms(words)) {
			if (index.containsKey(word)) {
				index.put(word, index.get(word)+1);
			}
			else{
				index.put(word, 1);
			}
		}
	}
	
	public List<Link> toLinks() {
		List<Link> links = new ArrayList<>();
		for (Term term : index.keySet()) {
			links.add(new Link(term, document, index.get(term)));
		}
		System.out.println(links);
		return links;
	}
}
