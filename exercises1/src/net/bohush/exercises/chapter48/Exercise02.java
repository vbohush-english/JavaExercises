package net.bohush.exercises.chapter48;

import java.util.ArrayList;
import java.util.LinkedList;

public class Exercise02 {

    public static void main(String[] args) {
        // Create a 2-4 tree
        Tree24<Integer> tree = new Tree24<Integer>();

        tree.insert(34);
        tree.insert(3);
        tree.insert(50);
        tree.insert(20);
        tree.insert(15);
        tree.insert(16);
        tree.insert(25);
        tree.insert(27);
        tree.insert(29);
        tree.insert(24);
        System.out.print("\nAfter inserting 24:");
        printTree(tree);
        printTree(tree.getRBTree());
        tree.insert(23);
        tree.insert(22);
        tree.insert(60);
        tree.insert(70);
        System.out.print("\nAfter inserting 70:");
        printTree(tree);
        printTree(tree.getRBTree());

        tree.delete(34);
        System.out.print("\nAfter deleting 34:");
        printTree(tree);
        printTree(tree.getRBTree());

        tree.delete(25);
        System.out.print("\nAfter deleting 25:");
        printTree(tree);
        printTree(tree.getRBTree());

        tree.delete(50);
        System.out.print("\nAfter deleting 50:");
        printTree(tree);
        printTree(tree.getRBTree());

        tree.delete(16);
        System.out.print("\nAfter deleting 16:");
        printTree(tree);
        printTree(tree.getRBTree());

        tree.delete(3);
        System.out.print("\nAfter deleting 3:");
        printTree(tree);
        printTree(tree.getRBTree());

        tree.delete(15);
        System.out.print("\nAfter deleting 15:");
        printTree(tree);        
        printTree(tree.getRBTree());
    }

    public static void printTree(Tree tree) {
        // Traverse tree
        System.out.print("\nInorder (sorted): ");
        tree.inorder();
        System.out.print("\nPostorder: ");
        tree.postorder();
        System.out.print("\nPreorder: ");
        tree.preorder();
        System.out.print("\nThe number of nodes is " + tree.getSize());
        System.out.println();
    }
    
    static class Tree24<E extends Comparable<E>> implements Tree<E> {

        private Tree24Node<E> root;
        private int size;

        /**
         * Create a default 2-4 tree
         */
        public Tree24() {
        }

        /**
         * Create a 2-4 tree from an array of objects
         */
        public Tree24(E[] elements) {
            for (int i = 0; i < elements.length; i++) {
                insert(elements[i]);
            }
        }
        
          
        public RBTree<E> getRBTree() {
            RBTree<E> result = new RBTree();
            converNode(root, null, false, result);
            return result;
        }

        private void converNode(Tree24.Tree24Node<E> tree24Node, RBTree.RBTreeNode<E> parentOfrbTreeNode, boolean isLeft, RBTree<E> result) {
            if(tree24Node.elements.size() == 1) {
                RBTree.RBTreeNode<E> rbTreeNode = new RBTree.RBTreeNode<>(tree24Node.elements.get(0));
                rbTreeNode.setBlack();
                if(parentOfrbTreeNode == null) {                    
                    result.root = rbTreeNode;
                } else {
                    if(isLeft) {
                        parentOfrbTreeNode.left = rbTreeNode;
                    } else {
                        parentOfrbTreeNode.right = rbTreeNode;
                    }
                }
                result.size++;
                if(tree24Node.child.size() > 0) {
                    converNode(tree24Node.child.get(0), (RBTree.RBTreeNode<E>) rbTreeNode, true, result);
                }
                if(tree24Node.child.size() > 1) {
                    converNode(tree24Node.child.get(1), (RBTree.RBTreeNode<E>) rbTreeNode, false, result);
                }
            } else if(tree24Node.elements.size() == 2) {
                RBTree.RBTreeNode<E> rbTreeNode1 = new RBTree.RBTreeNode<>(tree24Node.elements.get(0));
                rbTreeNode1.setBlack();
                RBTree.RBTreeNode<E> rbTreeNode2 = new RBTree.RBTreeNode<>(tree24Node.elements.get(1));
                rbTreeNode2.setRed();
                if(parentOfrbTreeNode == null) {                    
                    result.root = rbTreeNode1;
                } else {
                    if(isLeft) {
                        parentOfrbTreeNode.left = rbTreeNode1;
                    } else {
                        parentOfrbTreeNode.right = rbTreeNode1;
                    }
                }
                rbTreeNode1.right = rbTreeNode2;
                result.size++;
                result.size++;
                if(tree24Node.child.size() > 0) {
                    converNode(tree24Node.child.get(0), (RBTree.RBTreeNode<E>) rbTreeNode1, true, result);
                }
                if(tree24Node.child.size() > 1) {
                    converNode(tree24Node.child.get(1), (RBTree.RBTreeNode<E>) rbTreeNode2, true, result);
                }          
                if(tree24Node.child.size() > 2) {
                    converNode(tree24Node.child.get(2), (RBTree.RBTreeNode<E>) rbTreeNode2, false, result);
                }                  
            } else if(tree24Node.elements.size() == 3) {
                RBTree.RBTreeNode<E> rbTreeNode1 = new RBTree.RBTreeNode<>(tree24Node.elements.get(0));
                rbTreeNode1.setRed();
                RBTree.RBTreeNode<E> rbTreeNode2 = new RBTree.RBTreeNode<>(tree24Node.elements.get(1));
                rbTreeNode2.setBlack();
                RBTree.RBTreeNode<E> rbTreeNode3 = new RBTree.RBTreeNode<>(tree24Node.elements.get(2));
                rbTreeNode3.setRed();
                if(parentOfrbTreeNode == null) {                    
                    result.root = rbTreeNode2;
                } else {
                    if(isLeft) {
                        parentOfrbTreeNode.left = rbTreeNode2;
                    } else {
                        parentOfrbTreeNode.right = rbTreeNode2;
                    }
                }
                rbTreeNode2.left = rbTreeNode1;
                rbTreeNode2.right = rbTreeNode3;
                result.size++;
                result.size++;
                result.size++;
                if(tree24Node.child.size() > 0) {
                    converNode(tree24Node.child.get(0), (RBTree.RBTreeNode<E>) rbTreeNode1, true, result);
                }
                if(tree24Node.child.size() > 1) {
                    converNode(tree24Node.child.get(1), (RBTree.RBTreeNode<E>) rbTreeNode1, false, result);
                }          
                if(tree24Node.child.size() > 2) {
                    converNode(tree24Node.child.get(2), (RBTree.RBTreeNode<E>) rbTreeNode3, true, result);
                }        
                if(tree24Node.child.size() > 3) {
                    converNode(tree24Node.child.get(3), (RBTree.RBTreeNode<E>) rbTreeNode3, false, result);
                }                    
            } 
        }
                

