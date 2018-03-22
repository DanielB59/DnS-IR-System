package com.shenkar.ir.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.shenkar.ir.entities.Document;

import javafx.event.EventHandler;
import javafx.scene.control.Hyperlink;
import javafx.scene.shape.Path;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.awt.Panel;

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
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 295, 261);
		frame.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		ArrayList<Document> list = new ArrayList<Document>();
        Document d = new Document();
        list.add(d);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(10, 10, 275, 241);
		for (int i = 0 ; i < list.size() ; i++){
        	textArea.setText(d.toString());
        	textArea.setText("\n");  
        }	
		panel1.add(textArea);
		
		Panel panel2 = new Panel();
		panel2.setBounds(301, 0, 133, 261);
		frame.getContentPane().add(panel2);
		
		for (int i = 0 ; i < list.size() ; i++){
        	JButton b = new JButton();  
        	panel2.add(b);
        }
		
		
		
		
		
        	
		
	}
}


