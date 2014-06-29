package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Exercise14 extends JFrame{
	private JLabel jlblResult = new JLabel(" ");
	private JComboBox<String> jcb;
	private JList<String> jlst;
	private static final long serialVersionUID = 1L;

	public Exercise14() {
		JPanel panel1 = new JPanel();
		panel1.add(new JLabel("Choose Selection Mode "));
		jcb = new JComboBox<>(new String[]{"SINGLE_SELECTION", "SINGLE_INTERVAL_SELECTION", "MULTIPLE_INTERVAL_SELECTION"});
		panel1.add(jcb);
		jcb.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jcb.getSelectedIndex() == 0) {
					jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				} else if (jcb.getSelectedIndex() == 1) {
					jlst.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				} else if (jcb.getSelectedIndex() == 2) {
					jlst.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				}
			}
		});
		add(panel1, BorderLayout.NORTH);
		
		
		jlst = new JList<>(new String[]{"United States", "United Kingdom", "China", "Germany", "France", "Ukraine"});
		jlst.setVisibleRowCount(5);
		jlst.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String result = "";
				ArrayList<String> selectedList = (ArrayList<String>) jlst.getSelectedValuesList();
				for (int i = 0; i < selectedList.size(); i++) {
					result += selectedList.get(i) + " ";
				}
				jlblResult.setText(result);
			}
		});
		JScrollPane viewPainments = new JScrollPane(jlst);
		add(viewPainments, BorderLayout.CENTER);
		
		add(jlblResult, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		Exercise14 frame = new Exercise14();
		frame.setTitle("Exercise14");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
