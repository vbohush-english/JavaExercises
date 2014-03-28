package net.bohush.exercises.chapter28;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise06 extends JApplet {

	private static final long serialVersionUID = 1L;
	private LinearProbingPanel linearProbingPanel = new LinearProbingPanel();
	private JTextField jTextField2 = new JTextField(3);
	private JTextField jTextField1 = new JTextField("4", 3);
	private JTextField jTextField3 = new JTextField("0.75", 3);
	private MyHashMap<Integer, Integer> map = new MyHashMap<Integer, Integer>
			(Integer.parseInt(jTextField1.getText()), Float.parseFloat(jTextField3.getText()));
	
	public Exercise06() {
		JPanel jPanel1 = new JPanel();
		jPanel1.add(new JLabel("Table size: "));
		jPanel1.add(jTextField1);
		jPanel1.add(new JLabel("Load factor: "));
		jPanel1.add(jTextField3);
		JButton jButton5 = new JButton("New map");
		jPanel1.add(jButton5);
		
		
		jPanel1.add(new JLabel("                    Enter a value: "));
		jPanel1.add(jTextField2);
		JButton jButton1 = new JButton("Insert");
		jPanel1.add(jButton1);
		JButton jButton2 = new JButton("Delete");
		jPanel1.add(jButton2);
		JButton jButton3 = new JButton("Search");
		jPanel1.add(jButton3);
		JButton jButton4 = new JButton("Remove All");
		jPanel1.add(jButton4);
		add(jPanel1, BorderLayout.SOUTH);
		add(linearProbingPanel, BorderLayout.CENTER);
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jTextField2.getText());
					if(map.containsKey(value)) {
						JOptionPane.showMessageDialog(null, jTextField2.getText() + " is olready in the tree", "Warning", JOptionPane.WARNING_MESSAGE);
					} else {
						map.put(value, value);
						linearProbingPanel.repaint();
						jTextField2.setText("");
					}
					jTextField2.requestFocus();					
				} catch (NumberFormatException e2) {
					jTextField2.requestFocus();
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jTextField2.getText());
					if(map.containsKey(value)) {
						map.remove(value);
						linearProbingPanel.repaint();
						jTextField2.setText("");						
					} else {
						JOptionPane.showMessageDialog(null, jTextField2.getText() + " is not in the tree", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					jTextField2.requestFocus();					
				} catch (NumberFormatException e2) {
					jTextField2.requestFocus();
				}
			}
		});
		
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jTextField2.getText());
					if(map.containsKey(value)) {
						JOptionPane.showMessageDialog(null, jTextField2.getText() + " is in the tree", "Information", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, jTextField2.getText() + " is not in the tree", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					jTextField2.requestFocus();
				} catch (NumberFormatException e2) {
					jTextField2.requestFocus();
				}
			}
		});
		
		jButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				map.clear();
				linearProbingPanel.repaint();
			}
		});
		
		jButton5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					map = new MyHashMap<Integer, Integer>(Integer.parseInt(jTextField1.getText()), Float.parseFloat(jTextField3.getText()));		
					linearProbingPanel.repaint();
				} catch (NumberFormatException e2) {
					
				}
			}
		});
	}

	class LinearProbingPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("Monospaced", Font.PLAIN, 16));
			int size = 20;
			g.drawString("Table size = " + map.table.length + ". Number of keys = " + map.size() + ".", size, size);						
			g.drawString("Load factor = " + (1.0 * map.size()) / map.table.length + ". Load factor threshold = " + map.loadFactorThreshold + ".", size, size * 2);
			for (int i = 0; i < map.table.length; i++) {
				g.drawString("[" + i + "]", size, size * (i + 4));
				g.drawRect(size * 3, size * (i + 3) + 6, size * 2, size);
				if(map.table[i] != null) {
					g.drawString("" + map.table[i].getValue(), size * 3 + 2, size * (i + 4) + 2);
				}
			}
		}

	}
	

	static class MyHashMap<K, V> implements MyMap<K, V> {
		// Define the default hash table size. Must be a power of 2
		private static int DEFAULT_INITIAL_CAPACITY = 4;

		// Define the maximum hash table size. 1 << 30 is same as 2^30
		private static int MAXIMUM_CAPACITY = 1 << 30;

		// Current hash table capacity. Capacity is a power of 2
		private int capacity;

		// Define default load factor
		private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

		// Specify a load factor used in the hash table
		private float loadFactorThreshold;

		// The number of entries in the map
		private int size = 0;

		// Hash table is an array with each cell that is a linked list
		MyMap.Entry<K, V>[] table;

		/** Construct a map with the default capacity and load factor */
		public MyHashMap() {
			this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
		}

		/**
		 * Construct a map with the specified initial capacity and default load
		 * factor
		 */
		public MyHashMap(int initialCapacity) {
			this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
		}

		/**
		 * Construct a map with the specified initial capacity and load factor
		 */
		@SuppressWarnings("unchecked")
		public MyHashMap(int initialCapacity, float loadFactorThreshold) {
			if (initialCapacity > MAXIMUM_CAPACITY)
				this.capacity = MAXIMUM_CAPACITY;
			else
				this.capacity = trimToPowerOf2(initialCapacity);

			this.loadFactorThreshold = loadFactorThreshold;
			table = new MyMap.Entry[capacity];
		}

		@Override
		/** Remove all of the entries from this map */
		public void clear() {
			size = 0;
			removeEntries();
		}

		@Override
		/** Return true if the specified key is in the map */
		public boolean containsKey(K key) {
			int i = hash(key.hashCode());
			while (table[i] != null) {
				if (table[i].getKey().equals(key))
					return true;
				i = (i + 1) % capacity;
			}

			return false;
		}

		@Override
		/** Return true if this map contains the value */
		public boolean containsValue(V value) {
			for (int i = 0; i < capacity; i++) {
				if (table[i] != null) {
					if (table[i].getValue().equals(value))
						return true;
				}
			}

			return false;
		}

		@Override
		/** Return a set of entries in the map */
		public java.util.Set<MyMap.Entry<K, V>> entrySet() {
			java.util.Set<MyMap.Entry<K, V>> set = new java.util.HashSet<MyMap.Entry<K, V>>();

			for (int i = 0; i < capacity; i++) {
				if (table[i] != null) {
					set.add(table[i]);
				}
			}

			return set;
		}

		@Override
		/** Return the value that matches the specified key */
		public V get(K key) {
			int i = hash(key.hashCode());
			while (table[i] != null) {
				if (table[i].getKey().equals(key))
					return table[i].getValue();
				i = (i + 1) % capacity;
			}

			return null;
		}

		@Override
		/** Return true if this map contains no entries */
		public boolean isEmpty() {
			return size == 0;
		}

		@Override
		/** Return a set consisting of the keys in this map */
		public java.util.Set<K> keySet() {
			java.util.Set<K> set = new java.util.HashSet<K>();

			for (int i = 0; i < capacity; i++) {
				if (table[i] != null) {
					set.add(table[i].getKey());
				}
			}

			return set;
		}

		@Override
		/** Add an entry (key, value) into the map */
		public V put(K key, V value) {
			if (get(key) != null) { // The key is already in the map
				int i = hash(key.hashCode());
				while (table[i] != null) {
					if (table[i].getKey().equals(key)) {
						V oldValue = table[i].getValue();
						table[i].value = value;
						return oldValue;
					}
					i = (i + 1) % capacity;
				}
			}
			
			
			// Check load factor
			if (size >= capacity * loadFactorThreshold) {
				if (capacity == MAXIMUM_CAPACITY)
					throw new RuntimeException("Exceeding maximum capacity");

				rehash();
			}

			int i = hash(key.hashCode());

			while (table[i] != null) {
				i = (i + 1) % capacity;
			}
			table[i] = new MyMap.Entry<K, V>(key, value);
			size++;
			return value;
		}

		@Override
		/** Remove the entries for the specified key */
		public void remove(K key) {
			int i = hash(key.hashCode());
			while (table[i] != null) {
				if (table[i].getKey().equals(key)) {
					size--;
					table[i] = null;					
				}
				i = (i + 1) % capacity;
			}
		}

		@Override
		/** Return the number of entries in this map */
		public int size() {
			return size;
		}

		@Override
		/** Return a set consisting of the values in this map */
		public java.util.Set<V> values() {
			java.util.Set<V> set = new java.util.HashSet<V>();

			for (int i = 0; i < capacity; i++) {
				if (table[i] != null) {
					set.add(table[i].getValue());
				}
			}

			return set;
		}

		/** Hash function */
		private int hash(int hashCode) {
			return supplementalHash(hashCode) & (capacity - 1);
		}

		/** Ensure the hashing is evenly distributed */
		private static int supplementalHash(int h) {
			h ^= (h >>> 20) ^ (h >>> 12);
			return h ^ (h >>> 7) ^ (h >>> 4);
		}

		/** Return a power of 2 for initialCapacity */
		private int trimToPowerOf2(int initialCapacity) {
			int capacity = 1;
			while (capacity < initialCapacity) {
				capacity <<= 1;
			}

			return capacity;
		}

		/** Remove all entries from each bucket */
		private void removeEntries() {
			for (int i = 0; i < capacity; i++) {
				if (table[i] != null) {
					table[i] = null;
				}
			}
		}

		/** Rehash the map */
		@SuppressWarnings("unchecked")
		private void rehash() {
			java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
			capacity <<= 1; // Double capacity
			table = new MyMap.Entry[capacity];
			size = 0; // Reset size to 0

			for (Entry<K, V> entry : set) {
				put(entry.getKey(), entry.getValue()); // Store to new table
			}
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder("[");

			for (int i = 0; i < capacity; i++) {
				if (table[i] != null)
					builder.append(table[i]);
			}

			builder.append("]");
			return builder.toString();
		}
	}

	interface MyMap<K, V> {
		/** Remove all of the entries from this map */
		public void clear();

		/** Return true if the specified key is in the map */
		public boolean containsKey(K key);

		/** Return true if this map contains the specified value */
		public boolean containsValue(V value);

		/** Return a set of entries in the map */
		public java.util.Set<Entry<K, V>> entrySet();

		/** Return the first value that matches the specified key */
		public V get(K key);

		/** Return true if this map contains no entries */
		public boolean isEmpty();

		/** Return a set consisting of the keys in this map */
		public java.util.Set<K> keySet();

		/** Add an entry (key, value) into the map */
		public V put(K key, V value);

		/** Remove the entries for the specified key */
		public void remove(K key);

		/** Return the number of mappings in this map */
		public int size();

		/** Return a set consisting of the values in this map */
		public java.util.Set<V> values();

		/** Define inner class for Entry */
		public static class Entry<K, V> {
			K key;
			V value;

			public Entry(K key, V value) {
				this.key = key;
				this.value = value;
			}

			public K getKey() {
				return key;
			}

			public V getValue() {
				return value;
			}

			@Override
			public String toString() {
				return "[" + key + ", " + value + "]";
			}
		}
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise06");
		JApplet applet = new Exercise06();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}