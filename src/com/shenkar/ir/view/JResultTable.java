package com.shenkar.ir.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.BiConsumer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import com.shenkar.ir.entities.*;

/**
 * 
 * @author Daniel
 * Class representing the GUIs' JTable component as well as encapsulationg it
 * 
 * @see {@linkplain NewDialog} Inner class
 *
 */
public class JResultTable extends JPanel implements Runnable {
	private static final long serialVersionUID = 2L;
	
	//Static Variables
	private static JResultTable resultTable;
	
	//Instance Variables
	public JTable table;
	private JScrollPane JSPane;
	private DefaultTableModel TModel;
	
	private Map<Document, List<String>> dataMap = new HashMap<>();
	
	@SuppressWarnings("serial")
	public Action open = new AbstractAction() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JTable table = (JTable)e.getSource();
			int modelRow = Integer.valueOf( e.getActionCommand() );
			if (table.getSelectedColumn() == 3) {
				for (Document doc : dataMap.keySet()) {
					if (doc.getId() == Integer.valueOf(JResultTable.getResultTable().table.getValueAt(modelRow, 0).toString())) {
						doc.open.doClick();
						break;
					}
				}
			}
		}
	};
	public void setPreview(int row) {
		for (Document doc : dataMap.keySet()) {
			if (doc.getId() == Integer.valueOf(JResultTable.getResultTable().table.getValueAt(row, 0).toString())) {
				try {
					View.getInstance().setPreview(doc);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	/**
	 * Initializes & fetchs the singleton instance of JRateTable
	 * 
	 * @param					None
	 * @return	JRateTable		singleton instance of the class
	 */
	public static JResultTable getResultTable() {
		if (null == resultTable)
			resultTable = new JResultTable();
		return resultTable;
	}
	
	/**
	 * Implementation of Runnable interface, preparing the instances' Components for use.
	 * initially not required for the main apps' process, instead for the porpuse of testing.
	 * 
	 *  @param					None
	 *  @return					void
	 */
	@Override
	public void run() {
		getResultTable();
	}
	
	public void updateData(List<Link> links) {
		dataMap.clear();
		for (Link link : links) {
			if (!dataMap.containsKey(link.getDocument()))
				dataMap.put(link.getDocument(), View.getInstance().translate.get(link.getTerm().getTerm()));
			else
				dataMap.get(link.getDocument()).addAll(View.getInstance().translate.get(link.getTerm().getTerm()));
		}
		updateView();
	}

	/**
	 * Refreshing the JTable currencies from the data Map.
	 * 
	 * @param					None
	 * @return					void
	 */
	public void updateView() {
		int rowCount = TModel.getRowCount();
		
		//clearing previous data
		for (int i=0; i < rowCount; ++i) TModel.removeRow(0);
		
		//reinserting data from Map
		dataMap.forEach(new BiConsumer<Document, List<String>>() {

			@Override
			public void accept(Document document, List<String> words) {
				TModel.addRow(new Object[] {document.getId(), document, words, "Open"});
			}
			
		});
	}

	/**
	 * JRateTable Constructor
	 */
	private JResultTable() {
		this(new GridLayout(1, 1));
	}

	/**
	 * JRateTable Constructor
	 * 
	 * @param	LayoutManager	Defines JComonents layout strategy
	 */
	private JResultTable(LayoutManager layout) {
		super(layout);
		//Initializing JComponents
		init();
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		add(JSPane);
		
		this.updateView();
	}

	/**
	 * Initialization of instances' JComponent elements.
	 * 
	 * @param					None
	 * @return					void
	 */
	public void init() {
		//Initialization of JComponent elements
		TModel = new DefaultTableModel();
		TModel.addColumn("Doc#");
		TModel.addColumn("Document");
		TModel.addColumn("Terms");
		TModel.addColumn("Open");
		
		table = new JTable(TModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		
		JSPane = new JScrollPane(table);
	}

}
