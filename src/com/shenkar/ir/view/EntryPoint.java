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
import com.shenkar.ir.model.QueryService;
import com.shenkar.ir.model.StorageService;
import com.shenkar.ir.optimizations.*;

public class EntryPoint {
	
	public static void hello(String str) {
		System.out.println("hello " + str);
	}

	public static void main(String[] args) throws IOException, HibernateException, ClassNotFoundException {
		StorageService.addBatch("info.txt");
		StorageService.addBatch("info2.txt");
		StorageService.addBatch("info3.txt");
		StorageService.runBatch();
		QueryService.processQuery("derp marv");
		QueryService.optimizeQuery();
		for (Link link : Dao.getInstance().search(QueryService.queryWords)) {
			System.out.println(link);
		}
	}

}
