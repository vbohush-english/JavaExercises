package net.bohush.exercises.chapter39;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.*;

public class Exercise01 extends JApplet {
	private static final long serialVersionUID = 1L;
	private ChartModel model = new ChartModel();

	public Exercise01() {
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
		        frame.setSize(200, 200);
		        frame.setLocation(200, 200);
		        frame.setVisible(true);
			}
		});
		setLayout(new BorderLayout());
		add(jPanel1);
	}

	public static void main(String[] args) {
		Exercise01 applet = new Exercise01();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise01");
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
			if (model != null) {
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
		
	
	public class ChartController extends JPanel {
		private static final long serialVersionUID = 1L;
		private ChartModel chartModel;
		private JTextField[] jtfData;
		private JTextField[] jtfDataName;

		public void setModel(ChartModel newModel) {
			chartModel = newModel;
			if(chartModel != null) {
				removeAll();
				jtfData = new JTextField[chartModel.getSize()];
				jtfDataName = new JTextField[chartModel.getSize()];
			
				JPanel panel1 = new JPanel();
				panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
				panel1.setLayout(new GridLayout(chartModel.getSize(), 2, 5, 5));
	
				KeyAdapter keyAdapter = new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						try {
							double[] data = new double[jtfData.length];
							String[] dataName = new String[jtfData.length];
							for (int i = 0; i < dataName.length; i++) {
								data[i] = Double.parseDouble(jtfData[i].getText());
								if(data[i] < 0) {
									throw new NumberFormatException();
								}
								dataName[i] = jtfDataName[i].getText();
							}
							model.setChartData(dataName, data);	
						} catch (NumberFormatException e2) {
						}						
					}
				};
				
				for (int i = 0; i < chartModel.getSize(); i++) {
					jtfData[i] = new JTextField(chartModel.getData()[i] + "");
					jtfDataName[i] = new JTextField(chartModel.getDataName()[i]);
					panel1.add(jtfData[i]);
					panel1.add(jtfDataName[i]);
					jtfData[i].addKeyListener(keyAdapter);
					jtfDataName[i].addKeyListener(keyAdapter);
				}
				setLayout(new BorderLayout());
				add(panel1, BorderLayout.CENTER);
				revalidate();
			}
		}

		public ChartModel getModel() {
			return model;
		}
	}
}