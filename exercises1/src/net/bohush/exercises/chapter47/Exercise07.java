package net.bohush.exercises.chapter47;

import java.util.ArrayList;

public class Exercise07 {

    public static void main(String[] args) {
        BTree<Integer> tree = new BTree<Integer>();
        for (int i = 1; i <= 100; i++) {
            tree.insert(i);
        }
        printTree(tree);
    }

    public static void printTree(Tree tree) {
        // Traverse tree
        System.out.print("\nPreorder: ");
        tree.preorder();
        System.out.print("\nInorder: ");
        tree.inorder();
        System.out.print("\nThe number of nodes is " + tree.getSize());
        System.out.println();
    }    
    
    static class BTree<E extends Comparable<E>> implements Tree<E> {

        private BTreeNode<E> root;
        private int size;
        private int order = 4;

        public BTree() {
        }

        public BTree(int order) {
            this.order = order;
        }
        
        public BTree(E[] elements) {
            for (int i = 0; i < elements.length; i++) {
                insert(elements[i]);
            }
        }
        
        public BTree(int order, E[] elements) {
            this.order = order;
            for (int i = 0; i < elements.length; i++) {
                insert(elements[i]);
            }
        }

        @Override
        /**
         * Search an element in the tree
         */
        public boolean search(E e) {
            BTreeNode<E> current = root; // Start from the root

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
        private boolean matched(E e, BTreeNode<E> node) {
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
        private BTreeNode<E> getChildNode(E e, BTreeNode<E> node) {
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
                root = new BTreeNode<E>(e); // Create a new root for element
            } else {
                // Locate the leaf node for inserting e
                BTreeNode<E> leafNode = null;
                BTreeNode<E> current = root;
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
        private void insert(E e, BTreeNode<E> rightChildOfe, BTreeNode<E> u) {
            // Get the search path that leads to element e
            ArrayList<BTreeNode<E>> path = path(e);

            for (int i = path.size() - 1; i >= 0; i--) {
                if (u.elements.size() < (order - 1)) { // u is a 2-node or 3-node
                    insert23(e, rightChildOfe, u); // Insert e to node u
                    break; // No further insertion to u's parent needed
                } else {
                    BTreeNode<E> v = new BTreeNode<E>(); // Create a new node
                    E median = split(e, rightChildOfe, u, v); // Split u

                    if (u == root) {
                        root = new BTreeNode<E>(median); // New root
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
        private void insert23(E e, BTreeNode<E> rightChildOfe,  BTreeNode<E> node) {
            int i = this.locate(e, node); // Locate where to insert
            node.elements.add(i, e); // Insert the element into the node
            if (rightChildOfe != null) {
                node.child.add(i + 1, rightChildOfe); // Insert the child link
            }
        }

        /**
         * Split a 4-node u into u and v and insert e to u or v
         */
        private E split(E e, BTreeNode<E> rightChildOfe, BTreeNode<E> u, BTreeNode<E> v) {
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
        private ArrayList<BTreeNode<E>> path(E e) {
            ArrayList<BTreeNode<E>> list = new ArrayList<BTreeNode<E>>();
            BTreeNode<E> current = root; // Start from the root

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
            BTreeNode<E> node = root;
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
        private void delete(E e, BTreeNode<E> node) {
            if (node.child.size() == 0) { // e is in a leaf node
                // Get the path that leads to e from the root
                ArrayList<BTreeNode<E>> path = path(e);

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
                BTreeNode<E> current = node.child.get(index);
                while (current.child.size() > 0) {
                    current = current.child.get(current.child.size() - 1);
                }
                E rightmostElement = current.elements.get(current.elements.size() - 1);

                // Get the path that leads to e from the root
                ArrayList<BTreeNode<E>> path = path(rightmostElement);

                // Replace the deleted element with the rightmost element
                node.elements.set(index, current.elements.remove(
                        current.elements.size() - 1));

                validate(rightmostElement, current, path); // Check underflow
            }
        }

        /**
         * Perform transfer and confusion operations if necessary
         */
        private void validate(E e, BTreeNode<E> u, ArrayList<BTreeNode<E>> path) {
            for (int i = path.size() - 1; u.elements.size() == 0; i--) {
                BTreeNode<E> parentOfu = path.get(i - 1); // Get parent of u
                int k = locate(e, parentOfu); // Index of e in the parent node

                // Check two siblings
                if (k > 0 && parentOfu.child.get(k - 1).elements.size() > 1) {
                    leftSiblingTransfer(k, u, parentOfu);
                } else if (k + 1 < parentOfu.child.size()
                        && parentOfu.child.get(k + 1).elements.size() > 1) {
                    rightSiblingTransfer(k, u, parentOfu);
                } else if (k - 1 >= 0) { // Fusion with a left sibling
                    // Get left sibling of node u 
                    BTreeNode<E> leftNode = parentOfu.child.get(k - 1);

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
                    BTreeNode<E> rightNode = parentOfu.child.get(k + 1);

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
        private int locate(E o, BTreeNode<E> node) {
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
        private void leftSiblingTransfer(int k, BTreeNode<E> u, BTreeNode<E> parentOfu) {
            // Move an element from the parent to u
            u.elements.add(0, parentOfu.elements.get(k - 1));

            // Move an element from the left node to the parent
            BTreeNode<E> leftNode = parentOfu.child.get(k - 1);
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
        private void rightSiblingTransfer(int k, BTreeNode<E> u, BTreeNode<E> parentOfu) {
            // Transfer an element from the parent to u
            u.elements.add(parentOfu.elements.get(k));

            // Transfer an element from the right node to the parent
            BTreeNode<E> rightNode = parentOfu.child.get(k + 1);
            parentOfu.elements.set(k, rightNode.elements.remove(0));

            // Move the child link from right sibling to the node
            if (rightNode.child.size() > 0) {
                u.child.add(rightNode.child.remove(0));
            }
        }

        /**
         * Perform a fusion with a left sibling
         */
        private void leftSiblingFusion(int k, BTreeNode<E> leftNode, BTreeNode<E> u, BTreeNode<E> parentOfu) {
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
        private void rightSiblingFusion(int k, BTreeNode<E> rightNode, BTreeNode<E> u, BTreeNode<E> parentOfu) {
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
        private void preorder(BTreeNode<E> root) {
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
            // Left as exercise
        }
      
        /**
         * Postorder traversal from the root
         */
        public void postorder() {
            // Left as exercise
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
         * Define a tree node
         */
        protected class BTreeNode<E extends Comparable<E>> {

            // elements has maximum three values

            ArrayList<E> elements = new ArrayList<E>(order - 1);
            // Each has maximum four childres
            ArrayList<BTreeNode<E>> child  = new ArrayList<BTreeNode<E>>(order);

            /**
             * Create an empty Tree24 node
             */
            BTreeNode() {
            }

            /**
             * Create a Tree24 node with an initial element
             */
            BTreeNode(E o) {
                elements.add(o);
            }
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
