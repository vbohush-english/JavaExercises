package net.bohush.exercises.chapter31;

import java.util.*;

public class Exercise01 {

	  public static void main(String[] args) {
		    String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
		      "Denver", "Kansas City", "Chicago", "Boston", "New York",
		      "Atlanta", "Miami", "Dallas", "Houston"};

		    int[][] edges = {
		      {0, 1, 807}, {0, 3, 1331}, {0, 5, 2097},
		      {1, 0, 807}, {1, 2, 381}, {1, 3, 1267},
		      {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663}, {2, 10, 1435},
		      {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
		      {4, 2, 1663}, {4, 3, 599}, {4, 5, 533}, {4, 7, 1260}, {4, 8, 864}, {4, 10, 496},
		      {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983}, {5, 7, 787},
		      {6, 5, 983}, {6, 7, 214},
		      {7, 4, 1260}, {7, 5, 787}, {7, 6, 214}, {7, 8, 888},
		      {8, 4, 864}, {8, 7, 888}, {8, 9, 661}, {8, 10, 781}, {8, 11, 810},
		      {9, 8, 661}, {9, 11, 1187},
		      {10, 2, 1435}, {10, 4, 496}, {10, 8, 781}, {10, 11, 239},
		      {11, 8, 810}, {11, 9, 1187}, {11, 10, 239}
		    };


		    WeightedGraph<String> graph1 =  new WeightedGraph<String>(edges, vertices);
		    WeightedGraph<String>.MST tree1 = graph1.getMinimumSpanningTree();
		    System.out.println("Total weight is " + tree1.getTotalWeight());
		    tree1.printTree();
		    
		    WeightedGraph<String>.MST tree12 = graph1.getMinimumKruskalSpanningTree();
		    System.out.println("Total weight is " + tree12.getTotalWeight());
		    tree12.printTree();

		    edges = new int[][]{
		      {0, 1, 2}, {0, 3, 8}, 
		      {1, 0, 2}, {1, 2, 7}, {1, 3, 3},
		      {2, 1, 7}, {2, 3, 4}, {2, 4, 5},
		      {3, 0, 8}, {3, 1, 3}, {3, 2, 4}, {3, 4, 6},
		      {4, 2, 5}, {4, 3, 6}
		    };

		    WeightedGraph<Integer> graph2 = new WeightedGraph<Integer>(edges, 5);
		    WeightedGraph<Integer>.MST tree2 =  graph2.getMinimumSpanningTree(1);
		    System.out.println("Total weight is " + tree2.getTotalWeight());
		    tree2.printTree();
		    
		    WeightedGraph<Integer>.MST tree22 =  graph2.getMinimumKruskalSpanningTree(1);
		    System.out.println("Total weight is " + tree22.getTotalWeight());
		    tree22.printTree();
	}

	static class WeightedGraph<V> extends AbstractGraph<V> {
		// Priority adjacency lists
		private List<PriorityQueue<WeightedEdge>> queues = new ArrayList<PriorityQueue<WeightedEdge>>();

		/** Construct a WeightedGraph from edges and vertices in arrays */
		public WeightedGraph() {
		}

		/** Construct a WeightedGraph from edges and vertices in arrays */
		public WeightedGraph(int[][] edges, V[] vertices) {
			super(edges, vertices);
			createQueues(edges, vertices.length);
		}

		/** Construct a WeightedGraph from edges and vertices in List */
		public WeightedGraph(int[][] edges, int numberOfVertices) {
			super(edges, numberOfVertices);
			createQueues(edges, numberOfVertices);
		}

		/** Construct a WeightedGraph for vertices 0, 1, 2 and edge list */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public WeightedGraph(List<WeightedEdge> edges, List<V> vertices) {
			super((List) edges, vertices);
			createQueues(edges, vertices.size());
		}

		/** Construct a WeightedGraph from vertices 0, 1, and edge array */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
			super((List) edges, numberOfVertices);
			createQueues(edges, numberOfVertices);
		}

