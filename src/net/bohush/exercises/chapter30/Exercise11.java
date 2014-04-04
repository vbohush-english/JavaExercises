package net.bohush.exercises.chapter30;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise11 extends JApplet {

	private static final long serialVersionUID = 1L;
	private NineTailPanel nineTailPanel = new NineTailPanel();
	private JPanel jPanel1 = new JPanel(new FlowLayout());
	
	public Exercise11() {
		jPanel1.add(nineTailPanel);
		setLayout(new BorderLayout());
		add(jPanel1, BorderLayout.CENTER);
		JPanel jPanel2 = new JPanel(new FlowLayout());
		JButton jButton1 = new JButton("Solve");
		JButton jButton2 = new JButton("Start Over");
		jPanel2.add(jButton1);
		jPanel2.add(jButton2);
		add(jPanel2, BorderLayout.SOUTH);
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] initialNode = nineTailPanel.getInitialNode();


				NineTailModel model = new NineTailModel();
				java.util.List<Integer> path = model.getShortestPath(NineTailModel.getIndex(initialNode));

				for (int i = 1; i < path.size(); i++) {
					jPanel1.add(new NineTailPanel(NineTailModel.getNode(path.get(i).intValue()), NineTailModel.getNode(path.get(i - 1).intValue())));
				}
				jPanel1.updateUI();
			}
		});
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jPanel1.removeAll();
				nineTailPanel = new NineTailPanel();
				jPanel1.add(nineTailPanel);
				jPanel1.updateUI();
			}
		});
	}

	static class NineTailPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private int matrixSize = 3;
		private int coinSize = 50;
		private char[] initialNode = new char[matrixSize * matrixSize];
		private char[] previous = new char[matrixSize * matrixSize];
		
		public NineTailPanel() {
			for (int i = 0; i < initialNode.length; i++) {
				initialNode[i] = 'H';
			}
			previous = null;
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					changeState((e.getX() / coinSize) * matrixSize + (e.getY() / coinSize));
				}
			});
		}
		
		public NineTailPanel(char[] initialNode, char[] previous) {
			for (int i = 0; i < initialNode.length; i++) {
				this.initialNode[i] = initialNode[i];
				this.previous[i] = previous[i];
			}
		}
		
		public char[] getInitialNode() {
			return initialNode;
		}
		
		void changeState(int i) {
			if(initialNode[i] == 'H') {
				initialNode[i] = 'T';
			} else {
				initialNode[i] = 'H';
			}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font("Monospaced", Font.BOLD, 26));
			for (int i = 0; i < matrixSize; i++) {
				for (int j = 0; j < matrixSize; j++) {
					g.drawRect(i * coinSize, j * coinSize, coinSize, coinSize);
					if((previous != null) && (initialNode[i * matrixSize + j] != previous[i * matrixSize + j])) {
						g.setColor(Color.RED);
						g.drawString(initialNode[i * matrixSize + j] + "", i * coinSize + coinSize / 3, j * coinSize + 2 * (coinSize / 3));
						g.setColor(Color.BLACK);
					} else {
						g.drawString(initialNode[i * matrixSize + j] + "", i * coinSize + coinSize / 3, j * coinSize + 2 * (coinSize / 3));
					}
				}
			}
			
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(matrixSize * coinSize + 1, matrixSize * coinSize + 1);
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise11");
		JApplet applet = new Exercise11();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	static class NineTailModel {
		public final static int NUMBER_OF_NODES = 512;
		protected AbstractGraph<Integer>.Tree tree; // Define a tree

		/** Construct a model */
		public NineTailModel() {
			// Create edges
			List<AbstractGraph.Edge> edges = getEdges();

			// Create a graph
			UnweightedGraph<Integer> graph = new UnweightedGraph<Integer>(
					edges, NUMBER_OF_NODES);

			// Obtain a BSF tree rooted at the target node
			tree = graph.bfs(511);
		}

		/** Create all edges for the graph */
		private List<AbstractGraph.Edge> getEdges() {
			List<AbstractGraph.Edge> edges = new ArrayList<AbstractGraph.Edge>(); // Store
																					// edges

			for (int u = 0; u < NUMBER_OF_NODES; u++) {
				for (int k = 0; k < 9; k++) {
					char[] node = getNode(u); // Get the node for vertex u
					if (node[k] == 'H') {
						int v = getFlippedNode(node, k);
						// Add edge (v, u) for a legal move from node u to node
						// v
						edges.add(new AbstractGraph.Edge(v, u));
					}
				}
			}

			return edges;
		}

		public static int getFlippedNode(char[] node, int position) {
			int row = position / 3;
			int column = position % 3;

			flipACell(node, row, column);
			flipACell(node, row - 1, column);
			flipACell(node, row + 1, column);
			flipACell(node, row, column - 1);
			flipACell(node, row, column + 1);

			return getIndex(node);
		}

		public static void flipACell(char[] node, int row, int column) {
			if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
				// Within the boundary
				if (node[row * 3 + column] == 'H')
					node[row * 3 + column] = 'T'; // Flip from H to T
				else
					node[row * 3 + column] = 'H'; // Flip from T to H
			}
		}

		public static int getIndex(char[] node) {
			int result = 0;

			for (int i = 0; i < 9; i++)
				if (node[i] == 'T')
					result = result * 2 + 1;
				else
					result = result * 2 + 0;

			return result;
		}

		public static char[] getNode(int index) {
			char[] result = new char[9];

			for (int i = 0; i < 9; i++) {
				int digit = index % 2;
				if (digit == 0)
					result[8 - i] = 'H';
				else
					result[8 - i] = 'T';
				index = index / 2;
			}

			return result;
		}

		public List<Integer> getShortestPath(int nodeIndex) {
			return tree.getPath(nodeIndex);
		}

		public static void printNode(char[] node) {
			for (int i = 0; i < 9; i++)
				if (i % 3 != 2)
					System.out.print(node[i]);
				else
					System.out.println(node[i]);

			System.out.println();
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