        @Override
        /**
         * Search an element in the tree
         */
        public boolean search(E e) {
            Tree24Node<E> current = root; // Start from the root

            while (current != null) {
                if (matched(e, current)) { // Element is in the node
                    return true; // Element found
                } else {
                    current = getChildNode(e, current); // Search in a subtree
                }
            }

            return false; // Element is not in the tree
        }

        /**
         * Return true if the element is found in this node
         */
        private boolean matched(E e, Tree24Node<E> node) {
            for (int i = 0; i < node.elements.size(); i++) {
                if (node.elements.get(i).equals(e)) {
                    return true; // Element found
                }
            }
            return false; // No match in this node
        }

        /**
         * Locate a child node to search element e
         */
        private Tree24Node<E> getChildNode(E e, Tree24Node<E> node) {
            if (node.child.size() == 0) {
                return null; // node is a leaf
            }
            int i = locate(e, node); // Locate the insertion point for e
            return node.child.get(i); // Return the child node
        }

        @Override
        /**
         * Insert element e into the tree Return true if the element is inserted
         * successfully
         */
        public boolean insert(E e) {
            if (root == null) {
                root = new Tree24Node<E>(e); // Create a new root for element
            } else {
                // Locate the leaf node for inserting e
                Tree24Node<E> leafNode = null;
                Tree24Node<E> current = root;
                while (current != null) {
                    if (matched(e, current)) {
                        return false; // Duplicate element found, nothing inserted
                    } else {
                        leafNode = current;
                        current = getChildNode(e, current);
                    }
                }

                // Insert the element e into the leaf node
                insert(e, null, leafNode); // The right child of e is null
            }

            size++; // Increase size
            return true; // Element inserted
        }

        /**
         * Insert element e into node u
         */
        private void insert(E e, Tree24Node<E> rightChildOfe,
                Tree24Node<E> u) {
            // Get the search path that leads to element e
            ArrayList<Tree24Node<E>> path = path(e);

            for (int i = path.size() - 1; i >= 0; i--) {
                if (u.elements.size() < 3) { // u is a 2-node or 3-node
                    insert23(e, rightChildOfe, u); // Insert e to node u
                    break; // No further insertion to u's parent needed
                } else {
                    Tree24Node<E> v = new Tree24Node<E>(); // Create a new node
                    E median = split(e, rightChildOfe, u, v); // Split u

                    if (u == root) {
                        root = new Tree24Node<E>(median); // New root
                        root.child.add(u); // u is the left child of median
                        root.child.add(v); // v is the right child of median
                        break; // No further insertion to u's parent needed
                    } else {
                        // Use new values for the next iteration in the for loop
                        e = median; // Element to be inserted to parent
                        rightChildOfe = v; // Right child of the element
                        u = path.get(i - 1); // New node to insert element
                    }
                }
            }
        }

