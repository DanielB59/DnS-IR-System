package com.shenkar.ir.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.DropMode;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.TextField;
import java.awt.SystemColor;
import javax.swing.JLabel;

public class AdminWindow {

	private JFrame frmAdminPanel;
	private JPasswordField Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminWindow window = new AdminWindow();
					window.frmAdminPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdminPanel = new JFrame();
		frmAdminPanel.setTitle("Admin panel");
		frmAdminPanel.setBounds(100, 100, 450, 300);
		frmAdminPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdminPanel.getContentPane().setLayout(null);
		
		Password = new JPasswordField();
		Password.setToolTipText("");
		Password.setForeground(new Color(0, 0, 0));
		Password.setBackground(new Color(255, 255, 153));
		Password.setBounds(148, 128, 170, 20);
		frmAdminPanel.getContentPane().add(Password);
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setFont(new Font("David", Font.BOLD, 14));
		txtpnPassword.setText("password:");
		txtpnPassword.setBounds(63, 128, 75, 20);
		frmAdminPanel.getContentPane().add(txtpnPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(190, 185, 89, 23);
		frmAdminPanel.getContentPane().add(btnLogin);
		
		JTextArea UserName = new JTextArea();
		UserName.setForeground(new Color(0, 0, 0));
		UserName.setBackground(new Color(255, 255, 153));
		UserName.setBounds(148, 84, 170, 20);
		frmAdminPanel.getContentPane().add(UserName);
		
		JTextPane txtpnUserName = new JTextPane();
		txtpnUserName.setText("User name:");
		txtpnUserName.setFont(new Font("David", Font.BOLD, 14));
		txtpnUserName.setBounds(63, 84, 89, 20);
		frmAdminPanel.getContentPane().add(txtpnUserName);
		
		JLabel lblAdminPanel = new JLabel("Admin panel");
		lblAdminPanel.setFont(new Font("David", Font.BOLD, 32));
		lblAdminPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminPanel.setBounds(133, 11, 197, 42);
		frmAdminPanel.getContentPane().add(lblAdminPanel);
	}
}
