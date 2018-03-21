package com.shenkar.ir.controller;

import com.shenkar.ir.model.ParsingService;
import com.shenkar.ir.view.*;
import java.util.*;

public aspect Monitor {
	public Monitor() {
		System.out.println("constructor");
	}
	
	pointcut preAdd(ParsingService service, String str) : target(service) && call(boolean ParsingService.words.add(String)) && args(str);
	
	before(ParsingService service, String str) : preAdd(service, str) {
		System.out.println("before adding my word: " + str);
	}
	
//	pointcut preAdd2(Set words, String str) : target(words) && call(boolean add(String)) && args(str) && within(ParsingService);
//	
//	before(Set words, String str) : preAdd2(words, str) {
//		System.out.println("before adding my word: " + str);
//	}
	
	/*
	 * *	- single wild card.
	 * ..	- multi wild card.
	 */
	
	pointcut pre(String str) : execution(void EntryPoint.hello(String)) && args(str);
	
	before(String str) : pre(str) {
		System.out.println("arg before: " + str);
	}
	
	after(String str) : pre(str) {
		System.out.println("arg after: " + str);
	}
	
	void around(String str) :
		call (void EntryPoint.hello(String)) && args(str) {
		proceed(str+" around");
		return;
	}
}
