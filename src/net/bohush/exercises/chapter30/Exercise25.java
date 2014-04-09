package net.bohush.exercises.chapter30;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Exercise25 extends JApplet {
	private ArrayList<Point> points = new ArrayList<>();
	private ArrayList<Line> lines = new ArrayList<>();
	private GraphPanel graphPanel = new GraphPanel();
	
	private JTextField jTextField = new JTextField(5);
	private JTextField jTextField2 = new JTextField(5);
	private JTextField jTextField3 = new JTextField(5);
	private AbstractGraph<Integer>.Tree tree = null;
	private List<Integer> shortestPath = null;
	
	private static final long serialVersionUID = 1L;

	public Exercise25() {
		setLayout(new BorderLayout(5, 5));
		JPanel jPanel1 = new JPanel(new GridLayout(1, 5, 5, 5));
		jPanel1.setBorder(new LineBorder(Color.BLACK));
		jPanel1.add(new JLabel("INSTRUCTIONS"));
		jPanel1.add(new JLabel("Add: Left Click"));
		jPanel1.add(new JLabel("Move: Ctrl Drag"));
		jPanel1.add(new JLabel("Connect: Drag"));
		jPanel1.add(new JLabel("Remove: Right Click"));
		add(jPanel1, BorderLayout.NORTH);
		add(graphPanel, BorderLayout.CENTER);	
		
	
		JPanel jPanel3 = new JPanel(new GridLayout(1, 2, 5, 5));
		JPanel jPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jPanel2.setBorder(new TitledBorder("Display DFS/BFS"));
		jPanel2.add(new JLabel("Starting Vertex: "));
		jPanel2.add(jTextField);
		JButton jButton1 = new JButton("DFS Tree");
		jPanel2.add(jButton1);
		JButton jButton2 = new JButton("BFS Tree");
		jPanel2.add(jButton2);
		jPanel3.add(jPanel2, BorderLayout.SOUTH);
		
		JPanel jPanel4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jPanel4.setBorder(new TitledBorder("Find a shortest path"));
		jPanel4.add(new JLabel("Start: "));
		jPanel4.add(jTextField2);
		jPanel4.add(new JLabel("End: "));
		jPanel4.add(jTextField3);
		JButton jButton3 = new JButton("Shortest Path");
		jPanel4.add(jButton3);
		jPanel3.add(jPanel4, BorderLayout.SOUTH);
		add(jPanel3, BorderLayout.SOUTH);
		
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int startingVertex = Integer.parseInt(jTextField.getText());
					UnweightedGraph<Integer> graph = getGraph();
					tree = graph.dfs(startingVertex);
					shortestPath = null;
					graphPanel.repaint();					
				} catch (NumberFormatException | IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Bad Starting Vertex \"" + jTextField.getText() + "\"", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int startingVertex = Integer.parseInt(jTextField.getText());
					UnweightedGraph<Integer> graph = getGraph();
					tree = graph.bfs(startingVertex);
					shortestPath = null;
					graphPanel.repaint();					
				} catch (NumberFormatException | IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Bad Starting Vertex \"" + jTextField.getText() + "\"", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int u = Integer.parseInt(jTextField2.getText());
					int v = Integer.parseInt(jTextField3.getText());
					UnweightedGraph<Integer> graph = getGraph();
					shortestPath = graph.getPath(u, v);
					tree = null;
					graphPanel.repaint();					
				} catch (NumberFormatException | IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "Bad vertex number", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private UnweightedGraph<Integer> getGraph() {
		List<Integer> vertices = new ArrayList<>();
		for (int i = 0; i < points.size(); i++) {
			vertices.add(i);
		}
		List<AbstractGraph.Edge> edges = new ArrayList<>();
		for (int i = 0; i < lines.size(); i++) {
			Line line = lines.get(i);
			int u = points.indexOf(line.p1);
			int v = points.indexOf(line.p2);
			if(!edges.contains(new AbstractGraph.Edge(u, v))) {
				edges.add(new AbstractGraph.Edge(u, v));
				edges.add(new AbstractGraph.Edge(v, u));
			}
		}
		UnweightedGraph<Integer> graph = new UnweightedGraph<Integer>(edges, vertices);
		return graph;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise25());
		frame.setTitle("Exercise25");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class Line {
		public Point p1;
		public Point p2;
		public Line(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
	}
	
	class GraphPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private final int RADIUS = 20;
		private boolean isCreatingConnection = false;
		private int creatingFromConnectionNumber;
		private int creatingToConnectionNumber;
		private int draggingX;
		private int draggingY;
		
		private Point movingPoint;
		private int deltaX;
		private int deltaY;
		
		public GraphPanel() {
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						boolean toClose = false;
						for (int i = 0; i < points.size(); i++) {
							if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS * 2) {
								toClose = true;
								break;
							}
						}
						if (!toClose) {
							points.add(new Point(e.getX(), e.getY()));
							tree = null;
							shortestPath = null;
							repaint();							
						}					
					} else if (e.getButton() == MouseEvent.BUTTON3) {
						for (int i = 0; i < points.size(); i++) {
							if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS) {
								for (int j = 0; j < lines.size(); j++) {
									if ((points.get(i).equals(lines.get(j).p1)) || (points.get(i).equals(lines.get(j).p2))) {
										lines.remove(j);
										j--;
									}
								}
								points.remove(i);
								tree = null;
								shortestPath = null;
								repaint();
								break;
							}
						}
					}
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					for (int i = 0; i < points.size(); i++) {
						if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS) {
							if (e.isControlDown()) {
								movingPoint = points.get(i);
								deltaX = movingPoint.x - e.getX();
								deltaY = movingPoint.y - e.getY();
							} else {
								isCreatingConnection = true;
								creatingFromConnectionNumber = i;
								draggingX = points.get(creatingFromConnectionNumber).x;
								draggingY = points.get(creatingFromConnectionNumber).y;
							}
							repaint();
							break;
						}
					}
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					if (isCreatingConnection) {
						for (int i = 0; i < points.size(); i++) {
							if (getLenght(new Point(e.getX(), e.getY()), points.get(i)) < RADIUS) {
								if (creatingFromConnectionNumber == i) {
									continue;
								}
								creatingToConnectionNumber = i;								
								lines.add(new Line(points.get(creatingFromConnectionNumber), points.get(creatingToConnectionNumber)));
								tree = null;
								shortestPath = null;
								break;
							}
						}
						isCreatingConnection = false;
						repaint();
					}
				}
			});
			addMouseMotionListener(new MouseMotionListener() {
				
				@Override
				public void mouseMoved(MouseEvent e) {
				}
				
				@Override
				public void mouseDragged(MouseEvent e) {
					if (e.isControlDown()) {
						movingPoint.x = e.getX() + deltaX;
						movingPoint.y = e.getY() + deltaY;
						repaint();
					} else {
						if (isCreatingConnection) {
							draggingX = e.getX();
							draggingY = e.getY();
							repaint();
						}
					}
				}
			});
		}
		
	
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(800, 400);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (isCreatingConnection) {
				g.drawLine(draggingX, draggingY, points.get(creatingFromConnectionNumber).x, points.get(creatingFromConnectionNumber).y);
			}
			for (int i = 0; i < lines.size(); i++) {
				g.drawLine(lines.get(i).p1.x, lines.get(i).p1.y, lines.get(i).p2.x, lines.get(i).p2.y);
			}
			if(tree != null) {
				g.setColor(Color.RED);
				for (int i = 0; i < tree.parent.length; i++) {
					if (tree.parent[i] != -1) {
						int length = 20;
						int x0 = points.get(i).x;
						int y0 = points.get(i).y;
						int x1 = points.get(tree.parent[i]).x;
						int y1 = points.get(tree.parent[i]).y;
						double angle1 = Math.atan2(y1 - y0, x1 - x0);
						double distance = Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
						distance = distance - RADIUS;
						angle1 = Math.toRadians(Math.toDegrees(angle1) + 180);
						x0 = (int)(x1 + Math.cos(angle1) * distance);
						y0 = (int)(y1 + Math.sin(angle1) * distance);
						g.drawLine(x1, y1, x0, y0);
						angle1 = Math.toRadians(Math.toDegrees(angle1) + 180);
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
			} else if(shortestPath != null) {
				g.setColor(Color.BLUE);
				for (int i = 0; i < shortestPath.size() - 1; i++) {
						int length = 20;
						int x0 = points.get(shortestPath.get(i + 1)).x;
						int y0 = points.get(shortestPath.get(i + 1)).y;
						int x1 = points.get(shortestPath.get(i)).x;;
						int y1 = points.get(shortestPath.get(i)).y;
						double angle1 = Math.atan2(y1 - y0, x1 - x0);
						double distance = Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
						distance = distance - RADIUS;
						angle1 = Math.toRadians(Math.toDegrees(angle1) + 180);
						x0 = (int)(x1 + Math.cos(angle1) * distance);
						y0 = (int)(y1 + Math.sin(angle1) * distance);
						g.drawLine(x1, y1, x0, y0);
						angle1 = Math.toRadians(Math.toDegrees(angle1) + 180);
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
			
			if (points.size() > 0) {
				int minX = points.get(0).x;
				int minY = points.get(0).y;
				int maxX = points.get(0).x;
				int maxY = points.get(0).y;
				for (int i = 0; i < points.size(); i++) {
					if (points.get(i).y < minY) {
						minY = points.get(i).y;
					}
					if (points.get(i).x < minX) {
						minX = points.get(i).x;
					}
					if (points.get(i).y > maxY) {
						maxY = points.get(i).y;
					}
					if (points.get(i).x > maxX) {
						maxX = points.get(i).x;
					}
					
					g.setColor(getBackground());
					g.fillOval(points.get(i).x - RADIUS, points.get(i).y - RADIUS, 2 * RADIUS, 2 * RADIUS);
					g.setColor(Color.BLACK);
					g.drawOval(points.get(i).x - RADIUS, points.get(i).y - RADIUS, 2 * RADIUS, 2 * RADIUS);
					g.drawString(i + "", points.get(i).x, points.get(i).y);
				}

			}

		}
		
		private double getLenght(Point p1, Point p2){
			return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
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
			
			@Override
			public boolean equals(Object obj) {
				if (obj instanceof Edge) {
					Edge that = (Edge)obj;
					return (u == that.u) && (v == that.v);			
				} else {
					return false;
				}
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
