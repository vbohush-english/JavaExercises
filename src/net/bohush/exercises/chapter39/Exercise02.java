package net.bohush.exercises.chapter39;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class Exercise02 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JButton jbtController = new JButton("Show Controller");
	private JButton jbtView = new JButton("Show View");
	private CircleModel model = new CircleModel();

	public Exercise02() {
		setLayout(new FlowLayout());
		add(jbtController);
		add(jbtView);

		jbtController.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Controller");
				CircleController controller = new CircleController();
				controller.setModel(model);
				frame.add(controller);
				frame.setSize(200, 150);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});

		jbtView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("View");
				CircleView view = new CircleView();
				view.setModel(model);
				frame.add(view);
				frame.setSize(500, 200);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	public class CircleController extends JPanel {
		private static final long serialVersionUID = 1L;
		private CircleModel model;
		private JTextField jtfRadius = new JTextField();
		private JComboBox<Boolean> jcboFilled = new JComboBox<>(new Boolean[] {new Boolean(false), new Boolean(true) });
		JPanel jPanel3 = new JPanel(new BorderLayout());
		
		/** Creates new form CircleController */
		public CircleController() {
			// Panel to group labels
			JPanel panel1 = new JPanel();
			panel1.setLayout(new GridLayout(3, 1, 5, 5));
			panel1.add(new JLabel("Radius"));
			panel1.add(new JLabel("Filled"));
			panel1.add(new JLabel("Color"));

			// Panel to group text field, combo box, and another panel
			JPanel panel2 = new JPanel();
			panel2.setLayout(new GridLayout(3, 1, 5, 5));
			panel2.add(jtfRadius);
			panel2.add(jcboFilled);

			JButton jButton = new JButton("...");
			jPanel3.add(jButton, BorderLayout.EAST);
			jButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					Color selectedColor = JColorChooser.showDialog(null, "Pick a Color", Color.BLACK);
					if(selectedColor != null) {
						if (model != null) {
							jPanel3.setBackground(selectedColor);
							model.setColor(selectedColor);
						}
					}
				}
			});
			panel2.add(jPanel3);

			setLayout(new BorderLayout());
			add(panel1, BorderLayout.WEST);
			add(panel2, BorderLayout.CENTER);

			// Register listeners
			jtfRadius.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (model != null) // Set radius in the model
						model.setRadius(Double.parseDouble(jtfRadius.getText()));
				}
			});
			jcboFilled.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (model != null) // Set filled property value in the model
						model.setFilled(((Boolean) jcboFilled.getSelectedItem()).booleanValue());
				}
			});
		}

		public void setModel(CircleModel newModel) {
			model = newModel;
		}

		public CircleModel getModel() {
			return model;
		}
	}

	public static void main(String[] args) {
		Exercise02 applet = new Exercise02();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise02");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		frame.setSize(400, 320);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public class CircleView extends javax.swing.JPanel {
		private static final long serialVersionUID = 1L;
		private CircleModel model;

		/** Set a model */
		public void setModel(CircleModel newModel) {
			model = newModel;

			if (model != null)
				// Register the view as listener for the model
				model.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						repaint();
					}
				});
		}

		public CircleModel getModel() {
			return model;
		}

		@Override
		protected void paintComponent(Graphics g) {
			if (model != null) {
				super.paintComponent(g);
				g.setColor(model.getColor());

				int xCenter = getWidth() / 2;
				int yCenter = getHeight() / 2;
				int radius = (int) model.getRadius();

				if (model.isFilled()) {
					g.fillOval(xCenter - radius, yCenter - radius, 2 * radius,
							2 * radius);
				} else {
					g.drawOval(xCenter - radius, yCenter - radius, 2 * radius,
							2 * radius);
				}
			}
		}
	}

	public class CircleModel {
		/** Property radius. */
		private double radius = 20;

		/** Property filled. */
		private boolean filled;

		/** Property color. */
		private java.awt.Color color;

		/** Utility field used by event firing mechanism. */
		private ArrayList<ActionListener> actionListenerList;

		public double getRadius() {
			return radius;
		}

		public void setRadius(double radius) {
			this.radius = radius;

			// Notify the listener for the change on radius
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
					"radius"));
		}

		public boolean isFilled() {
			return filled;
		}

		public void setFilled(boolean filled) {
			this.filled = filled;

			// Notify the listener for the change on filled
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
					"filled"));
		}

		public java.awt.Color getColor() {
			return color;
		}

		public void setColor(java.awt.Color color) {
			this.color = color;

			// Notify the listener for the change on color
			processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
					"color"));
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
}