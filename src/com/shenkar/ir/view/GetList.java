package com.shenkar.ir.view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.shenkar.ir.entities.Document;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.TextArea;

public class GetList {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetList window = new GetList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public GetList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(panel);
		
		ArrayList<Document> list = new ArrayList<Document>();
        Document d = new Document();
        list.add(d);
		
		TextArea textArea = new TextArea();
		textArea.setText(d.toString());
		panel.add(textArea);
		
		
		
	}
}