        /**
         * Insert element to a 2- or 3- and return the insertion point
         */
        private void insert23(E e, Tree24Node<E> rightChildOfe,
                Tree24Node<E> node) {
            int i = this.locate(e, node); // Locate where to insert
            node.elements.add(i, e); // Insert the element into the node
            if (rightChildOfe != null) {
                node.child.add(i + 1, rightChildOfe); // Insert the child link
            }
        }

        /**
         * Split a 4-node u into u and v and insert e to u or v
         */
        private E split(E e, Tree24Node<E> rightChildOfe,
                Tree24Node<E> u, Tree24Node<E> v) {
            // Move the last element in node u to node v
            v.elements.add(u.elements.remove(2));
            E median = u.elements.remove(1);

    // Split children for a non-leaf node
            // Move the last two children in node u to node v
            if (u.child.size() > 0) {
                v.child.add(u.child.remove(2));
                v.child.add(u.child.remove(2));
            }

            // Insert e into a 2- or 3- node u or v.
            if (e.compareTo(median) < 0) {
                insert23(e, rightChildOfe, u);
            } else {
                insert23(e, rightChildOfe, v);
            }

            return median; // Return the median element
        }

        /**
         * Return a search path that leads to element e
         */
        private ArrayList<Tree24Node<E>> path(E e) {
            ArrayList<Tree24Node<E>> list = new ArrayList<Tree24Node<E>>();
            Tree24Node<E> current = root; // Start from the root

            while (current != null) {
                list.add(current); // Add the node to the list
                if (matched(e, current)) {
                    break; // Element found
                } else {
                    current = getChildNode(e, current);
                }
            }

            return list; // Return an array of nodes
        }

        @Override
        /**
         * Delete the specified element from the tree
         */
        public boolean delete(E e) {
            // Locate the node that contains the element e
            Tree24Node<E> node = root;
            while (node != null) {
                if (matched(e, node)) {
                    delete(e, node); // Delete element e from node
                    size--; // After one element deleted
                    return true; // Element deleted successfully
                } else {
                    node = getChildNode(e, node);
                }
            }

            return false; // Element not in the tree
        }

        /**
         * Delete the specified element from the node
         */
        private void delete(E e, Tree24Node<E> node) {
            if (node.child.size() == 0) { // e is in a leaf node
                // Get the path that leads to e from the root
                ArrayList<Tree24Node<E>> path = path(e);

                node.elements.remove(e); // Remove element e

                if (node == root) { // Special case
                    if (node.elements.size() == 0) {
                        root = null; // Empty tree
                    }
                    return; // Done
                }

                validate(e, node, path); // Check underflow node
            } else { // e is in an internal node
                // Locate the rightmost node in the left subtree of the node 
                int index = locate(e, node); // Index of e in node
                Tree24Node<E> current = node.child.get(index);
                while (current.child.size() > 0) {
                    current = current.child.get(current.child.size() - 1);
                }
                E rightmostElement
                        = current.elements.get(current.elements.size() - 1);

                // Get the path that leads to e from the root
                ArrayList<Tree24Node<E>> path = path(rightmostElement);

                // Replace the deleted element with the rightmost element
                node.elements.set(index, current.elements.remove(
                        current.elements.size() - 1));

                validate(rightmostElement, current, path); // Check underflow
            }
        }

        /**
         * Perform transfer and confusion operations if necessary
         */
        private void validate(E e, Tree24Node<E> u,
                ArrayList<Tree24Node<E>> path) {
            for (int i = path.size() - 1; u.elements.size() == 0; i--) {
                Tree24Node<E> parentOfu = path.get(i - 1); // Get parent of u
                int k = locate(e, parentOfu); // Index of e in the parent node

                // Check two siblings
                if (k > 0 && parentOfu.child.get(k - 1).elements.size() > 1) {
                    leftSiblingTransfer(k, u, parentOfu);
                } else if (k + 1 < parentOfu.child.size()
                        && parentOfu.child.get(k + 1).elements.size() > 1) {
                    rightSiblingTransfer(k, u, parentOfu);
                } else if (k - 1 >= 0) { // Fusion with a left sibling
                    // Get left sibling of node u 
                    Tree24Node<E> leftNode = parentOfu.child.get(k - 1);

                    // Perform a fusion with left sibling on node u
                    leftSiblingFusion(k, leftNode, u, parentOfu);

                    // Done when root becomes empty
                    if (parentOfu == root && parentOfu.elements.size() == 0) {
                        root = leftNode;
                        break;
                    }

                    u = parentOfu; // Back to the loop to check the parent node
                } else { // Fusion with right sibling (right sibling must exist)
                    // Get left sibling of node u 
                    Tree24Node<E> rightNode = parentOfu.child.get(k + 1);

                    // Perform a fusion with right sibling on node u
                    rightSiblingFusion(k, rightNode, u, parentOfu);

                    // Done when root becomes empty
                    if (parentOfu == root && parentOfu.elements.size() == 0) {
                        root = rightNode;
                        break;
                    }

                    u = parentOfu; // Back to the loop to check the parent node
                }
            }
        }

