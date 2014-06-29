package net.bohush.exercises.chapter30;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

public class Exercise20 extends JApplet {

	private static final long serialVersionUID = 1L;

	private GraphView graphView;
	private JTextField jTextField = new JTextField(10);
	private AbstractGraph<City>.Tree tree = null;
	public Exercise20(final UnweightedGraph<City> graph) {
		graphView = new GraphView(graph);
		setLayout(new BorderLayout(5, 5));
		add(graphView, BorderLayout.CENTER);
		JPanel jPanel1 = new JPanel();
		jPanel1.add(new JLabel("Starting City"));
		jPanel1.add(jTextField);
		JButton jButton1 = new JButton("Display DFS Tree");
		jPanel1.add(jButton1);
		JButton jButton2 = new JButton("Display BFS Tree");
		jPanel1.add(jButton2);
		add(jPanel1, BorderLayout.SOUTH);
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cityName = jTextField.getText();
				tree = null;
				List<City> list = graph.getVertices();
				for (int i = 0; i < list.size(); i++) {
					if(cityName.equals(list.get(i).name)) {
						tree = graph.dfs(i);						
						break;
					}
				}
				graphView.repaint();
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cityName = jTextField.getText();
				tree = null;
				List<City> list = graph.getVertices();
				for (int i = 0; i < list.size(); i++) {
					if(cityName.equals(list.get(i).name)) {
						tree = graph.bfs(i);
						break;
					}
				}
				graphView.repaint();
			}
		});
	}
	
	class GraphView extends javax.swing.JPanel {
		private static final long serialVersionUID = 1L;
		private Graph<? extends Displayable> graph;

		public GraphView(Graph<? extends Displayable> graph) {
			this.graph = graph;
		}

		@Override
		protected void paintComponent(java.awt.Graphics g) {
			super.paintComponent(g);

			if(graph == null) {
				return;
			}
			// Draw vertices
			java.util.List<? extends Displayable> vertices = graph.getVertices();
			for (int i = 0; i < graph.getSize(); i++) {
				int x = vertices.get(i).getX();
				int y = vertices.get(i).getY();
				String name = vertices.get(i).getName();

				g.fillOval(x - 8, y - 8, 16, 16); // Display a vertex
				g.drawString(name, x - 12, y - 12); // Display the name
			}

			// Draw edges for pair of vertices
			for (int i = 0; i < graph.getSize(); i++) {
				java.util.List<Integer> neighbors = graph.getNeighbors(i);
				int x1 = graph.getVertex(i).getX();
				int y1 = graph.getVertex(i).getY();
				for (int v : neighbors) {
					int x2 = graph.getVertex(v).getX();
					int y2 = graph.getVertex(v).getY();

					g.drawLine(x1, y1, x2, y2); // Draw an edge for (i, v)
				}
			}
			
			if(tree != null) {
				g.setColor(Color.RED);
				for (int i = 0; i < tree.parent.length; i++) {
					if (tree.parent[i] != -1) {
						int length = 20;
						int x0 = vertices.get(i).getX();
						int y0 = vertices.get(i).getY();
						int x1 = vertices.get(tree.parent[i]).getX();
						int y1 = vertices.get(tree.parent[i]).getY();
						double angle1 = Math.atan2(y1 - y0, x1 - x0);
						g.drawLine(x1, y1, x0, y0);
						
						double angle2 = Math.toRadians(Math.toDegrees(angle1) + 30);
						int x2 = (int)(x0 + Math.cos(angle2) * length);
						int y2 = (int)(y0 + Math.sin(angle2) * length);						
						g.drawLine(x0, y0, x2, y2);
						
						double angle3 = Math.toRadians(Math.toDegrees(angle1) - 30);
						int x3 = (int)(x0 + Math.cos(angle3) * length);
						int y3 = (int)(y0 + Math.sin(angle3) * length);						
						g.drawLine(x0, y0, x3, y3);
						
					}
				}
			}

		}
	}

	static class City implements Displayable {
		private int x, y;
		private String name;

		City(String name, int x, int y) {
			this.name = name;
			this.x = x;
			this.y = y;
		}

		@Override
		public int getX() {
			return x;
		}

		@Override
		public int getY() {
			return y;
		}

		@Override
		public String getName() {
			return name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = JOptionPane.showInputDialog(null, "Enter file name", "Input Dialog", JOptionPane.QUESTION_MESSAGE);
		File file = new File(fileName);
		if(!file.exists()) {
			JOptionPane.showMessageDialog(null, "File \"" + fileName + "\" not found!", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		Scanner inputGraph = new Scanner(file);
		int numberOfVertices = inputGraph.nextInt();
		inputGraph.nextLine();
		System.out.println("The number of vertices is " + numberOfVertices);
		ArrayList<City> vertices = new ArrayList<>();
		ArrayList<AbstractGraph.Edge> edges = new ArrayList<>();
		for (int i = 0; i < numberOfVertices; i++) {
			Scanner inputVertic = new Scanner(inputGraph.nextLine());
			String name = inputVertic.next();
			int x = inputVertic.nextInt();
			int y = inputVertic.nextInt();
			vertices.add(new City(name, x, y));
			while(inputVertic.hasNext()) {
				edges.add(new AbstractGraph.Edge(i, inputVertic.nextInt()));
			}
			inputVertic.close();
		}
		inputGraph.close();
		UnweightedGraph<City> graph = new UnweightedGraph<>(edges, vertices);

	
		JFrame frame = new JFrame("Exercise20");
		Exercise20 applet = new Exercise20(graph);
		frame.add(applet);
		applet.init();
		applet.start();

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	interface Displayable {
		public int getX(); // Get x-coordinate of the vertex

		public int getY(); // Get y-coordinate of the vertex

		public String getName(); // Get display name of the vertex
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

		@Override
		/** Starting bfs search from vertex v */
		/** To be discussed in Section 27.7 */
		public Tree bfs(int v) {
			List<Integer> searchOrder = new ArrayList<Integer>();
			int[] parent = new int[vertices.size()];
			for (int i = 0; i < parent.length; i++)
				parent[i] = -1; // Initialize parent[i] to -1

			java.util.LinkedList<Integer> queue = new java.util.LinkedList<Integer>(); // list
																						// used
																						// as
																						// a
																						// queue
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
