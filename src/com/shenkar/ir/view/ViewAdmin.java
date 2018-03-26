package com.shenkar.ir.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import org.hibernate.HibernateException;

import com.shenkar.ir.model.Dao;
import com.shenkar.ir.model.StorageService;

public class ViewAdmin implements Runnable, ActionListener {
	
	private static ViewAdmin instance = null;
	
	public static ViewAdmin getInstance() {
		if (null == instance)
			instance = new ViewAdmin();
		return instance;
	}
	
	private ViewAdmin() {}
	
	JFrame frame = null;
	
	JPanel content = null;
	
	JTextField docId = null;
	JButton disable = null;
	
	JTextField path = null;
	JButton batch = null;
	JButton process = null;

	public void init() {
		docId = new JTextField(20);
		path = new JTextField(20);
		
		disable = new JButton("disable");
		batch = new JButton("batch");
		process = new JButton("process");
		
		disable.addActionListener(this);
		batch.addActionListener(this);
		process.addActionListener(this);
		
		content = new JPanel();
		content.add(docId);
		content.add(disable);
		
		frame = new JFrame("Admin Panel:");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setContentPane(content);
		frame.pack();
		frame.add(path);
		frame.add(batch);
		frame.add(process);
		frame.setSize(frame.getWidth(), 150);
		frame.setResizable(false);
		frame.setVisible(false);
	}
	
	public void toggleVisibility() {
		frame.setVisible(!frame.isVisible());
	}
	
	@Override
	public void run() {
		init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "disable":
				try {
					Dao.getInstance().disableDocuments(Dao.getInstance().getDocument(Integer.parseInt(docId.getText())));
					docId.setText("");
				} catch (HibernateException | ClassNotFoundException e1) {
					JOptionPane.showConfirmDialog(this.frame, "Invalid Entry!", "Error", JOptionPane.CLOSED_OPTION);
					e1.printStackTrace();
				}
				break;
			case "batch":
				try {
					StorageService.addBatch(path.getText());
					path.setText("");
				} catch (HibernateException | ClassNotFoundException | IOException e1) {
					JOptionPane.showConfirmDialog(this.frame, "Invalid Entry!", "Error", JOptionPane.CLOSED_OPTION);
					e1.printStackTrace();
				}
				break;
			case "process":
				try {
					StorageService.runBatch();
				} catch (ClassNotFoundException | IOException e1) {
					JOptionPane.showConfirmDialog(this.frame, "Internal Error!", "Error", JOptionPane.CLOSED_OPTION);
					e1.printStackTrace();
				}
				break;
		}
	}
}
