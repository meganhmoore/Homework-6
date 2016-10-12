/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_binarytrees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 *
 * @author ogm2
 */
public class BinarySearchTree<E extends Comparable<E>> implements ExtendedTreeIF<E> {

    /**
     * The root node of the BST.
     */
    BSTNode root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(BSTNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    @Override
    public void add(E value) {
        if (root == null) {
            root = new BSTNode<>(value);
        } else {
            root.add(value);
        }
    }

    @Override
    public void displayInOrder() {
        if (this.isEmpty()) {
            System.out.println("Tree is empty");
        } else {
            System.out.println("In Order - Root Value " + root.getValue());
            root.displayInOrder();
            System.out.println("");
        }
    }

    @Override
    public void displayPostOrder() {
        if (this.isEmpty()) {
            System.out.println("Tree is empty");
        } else {
            System.out.println("Post Order - Root Value " + root.getValue());
            root.displayPostOrder();
            System.out.println("");
        }
    }

    @Override
    public void displayPreOrder() {
        if (this.isEmpty()) {
            System.out.println("Tree is empty");
        } else {
            System.out.println("Pre Order - Root Value " + root.getValue());
            root.displayPreOrder();
            System.out.println("");
        }
    }

    @Override
    public void displayLevelOrder() {
        if (this.isEmpty()) {
            System.out.println("Tree is empty");
        } else {
            System.out.println("Level Order - Root Value " + root.getValue());
            Queue<BSTNode> nodes = new ArrayDeque<>();
            Queue<Integer> levels = new ArrayDeque<>();
            nodes.add(root);
            levels.add(1);
            int lvl = 1;
            while(!(nodes.isEmpty())) {
                BSTNode current = nodes.remove();
                int newlvl = levels.remove();
                if (newlvl != lvl) {
                    lvl = newlvl;
                    System.out.println("");
                }
                System.out.print("(lvl " + lvl + " - v "
                                + current.getValue().toString() + ") ");
                if (current.getLeftChild() != null) {
                    nodes.add(current.getLeftChild());
                    levels.add(lvl + 1);
                }
                if (current.getRightChild() != null) {
                    nodes.add(current.getRightChild());
                    levels.add(lvl + 1);
                }
            }
            System.out.println("");
        }
        
    }

    @Override
    public void remove(E value) throws NoSuchElementException {
        BSTNode victim = null;
        BSTNode current = root;
        BSTNode parent = root;
        //BEWARE THE TYPE OF E
        while ((victim == null) && (current != null)) {
            int comparison = value.compareTo((E)current.getValue());
            if (comparison == 0) {
                victim = current;
            } else {
                parent = current;
                if (comparison < 0) {
                    current = current.getLeftChild();
                } else {
                    current = current.getRightChild();
                }
            }
        }
        if (victim == null) {
            throw new NoSuchElementException(value.toString());
        }
        //Decrement counter on victim
        victim.setCounter(victim.getCounter() - 1);
        if (victim.getCounter() == 0) {
            System.out.println("\n\nFound victim " + victim.getValue());
            //Method 'removeNode' replaces a node with its successor if it has 2
            //children, so specific operations to remove the root node if it is
            //the victim are only necessary when the root node has 0 or 1 child
            if ((root == victim) && 
                    ((root.isLeaf()) || (root.hasSingleChild()))){
                System.out.println("Calling remove on root");
                if (root.isLeaf()) {
                    root = null;
                } else if (root.getLeftChild() == null) {
                        root = root.getRightChild();
                } else {
                    root = root.getLeftChild();
                }
            } else {
                //The following applies both if the root node is the victim
                //AND has 2 children, and if the victim is not the root node.
                System.out.println("Calling remove on victim");
                victim.removeNode(parent);                
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public BSTNode find(E value) {
        BSTNode result = null;
        if (!(this.isEmpty())) {
            result = root.find(value);
        }
        return result;
    }


    @Override
    public int height() {
        int height = 0;
        if (!(this.isEmpty())) {
            height = root.height();
        }
        return height;        
    }

    @Override
    public int nbOfNodes() {
        int nodeNum = 0;
        if (!(this.isEmpty())){
            nodeNum = root.nbOfNodes();
        }
        return nodeNum;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int nbOfLeaves() {
        int leavesNum = 0;
        if (!(this.isEmpty())){
            leavesNum = root.nbOfLeaves();
        }
        return leavesNum;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reverseTree() {
        root.reverseTree();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<E> getAllInRange(E min, E max) {
        ArrayList<E> l=new ArrayList();
        root.getAllInRange(min, max, l);
        return l;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
