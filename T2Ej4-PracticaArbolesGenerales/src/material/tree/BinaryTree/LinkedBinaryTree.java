
package material.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import material.Position;

/**
 *
 * @author mayte
 */
public class LinkedBinaryTree<E> implements BinaryTree<E>{

    private BTNode<E> checkPosition(Position<E> v) {
        if (v == null || !(v instanceof BTNode))
            throw new RuntimeException();
        return (BTNode<E>) v;
    }

    private LinkedBinaryTree<E> checkBinaryTree(BinaryTree<E> t1) {
        if (t1 == null || !(t1 instanceof LinkedBinaryTree))
            throw new RuntimeException();
        return (LinkedBinaryTree<E>) t1;
    
    }
    private class BTNode<T> implements Position<T>{

        private T element;
        private BTNode<T> parent, left, right;

        public BTNode(T element, BTNode<T> parent, BTNode<T> left, BTNode<T> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public T getElement() {
            return element;
        }
        
    }

    private BTNode<E> root;
    
    @Override
    public Position<E> left(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        if (node.left == null)
            throw new RuntimeException();
        return node.left;
    }

    @Override
    public Position<E> right(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        if (node.right == null)
            throw new RuntimeException();
        return node.right;
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        return node.left != null;
    }

    @Override
    public boolean hasRight(Position<E> v) {
        BTNode<E> node = checkPosition(v);
        return node.right != null;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return (hasLeft(v) || hasRight(v));
    }

    @Override
    public boolean isLeaf(Position<E> p) {
        return !isInternal(p);
    }

    @Override
    public boolean isRoot(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        return root == node;
    }

    @Override
    public Position<E> root() {
        if (root == null)
            throw new RuntimeException();
        return root;
    }

    @Override
    public E replace(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        E aux = node.element;
        node.element = e;
        return aux;
    }

    @Override
    public Position<E> sibling(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        if (node == root)
            throw new RuntimeException();
        BTNode<E> parent = node.parent;
        if (parent.left == null || parent.right == null)
            throw new RuntimeException();
        if (parent.left == node)
            return parent.right;
        else 
            return parent.left;
    }

    @Override
    public Position<E> addRoot(E e) {
        if (root != null)
            throw new RuntimeException();
        return root = new BTNode<>(e, null,null,null);
        
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        if (node.left != null)
            throw new RuntimeException();
        return node.left = new BTNode<>(e,node,null,null);
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) {
        BTNode<E> node = checkPosition(p);
        if (node.right != null)
            throw new RuntimeException();
        return node.right = new BTNode<>(e,node,null,null);
    }

    @Override
    public E remove(Position<E> p) {
        BTNode<E> node = checkPosition(p);
        if (node.left != null && node.right != null)
            throw new RuntimeException();
        BTNode<E> hijo = (node.left == null)? node.right: node.left;
        if (node == root){
            root = hijo;
            if (hijo != null)
                hijo.parent = null;
        }else{
            if (hijo != null)
                hijo.parent = node.parent;
            if (node.parent.left == node)
                node.parent.left = hijo;
            else
                node.parent.right = hijo;
        }
        return node.element;
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        BTNode<E> node1 = checkPosition(p1);
        BTNode<E> node2 = checkPosition(p2);
        BTNode<E> parent1 = node1.parent, parent2 = node2.parent, left1 = node1.left, left2 = node2.left, right1 = node1.right, right2 = node2.right;
        boolean isChildLeft1= ((parent1 != null) && (parent1.left == node1));
        boolean isChildLeft2= ((parent2 != null) && (parent2.left == node2));
        
        if (parent1 == node2){//el nodo2 es padre del nodo 1
            parent1 = node1;
            
        }else if (parent2 == node1){//el nodo1 es el padre del nodo2
            parent2 = node2;
        }
        node1.parent = parent2;
        node1.left = left2;
        node1.right = right2;
        reasignFamily(parent2, isChildLeft2, node1, left2, right2);
        node2.parent = parent1;
        node2.left = left1;
        node2.right = right1;
        reasignFamily(parent1, isChildLeft1, node2, left1, right1);
        
    }

    private void reasignFamily(BTNode<E> parent2, boolean isChildLeft2, BTNode<E> node1, BTNode<E> left2, BTNode<E> right2) {
        if (parent2 != null){
            if (isChildLeft2){
                parent2.left = node1;
            }else
                parent2.right = node1;
        }else{//el nodo2 era la raiz
            root = node1;
        }
        if (left2 != null) left2.parent = node1;
        if (right2 != null) right2.parent = node1;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Position<E> parent(Position<E> v) {
         BTNode<E> node = checkPosition(v);
         if (node == root)
             throw new RuntimeException();
         return node.parent;
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
         BTNode<E> node = checkPosition(v);
         Collection<Position<E>> l = new ArrayList<>();
         if (node.left != null)
             l.add(node.left);
         if (node.right != null)
             l.add(node.right);
         return Collections.unmodifiableCollection(l);
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new InorderBinaryTreeIterator<>(this);
    }


    @Override
    public LinkedBinaryTree<E> subTree(Position<E> h) {
         BTNode<E> node = checkPosition(h);
         LinkedBinaryTree<E> tree = new LinkedBinaryTree<>();
         if (node == root){
             this.root = null;
         }else{
             if (node.parent.left == node)
                 node.parent.left = null;
             else
                 node.parent.right = null;
             node.parent = null;
         }
         tree.root = node;
         return tree;
    }

    @Override
    public void attachLeft(Position<E> h, BinaryTree<E> t1) {
        BTNode<E> node = checkPosition(h);
        LinkedBinaryTree<E> tree = checkBinaryTree(t1);
        if (node.left != null)
            throw new RuntimeException();
        if (tree.root != null){
            node.left = tree.root;
            tree.root.parent = node;
            tree.root = null;
        }
    }

    @Override
    public void attachRight(Position<E> h, BinaryTree<E> t1) {
        BTNode<E> node = checkPosition(h);
        LinkedBinaryTree<E> tree = checkBinaryTree(t1);
        if (node.right != null)
            throw new RuntimeException();
        if (tree.root != null){
            node.right = tree.root;
            tree.root.parent = node;
            tree.root = null;
        }
    }

}
