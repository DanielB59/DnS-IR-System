package com.shenkar.ir.controller;

import com.shenkar.ir.view.*;

public aspect Monitor {
	public Monitor() {
		System.out.println("constructor");
	}
	
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