        /**
         * Locate the insertion point of the element in the node
         */
        private int locate(E o, Tree24Node<E> node) {
            for (int i = 0; i < node.elements.size(); i++) {
                if (o.compareTo(node.elements.get(i)) <= 0) {
                    return i;
                }
            }

            return node.elements.size();
        }

        /**
         * Perform a transfer with a left sibling
         */
        private void leftSiblingTransfer(int k,
                Tree24Node<E> u, Tree24Node<E> parentOfu) {
            // Move an element from the parent to u
            u.elements.add(0, parentOfu.elements.get(k - 1));

            // Move an element from the left node to the parent
            Tree24Node<E> leftNode = parentOfu.child.get(k - 1);
            parentOfu.elements.set(k - 1,
                    leftNode.elements.remove(leftNode.elements.size() - 1));

            // Move the child link from left sibling to the node
            if (leftNode.child.size() > 0) {
                u.child.add(0, leftNode.child.remove(
                        leftNode.child.size() - 1));
            }
        }

        /**
         * Perform a transfer with a right sibling
         */
        private void rightSiblingTransfer(int k,
                Tree24Node<E> u, Tree24Node<E> parentOfu) {
            // Transfer an element from the parent to u
            u.elements.add(parentOfu.elements.get(k));

            // Transfer an element from the right node to the parent
            Tree24Node<E> rightNode = parentOfu.child.get(k + 1);
            parentOfu.elements.set(k, rightNode.elements.remove(0));

            // Move the child link from right sibling to the node
            if (rightNode.child.size() > 0) {
                u.child.add(rightNode.child.remove(0));
            }
        }

        /**
         * Perform a fusion with a left sibling
         */
        private void leftSiblingFusion(int k, Tree24Node<E> leftNode,
                Tree24Node<E> u, Tree24Node<E> parentOfu) {
            // Transfer an element from the parent to the left sibling    
            leftNode.elements.add(parentOfu.elements.remove(k - 1));

            // Remove the link to the empty node
            parentOfu.child.remove(k);

            // Adjust child links for non-leaf node
            if (u.child.size() > 0) {
                leftNode.child.add(u.child.remove(0));
            }
        }

        /**
         * Perform a fusion with a right sibling
         */
        private void rightSiblingFusion(int k, Tree24Node<E> rightNode,
                Tree24Node<E> u, Tree24Node<E> parentOfu) {
            // Transfer an element from the parent to the right sibling
            rightNode.elements.add(0, parentOfu.elements.remove(k));

            // Remove the link to the empty node
            parentOfu.child.remove(k);

            // Adjust child links for non-leaf node
            if (u.child.size() > 0) {
                rightNode.child.add(0, u.child.remove(0));
            }
        }

        @Override
        /**
         * Get the number of nodes in the tree
         */
        public int getSize() {
            return size;
        }

        @Override
        /**
         * Preorder traversal from the root
         */
        public void preorder() {
            preorder(root);
        }

        /**
         * Preorder traversal from a subtree
         */
        private void preorder(Tree24Node<E> root) {
            if (root == null) {
                return;
            }
            for (int i = 0; i < root.elements.size(); i++) {
                System.out.print(root.elements.get(i) + " ");
            }

            for (int i = 0; i < root.child.size(); i++) {
                preorder(root.child.get(i));
            }
        }

        @Override
        /**
         * Inorder traversal from the root
         */
        public void inorder() {
            inorder(root);
        }
        
        protected void inorder(Tree24Node<E> root) {
            if (root == null) {
                return;
            }
            for (int i = 0; i < root.elements.size(); i++) {
                if(i < root.child.size()) {
                    inorder(root.child.get(i));
                }
                System.out.print(root.elements.get(i) + " ");
            }
            if(root.elements.size() < root.child.size()) {
                inorder(root.child.get(root.child.size() - 1));
            }
        }
        

        /**
         * Postorder traversal from the root
         */
        public void postorder() {
            postorder(root);
        }
        
        private void postorder(Tree24Node<E> root) {
            if (root == null) {
                return;
            }
            for (int i = 0; i < root.child.size(); i++) {
                postorder(root.child.get(i));
            }            
            for (int i = 0; i < root.elements.size(); i++) {
                System.out.print(root.elements.get(i) + " ");
            }
        }   

        @Override
        /**
         * Return true if the tree is empty
         */
        public boolean isEmpty() {
            return root == null;
        }

        @Override
        /**
         * Return an iterator to traverse elements in the tree
         */
        public java.util.Iterator<E> iterator() {
            // Left as exercise
            return null;
        }