		/** Create priority adjacency lists from edge arrays */
		private void createQueues(int[][] edges, int numberOfVertices) {
			for (int i = 0; i < numberOfVertices; i++) {
				queues.add(new PriorityQueue<WeightedEdge>()); // Create a queue
			}

			for (int i = 0; i < edges.length; i++) {
				int u = edges[i][0];
				int v = edges[i][1];
				int weight = edges[i][2];
				// Insert an edge into the queue
				queues.get(u).offer(new WeightedEdge(u, v, weight));
			}
		}

		/** Create priority adjacency lists from edge lists */
		private void createQueues(List<WeightedEdge> edges, int numberOfVertices) {
			for (int i = 0; i < numberOfVertices; i++) {
				queues.add(new PriorityQueue<WeightedEdge>()); // Create a queue
			}

			for (WeightedEdge edge : edges) {
				queues.get(edge.u).offer(edge); // Insert an edge into the queue
			}
		}

		/** Display edges with weights */
		public void printWeightedEdges() {
			for (int i = 0; i < queues.size(); i++) {
				System.out.print(getVertex(i) + " (" + i + "): ");
				for (WeightedEdge edge : queues.get(i)) {
					System.out.print("(" + edge.u + ", " + edge.v + ", "
							+ edge.weight + ") ");
				}
				System.out.println();
			}
		}

		/** Get the edges from the weighted graph */
		public List<PriorityQueue<WeightedEdge>> getWeightedEdges() {
			return queues;
		}

		/** Clears the weighted graph */
		public void clear() {
			vertices.clear();
			neighbors.clear();
			queues.clear();
		}

		/** Add vertices to the weighted graph */
		public void addVertex(V vertex) {
			super.addVertex(vertex);
			queues.add(new PriorityQueue<WeightedEdge>());
		}

		/** Add edges to the weighted graph */
		public void addEdge(int u, int v, double weight) {
			super.addEdge(u, v);
			queues.get(u).add(new WeightedEdge(u, v, weight));
			queues.get(v).add(new WeightedEdge(v, u, weight));
		}

		public MST getMinimumKruskalSpanningTree() {
			return getMinimumKruskalSpanningTree(0);
		}
		
		public MST getMinimumKruskalSpanningTree(int startingVertex) {
			ArrayList<WeightedEdge> all = new ArrayList<>();
			for (int i = 0; i < queues.size(); i++) {
				PriorityQueue<WeightedEdge> tmpQueue = queues.get(i);
				for (WeightedEdge weightedEdge : tmpQueue) {
					all.add(weightedEdge);
				}
			}
			Collections.sort(all);
			
			List<V> newVertices = getVertices();
			List<WeightedEdge> newEdges = new LinkedList<>();
			
			while(true) {
				WeightedEdge newEdge1 = all.remove(0);
				WeightedEdge newEdge2 = new WeightedEdge(newEdge1.v, newEdge1.u, newEdge1.weight);
				for (int i = 0; i < all.size(); i++) {
					if(newEdge2.equals(all.get(i))) {
						all.remove(i);
						break;
					}
				}
				newEdges.add(newEdge1);
				newEdges.add(newEdge2);
				
				WeightedGraph<V> tmpGraph =  new WeightedGraph<V>(newEdges, newVertices);
				if(tmpGraph.isCyclic()) {
					newEdges.remove(newEdges.size() - 1);
					newEdges.remove(newEdges.size() - 1);
				}
			
				Tree tree = tmpGraph.dfs(startingVertex);
				if(tree.getNumberOfVerticesFound() == newVertices.size()) {					
					double totalWeight = 0;
					for (int i = 0; i < newEdges.size(); i++) {
						totalWeight += newEdges.get(i).weight;
					}
					totalWeight = totalWeight / 2;					
					return new MST(startingVertex, tree.parent, tree.searchOrder, totalWeight);
				}
			}
		}
		
