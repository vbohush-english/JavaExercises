package net.bohush.exercises.chapter19;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class Exercise09 extends JFrame {
	private static final long serialVersionUID = 1L;
	// Specify the size of five string fields in the record
	final static int NAME_SIZE = 32;
	final static int STREET_SIZE = 32;
	final static int CITY_SIZE = 20;
	final static int STATE_SIZE = 2;
	final static int ZIP_SIZE = 5;
	final static int RECORD_SIZE = (NAME_SIZE + STREET_SIZE + CITY_SIZE
			+ STATE_SIZE + ZIP_SIZE);
	// Access address.dat using RandomAccessFile
	private RandomAccessFile raf;
	// Text fields
	private JTextField jtfName = new JTextField(NAME_SIZE);
	private JTextField jtfStreet = new JTextField(STREET_SIZE);
	private JTextField jtfCity = new JTextField(CITY_SIZE);
	private JTextField jtfState = new JTextField(STATE_SIZE);
	private JTextField jtfZip = new JTextField(ZIP_SIZE);
	// Buttons
	private JButton jbtAdd = new JButton("Add");
	private JButton jbtFirst = new JButton("First");
	private JButton jbtNext = new JButton("Next");
	private JButton jbtPrevious = new JButton("Previous");
	private JButton jbtLast = new JButton("Last");
	private JButton jbtUpdate = new JButton("Update");

	public Exercise09() {
		// Open or create a random access file
		try {
			raf = new RandomAccessFile("tmp/address.dat", "rw");
		} catch (IOException ex) {
			System.out.print("Error: " + ex);
			System.exit(0);
		}
		// Panel p1 for holding labels Name, Street, and City
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 1));
		p1.add(new JLabel("Name"));
		p1.add(new JLabel("Street"));
		p1.add(new JLabel("City"));
		// Panel jpState for holding state
		JPanel jpState = new JPanel();
		jpState.setLayout(new BorderLayout());
		jpState.add(new JLabel("State"), BorderLayout.WEST);
		jpState.add(jtfState, BorderLayout.CENTER);
		// Panel jpZip for holding zip
		JPanel jpZip = new JPanel();
		jpZip.setLayout(new BorderLayout());
		jpZip.add(new JLabel("Zip"), BorderLayout.WEST);
		jpZip.add(jtfZip, BorderLayout.CENTER);
		// Panel p2 for holding jpState and jpZip
		JPanel p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		p2.add(jpState, BorderLayout.WEST);
		p2.add(jpZip, BorderLayout.CENTER);
		// Panel p3 for holding jtfCity and p2
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(jtfCity, BorderLayout.CENTER);
		p3.add(p2, BorderLayout.EAST);
		// Panel p4 for holding jtfName, jtfStreet, and p3
		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(3, 1));
		p4.add(jtfName);
		p4.add(jtfStreet);
		p4.add(p3);
		// Place p1 and p4 into jpAddress
		JPanel jpAddress = new JPanel(new BorderLayout());
		jpAddress.add(p1, BorderLayout.WEST);
		jpAddress.add(p4, BorderLayout.CENTER);
		// Set the panel with line border
		jpAddress.setBorder(new BevelBorder(BevelBorder.RAISED));
		// Add buttons to a panel
		JPanel jpButton = new JPanel();
		jpButton.add(jbtAdd);
		jpButton.add(jbtFirst);
		jpButton.add(jbtNext);
		jpButton.add(jbtPrevious);
		jpButton.add(jbtLast);
		jpButton.add(jbtUpdate);
		// Add jpAddress and jpButton to the frame
		add(jpAddress, BorderLayout.CENTER);
		add(jpButton, BorderLayout.SOUTH);
		jbtAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeAddress();
			}
		});
		jbtFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (raf.length() > 0)
						readAddress(0);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		jbtNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long currentPosition = raf.getFilePointer();
					if (currentPosition < raf.length())
						readAddress(currentPosition);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		jbtPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long currentPosition = raf.getFilePointer();
					if (currentPosition - 2 * RECORD_SIZE > 0)
						// Why 2 * 2 * RECORD_SIZE? See the follow-up remarks
						readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
					else
						readAddress(0);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		jbtLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					long lastPosition = raf.length();
					if (lastPosition > 0)
						// Why 2 * RECORD_SIZE? See the follow-up remarks
						readAddress(lastPosition - 2 * RECORD_SIZE);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		jbtUpdate.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					long currentPosition = raf.getFilePointer();
					if (currentPosition > 0)
						updateAddress(currentPosition - 2 * RECORD_SIZE);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		// Display the first record if exists
		try {
			if (raf.length() > 0)
				readAddress(0);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/** Write a record at the end of the file */
	public void writeAddress() {
		try {
			raf.seek(raf.length());
			FixedLengthStringIO.writeFixedLengthString(jtfName.getText(), NAME_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(jtfStreet.getText(), STREET_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(jtfCity.getText(), CITY_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(jtfState.getText(), STATE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(jtfZip.getText(), ZIP_SIZE, raf);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	
	public void updateAddress(long position) {
		try {
			raf.seek(position);
			FixedLengthStringIO.writeFixedLengthString(jtfName.getText(), NAME_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(jtfStreet.getText(), STREET_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(jtfCity.getText(), CITY_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(jtfState.getText(), STATE_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString(jtfZip.getText(), ZIP_SIZE, raf);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/** Read a record at the specified position */
	public void readAddress(long position) throws IOException {
		raf.seek(position);
		String name = FixedLengthStringIO.readFixedLengthString(NAME_SIZE, raf);
		String street = FixedLengthStringIO.readFixedLengthString(STREET_SIZE, raf);
		String city = FixedLengthStringIO.readFixedLengthString(CITY_SIZE, raf);
		String state = FixedLengthStringIO.readFixedLengthString(STATE_SIZE, raf);
		String zip = FixedLengthStringIO.readFixedLengthString(ZIP_SIZE, raf);
		jtfName.setText(name);
		jtfStreet.setText(street);
		jtfCity.setText(city);
		jtfState.setText(state);
		jtfZip.setText(zip);
	}

	public static void main(String[] args) {
		Exercise09 frame = new Exercise09();
		frame.pack();
		frame.setTitle("AddressBook");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static class FixedLengthStringIO {
		/** Read fixed number of characters from a DataInput stream */
		public static String readFixedLengthString(int size, DataInput in)
				throws IOException {
			// Declare an array of characters
			char[] chars = new char[size];
			// Read fixed number of characters to the array
			for (int i = 0; i < size; i++)
				chars[i] = in.readChar();
			return new String(chars);
		}

		/** Write fixed number of characters to a DataOutput stream */
		public static void writeFixedLengthString(String s, int size,
				DataOutput out) throws IOException {
			char[] chars = new char[size];
			// Fill an array of characters from the string
			s.getChars(0, Math.min(s.length(), size), chars, 0);
			// Fill in blank characters in the rest of the array
			for (int i = Math.min(s.length(), size); i < chars.length; i++)
				chars[i] = ' ';
			// Create and write a new string padded with blank characters
			out.writeChars(new String(chars));
		}
	}
}