        /**
         * Define a 2-4 tree node
         */
        protected static class Tree24Node<E extends Comparable<E>> {

            // elements has maximum three values

            ArrayList<E> elements = new ArrayList<E>(3);
            // Each has maximum four childres
            ArrayList<Tree24Node<E>> child  = new ArrayList<Tree24Node<E>>(4);

            /**
             * Create an empty Tree24 node
             */
            Tree24Node() {
            }

            /**
             * Create a Tree24 node with an initial element
             */
            Tree24Node(E o) {
                elements.add(o);
            }
        }
    }    
    public static class RBTree<E extends Comparable<E>> extends BST<E> {

        /**
         * Create a default RB tree
         */
        public RBTree() {
        }

        /**
         * Create an RB tree from an array of elements
         */
        public RBTree(E[] elements) {
            super(elements);
        }

        /**
         * Override createNewNode to create an RBTreeNode
         */
        protected RBTreeNode<E> createNewNode(E e) {
            return new RBTreeNode<E>(e);
        }

        @Override
        /**
         * Override the insert method to balance the tree if necessary
         */
        public boolean insert(E e) {
            boolean successful = super.insert(e);
            if (!successful) {
                return false; // e is already in the tree
            } else {
                ensureRBTree(e);
            }

            return true; // e is inserted
        }

        /**
         * Ensure that the tree is a red-black tree
         */
        private void ensureRBTree(E e) {
            // Get the path that leads to element e from the root 
            ArrayList<TreeNode<E>> path = path(e);

            int i = path.size() - 1; // Index to the current node in the path

            // u is the last node in the path. u contains element e
            RBTreeNode<E> u = (RBTreeNode<E>) (path.get(i));

            // v is the parent of of u, if exists
            RBTreeNode<E> v = (u == root) ? null
                    : (RBTreeNode<E>) (path.get(i - 1));

            u.setRed(); // It is OK to set u red    

            if (u == root) // If e is inserted as the root, set root black
            {
                u.setBlack();
            } else if (v.isRed()) {
                fixDoubleRed(u, v, path, i); // Fix double red violation at u
            }
        }

        /**
         * Fix double red violation at node u
         */
        private void fixDoubleRed(RBTreeNode<E> u, RBTreeNode<E> v,
                ArrayList<TreeNode<E>> path, int i) {
            // w is the grandparent of u
            RBTreeNode<E> w = (RBTreeNode<E>) (path.get(i - 2));
            RBTreeNode<E> parentOfw = (w == root) ? null
                    : (RBTreeNode<E>) path.get(i - 3);

            // Get v's sibling named x
            RBTreeNode<E> x = (w.left == v)
                    ? (RBTreeNode<E>) (w.right) : (RBTreeNode<E>) (w.left);

            if (x == null || x.isBlack()) {
                // Case 1: v's sibling x is black
                if (w.left == v && v.left == u) {
                    // Case 1.1: u < v < w, Restructure and recolor nodes
                    restructureRecolor(u, v, w, w, parentOfw);

                    w.left = v.right; // v.right is y3 in Figure 11.7
                    v.right = w;
                } else if (w.left == v && v.right == u) {
                    // Case 1.2: v < u < w, Restructure and recolor nodes
                    restructureRecolor(v, u, w, w, parentOfw);
                    v.right = u.left;
                    w.left = u.right;
                    u.left = v;
                    u.right = w;
                } else if (w.right == v && v.right == u) {
                    // Case 1.3: w < v < u, Restructure and recolor nodes
                    restructureRecolor(w, v, u, w, parentOfw);
                    w.right = v.left;
                    v.left = w;
                } else {
                    // Case 1.4: w < u < v, Restructure and recolor nodes
                    restructureRecolor(w, u, v, w, parentOfw);
                    w.right = u.left;
                    v.left = u.right;
                    u.left = w;
                    u.right = v;
                }
            } else { // Case 2: v's sibling x is red 
                // Recolor nodes
                w.setRed();
                u.setRed();
                ((RBTreeNode<E>) (w.left)).setBlack();
                ((RBTreeNode<E>) (w.right)).setBlack();

                if (w == root) {
                    w.setBlack();
                } else if (((RBTreeNode<E>) parentOfw).isRed()) {
                    // Propagate along the path to fix new double red violation
                    u = w;
                    v = (RBTreeNode<E>) parentOfw;
                    fixDoubleRed(u, v, path, i - 2); // i – 2 propagates upward
                }
            }
        }

        /**
         * Connect b with parentOfw and recolor a, b, c for a < b < c
         */
        private void restructureRecolor(RBTreeNode<E> a, RBTreeNode<E> b,
                RBTreeNode<E> c, RBTreeNode<E> w, RBTreeNode<E> parentOfw) {
            if (parentOfw == null) {
                root = b;
            } else if (parentOfw.left == w) {
                parentOfw.left = b;
            } else {
                parentOfw.right = b;
            }

            b.setBlack(); // b becomes the root in the subtree
            a.setRed(); // a becomes the left child of b
            c.setRed(); // c becomes the right child of b
        }

