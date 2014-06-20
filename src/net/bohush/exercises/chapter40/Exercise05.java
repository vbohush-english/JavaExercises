package net.bohush.exercises.chapter40;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.*;

public class Exercise05 extends JApplet {
	private static final long serialVersionUID = 1L;
	private ChartModel model = new ChartModel();

	public Exercise05() {
		double[] data = {200, 40, 50, 100, 40};
		String[] dataName = {"CS", "Math", "Chem", "Biol", "Phys"};
		model.setChartData(dataName, data);
		PieChartView pieChartView = new PieChartView();
		pieChartView.setModel(model);
		BarChartView barChartView = new BarChartView();
		barChartView.setModel(model);
		
		JPanel jPanel1 = new JPanel(new GridLayout(1, 2, 5, 5));
		jPanel1.setBorder(new EmptyBorder(10, 10, 10, 10));
		jPanel1.add(barChartView);
		jPanel1.add(pieChartView);
		jPanel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		        JFrame frame = new JFrame("Controller");
		        ChartController controller = new ChartController();
		        controller.setModel(model);
		        frame.add(controller);
		        frame.setSize(300, 300);
		        frame.setLocation(200, 200);
		        frame.setVisible(true);
			}
		});
		setLayout(new BorderLayout());
		add(jPanel1);
	}

	public static void main(String[] args) {
		Exercise05 applet = new Exercise05();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise05");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public class ChartModel {
		private double[] data;
		private String[] dataName;
		private Color[] dataColors;

		private ArrayList<ActionListener> actionListenerList;
		
		public double[] getData() {
			return data;
		}
		
		public String[] getDataName() {
			return dataName;
		}

		public Color[] getDataColors() {
			return dataColors;
		}
		
		public int getSize() {
			return data.length;
		}
		
		public void setChartData(String[] newDataName, double[] newData) {
			if(newDataName.length != newData.length) {
				throw new IllegalArgumentException();
			}
			data = new double[newData.length];
			dataName = new String[newData.length];
			if((dataColors == null)||(dataColors.length != newData.length)) {
				dataColors = new Color[newData.length];
				for (int i = 0; i < dataColors.length; i++) {
					dataColors[i] = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
				}
			}
			for (int i = 0; i < newData.length; i++) {
				data[i] = newData[i];
				dataName[i]= newDataName[i];
			}
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "filled"));
		}
		
		/** Register an action event listener */
		public synchronized void addActionListener(ActionListener l) {
			if (actionListenerList == null)
				actionListenerList = new ArrayList<ActionListener>();

			actionListenerList.add(l);
		}

		/** Remove an action event listener */
		public synchronized void removeActionListener(ActionListener l) {
			if (actionListenerList != null && actionListenerList.contains(l))
				actionListenerList.remove(l);
		}

		/** Fire TickEvent */
		@SuppressWarnings("unchecked")
		private void processEvent(ActionEvent e) {
			ArrayList<ActionListener> list;

			synchronized (this) {
				if (actionListenerList == null)
					return;
				list = (ArrayList<ActionListener>) (actionListenerList.clone());
			}

			for (int i = 0; i < list.size(); i++) {
				ActionListener listener = list.get(i);
				listener.actionPerformed(e);
			}
		}
	}

	
	public class PieChartView extends JPanel {
		private static final long serialVersionUID = 1L;
		private ChartModel model;

		public void setModel(ChartModel model) {
			this.model = model;
			if (model != null) {
				model.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						repaint();
					}
				});
			}
		}

		public ChartModel getModel() {
			return model;
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (model != null) {
				int width = getWidth();
				int height = getHeight();
				int minLen = (width < height ? width : height);
				int startAngle = 0;
				double sum = 0;
				for (int i = 0; i < model.getSize(); i++) {
					sum += model.getData()[i];
				}
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				for (int i = 0; i < model.getSize(); i++) {
					
					g.setColor(model.getDataColors()[i]);
					g.fillArc((width - minLen) / 2 + 10, (height - minLen) / 2 + 10, minLen - 20, minLen - 20, startAngle, (int)Math.ceil((360 / sum) * model.getData()[i]));
					double textAngle = Math.toRadians(360 - (startAngle + startAngle + (int)Math.ceil((360 / sum) * model.getData()[i])) / 2);
					startAngle = startAngle + (int)Math.ceil((360 / sum) * model.getData()[i]);
					g.setColor(Color.BLACK);

					int x2 = (int)((width / 2) + Math.cos(textAngle) * (minLen / 2 - 15));
					int y2 = (int)((height / 2) + Math.sin(textAngle) * (minLen / 2 - 15));						
					g.drawString(model.getDataName()[i], x2, y2);
				}
			}
		}
	}
	
	public class BarChartView extends JPanel {
		private static final long serialVersionUID = 1L;
		private ChartModel model;

		public void setModel(ChartModel model) {
			this.model = model;
			if (model != null) {
				model.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						repaint();
					}
				});
			}
		}

		public ChartModel getModel() {
			return model;
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if ((model != null)&&(model.getSize()>0)) {
				int width = getWidth();
				int height = getHeight();
				int step = width / model.getSize();
				int widthStep = step - 10;
				double heightStep = model.getData()[0];
				for (int i = 1; i < model.getSize(); i++) {
					if(model.getData()[i] > heightStep) {
						heightStep = model.getData()[i];
					}
				}
				heightStep = (height - 20.0) / heightStep;
				g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
				for (int i = 0; i < model.getSize(); i++) {
					g.setColor(model.getDataColors()[i]);
					g.fillRect(5 + step * i, height - (int)(heightStep * model.getData()[i]) - 5, widthStep, (int)(heightStep * model.getData()[i]) - 5);
					g.setColor(Color.BLACK);
					g.drawString(model.getDataName()[i], 5 + step * i, height - (int)(heightStep * model.getData()[i]) - 10);
				}
				g.drawLine(0, height - 10, getWidth(), height - 10);
			}
		}
	}
		
	public class MyTableModel extends DefaultTableModel {
		private static final long serialVersionUID = 1L;

		public MyTableModel() {
		}

		/** Construct a table model with specified data and columnNames */
		public MyTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		/** Override this method to return a class for the column */
		public Class<? extends Object> getColumnClass(int column) {
			if(column == 1) {
				return Double.class;
			} else {
				return String.class;
			}
		}

		/** Override this method to return true if cell is editable */
		public boolean isCellEditable(int row, int column) {
			Class<?> columnClass = getColumnClass(column);
			return columnClass != ImageIcon.class && columnClass != Date.class;
		}
	}
	
	public class ChartController extends JPanel {
		private static final long serialVersionUID = 1L;
		private ChartModel chartModel;
		private MyTableModel tableModel;
		private JTable jTable1;
		
		public void setModel(ChartModel newModel) {
			chartModel = newModel;
			if(chartModel != null) {
				removeAll();

				
				
				JPanel panel1 = new JPanel();
				panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
				panel1.setLayout(new BorderLayout(5, 5));
				
				String[] columnNames = {"DataName", "Data"};
				Object[][] rowData = new Object[chartModel.getSize()][2];
				for (int i = 0; i < rowData.length; i++) {
					rowData[i][0] = chartModel.getDataName()[i];
					rowData[i][1] = chartModel.getData()[i];
				}
				tableModel = new MyTableModel(rowData, columnNames);
				jTable1 = new JTable(tableModel);
				panel1.add(new JScrollPane(jTable1), BorderLayout.CENTER);
				
				setLayout(new BorderLayout());
				add(panel1, BorderLayout.CENTER);
				
				JPanel panel2 = new JPanel(new GridLayout(1, 3, 5, 5));
				panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
				JButton jButton1 = new JButton("Insert");
				JButton jButton2 = new JButton("Delete");
				JButton jButton3 = new JButton("Update");
				
				jButton1.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (jTable1.getSelectedRow() >= 0) {
							tableModel.insertRow(jTable1.getSelectedRow(), new java.util.Vector<String>());
						} else {
							tableModel.addRow(new java.util.Vector<String>());
						}
					}
				});
				
				jButton2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(jTable1.getSelectedRow() != -1) {
							tableModel.removeRow(jTable1.getSelectedRow());
						}	
					}
				});

				jButton3.addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							double[] data = new double[jTable1.getRowCount()];
							String[] dataName = new String[jTable1.getRowCount()];
							for (int i = 0; i < dataName.length; i++) {
								if((tableModel.getValueAt(i, 0) == null)||(tableModel.getValueAt(i, 1) == null)) {
									return;
								}
								data[i] = (double)(tableModel.getValueAt(i, 1));
								if(data[i] < 0) {
									throw new NumberFormatException();
								}
								dataName[i] = (String)(tableModel.getValueAt(i, 0));
							}
							model.setChartData(dataName, data);	
						} catch (NumberFormatException e2) {
						}	
					}
				});
				
				panel2.add(jButton1);
				panel2.add(jButton2);
				panel2.add(jButton3);
				add(panel2, BorderLayout.NORTH);
				revalidate();
			}
		}

		public ChartModel getModel() {
			return model;
		}
	}
}