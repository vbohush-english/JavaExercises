package net.bohush.exercises.chapter48;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exercise03 extends JApplet {

    private JTextField jtfKey = new JTextField(7);
    private RBTree<Integer> tree = new RBTree<>();
    private RBTreePanel rbTreePanel = new RBTreePanel(tree); 
    
    public Exercise03() throws HeadlessException {
        for (int i = 0; i < 50; i++) {
            tree.insert((int)(Math.random() * 100));
        }
        rbTreePanel.repaint();
        setLayout(new BorderLayout(5, 5));
        add(rbTreePanel, BorderLayout.CENTER);
        JPanel jpControl = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        jpControl.add(new JLabel("Enter a key:"));
        jpControl.add(jtfKey);
        JButton jbSearch = new JButton("Search");
        jpControl.add(jbSearch);
        JButton jbInsert = new JButton("Insert");
        jpControl.add(jbInsert);
        JButton jbDelete = new JButton("Delete");
        jpControl.add(jbDelete);        
        add(jpControl, BorderLayout.SOUTH);
        jbSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(tree.search(Integer.parseInt(jtfKey.getText()))) {
                        JOptionPane.showMessageDialog(null, jtfKey.getText() + " is in the tree", "Search", JOptionPane.INFORMATION_MESSAGE);                        
                    } else {
                        JOptionPane.showMessageDialog(null, jtfKey.getText() + " is not in the tree", "Search", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, jtfKey.getText() + " is not in the tree", "Search", JOptionPane.INFORMATION_MESSAGE);
                }
                jtfKey.requestFocus();
            }
        });
        jbInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tree.insert(Integer.parseInt(jtfKey.getText()));
                    rbTreePanel.repaint();
                    jtfKey.setText("");     
                } catch (NumberFormatException ex) {
                }
                jtfKey.requestFocus();
            }
        });
        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!tree.delete(Integer.parseInt(jtfKey.getText()))) {
                        JOptionPane.showMessageDialog(null, jtfKey.getText() + " is not in the tree", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        rbTreePanel.repaint();
                        jtfKey.setText("");                             
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, jtfKey.getText() + " is not in the tree", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                jtfKey.requestFocus();
            }
        });
    }
    
    class RBTreePanel extends JPanel {
        
        private RBTree tree;
        
        public RBTreePanel(RBTree tree) {
            this.tree = tree;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
            g.setColor(Color.BLACK);
            if(!tree.isEmpty()) {
                drawNode(g, (RBTree.RBTreeNode) tree.root, getWidth() / 2, 10, getWidth());
            }
        }
        
        private void drawNode(Graphics g, RBTree.RBTreeNode node, int x, int y, int width) {
            String element = node.element + " ";
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(element);
            int textHeight = fm.getHeight();
            
            if(node.left != null) {
                g.drawLine(x, y + textHeight / 2, x - width / 4, y + 50 + textHeight / 2);
                drawNode(g, (RBTree.RBTreeNode)node.left, x - width / 4, y + 50, width / 2);
            }

            if(node.right != null) {
                g.drawLine(x, y + textHeight / 2, x + width / 4, y + 50 + textHeight / 2);
                drawNode(g, (RBTree.RBTreeNode)node.right, x + width / 4, y + 50, width / 2);
            }

            if(node.isRed()) {
                g.setColor(Color.RED);
            }
            g.fillRoundRect(x - textWidth / 2 - 2, y + 2, textWidth, textHeight, 5, 5);
            g.setColor(Color.WHITE);
            g.drawString(element, x - textWidth / 2, y + textHeight);
            g.setColor(Color.BLACK);
        }
        
    }
    
    public static void main(String[] args) {
        Exercise03 applet = new Exercise03();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(applet, BorderLayout.CENTER);
        frame.setTitle("Exercise03");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
