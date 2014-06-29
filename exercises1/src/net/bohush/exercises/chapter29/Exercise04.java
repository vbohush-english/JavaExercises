package net.bohush.exercises.chapter29;

import java.util.ArrayList;

public class Exercise04 {

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		for (int i = 1; i <= 100; i++) {
			tree.insert(i);			
		}
		tree.displayPath();
	}
	
	static class AVLTree<E extends Comparable<E>> extends BST<E> {
		/** Create a default AVL tree */
		public AVLTree() {
		}

		/** Create an AVL tree from an array of objects */
		public AVLTree(E[] objects) {
			super(objects);
		}

		public void displayPath() {
			displayPath(root);
		}
		
		public void displayPath(TreeNode<E> node) {
			ArrayList<TreeNode<E>> list = getPath(node);
			for (TreeNode<E> treeNode : list) {
				System.out.print(treeNode.element + " ");
			}
			System.out.println();
			if(node.left != null) {
				displayPath(node.left);
			}
			if(node.right != null) {
				displayPath(node.right);
			}			
		}
		
	
		@Override
		/** Override createNewNode to create an AVLTreeNode */
		protected AVLTreeNode<E> createNewNode(E e, TreeNode<E> parent) {
			return new AVLTreeNode<E>(e, parent);
		}

		@Override
		/** Insert an element and rebalance if necessary */
		public boolean insert(E e) {
			boolean successful = super.insert(e);
			if (!successful)
				return false; // e is already in the tree
			else {
				balancePath(e); // Balance from e to the root if necessary
			}

			return true; // e is inserted
		}

		/** Update the height of a specified node */
		private void updateHeight(AVLTreeNode<E> node) {
			if (node.left == null && node.right == null) // node is a leaf
				node.height = 0;
			else if (node.left == null) // node has no left subtree
				node.height = 1 + ((AVLTreeNode<E>) (node.right)).height;
			else if (node.right == null) // node has no right subtree
				node.height = 1 + ((AVLTreeNode<E>) (node.left)).height;
			else
				node.height = 1 + Math.max(
						((AVLTreeNode<E>) (node.right)).height,
						((AVLTreeNode<E>) (node.left)).height);
		}

		/**
		 * Balance the nodes in the path from the specified node to the root if
		 * necessary
		 */
		private void balancePath(E e) {
			java.util.ArrayList<TreeNode<E>> path = path(e);
			for (int i = path.size() - 1; i >= 0; i--) {
				AVLTreeNode<E> A = (AVLTreeNode<E>) (path.get(i));
				updateHeight(A);
				switch (balanceFactor(A)) {
				case -2:
					if (balanceFactor((AVLTreeNode<E>) A.left) <= 0) {
						balanceLL(A); // Perform LL rotation
					} else {
						balanceLR(A); // Perform LR rotation
					}
					break;
				case +2:
					if (balanceFactor((AVLTreeNode<E>) A.right) >= 0) {
						balanceRR(A); // Perform RR rotation
					} else {
						balanceRL(A); // Perform RL rotation
					}
				}
			}
		}

		/** Return the balance factor of the node */
		private int balanceFactor(AVLTreeNode<E> node) {
			if (node.right == null) // node has no right subtree
				return -node.height;
			else if (node.left == null) // node has no left subtree
				return +node.height;
			else
				return ((AVLTreeNode<E>) node.right).height
						- ((AVLTreeNode<E>) node.left).height;
		}

		/** Balance LL (see Figure 9.1) */
		private void balanceLL(TreeNode<E> A) {
			TreeNode<E> parentOfA = A.parent;
			TreeNode<E> B = A.left; // A is left-heavy and B is left-heavy
			A.parent = B;
			if(B.right != null) {
				B.right.parent = A;
			}
			B.parent = parentOfA;						
			if (A == root) {
				root = B;
			} else {
				if (parentOfA.left == A) {
					parentOfA.left = B;
				} else {
					parentOfA.right = B;
				}
			}

			A.left = B.right; // Make T2 the left subtree of A
			B.right = A; // Make A the left child of B
			updateHeight((AVLTreeNode<E>) A);
			updateHeight((AVLTreeNode<E>) B);
		}

		/** Balance LR (see Figure 9.1(c)) */
		private void balanceLR(TreeNode<E> A) {
			TreeNode<E> parentOfA = A.parent;
			TreeNode<E> B = A.left; // A is left-heavy
			TreeNode<E> C = B.right; // B is right-heavy
			A.parent = C;
			B.parent = C;
			if(C.left != null) {
				C.left.parent = B;
			}
			if(C.right != null) {
				C.right.parent = A;
			}
			C.parent = parentOfA;			

			if (A == root) {
				root = C;
			} else {
				if (parentOfA.left == A) {
					parentOfA.left = C;
				} else {
					parentOfA.right = C;
				}
			}

			A.left = C.right; // Make T3 the left subtree of A
			B.right = C.left; // Make T2 the right subtree of B
			C.left = B;
			C.right = A;

			// Adjust heights
			updateHeight((AVLTreeNode<E>) A);
			updateHeight((AVLTreeNode<E>) B);
			updateHeight((AVLTreeNode<E>) C);
		}

		/** Balance RR (see Figure 9.1(b)) */
		private void balanceRR(TreeNode<E> A) {
			TreeNode<E> parentOfA = A.parent;
			TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy
			A.parent = B;
			if(B.left != null) {
				B.left.parent = A;
			}
			B.parent = parentOfA;
			
			if (A == root) {
				root = B;
			} else {
				if (parentOfA.left == A) {
					parentOfA.left = B;
				} else {
					parentOfA.right = B;
				}
			}

			A.right = B.left; // Make T2 the right subtree of A
			B.left = A;
			updateHeight((AVLTreeNode<E>) A);
			updateHeight((AVLTreeNode<E>) B);
		}

		/** Balance RL (see Figure 9.1(d)) */
		private void balanceRL(TreeNode<E> A) {
			TreeNode<E> parentOfA = A.parent;
			TreeNode<E> B = A.right; // A is right-heavy
			TreeNode<E> C = B.left; // B is left-heavy
			A.parent = C;
			B.parent = C;
			if(C.left != null) {
				C.left.parent = A;
			}
			if(C.right != null) {
				C.right.parent = B;
			}
			C.parent = parentOfA;
			
			if (A == root) {
				root = C;
			} else {
				if (parentOfA.left == A) {
					parentOfA.left = C;
				} else {
					parentOfA.right = C;
				}
			}

			A.right = C.left; // Make T2 the right subtree of A
			B.left = C.right; // Make T3 the left subtree of B
			C.left = A;
			C.right = B;

			// Adjust heights
			updateHeight((AVLTreeNode<E>) A);
			updateHeight((AVLTreeNode<E>) B);
			updateHeight((AVLTreeNode<E>) C);
		}

		@Override
		/** Delete an element from the binary tree.
		 * Return true if the element is deleted successfully
		 * Return false if the element is not in the tree */
		public boolean delete(E element) {
			if (root == null)
				return false; // Element is not in the tree

			// Locate the node to be deleted and also locate its parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (element.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (element.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					break; // Element is in the tree pointed by current
			}

			if (current == null)
				return false; // Element is not in the tree

			// Case 1: current has no left children (See Figure 23.6)
			if (current.left == null) {
				// Connect the parent with the right child of the current node
				if (parent == null) {
					root = current.right;
				} else {
					if (element.compareTo(parent.element) < 0)
						parent.left = current.right;
					else
						parent.right = current.right;

					// Balance the tree if necessary
					balancePath(parent.element);
				}
			} else {
				// Case 2: The current node has a left child
				// Locate the rightmost node in the left subtree of
				// the current node and also its parent
				TreeNode<E> parentOfRightMost = current;
				TreeNode<E> rightMost = current.left;

				while (rightMost.right != null) {
					parentOfRightMost = rightMost;
					rightMost = rightMost.right; // Keep going to the right
				}

				// Replace the element in current by the element in rightMost
				current.element = rightMost.element;

				// Eliminate rightmost node
				if (parentOfRightMost.right == rightMost)
					parentOfRightMost.right = rightMost.left;
				else
					// Special case: parentOfRightMost is current
					parentOfRightMost.left = rightMost.left;

				// Balance the tree if necessary
				balancePath(parentOfRightMost.element);
			}

			size--;
			return true; // Element inserted
		}

		/** AVLTreeNode is TreeNode plus height */
		protected static class AVLTreeNode<E extends Comparable<E>> extends BST.TreeNode<E> {
			protected int height = 0; // New data field

			public AVLTreeNode(E o, TreeNode<E> parent) {
				super(o, parent);
			}
		}
	}
	

	static class BST<E extends Comparable<E>> extends AbstractTree<E> {
		protected TreeNode<E> root;
		protected int size = 0;
		
		/** Returns the parent for the specified node. */
		public TreeNode<E> getParent(TreeNode<E> node) {
			return node.parent;
		}
		
		/** Returns the path from the specified node to the root
		* in an array list. */
		public ArrayList<TreeNode<E>> getPath(TreeNode<E> node) {
			ArrayList<TreeNode<E>> result = new ArrayList<>();
			while(node.parent != null) {
				result.add(node);
				node = node.parent;
			}
			result.add(node);
			return result;
		}
		
		
		/** Create a default binary tree */
		public BST() {
		}

		/** Create a binary tree from an array of objects */
		public BST(E[] objects) {
			for (int i = 0; i < objects.length; i++)
				insert(objects[i]);
		}

		@Override
		/** Returns true if the element is in the tree */
		public boolean search(E e) {
			TreeNode<E> current = root; // Start from the root

			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					current = current.right;
				} else
					// element matches current.element
					return true; // Element is found
			}

			return false;
		}

		@Override
		/** Insert element o into the binary tree
		 * Return true if the element is inserted successfully */
		public boolean insert(E e) {
			if (root == null)
				root = createNewNode(e, null); // Create a new root
			else {
				// Locate the parent node
				TreeNode<E> parent = null;
				TreeNode<E> current = root;
				while (current != null)
					if (e.compareTo(current.element) < 0) {
						parent = current;
						current = current.left;
					} else if (e.compareTo(current.element) > 0) {
						parent = current;
						current = current.right;
					} else
						return false; // Duplicate node not inserted

				// Create the new node and attach it to the parent node
				if (e.compareTo(parent.element) < 0)
					parent.left = createNewNode(e, parent);
				else
					parent.right = createNewNode(e, parent);
			}

			size++;
			return true; // Element inserted
		}

		protected TreeNode<E> createNewNode(E e, TreeNode<E> parent) {
			return new TreeNode<E>(e, parent);
		}

		@Override
		/** Inorder traversal from the root*/
		public void inorder() {
			inorder(root);
		}

		/** Inorder traversal from a subtree */
		protected void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			System.out.print(root.element + " ");
			inorder(root.right);
		}

		@Override
		/** Postorder traversal from the root */
		public void postorder() {
			postorder(root);
		}

		/** Postorder traversal from a subtree */
		protected void postorder(TreeNode<E> root) {
			if (root == null)
				return;
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.element + " ");
		}

		@Override
		/** Preorder traversal from the root */
		public void preorder() {
			preorder(root);
		}

		/** Preorder traversal from a subtree */
		protected void preorder(TreeNode<E> root) {
			if (root == null)
				return;
			System.out.print(root.element + " ");
			preorder(root.left);
			preorder(root.right);
		}

		/**
		 * This inner class is static, because it does not access any instance
		 * members defined in its outer class
		 */
		public static class TreeNode<E extends Comparable<E>> {
			protected E element;
			protected TreeNode<E> left;
			protected TreeNode<E> right;
			protected TreeNode<E> parent;

			public TreeNode(E e, TreeNode<E> parent) {
				element = e;
				this.parent = parent;
			}			
		}

		@Override
		/** Get the number of nodes in the tree */
		public int getSize() {
			return size;
		}

		/** Returns the root of the tree */
		public TreeNode<E> getRoot() {
			return root;
		}

		/** Returns a path from the root leading to the specified element */
		public java.util.ArrayList<TreeNode<E>> path(E e) {
			java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
			TreeNode<E> current = root; // Start from the root

			while (current != null) {
				list.add(current); // Add the node to the list
				if (e.compareTo(current.element) < 0) {
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					current = current.right;
				} else
					break;
			}

			return list; // Return an array of nodes
		}

		@Override
		/** Delete an element from the binary tree.
		 * Return true if the element is deleted successfully
		 * Return false if the element is not in the tree */
		public boolean delete(E e) {
			// Locate the node to be deleted and also locate its parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					break; // Element is in the tree pointed at by current
			}

			if (current == null)
				return false; // Element is not in the tree

			// Case 1: current has no left children
			if (current.left == null) {
				// Connect the parent with the right child of the current node
				if (parent == null) {
					root = current.right;
				} else {
					if (e.compareTo(parent.element) < 0)
						parent.left = current.right;
					else
						parent.right = current.right;
				}
			} else {
				// Case 2: The current node has a left child
				// Locate the rightmost node in the left subtree of
				// the current node and also its parent
				TreeNode<E> parentOfRightMost = current;
				TreeNode<E> rightMost = current.left;

				while (rightMost.right != null) {
					parentOfRightMost = rightMost;
					rightMost = rightMost.right; // Keep going to the right
				}

				// Replace the element in current by the element in rightMost
				current.element = rightMost.element;

				// Eliminate rightmost node
				if (parentOfRightMost.right == rightMost)
					parentOfRightMost.right = rightMost.left;
				else
					// Special case: parentOfRightMost == current
					parentOfRightMost.left = rightMost.left;
			}

			size--;
			return true; // Element inserted
		}

		@Override
		/** Obtain an iterator. Use inorder. */
		public java.util.Iterator<E> iterator() {
			return new InorderIterator();
		}

		// Inner class InorderIterator
		private class InorderIterator implements java.util.Iterator<E> {
			// Store the elements in a list
			private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
			private int current = 0; // Point to the current element in list

			public InorderIterator() {
				inorder(); // Traverse binary tree and store elements in list
			}

			/** Inorder traversal from the root */
			private void inorder() {
				inorder(root);
			}

			/** Inorder traversal from a subtree */
			private void inorder(TreeNode<E> root) {
				if (root == null)
					return;
				inorder(root.left);
				list.add(root.element);
				inorder(root.right);
			}

			@Override
			/** More elements for traversing? */
			public boolean hasNext() {
				if (current < list.size())
					return true;

				return false;
			}

			@Override
			/** Get the current element and move to the next */
			public E next() {
				return list.get(current++);
			}

			@Override
			/** Remove the current element */
			public void remove() {
				delete(list.get(current)); // Delete the current element
				list.clear(); // Clear the list
				inorder(); // Rebuild the list
			}
		}

		/** Remove all elements from the tree */
		public void clear() {
			root = null;
			size = 0;
		}
	}

	static abstract class AbstractTree<E> implements Tree<E> {
		@Override
		/** Inorder traversal from the root*/
		public void inorder() {
		}

		@Override
		/** Postorder traversal from the root */
		public void postorder() {
		}

		@Override
		/** Preorder traversal from the root */
		public void preorder() {
		}

		@Override
		/** Return true if the tree is empty */
		public boolean isEmpty() {
			return getSize() == 0;
		}

		@Override
		/** Return an iterator for the tree */
		public java.util.Iterator<E> iterator() {
			return null;
		}
	}

	interface Tree<E> extends Iterable<E> {
		/** Return true if the element is in the tree */
		public boolean search(E e);

		/**
		 * Insert element o into the binary tree Return true if the element is
		 * inserted successfully
		 */
		public boolean insert(E e);

		/**
		 * Delete the specified element from the tree Return true if the element
		 * is deleted successfully
		 */
		public boolean delete(E e);

		/** Inorder traversal from the root */
		public void inorder();

		/** Postorder traversal from the root */
		public void postorder();

		/** Preorder traversal from the root */
		public void preorder();

		/** Get the number of nodes in the tree */
		public int getSize();

		/** Return true if the tree is empty */
		public boolean isEmpty();

		public java.util.Iterator<E> iterator();
	}
}
