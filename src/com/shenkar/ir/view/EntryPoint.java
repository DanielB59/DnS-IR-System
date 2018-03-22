package com.shenkar.ir.view;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.hibernate.HibernateException;

import com.shenkar.ir.entities.*;
import com.shenkar.ir.model.Dao;
import com.shenkar.ir.model.ParsingService;
import com.shenkar.ir.optimizations.*;

public class EntryPoint {
	
	public static void hello(String str) {
		System.out.println("hello " + str);
	}

	public static void main(String[] args) throws IOException, HibernateException, ClassNotFoundException {
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
		
		ParsingService parser = new ParsingService();
		parser.readFile(new File("info.txt"));
		Index myindex = new Index(parser.words, new Document(3, "jhkjh", "ll", "me", "him"));
		myindex.toLinks();
		Dao.getInstance().insertLinks(myindex);
//		Dao.getInstance().insertTerms(parser.toTerms());
		
//		Document doc = new Document(1, "", "book", "myself", "derp!");
//		Term term = new Term("hello", 1, 10);
//		Link link = new Link(term, doc, 10);
//		
//		try {
//			Dao.getInstance();
//			Dao.getInstance().entityInsert(doc, term, link);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
