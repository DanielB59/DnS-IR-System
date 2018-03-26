package com.shenkar.ir.model;

import java.io.*;
import org.hibernate.HibernateException;

import com.shenkar.ir.entities.*;

public class StorageService {
	
	public static void addBatch(String path) throws IOException, HibernateException, ClassNotFoundException {
		Dao.getInstance().insertEntity(new Batch(path));
	}
	
	public static void runBatch() throws ClassNotFoundException, IOException {
		ParsingService parser = new ParsingService();
		Index batchIndex = null;
		for (Batch batch : Dao.getInstance().getAllBatches()) {
			File file = new File(batch.getPath());
			parser.readFile(file);
			batchIndex = new Index(parser.words, new Document(0, file.getPath(), file.getName(), "auther", "text", true));
			Dao.getInstance().insertLinks(batchIndex);
		}
	}
}