		/** Get a minimum spanning tree rooted at vertex 0 */
		public MST getMinimumSpanningTree() {
			return getMinimumSpanningTree(0);
		}

		/** Get a minimum spanning tree rooted at a specified vertex */
		public MST getMinimumSpanningTree(int startingVertex) {
			List<Integer> T = new ArrayList<Integer>();
			// T initially contains the startingVertex;
			T.add(startingVertex);

			int numberOfVertices = vertices.size(); // Number of vertices
			int[] parent = new int[numberOfVertices]; // Parent of a vertex
			// Initially set the parent of all vertices to -1
			for (int i = 0; i < parent.length; i++)
				parent[i] = -1;
			double totalWeight = 0; // Total weight of the tree thus far

			// Clone the priority queue, so to keep the original queue intact
			List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);

			// All vertices are found?
			while (T.size() < numberOfVertices) {
				// Search for the vertex with the smallest edge adjacent to
				// a vertex in T
				int v = -1;
				double smallestWeight = Double.MAX_VALUE;
				for (int u : T) {
					while (!queues.get(u).isEmpty()
							&& T.contains(queues.get(u).peek().v)) {
						// Remove the edge from queues[u] if the adjacent
						// vertex of u is already in T
						queues.get(u).remove();
					}

					if (queues.get(u).isEmpty()) {
						continue; // Consider the next vertex in T
					}

					// Current smallest weight on an edge adjacent to u
					WeightedEdge edge = queues.get(u).peek();
					if (edge.weight < smallestWeight) {
						v = edge.v;
						smallestWeight = edge.weight;
						// If v is added to the tree, u will be its parent
						parent[v] = u;
					}
				} // End of for

				if (v != -1)
					T.add(v); // Add a new vertex to the tree
				else
					break; // The tree is not connected, a partial MST is found

				totalWeight += smallestWeight;
			} // End of while

