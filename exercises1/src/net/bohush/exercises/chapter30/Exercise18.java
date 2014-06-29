package net.bohush.exercises.chapter30;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise18 extends JApplet {
	private static final long serialVersionUID = 1L;
	private KnightTourModel model = new KnightTourModel();
	private PaintKnightTour paintKnightTour = new PaintKnightTour();
	private JTextField jtfRow = new JTextField(2);
	private JTextField jtfColumn = new JTextField(2);
	private JButton jbtSearch = new JButton("Search");

	public Exercise18() {
		JPanel panel = new JPanel();
		panel.add(new JLabel("Specify a starting position, row: "));
		panel.add(jtfRow);
		panel.add(new JLabel("column: "));
		panel.add(jtfColumn);
		panel.add(jbtSearch);
		add(paintKnightTour, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

		jbtSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int position = Integer.parseInt(jtfRow.getText()) * 8
						+ Integer.parseInt(jtfColumn.getText());
				paintKnightTour.displayPath(model.getHamiltonianPath(position));
			}
		});
	}

	/** A panel to paint the chessboard and the knight tour */
	private static class PaintKnightTour extends JPanel {
		private static final long serialVersionUID = 1L;
		private List<Integer> path; // A Knight tour path

		public PaintKnightTour() {
			setBorder(BorderFactory.createLineBorder(Color.black, 1));
		}

		public void displayPath(List<Integer> path) {
			this.path = path;
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			// Display horizontal lines
			for (int i = 0; i < 8; i++)
				g.drawLine(0, i * getHeight() / 8, getWidth(), i * getHeight()
						/ 8);

			// Display vertical lines
			for (int i = 0; i < 8; i++)
				g.drawLine(i * getWidth() / 8, 0, (int) i * getWidth() / 8,
						getHeight());

			if (path == null)
				return; // No path to be displayed yet

			for (int i = 0; i < path.size() - 1; i++) {
				int u = path.get(i);
				int v = path.get(i + 1);

				// Knight moves from u and v. Draw a line to connect u and v
				g.drawLine((u % 8) * getWidth() / 8 + getWidth() / 16, (u / 8)
						* getHeight() / 8 + getHeight() / 16, (v % 8)
						* getWidth() / 8 + getWidth() / 16, (v / 8)
						* getHeight() / 8 + getHeight() / 16);
			}
		}
	}

	public static void main(String[] args) {
		// Create a frame
		JFrame frame = new JFrame("Knight's Tour");

		// Create an instance of the applet
		Exercise18 applet = new Exercise18();

		// Add the applet instance to the frame
		frame.add(applet, BorderLayout.CENTER);

		// Display the frame
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
	}

	static class KnightTourModel {
		private UnweightedGraph<Integer> graph; // Define a graph

		public KnightTourModel() {
			// (u, v) is an edge if a knight can move from u and v
			ArrayList<AbstractGraph.Edge> edges = getEdges();

			// Create a graph with 64 vertices labeled 0 to 63
			graph = new UnweightedGraph<Integer>(edges, 64);
		}

		/** Get a Hamiltonian path starting from vertex v */
		public List<Integer> getHamiltonianPath(int v) {
			return graph.getHamiltonianPath(v);
		}

		/** Create edges for the graph */
		public static ArrayList<AbstractGraph.Edge> getEdges() {
			ArrayList<AbstractGraph.Edge> edges = new ArrayList<AbstractGraph.Edge>(); // Store
																						// edges
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					int u = i * 8 + j; // The vertex label

					// Check eight possible edges from u
					if (i - 1 >= 0 && j - 2 >= 0) {
						int v1 = (i - 1) * 8 + (j - 2);
						edges.add(new AbstractGraph.Edge(u, v1));
					}

					if (i - 2 >= 0 && j - 1 >= 0) {
						int v2 = (i - 2) * 8 + (j - 1);
						edges.add(new AbstractGraph.Edge(u, v2));
					}

					if (i - 2 >= 0 && j + 1 <= 7) {
						int v3 = (i - 2) * 8 + (j + 1);
						edges.add(new AbstractGraph.Edge(u, v3));
					}

					if (i - 1 >= 0 && j + 2 <= 7) {
						int v4 = (i - 1) * 8 + (j + 2);
						edges.add(new AbstractGraph.Edge(u, v4));
					}

					if (i + 1 <= 7 && j + 2 <= 7) {
						int v5 = (i + 1) * 8 + (j + 2);
						edges.add(new AbstractGraph.Edge(u, v5));
					}

					if (i + 2 <= 7 && j + 1 <= 7) {
						int v6 = (i + 2) * 8 + (j + 1);
						edges.add(new AbstractGraph.Edge(u, v6));
					}

					if (i + 2 <= 7 && j - 1 >= 0) {
						int v7 = (i + 2) * 8 + (j - 1);
						edges.add(new AbstractGraph.Edge(u, v7));
					}

					if (i + 1 <= 7 && j - 2 >= 0) {
						int v8 = (i + 1) * 8 + (j - 2);
						edges.add(new AbstractGraph.Edge(u, v8));
					}
				}

			return edges;
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
		
		public List<Integer> getHamiltonianPath(int u) {
			List<Integer> searchOrder = new ArrayList<Integer>();
			int[] parent = new int[vertices.size()];
			for (int i = 0; i < parent.length; i++) {
				parent[i] = -1;
			}

			boolean[] isVisited = new boolean[vertices.size()];

			return getAllCycles(u, u, searchOrder, isVisited);
		}

		private List<Integer> getAllCycles(int first, int v, List<Integer> searchOrder, boolean[] isVisited) {
			searchOrder.add(v);
			isVisited[v] = true;

			for (int i : neighbors.get(v)) {
				if (!isVisited[i]) {
					boolean[] newIsVisited = java.util.Arrays.copyOf(isVisited, isVisited.length);
					@SuppressWarnings("unchecked")
					List<Integer> newSearchOrder = (List<Integer>) ((ArrayList<Integer>)searchOrder).clone();
					List<Integer> result = getAllCycles(first, i, newSearchOrder, newIsVisited);
					if(result != null) {
						return result;
					}
				} else if(first == i) {
					if(searchOrder.size() == vertices.size()) {
						return searchOrder;	
					}
				}
			}
			return null;
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