        @Override
        /**
         * Delete an element from the RBTree. Return true if the element is
         * deleted successfully Return false if the element is not in the tree
         */
        public boolean delete(E e) {
            // Locate the node to be deleted
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                } else {
                    break; // Element is in the tree pointed by current
                }
            }

            if (current == null) {
                return false; // Element is not in the tree
            }
            java.util.ArrayList<TreeNode<E>> path;

            // current node is an internal node 
            if (current.left != null && current.right != null) {
                // Locate the rightmost node in the left subtree of current
                TreeNode<E> rightMost = current.left;
                while (rightMost.right != null) {
                    rightMost = rightMost.right; // Keep going to the right
                }

                path = path(rightMost.element); // Get path before replacement

                // Replace the element in current by the element in rightMost
                current.element = rightMost.element;
            } else {
                path = path(e); // Get path to current node
            }
            // Delete the last node in the path and propagate if needed
            deleteLastNodeInPath(path);

            size--; // After one element deleted
            return true; // Element deleted
        }

        /**
         * Delete the last node from the path.
         */
        public void deleteLastNodeInPath(ArrayList<TreeNode<E>> path) {
            int i = path.size() - 1; // Index to the node in the path
            // u is the last node in the path
            RBTreeNode<E> u = (RBTreeNode<E>) (path.get(i));
            RBTreeNode<E> parentOfu = (u == root) ? null
                    : (RBTreeNode<E>) (path.get(i - 1));
            RBTreeNode<E> grandparentOfu = (parentOfu == null
                    || parentOfu == root) ? null
                    : (RBTreeNode<E>) (path.get(i - 2));
            RBTreeNode<E> childOfu = (u.left == null)
                    ? (RBTreeNode<E>) (u.right) : (RBTreeNode<E>) (u.left);

            // Delete node u. Connect childOfu with parentOfu
            connectNewParent(parentOfu, u, childOfu);

            // Recolor the nodes and fix double black if needed
            if (childOfu == root || u.isRed()) {
                return; // Done if childOfu is root or if u is red 
            } else if (childOfu != null && childOfu.isRed()) {
                childOfu.setBlack(); // Set it black, done
            } else // u is black, childOfu is null or black
            // Fix double black on parentOfu
            {
                fixDoubleBlack(grandparentOfu, parentOfu, childOfu, path, i);
            }
        }

        /**
         * Fix the double black problem at node parent
         */
        private void fixDoubleBlack(
                RBTreeNode<E> grandparent, RBTreeNode<E> parent,
                RBTreeNode<E> db, ArrayList<TreeNode<E>> path, int i) {
            // Obtain y, y1, and y2
            RBTreeNode<E> y = (parent.right == db)
                    ? (RBTreeNode<E>) (parent.left) : (RBTreeNode<E>) (parent.right);
            RBTreeNode<E> y1 = (RBTreeNode<E>) (y.left);
            RBTreeNode<E> y2 = (RBTreeNode<E>) (y.right);

            if (y.isBlack() && y1 != null && y1.isRed()) {
                if (parent.right == db) {
                    // Case 1.1: y is a left black sibling and y1 is red
                    connectNewParent(grandparent, parent, y);
                    recolor(parent, y, y1); // Adjust colors

                    // Adjust child links
                    parent.left = y.right;
                    y.right = parent;
                } else {
                    // Case 1.3: y is a right black sibling and y1 is red        
                    connectNewParent(grandparent, parent, y1);
                    recolor(parent, y1, y); // Adjust colors

                    // Adjust child links
                    parent.right = y1.left;
                    y.left = y1.right;
                    y1.left = parent;
                    y1.right = y;
                }
            } else if (y.isBlack() && y2 != null && y2.isRed()) {
                if (parent.right == db) {
                    // Case 1.2: y is a left black sibling and y2 is red
                    connectNewParent(grandparent, parent, y2);
                    recolor(parent, y2, y); // Adjust colors

                    // Adjust child links
                    y.right = y2.left;
                    parent.left = y2.right;
                    y2.left = y;
                    y2.right = parent;
                } else {
                    // Case 1.4: y is a right black sibling and y2 is red        
                    connectNewParent(grandparent, parent, y);
                    recolor(parent, y, y2); // Adjust colors

                    // Adjust child links
                    y.left = parent;
                    parent.right = y1;
                }
            } else if (y.isBlack()) {
                // Case 2: y is black and y's children are black or null
                y.setRed(); // Change y to red
                if (parent.isRed()) {
                    parent.setBlack(); // Done
                } else if (parent != root) {
                    // Propagate double black to the parent node
                    // Fix new appearance of double black recursively
                    db = parent;
                    parent = grandparent;
                    grandparent
                            = (i >= 3) ? (RBTreeNode<E>) (path.get(i - 3)) : null;
                    fixDoubleBlack(grandparent, parent, db, path, i - 1);
                }
            } else { // y.isRed()
                if (parent.right == db) {
                    // Case 3.1: y is a left red child of parent
                    parent.left = y2;
                    y.right = parent;
                } else {
                    // Case 3.2: y is a right red child of parent
                    parent.right = y.left;
                    y.left = parent;
                }

                parent.setRed(); // Color parent red
                y.setBlack(); // Color y black
                connectNewParent(grandparent, parent, y); // y is new parent
                fixDoubleBlack(y, parent, db, path, i - 1);
            }
        }

        /**
         * Recolor parent, newParent, and c. Case 1 removal
         */
        private void recolor(RBTreeNode<E> parent,
                RBTreeNode<E> newParent, RBTreeNode<E> c) {
            // Retain the parent's color for newParent
            if (parent.isRed()) {
                newParent.setRed();
            } else {
                newParent.setBlack();
            }

            // c and parent become the children of newParent, set them black
            parent.setBlack();
            c.setBlack();
        }

        /**
         * Connect newParent with grandParent
         */
        private void connectNewParent(RBTreeNode<E> grandparent,
                RBTreeNode<E> parent, RBTreeNode<E> newParent) {
            if (parent == root) {
                root = newParent;
                if (root != null) {
                    newParent.setBlack();
                }
            } else if (grandparent.left == parent) {
                grandparent.left = newParent;
            } else {
                grandparent.right = newParent;
            }
        }

        @Override
        /**
         * Preorder traversal from a subtree
         */
        protected void preorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            System.out.print(root.element  + (((RBTreeNode<E>) root).isRed() ? " (red) " : " (black) "));
            preorder(root.left);
            preorder(root.right);
        }

        

        
        
        /**
         * RBTreeNode is TreeNode plus color indicator
         */
        protected static class RBTreeNode<E extends Comparable<E>> extends BST.TreeNode<E> {

            private boolean red = true; // Indicate node color

            public RBTreeNode(E e) {
                super(e);
            }

            public boolean isRed() {
                return red;
            }

            public boolean isBlack() {
                return !red;
            }

            public void setBlack() {
                red = false;
            }

            public void setRed() {
                red = true;
            }

            int blackHeight;
        }
    }

    static class BST<E extends Comparable<E>> extends AbstractTree<E> {

        protected TreeNode<E> root;
        protected int size = 0;

        public void inorder2() {
            if (root == null) {
                return;
            }

            LinkedList<TreeNode<E>> list = new LinkedList<>();
            LinkedList<TreeNode<E>> stack = new LinkedList<>();
            stack.add(root);

            while (!stack.isEmpty()) {
                TreeNode<E> node = stack.getFirst();
                if ((node.left != null) && (!list.contains(node.left))) {
                    stack.push(node.left);
                } else {
                    stack.removeFirst();
                    list.add(node);
                    if (node.right != null) {
                        stack.addFirst(node.right);
                    }
                }
            }
            for (TreeNode<E> treeNode : list) {
                System.out.print(treeNode.element + " ");
            }
        }

        public boolean isFullBST() {
            return size == Math.round(Math.pow(2, height()) - 1);
        }

        /**
         * Returns the height of this binary tree, i.e., the number of the nodes
         * in the longest path of the root to a leaf
         */
        public int height() {
            return height(root);
        }

        public int height(TreeNode<E> node) {
            if (node == null) {
                return 0;
            } else {
                return 1 + Math.max(height(node.left), height(node.right));
            }
        }

        /**
         * Create a default binary tree
         */
        public BST() {
        }

        /**
         * Create a binary tree from an array of objects
         */
        public BST(E[] objects) {
            for (int i = 0; i < objects.length; i++) {
                insert(objects[i]);
            }
        }

        /**
         * Returns true if the element is in the tree
         */
        public ArrayList<E> searchPath(E e) {
            TreeNode<E> current = root; // Start from the root
            ArrayList<E> result = new ArrayList<>();
            while (current != null) {
                result.add(current.element);
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                } else {
                    return result;
                }
            }
            return null;
        }

        @Override
        /**
         * Returns true if the element is in the tree
         */
        public boolean search(E e) {
            TreeNode<E> current = root; // Start from the root

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                } else // element matches current.element
                {
                    return true; // Element is found
                }
            }

            return false;
        }

        @Override
        /**
         * Insert element o into the binary tree Return true if the element is
         * inserted successfully
         */
        public boolean insert(E e) {
            if (root == null) {
                root = createNewNode(e); // Create a new root
            } else {
                // Locate the parent node
                TreeNode<E> parent = null;
                TreeNode<E> current = root;
                while (current != null) {
                    if (e.compareTo(current.element) < 0) {
                        parent = current;
                        current = current.left;
                    } else if (e.compareTo(current.element) > 0) {
                        parent = current;
                        current = current.right;
                    } else {
                        return false; // Duplicate node not inserted
                    }
                }
                // Create the new node and attach it to the parent node
                if (e.compareTo(parent.element) < 0) {
                    parent.left = createNewNode(e);
                } else {
                    parent.right = createNewNode(e);
                }
            }

            size++;
            return true; // Element inserted
        }

        protected TreeNode<E> createNewNode(E e) {
            return new TreeNode<E>(e);
        }

        @Override
        /**
         * Inorder traversal from the root
         */
        public void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        protected void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            System.out.print(root.element + " ");
            inorder(root.right);
        }

        @Override
        /**
         * Postorder traversal from the root
         */
        public void postorder() {
            postorder(root);
        }

        /**
         * Postorder traversal from a subtree
         */
        protected void postorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.element + " ");
        }

        @Override
        /**
         * Preorder traversal from the root
         */
        public void preorder() {
            preorder(root);
        }

        /**
         * Preorder traversal from a subtree
         */
        protected void preorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
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

            public TreeNode(E e) {
                element = e;
            }
        }

        @Override
        /**
         * Get the number of nodes in the tree
         */
        public int getSize() {
            return size;
        }

        /**
         * Returns the root of the tree
         */
        public TreeNode<E> getRoot() {
            return root;
        }

        /**
         * Returns a path from the root leading to the specified element
         */
        public java.util.ArrayList<TreeNode<E>> path(E e) {
            java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
            TreeNode<E> current = root; // Start from the root

            while (current != null) {
                list.add(current); // Add the node to the list
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                } else {
                    break;
                }
            }

            return list; // Return an array of nodes
        }

        @Override
        /**
         * Delete an element from the binary tree. Return true if the element is
         * deleted successfully Return false if the element is not in the tree
         */
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
                } else {
                    break; // Element is in the tree pointed at by current
                }
            }

            if (current == null) {
                return false; // Element is not in the tree
            }
            // Case 1: current has no left children
            if (current.left == null) {
                // Connect the parent with the right child of the current node
                if (parent == null) {
                    root = current.right;
                } else {
                    if (e.compareTo(parent.element) < 0) {
                        parent.left = current.right;
                    } else {
                        parent.right = current.right;
                    }
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
                if (parentOfRightMost.right == rightMost) {
                    parentOfRightMost.right = rightMost.left;
                } else // Special case: parentOfRightMost == current
                {
                    parentOfRightMost.left = rightMost.left;
                }
            }

            size--;
            return true; // Element inserted
        }

        @Override
        /**
         * Obtain an iterator. Use inorder.
         */
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

            /**
             * Inorder traversal from the root
             */
            private void inorder() {
                inorder(root);
            }

            /**
             * Inorder traversal from a subtree
             */
            private void inorder(TreeNode<E> root) {
                if (root == null) {
                    return;
                }
                inorder(root.left);
                list.add(root.element);
                inorder(root.right);
            }

            @Override
            /**
             * More elements for traversing?
             */
            public boolean hasNext() {
                if (current < list.size()) {
                    return true;
                }

                return false;
            }

            @Override
            /**
             * Get the current element and move to the next
             */
            public E next() {
                return list.get(current++);
            }

            @Override
            /**
             * Remove the current element
             */
            public void remove() {
                delete(list.get(current)); // Delete the current element
                list.clear(); // Clear the list
                inorder(); // Rebuild the list
            }
        }

        /**
         * Remove all elements from the tree
         */
        public void clear() {
            root = null;
            size = 0;
        }
    }

    static abstract class AbstractTree<E> implements Tree<E> {

        @Override
        /**
         * Inorder traversal from the root
         */
        public void inorder() {
        }

        @Override
        /**
         * Postorder traversal from the root
         */
        public void postorder() {
        }

        @Override
        /**
         * Preorder traversal from the root
         */
        public void preorder() {
        }

        @Override
        /**
         * Return true if the tree is empty
         */
        public boolean isEmpty() {
            return getSize() == 0;
        }

        @Override
        /**
         * Return an iterator for the tree
         */
        public java.util.Iterator<E> iterator() {
            return null;
        }
    }

    interface Tree<E> extends Iterable<E> {

        /**
         * Return true if the element is in the tree
         */
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

        /**
         * Inorder traversal from the root
         */
        public void inorder();

        /**
         * Postorder traversal from the root
         */
        public void postorder();

        /**
         * Preorder traversal from the root
         */
        public void preorder();

        /**
         * Get the number of nodes in the tree
         */
        public int getSize();

        /**
         * Return true if the tree is empty
         */
        public boolean isEmpty();

        public java.util.Iterator<E> iterator();
    }
}
