package net.bohush.exercises.chapter47;

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
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exercise05 extends JApplet {

    private JTextField jtfKey = new JTextField(7);
    private Tree24<Integer> tree = new Tree24<>();
    private Tree24Panel tree24Panel = new Tree24Panel(tree); 
    
    public Exercise05() throws HeadlessException {
        for (int i = 0; i < 30; i++) {
            tree.insert((int)(Math.random() * 100));
        }
        tree24Panel.repaint();
        setLayout(new BorderLayout(5, 5));
        add(tree24Panel, BorderLayout.CENTER);
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
                    tree24Panel.repaint();
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
                        tree24Panel.repaint();
                        jtfKey.setText("");                             
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, jtfKey.getText() + " is not in the tree", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                jtfKey.requestFocus();
            }
        });
    }
    
    class Tree24Panel extends JPanel {
        
        private Tree24 tree;
        
        public Tree24Panel(Tree24 tree) {
            this.tree = tree;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
            g.setColor(Color.BLACK);
            if(!tree.isEmpty()) {
                drawNode(g, tree.root, getWidth() / 2, 10, getWidth());
            }
        }
        
        private void drawNode(Graphics g, Tree24.Tree24Node node, int x, int y, int width) {
            String elements = "";
            for (Object element : node.elements) {
                elements += element + "  ";
            }
            
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(elements);
            int textHeight = fm.getHeight();
            
            for (int i = 0; i < node.child.size(); i++) {
                int nextX = x - width / 2 + (int)(width / node.child.size() * (i + 0.5));
                int nextY = y + 50;
                g.drawLine(x, y + textHeight / 2, nextX, nextY + textHeight / 2);
                drawNode(g, (Tree24.Tree24Node)node.child.get(i), nextX, nextY, width / node.child.size());
            }

            g.setColor(getBackground());
            g.fillRect(x - textWidth / 2 - 2, y + 2, textWidth, textHeight);
            g.setColor(Color.BLACK);
            g.drawString(elements, x - textWidth / 2, y + textHeight);
            g.drawRoundRect(x - textWidth / 2 - 2, y + 2, textWidth, textHeight, 5, 5);
        }
        
    }
    
    public static void main(String[] args) {
        Exercise05 applet = new Exercise05();
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(applet, BorderLayout.CENTER);
        frame.setTitle("Exercise05");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
