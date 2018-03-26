package com.shenkar.ir.view;

import java.io.IOException;

import javax.swing.SwingUtilities;

import org.hibernate.HibernateException;

public class EntryPoint {
	
	public static void main(String[] args) throws IOException, HibernateException, ClassNotFoundException {
		SwingUtilities.invokeLater(View.getInstance());
	}

}
