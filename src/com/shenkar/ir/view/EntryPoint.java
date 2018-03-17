package com.shenkar.ir.view;

import com.shenkar.ir.optimizations.*;

public class EntryPoint {
	
	public static void hello(String str) {
		System.out.println("hello " + str);
	}

	public static void main(String[] args) {
		System.out.println("hello world!");
		hello("daniel");
		
		System.out.println(Algorithms.soundex("Armenia"));
		System.out.println(Algorithms.soundex("Israel"));
		System.out.println(Algorithms.soundex("Izzreil"));
		System.out.println(Algorithms.stem("computering"));
		System.out.println(Algorithms.stem("measurement"));
		System.out.println(Algorithms.stem("Armenia"));
		System.out.println(Algorithms.stem("Israel"));
		System.out.println(Algorithms.stem("Izzreil"));
	}

}
