package com.shenkar.ir.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;

public class View extends JFrame{
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public View() {
		setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		Button button = new Button("press for search");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		button.setActionCommand("search_button\r\n");
		button.setBackground(new Color(255, 204, 51));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(159, 240, 141, 22);
		getContentPane().add(button);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBackground(new Color(255, 255, 153));
		formattedTextField.setBounds(29, 151, 375, 29);
		getContentPane().add(formattedTextField);
		
		JButton btnLogin = new JButton("Sgin out");
		buttonGroup.add(btnLogin);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogin.setBounds(245, 11, 86, 23);
		getContentPane().add(btnLogin);
		
		JButton button_1 = new JButton("Login");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		buttonGroup.add(button_1);
		button_1.setBounds(338, 11, 86, 23);
		getContentPane().add(button_1);
	}
			
	public static void main(String[] args)    { 

		    JFrame frame = new View();
		    frame.setTitle("View");
		    frame.setLocation(400, 50);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(600, 600);
		    frame.setAlwaysOnTop(true);
		    frame.setVisible(true); 
	}
}
