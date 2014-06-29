package net.bohush.exercises.chapter36;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exercise03 extends JFrame {
	private static final long serialVersionUID = 1L;
	private Hurricane hurricane = new Hurricane();
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	
	public Exercise03() {
		setLayout(new BorderLayout());
		JPanel jPanel1 = new JPanel(new BorderLayout());
		JLabel jLabel1 = new JLabel("Enter Hurricane Category: ");
		jPanel1.add(jLabel1, BorderLayout.WEST);
		jTextField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					jTextField2.setText("Hurricane Category " + Integer.parseInt(jTextField1.getText()));
					hurricane.setCategory(Integer.parseInt(jTextField1.getText()));	
				} catch (NumberFormatException e2) {
				}
			}
		});
		jPanel1.add(jTextField1, BorderLayout.CENTER);
		add(jPanel1, BorderLayout.NORTH);
		jTextField2.setHorizontalAlignment(JTextField.CENTER);
		jTextField2.setEditable(false);
		add(jTextField2, BorderLayout.CENTER);
		hurricane.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jTextField2.setText(jTextField2.getText() + ". Warning!!!");
			}
		});
	}
	
	public static void main(String[] args) {
		Exercise03 frame = new Exercise03();
		frame.setSize(300, 200);
		frame.setTitle("Exercise03");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	class Hurricane {
		private String name;
		private int category;
		
		private ArrayList<ActionListener> actionListenerList; 
		
		public Hurricane() {
			this("", 0);
		}
		
		public Hurricane(String name, int category) {
			this.name = name;
			this.category = category;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getCategory() {
			return category;
		}
		public void setCategory(int category) {
			this.category = category;
			if(category > 1) {
				processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
			}
		}
		
		public synchronized void addActionListener(ActionListener listener) {
			if (actionListenerList == null) {
				actionListenerList = new ArrayList<ActionListener>();
			}

			if (!actionListenerList.contains(listener)) {
				actionListenerList.add(listener);
			}
		}
		
		public synchronized void removeActionListener(ActionListener listener) {
			if ((actionListenerList != null) && (actionListenerList.contains(listener))) {
				actionListenerList.remove(listener);
			}
		}
		
		@SuppressWarnings("unchecked")
		private void processEvent(ActionEvent e) {
			ArrayList<ActionListener> list;

			synchronized (this) {
				if (actionListenerList == null) {
					return;
				}				
				list = (ArrayList<ActionListener>) actionListenerList.clone();
			}
			for (int i = 0; i < list.size(); i++) {
				ActionListener listener = (ActionListener) list.get(i);
				listener.actionPerformed(e);
			}
		}
		
	}
}
