package com.shenkar.ir.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import com.shenkar.ir.entities.*;
import com.shenkar.ir.model.*;
import com.shenkar.ir.optimizations.Algorithms;

public class View implements Runnable {
	
	private static View instance = null;
	
	public static View getInstance() {
		if (null == instance)
			instance = new View();
		return instance;
	}
	
	private View() {}
	
	private JFrame frame = null;
	private JPanel content = null;
	
	private JTextField input = null;
	private JButton search = null;
	private JButton admin = null;
	
	private JTextArea preview = null;
	
	public Map<String, List<String>> translate = new HashMap<String, List<String>>();
	
	public void setPreview(Document doc) throws IOException {
		preview.getHighlighter().removeAllHighlights();
		preview.setText("");
		FileInputStream fis = new FileInputStream(doc.getPath());
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		
		try {
			String input = null;
			while (null != (input = reader.readLine()))
				preview.append(input+"\n");
		} catch (EOFException e) {
			e.printStackTrace();
		}
		
		try {
			highlight(preview, translate.keySet());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void highlight(JTextArea area, Set<String> values) throws BadLocationException {
		//	TODO
		Pattern pattern = Pattern.compile("[\\w][)( \n\r\\/,.\"?!]+");
		Matcher matcher = pattern.matcher(preview.getDocument().getText(0, preview.getDocument().getLength()));
		Highlighter lighter = preview.getHighlighter();
		HighlightPainter painter = new DefaultHighlightPainter(Color.YELLOW);
		int pos = 0;
		while (matcher.find(pos)) {
			int start = matcher.start();
			int end = matcher.end();
			String key = preview.getDocument().getText(start, end);
			System.out.println(key);
			if (!ParsingService.stopList.contains(key.toLowerCase()) && translate.containsKey(Algorithms.soundex(Algorithms.stem(key))));
				lighter.addHighlight(start, end, painter);
			pos = end;
		}
	}
	
	private void init() {
		input = new JTextField(50);
		
		search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			
			@SuppressWarnings("serial")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					QueryService.processQuery(input.getText());
					translate.clear();
					for (String word : QueryService.queryWords) {
						String opti = Algorithms.soundex(Algorithms.stem(word));
						if (translate.containsKey(opti))
							translate.get(opti).add(word);
						else
							translate.put(opti, new ArrayList<String>(){{add(word);}});
					}
					QueryService.optimizeQuery();
					JResultTable.getResultTable().updateData(Dao.getInstance().search(QueryService.queryWords));
					View.getInstance().frame.repaint();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		admin = new JButton("admin");
		admin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewAdmin.getInstance().toggleVisibility();
			}
		});
		
		preview = new JTextArea(25, 40);
		preview.setAutoscrolls(true);
		preview.setEditable(false);
		
		content = new JPanel();
		content.add(input);
		content.add(search);
		content.add(admin);
		
		frame = new JFrame("IR DnS:");
		frame.setBounds(0, 0, 1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setContentPane(content);
		frame.add(JResultTable.getResultTable());
		frame.add(new JScrollPane(preview));
		frame.setResizable(false);
		frame.setVisible(true);
	}

	@Override
	public void run() {
		init();
		ViewAdmin.getInstance().run();
		ButtonColumn buttonColumn = new ButtonColumn(JResultTable.getResultTable().table, JResultTable.getResultTable().open, 3);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
}
