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
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Canvas;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class View2 extends JFrame{
	JPanel panelAdmin = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public View2() {
		
		setTitle("Ir system");
		setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 424, 319);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(49, 189, 337, 23);
		panel.add(formattedTextField);
		formattedTextField.setBackground(new Color(255, 255, 153));
		
		JButton btnAdmin = new JButton("Admin panel");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setContentPane(panelAdmin);
				repaint();
				panelAdmin.revalidate();
			}
		});
		btnAdmin.setBounds(10, 11, 110, 23);
		panel.add(btnAdmin);
		
		Button button = new Button("press for search");
		button.setBounds(164, 238, 96, 22);
		panel.add(button);
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
		
		JLabel lblIrSystem = new JLabel("IR System");
		lblIrSystem.setBounds(121, 83, 184, 60);
		panel.add(lblIrSystem);
		lblIrSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblIrSystem.setFont(new Font("David", Font.BOLD, 38));
		
		
		
		getContentPane().add(panelAdmin);
		 
		panelAdmin.add(new JButton("login"));
		
			panelAdmin.setLayout(null);

			JLabel userLabel = new JLabel("User");
			userLabel.setBounds(10, 10, 80, 25);
			panelAdmin.add(userLabel);

			JTextField userText = new JTextField(20);
			userText.setBounds(100, 10, 160, 25);
			panelAdmin.add(userText);

			JLabel passwordLabel = new JLabel("Password");
			passwordLabel.setBounds(10, 40, 80, 25);
			panelAdmin.add(passwordLabel);

			JPasswordField passwordText = new JPasswordField(20);
			passwordText.setBounds(100, 40, 160, 25);
			panelAdmin.add(passwordText);

			JButton loginButton = new JButton("login");
			loginButton.setBounds(10, 80, 80, 25);
			panelAdmin.add(loginButton);
		
	
		
		
	
	}
			
	public static void main(String[] args)    { 

		    JFrame frame = new View2();
		    frame.setTitle("View");
		    frame.setLocation(400, 50);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(600, 600);
		    frame.setAlwaysOnTop(true);
		    frame.setVisible(true); 
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
