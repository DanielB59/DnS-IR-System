package com.shenkar.ir.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParsingService implements Service {
	
	private List<String> lines = new ArrayList<>();
	private List<String> words = new ArrayList<>();
	
	public static final List<String> stopList = new ArrayList<String>() {{
		add("a");	add("all");		add("and");		add("any");		add("at");
		add("be");	add("do");		add("for");		add("her");		add("how");
		add("if");	add("is");		add("many");	add("not");		add("see");
		add("the");	add("thier");	add("when");	add("why");
	}};
	
	public ParsingService() {
		super();
	}

	public void readFile(File document) throws IOException {
		FileInputStream fis = new FileInputStream(document);
		DataInputStream dis = new DataInputStream(fis);
		
		try {
			String str;
			while (0 < dis.available()) {
				str = dis.readUTF();
				System.out.println("str: " + str);
				lines.add(str);
			}
		} catch (EOFException e) {
			System.out.println("lines: " + lines);
			System.out.println("end of file reached");
		}
		
		for (String line : lines) {
			String[] arr = line.split(" \n\r\\/,.\"?!");
			System.out.println("here: " + arr);
		}
		dis.close();
	}
}