			return new MST(startingVertex, parent, T, totalWeight);
		}

		/** Clone an array of queues */
		private List<PriorityQueue<WeightedEdge>> deepClone(
				List<PriorityQueue<WeightedEdge>> queues) {
			List<PriorityQueue<WeightedEdge>> copiedQueues = new ArrayList<PriorityQueue<WeightedEdge>>();

			for (int i = 0; i < queues.size(); i++) {
				copiedQueues.add(new PriorityQueue<WeightedEdge>());
				for (WeightedEdge e : queues.get(i)) {
					copiedQueues.get(i).add(e);
				}
			}

			return copiedQueues;
		}

		/** MST is an inner class in WeightedGraph */
		public class MST extends Tree {
			private double totalWeight; // Total weight of all edges in the tree

			public MST(int root, int[] parent, List<Integer> searchOrder,
					double totalWeight) {
				super(root, parent, searchOrder);
				this.totalWeight = totalWeight;
			}

			public double getTotalWeight() {
				return totalWeight;
			}
		}

		/** Find single source shortest paths */
		public ShortestPathTree getShortestPath(int sourceVertex) {
			// T stores the vertices whose path found so far
			List<Integer> T = new ArrayList<Integer>();
			// T initially contains the sourceVertex;
			T.add(sourceVertex);

			// vertices is defined in AbstractGraph
			int numberOfVertices = vertices.size();

			// parent[v] stores the previous vertex of v in the path
			int[] parent = new int[numberOfVertices];
			parent[sourceVertex] = -1; // The parent of source is set to -1

			// cost[v] stores the cost of the path from v to the source
			double[] cost = new double[numberOfVertices];
			for (int i = 0; i < cost.length; i++) {
				cost[i] = Double.MAX_VALUE; // Initial cost set to infinity
			}
			cost[sourceVertex] = 0; // Cost of source is 0

			// Get a copy of queues
			List<PriorityQueue<WeightedEdge>> queues = deepClone(this.queues);

			// Expand T
			while (T.size() < numberOfVertices) {
				int v = -1; // Vertex to be determined
				double smallestCost = Double.MAX_VALUE; // Set to infinity
				for (int u : T) {
					while (!queues.get(u).isEmpty()
							&& T.contains(queues.get(u).peek().v)) {
						queues.get(u).remove(); // Remove the vertex in queue
												// for u
					}

					if (queues.get(u).isEmpty()) {
						// All vertices adjacent to u are in T
						continue;
					}

					WeightedEdge e = queues.get(u).peek();
					if (cost[u] + e.weight < smallestCost) {
						v = e.v;
						smallestCost = cost[u] + e.weight;
						// If v is added to the tree, u will be its parent
						parent[v] = u;
					}
				} // End of for

				T.add(v); // Add a new vertex to T
				cost[v] = smallestCost;
			} // End of while

			// Create a ShortestPathTree
			return new ShortestPathTree(sourceVertex, parent, T, cost);
		}

		/** ShortestPathTree is an inner class in WeightedGraph */
		public class ShortestPathTree extends Tree {
			private double[] cost; // cost[v] is the cost from v to source

			/** Construct a path */
			public ShortestPathTree(int source, int[] parent,
					List<Integer> searchOrder, double[] cost) {
				super(source, parent, searchOrder);
				this.cost = cost;
			}

			/** Return the cost for a path from the root to vertex v */
			public double getCost(int v) {
				return cost[v];
			}

			/** Print paths from all vertices to the source */
			public void printAllPaths() {
				System.out.println("All shortest paths from "
						+ vertices.get(getRoot()) + " are:");
				for (int i = 0; i < cost.length; i++) {
					printPath(i); // Print a path from i to the source
					System.out.println("(cost: " + cost[i] + ")"); // Path cost
				}
			}
		}
	}

	static class WeightedEdge extends AbstractGraph.Edge implements Comparable<WeightedEdge> {
		public double weight; // The weight on edge (u, v)

		/** Create a weighted edge on (u, v) */
		public WeightedEdge(int u, int v, double weight) {
			super(u, v);
			this.weight = weight;
		}

		/** Compare two edges on weights */
		public int compareTo(WeightedEdge edge) {
			if (weight > edge.weight) {
				return 1;
			} else if (weight == edge.weight) {
				return 0;
			} else {
				return -1;
			}
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof WeightedEdge) {
				WeightedEdge that = (WeightedEdge)obj;
				return (u == that.u) && (v == that.v) && (weight == that.weight);			
			} else {
				return false;
			}
		}
		
		@Override
		public String toString() {
			return "(" + u + ", " + v + ", " + weight + ")";
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
		
		public boolean isCyclic() {
			for (int i = 0; i < vertices.size(); i++) {
				if(isCyclic(i)) {
					return true;
				}
			}
			return false;
		}
		
		private boolean isCyclic(int v) {
			List<Integer> searchOrder = new ArrayList<Integer>();
			int[] parent = new int[vertices.size()];
			for (int i = 0; i < parent.length; i++) {
				parent[i] = -1;
			}

			boolean[] isVisited = new boolean[vertices.size()];

			return isCyclic(v, v, parent, searchOrder, isVisited);

		}

		private boolean isCyclic(int first, int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
			searchOrder.add(v);
			isVisited[v] = true;

			for (int i : neighbors.get(v)) {
				if (!isVisited[i]) {
					parent[i] = v;
					int[] newParent = java.util.Arrays.copyOf(parent, parent.length);
					boolean[] newIsVisited = java.util.Arrays.copyOf(isVisited, parent.length);
					@SuppressWarnings("unchecked")
					List<Integer> newSearchOrder = (List<Integer>) ((ArrayList<Integer>)searchOrder).clone();
					if(isCyclic(first, i, newParent, newSearchOrder, newIsVisited)) {
						return true;
					}
				} else if(first == i) {
					if(searchOrder.size() > 2) {
						return true;
					}
				}
			}
			return false;
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
