package net.bohush.exercises.chapter30;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


public class Exercise15 extends JApplet{
	private static final long serialVersionUID = 1L;
	private DynamicGrpah dynamicGrpah = new DynamicGrpah();
	private ArrayList<Vertex> vertexes = new ArrayList<>();
	private ArrayList<Edge> edges = new ArrayList<>();
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField5 = new JTextField();
	private JTextField jTextField6 = new JTextField();
	private JTextField jTextField7 = new JTextField();
	private List<Integer> path = null;
	
	public Exercise15() {
	
		setLayout(new BorderLayout());
		JPanel jPanel6 = new JPanel(new BorderLayout(5, 5));
		jPanel6.setBorder(new EmptyBorder(5, 5, 5, 5));
		jPanel6.add(dynamicGrpah, BorderLayout.CENTER);
		
		JPanel jPanel1 = new JPanel(new GridLayout(1, 3, 5, 5));
		JPanel jPanel2 = new JPanel(new GridLayout(4, 2, 5, 5));
		JPanel jPanel3 = new JPanel(new GridLayout(3, 2, 5, 5));
		JPanel jPanel4 = new JPanel(new GridLayout(3, 2, 5, 5));
		jPanel2.setBorder(new TitledBorder("Add a new vertex"));
		jPanel3.setBorder(new TitledBorder("Add a new ebge"));
		jPanel4.setBorder(new TitledBorder("Find a shortest path"));
		
		jPanel2.add(new JLabel("Vertex name: "));
		jPanel2.add(jTextField1);
		jPanel2.add(new JLabel("x-coordinate: "));
		jPanel2.add(jTextField2);
		jPanel2.add(new JLabel("y-coordinate: "));
		jPanel2.add(jTextField3);
		jPanel2.add(new JLabel(" "));
		JButton jButton2 = new JButton("Add Vertex");
		jPanel2.add(jButton2);
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = jTextField1.getText();
					int x = Integer.parseInt(jTextField2.getText());
					int y = Integer.parseInt(jTextField3.getText());
					vertexes.add(new Vertex(name, x, y));
					jTextField1.setText("");
					jTextField2.setText("");
					jTextField3.setText("");
					path = null;
					dynamicGrpah.repaint();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Wrong Number Format", "Error", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		
		jPanel3.add(new JLabel("Vertex u (index): "));
		jPanel3.add(jTextField4);
		jPanel3.add(new JLabel("Vertex v (index): "));
		jPanel3.add(jTextField5);
		jPanel3.add(new JLabel(" "));
		JButton jButton3 = new JButton("Add Edge");
		jPanel3.add(jButton3);
		
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int uIndex = Integer.parseInt(jTextField4.getText());
					int vIndex = Integer.parseInt(jTextField5.getText());
					Vertex u = vertexes.get(uIndex);
					Vertex v = vertexes.get(vIndex);
					edges.add(new Edge(u, v));
					jTextField4.setText("");
					jTextField5.setText("");
					path = null;
					dynamicGrpah.repaint();
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Wrong Number Format", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Wrong Index", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jPanel4.add(new JLabel("Starting vertex: "));
		jPanel4.add(jTextField6);
		jPanel4.add(new JLabel("Ending vertex: "));
		jPanel4.add(jTextField7);
		jPanel4.add(new JLabel(" "));
		JButton jButton4 = new JButton("Shortest Path");
		jPanel4.add(jButton4);
		
		jButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int from = Integer.parseInt(jTextField6.getText());
					int to = Integer.parseInt(jTextField7.getText());
					
					ArrayList<String> newVertices = new ArrayList<>();
					for (int i = 0; i < vertexes.size(); i++) {
						newVertices.add(vertexes.get(i).name);
					}
					
					ArrayList<AbstractGraph.Edge> newEdges = new ArrayList<>();
					for (int i = 0; i < edges.size(); i++) {
						int u = newVertices.indexOf(edges.get(i).u.name);
						int v = newVertices.indexOf(edges.get(i).v.name);
						newEdges.add(new AbstractGraph.Edge(u, v));
						newEdges.add(new AbstractGraph.Edge(v, u));
					}

					UnweightedGraph<String> graph = new UnweightedGraph<>(newEdges, newVertices);			
					try {
						path = graph.getPath(from, to);
						jTextField6.setText("");
						jTextField7.setText("");
						dynamicGrpah.repaint();					
					} catch (IndexOutOfBoundsException e2) {
						JOptionPane.showMessageDialog(null, "No Path", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Wrong Number Format", "Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		
		jPanel1.add(jPanel2);
		jPanel1.add(jPanel3);
		jPanel1.add(jPanel4);
		JPanel jPanel5 = new JPanel(new BorderLayout(5, 5));
		jPanel5.add(jPanel1, BorderLayout.CENTER);
		JButton jButton1 = new JButton("Start Over (Clear Graph)");
		jPanel5.add(jButton1, BorderLayout.SOUTH);
		jPanel6.add(jPanel5, BorderLayout.SOUTH);
		add(jPanel6);
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				vertexes.clear();
				edges.clear();
				jTextField1.setText("");
				jTextField2.setText("");
				jTextField3.setText("");
				jTextField4.setText("");
				jTextField5.setText("");
				jTextField6.setText("");
				jTextField7.setText("");
				dynamicGrpah.repaint();
			}
		});
	}

	
	
	static class Vertex {
		String name;
		int x;
		int y;
		
		public Vertex(String name, int x, int y) {
			this.name = name;
			this.x = x;
			this.y = y;
		}		
	}
	
	static class Edge {
		Vertex u;
		Vertex v;
		public Edge(Vertex u, Vertex v) {
			this.u = u;
			this.v = v;
		}		
	}
	
	public static void main(String[] args) {
		JFrame jFrame = new JFrame("Exercise15");
		JApplet jApplet = new Exercise15();
		jFrame.add(jApplet);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setMinimumSize(new Dimension(jFrame.getWidth(), jFrame.getHeight()));
		jFrame.setLocationRelativeTo(null);
		jFrame.setVisible(true);
	}
	
	class DynamicGrpah extends JPanel {
		private static final long serialVersionUID = 1L;
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(800, 300);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int radius = 10;
			g.setFont(new Font("SansSerif", Font.BOLD, 16));
			for (int i = 0; i < vertexes.size(); i++) {
				g.drawString(vertexes.get(i).name + "(" + i + ")", vertexes.get(i).x - radius, vertexes.get(i).y - radius);
				g.fillOval(vertexes.get(i).x - radius, vertexes.get(i).y - radius, radius * 2, radius * 2);
			}
			for (int i = 0; i < edges.size(); i++) {
				Vertex u = edges.get(i).u;
				Vertex v = edges.get(i).v;
				g.drawLine(u.x, u.y, v.x, v.y);
			}
			if(path != null) {
				g.setColor(Color.RED);
				for (int i = 0; i < path.size() - 1; i++) {
					Vertex u = vertexes.get(path.get(i));
					Vertex v = vertexes.get(path.get(i + 1));
					g.drawLine(u.x, u.y, v.x, v.y);					
				}
			}
		}
	}
	
	static class UnweightedGraph<V> extends AbstractGraph<V> {
		/** Construct an empty graph */
		public UnweightedGraph() {
		}

		/** Construct a graph from edges and vertices stored in arrays */
		public UnweightedGraph(int[][] edges, V[] vertices) {
			super(edges, vertices);
		}

		/** Construct a graph from edges and vertices stored in List */
		public UnweightedGraph(List<Edge> edges, List<V> vertices) {
			super(edges, vertices);
		}

		/** Construct a graph for integer vertices 0, 1, 2 and edge list */
		public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
			super(edges, numberOfVertices);
		}

		/** Construct a graph from integer vertices 0, 1, and edge array */
		public UnweightedGraph(int[][] edges, int numberOfVertices) {
			super(edges, numberOfVertices);
		}
	}

	static abstract class AbstractGraph<V> implements Graph<V> {
		protected List<V> vertices = new ArrayList<V>(); // Store vertices
		protected List<List<Integer>> neighbors = new ArrayList<List<Integer>>(); // Adjacency
																					// lists

		/** Construct an empty graph */
		protected AbstractGraph() {
		}

		/** Construct a graph from edges and vertices stored in arrays */
		protected AbstractGraph(int[][] edges, V[] vertices) {
			for (int i = 0; i < vertices.length; i++)
				this.vertices.add(vertices[i]);

			createAdjacencyLists(edges, vertices.length);
		}

		/** Construct a graph from edges and vertices stored in List */
		protected AbstractGraph(List<Edge> edges, List<V> vertices) {
			for (int i = 0; i < vertices.size(); i++)
				this.vertices.add(vertices.get(i));

			createAdjacencyLists(edges, vertices.size());
		}

		/** Construct a graph for integer vertices 0, 1, 2 and edge list */
		@SuppressWarnings("unchecked")
		protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
			for (int i = 0; i < numberOfVertices; i++)
				vertices.add((V) (new Integer(i))); // vertices is {0, 1, ...}

			createAdjacencyLists(edges, numberOfVertices);
		}

		/** Construct a graph from integer vertices 0, 1, and edge array */
		@SuppressWarnings("unchecked")
		protected AbstractGraph(int[][] edges, int numberOfVertices) {
			for (int i = 0; i < numberOfVertices; i++)
				vertices.add((V) (new Integer(i))); // vertices is {0, 1, ...}

			createAdjacencyLists(edges, numberOfVertices);
		}

		/** Create adjacency lists for each vertex */
		private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
			// Create a linked list
			for (int i = 0; i < numberOfVertices; i++) {
				neighbors.add(new ArrayList<Integer>());
			}

			for (int i = 0; i < edges.length; i++) {
				int u = edges[i][0];
				int v = edges[i][1];
				neighbors.get(u).add(v);
			}
		}

		/** Create adjacency lists for each vertex */
		private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
			// Create a linked list for each vertex
			for (int i = 0; i < numberOfVertices; i++) {
				neighbors.add(new ArrayList<Integer>());
			}

			for (Edge edge : edges) {
				neighbors.get(edge.u).add(edge.v);
			}
		}

		@Override
		/** Return the number of vertices in the graph */
		public int getSize() {
			return vertices.size();
		}

		@Override
		/** Return the vertices in the graph */
		public List<V> getVertices() {
			return vertices;
		}

		@Override
		/** Return the object for the specified vertex */
		public V getVertex(int index) {
			return vertices.get(index);
		}

		@Override
		/** Return the index for the specified vertex object */
		public int getIndex(V v) {
			return vertices.indexOf(v);
		}

		@Override
		/** Return the neighbors of the specified vertex */
		public List<Integer> getNeighbors(int index) {
			return neighbors.get(index);
		}

		@Override
		/** Return the degree for a specified vertex */
		public int getDegree(int v) {
			return neighbors.get(v).size();
		}

		@Override
		/** Print the edges */
		public void printEdges() {
			for (int u = 0; u < neighbors.size(); u++) {
				System.out.print(getVertex(u) + " (" + u + "): ");
				for (int j = 0; j < neighbors.get(u).size(); j++) {
					System.out.print("(" + u + ", " + neighbors.get(u).get(j)
							+ ") ");
				}
				System.out.println();
			}
		}

		@Override
		/** Clear graph */
		public void clear() {
			vertices.clear();
			neighbors.clear();
		}

		@Override
		/** Add a vertex to the graph */
		public void addVertex(V vertex) {
			vertices.add(vertex);
			neighbors.add(new ArrayList<Integer>());
		}

		@Override
		/** Add an edge to the graph */
		public void addEdge(int u, int v) {
			neighbors.get(u).add(v);
			neighbors.get(v).add(u);
		}

		/** Edge inner class inside the AbstractGraph class */
		public static class Edge {
			public int u; // Starting vertex of the edge
			public int v; // Ending vertex of the edge

			/** Construct an edge for (u, v) */
			public Edge(int u, int v) {
				this.u = u;
				this.v = v;
			}
			
			@Override
			public String toString() {
				return "(" + u + ", " + v + ")";
			}
		}

		
		@Override
		/** Obtain a DFS tree starting from vertex v */
		/** To be discussed in Section 27.6 */
		public Tree dfs(int v) {
			List<Integer> searchOrder = new ArrayList<Integer>();
			int[] parent = new int[vertices.size()];
			for (int i = 0; i < parent.length; i++)
				parent[i] = -1; // Initialize parent[i] to -1

			// Mark visited vertices
			boolean[] isVisited = new boolean[vertices.size()];

			// Recursively search
			dfs(v, parent, searchOrder, isVisited);

			// Return a search tree
			return new Tree(v, parent, searchOrder);
		}

		/** Recursive method for DFS search */
		private void dfs(int v, int[] parent, List<Integer> searchOrder,
				boolean[] isVisited) {
			// Store the visited vertex
			searchOrder.add(v);
			isVisited[v] = true; // Vertex v visited

			for (int i : neighbors.get(v)) {
				if (!isVisited[i]) {
					parent[i] = v; // The parent of vertex i is v
					dfs(i, parent, searchOrder, isVisited); // Recursive search
				}
			}
		}

		
		public List<Integer> getPath(int u, int v) {
			List<Integer> searchOrder = new ArrayList<Integer>();

			boolean[] isVisited = new boolean[vertices.size()];

			List<List<Integer>> list = new ArrayList<>();
			getACycle(u, v, searchOrder, isVisited, list);
			
			List<Integer> result = list.get(0);
			for (int i = 1; i < list.size(); i++) {
				if(list.get(i).size() < result.size()) {
					result = list.get(i);
				}
			}
			return result;
			
		}

		private void getACycle(int u, int v, List<Integer> searchOrder, boolean[] isVisited, List<List<Integer>> result) {
			searchOrder.add(u);
			if(v == u) {
				result.add(searchOrder);
			} else {
				isVisited[u] = true;
				for (int i : neighbors.get(u)) {
					if (!isVisited[i]) {
						boolean[] newIsVisited = java.util.Arrays.copyOf(isVisited, isVisited.length);
						@SuppressWarnings("unchecked")
						List<Integer> newSearchOrder = (List<Integer>) ((ArrayList<Integer>)searchOrder).clone();
						getACycle(i, v, newSearchOrder, newIsVisited, result);
					}
				}
			}
		}
		
		
		
		@Override
		/** Starting bfs search from vertex v */
		/** To be discussed in Section 27.7 */
		public Tree bfs(int v) {
			List<Integer> searchOrder = new ArrayList<Integer>();
			int[] parent = new int[vertices.size()];
			for (int i = 0; i < parent.length; i++)
				parent[i] = -1; // Initialize parent[i] to -1

			java.util.LinkedList<Integer> queue = new java.util.LinkedList<Integer>(); // list used as a queue
			boolean[] isVisited = new boolean[vertices.size()];
			queue.offer(v); // Enqueue v
			isVisited[v] = true; // Mark it visited

			while (!queue.isEmpty()) {
				int u = queue.poll(); // Dequeue to u
				searchOrder.add(u); // u searched
				for (int w : neighbors.get(u)) {
					if (!isVisited[w]) {
						queue.offer(w); // Enqueue w
						parent[w] = u; // The parent of w is u
						isVisited[w] = true; // Mark it visited
					}
				}
			}
			return new Tree(v, parent, searchOrder);
		}

		/** Tree inner class inside the AbstractGraph class */
		/** To be discussed in Section 27.5 */
		public class Tree {
			private int root; // The root of the tree
			private int[] parent; // Store the parent of each vertex
			private List<Integer> searchOrder; // Store the search order

			/** Construct a tree with root, parent, and searchOrder */
			public Tree(int root, int[] parent, List<Integer> searchOrder) {
				this.root = root;
				this.parent = parent;
				this.searchOrder = searchOrder;
			}

			/** Return the root of the tree */
			public int getRoot() {
				return root;
			}

			/** Return the parent of vertex v */
			public int getParent(int v) {
				return parent[v];
			}

			/** Return an array representing search order */
			public List<Integer> getSearchOrder() {
				return searchOrder;
			}

			/** Return number of vertices found */
			public int getNumberOfVerticesFound() {
				return searchOrder.size();
			}

			/** Return the path of vertices from a vertex to the root */
			public List<V> getPath(int index) {
				ArrayList<V> path = new ArrayList<V>();

				do {
					path.add(vertices.get(index));
					index = parent[index];
				} while (index != -1);

				return path;
			}

			/** Print a path from the root to vertex v */
			public void printPath(int index) {
				List<V> path = getPath(index);
				System.out.print("A path from " + vertices.get(root) + " to "
						+ vertices.get(index) + ": ");
				for (int i = path.size() - 1; i >= 0; i--)
					System.out.print(path.get(i) + " ");
			}

			/** Print the whole tree */
			public void printTree() {
				System.out.println("Root is: " + vertices.get(root));
				System.out.print("Edges: ");
				for (int i = 0; i < parent.length; i++) {
					if (parent[i] != -1) {
						// Display an edge
						System.out.print("(" + vertices.get(parent[i]) + ", "
								+ vertices.get(i) + ") ");
					}
				}
				System.out.println();
			}
		}
	}

	interface Graph<V> {
		/** Return the number of vertices in the graph */
		public int getSize();

		/** Return the vertices in the graph */
		public java.util.List<V> getVertices();

		/** Return the object for the specified vertex index */
		public V getVertex(int index);

		/** Return the index for the specified vertex object */
		public int getIndex(V v);

		/** Return the neighbors of vertex with the specified index */
		public java.util.List<Integer> getNeighbors(int index);

		/** Return the degree for a specified vertex */
		public int getDegree(int v);

		/** Print the edges */
		public void printEdges();

		/** Clear graph */
		public void clear();

		/** Add a vertex to the graph */
		public void addVertex(V vertex);

		/** Add an edge to the graph */
		public void addEdge(int u, int v);

		/** Obtain a depth-first search tree */
		public AbstractGraph<V>.Tree dfs(int v);

		/** Obtain a breadth-first search tree */
		public AbstractGraph<V>.Tree bfs(int v);
	}

}
