package net.bohush.exercises.chapter33;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Exercise06c extends JApplet {
	private static final long serialVersionUID = 1L;
	private JTextField jtfName = new JTextField();
	private JTextField jtfStreet = new JTextField();
	private JTextField jtfCity = new JTextField();
	private JTextField jtfState = new JTextField();
	private JTextField jtfZip = new JTextField();
	private JLabel jlblName = new JLabel("Name");
	private JLabel jlblStreet = new JLabel("Street");
	private JLabel jlblCity = new JLabel("City");
	private JLabel jlblState = new JLabel("State");
	private JLabel jlblZip = new JLabel("Zip");
	private JButton jbtnAdd = new JButton("Add");
	private JButton jbtnFirst = new JButton("First");
	private JButton jbtnNext = new JButton("Next");
	private JButton jbtnPrevious = new JButton("Previous");
	private JButton jbtnLast = new JButton("Last");
	private Scanner fromServer;
	private PrintWriter toServer;
	
	public void readAddress() {
		String isAvaliable = fromServer.next();
		if(isAvaliable.equals("1")) {
			jtfName.setText(fromServer.next());
			jtfStreet.setText(fromServer.next());
			jtfCity.setText(fromServer.next());
			jtfState.setText(fromServer.next());
			jtfZip.setText(fromServer.next());			
		} else {
			jtfName.setText("");
			jtfStreet.setText("");
			jtfCity.setText("");
			jtfState.setText("");
			jtfZip.setText("");			
		}

	}
	
	public Exercise06c() {
		jbtnAdd.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				toServer.write("1 ");
				toServer.flush();
				Exercise06s.Address address = new Exercise06s.Address(jtfName.getText(), jtfStreet.getText(), jtfCity.getText(), jtfState.getText(), jtfZip.getText());
				address.send(toServer);
				toServer.write("5 ");
				toServer.flush();
				readAddress();
			}
		});
		jbtnFirst.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				toServer.write("2 ");
				toServer.flush();
				readAddress();
			}
		});
		jbtnNext.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				toServer.write("3 ");
				toServer.flush();
				readAddress();
			}
		});
		jbtnPrevious.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				toServer.write("4 ");
				toServer.flush();
				readAddress();
			}
		});
		jbtnLast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				toServer.write("5 ");
				toServer.flush();
				readAddress();
			}
		});
		
		jlblName.setHorizontalAlignment(SwingConstants.RIGHT);
		jlblStreet.setHorizontalAlignment(SwingConstants.RIGHT);
		jlblCity.setHorizontalAlignment(SwingConstants.RIGHT);
		jlblState.setHorizontalAlignment(SwingConstants.RIGHT);
		jlblZip.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JPanel jPanel1 = new JPanel(new GridLayout(3, 1, 5, 5));
		jPanel1.add(jlblName);
		jPanel1.add(jlblStreet);
		jPanel1.add(jlblCity);
		
		JPanel jPanel2 = new JPanel(new GridLayout(1, 4, 5, 5));
		jPanel2.add(jlblState);
		jPanel2.add(jtfState);
		jPanel2.add(jlblZip);
		jPanel2.add(jtfZip);
		
		JPanel jPanel3 = new JPanel(new GridLayout(1, 2, 5, 5));
		jPanel3.add(jtfCity);
		jPanel3.add(jPanel2);
		
		JPanel jPanel4 = new JPanel(new GridLayout(3, 1, 5, 5));
		jPanel4.add(jtfName);
		jPanel4.add(jtfStreet);
		jPanel4.add(jPanel3);
		
		JPanel jPanel5 = new JPanel(new BorderLayout(5,  5));
		jPanel5.add(jPanel1, BorderLayout.WEST);
		jPanel5.add(jPanel4, BorderLayout.CENTER);
		
		JPanel jPanel6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jPanel6.add(jbtnAdd);
		jPanel6.add(jbtnFirst);
		jPanel6.add(jbtnNext);
		jPanel6.add(jbtnPrevious);
		jPanel6.add(jbtnLast);
		
		JPanel jPanel7 = new JPanel(new BorderLayout(10, 10));
		jPanel7.setBorder(new EmptyBorder(10, 10, 10, 10));
		jPanel7.add(jPanel5, BorderLayout.CENTER);
		jPanel7.add(jPanel6, BorderLayout.SOUTH);
		add(jPanel7);
		
		Socket socket;
		try {
			socket = new Socket("localhost", 8000);
			fromServer = new Scanner(socket.getInputStream());
			toServer = new PrintWriter(socket.getOutputStream());
			toServer.write("2 ");
			toServer.flush();
			readAddress();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise06c");
		Exercise06c applet = new Exercise06c();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 180);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
