package net.bohush.exercises.chapter38;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Exercise06 extends JApplet {
	private static final long serialVersionUID = 1L;
	private LoginDialog loginDialog = new LoginDialog();
	private JButton jbtLogin = new JButton("Enter Username and Paswsword");
	private JLabel jLabel = new JLabel("", JLabel.CENTER);

	public Exercise06() {
		setLayout(new BorderLayout());
		jbtLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginDialog.setVisible(true);
				if ((loginDialog.getUsername() != null)&&(loginDialog.getPassword() != null)) {
					jLabel.setText("Username: " + loginDialog.getUsername() + "; Password: " + loginDialog.getPassword());
				}
			}
		});
		add(jbtLogin, BorderLayout.NORTH);
		add(jLabel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		Exercise06 applet = new Exercise06();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TestColorDialog");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400, 320);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class LoginDialog extends JDialog {
		private static final long serialVersionUID = 1L;

		private String username = null;
		private String password = null;
		
		private JTextField jTextField1 = new JTextField(8);
		private JTextField jTextField2 = new JTextField(8);

		// Create two buttons
		private JButton jbtOK = new JButton("OK");
		private JButton jbtCancel = new JButton("Cancel");

		public LoginDialog() {
			this(null, true);
		}

		public LoginDialog(java.awt.Frame parent, boolean modal) {
			super(parent, modal);
			setTitle("Login");

			jTextField1.setText("");
			jTextField2.setText("");
			username = null;
			password = null;
			
			// Group two buttons OK and Cancel
			JPanel jpButtons = new JPanel();
			jpButtons.add(jbtOK);
			jpButtons.add(jbtCancel);

			JPanel jPanel = new JPanel(new GridLayout(2, 2, 5, 5));
			jPanel.add(new JLabel("Username:", JLabel.RIGHT));
			jPanel.add(jTextField1);
			jPanel.add(new JLabel("Password:", JLabel.RIGHT));
			jPanel.add(jTextField2);
			
			add(jpButtons, BorderLayout.SOUTH);
			add(jPanel, BorderLayout.CENTER);
			
			pack();

			jbtOK.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if((jTextField1.getText() != null) &&
						(!jTextField1.getText().equals("")) &&
						(jTextField2.getText() != null) &&
						(!jTextField2.getText().equals(""))) {
						username = jTextField1.getText();
						password = jTextField2.getText();
					} else {
						username = null;
						password = null;
					}
					setVisible(false);
				}
			});

			jbtCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					username = null;
					password = null;
					setVisible(false);
				}
			});

			setLocationRelativeTo(null);
		}

		public String getUsername() {
			return username;
		}
		
		public String getPassword() {
			return password;
		}
	}
}
