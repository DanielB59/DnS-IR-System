package com.shenkar.ir.view;

import java.io.IOException;
import org.hibernate.HibernateException;

import com.shenkar.ir.model.StorageService;

public class EntryPoint {
	
	public static void hello(String str) {
		System.out.println("hello " + str);
	}

	public static void main(String[] args) throws IOException, HibernateException, ClassNotFoundException {
		switch (args[0]) {
			case "batch":
				StorageService.addBatch(args[1]);
				break;
			case "process":
				StorageService.runBatch();
				break;
			case "search":
				/*StringBuilder builder = new StringBuilder();
				for (int i = 1; i < args.length; ++i){
					builder.append(args[i]);
					builder.append(" ");
				}
				QueryService.processQuery(builder.toString());
				QueryService.optimizeQuery();
				for (Link link : Dao.getInstance().search(QueryService.queryWords)) {
					System.out.println(link);
				}*/
				break;
		}
//		StorageService.addBatch("doyouwanttoknowasecret.txt");
//		StorageService.addBatch("isawherstandingthere.txt");
//		StorageService.addBatch("info3.txt");
//		StorageService.runBatch();
//		QueryService.processQuery("derp marv");
//		QueryService.optimizeQuery();
//		for (Link link : Dao.getInstance().search(QueryService.queryWords)) {
//			System.out.println(link);
//		}
	}

}
