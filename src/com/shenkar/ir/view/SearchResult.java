package com.shenkar.ir.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToggleButton;
import java.awt.Panel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTextPane;

public class SearchResult {

	private JFrame frmResultPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchResult window = new SearchResult();
					window.frmResultPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SearchResult() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmResultPage = new JFrame();
		frmResultPage.setTitle("Result page");
		frmResultPage.setBounds(100, 100, 450, 300);
		frmResultPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResultPage.getContentPane().setLayout(null);
	}
}
