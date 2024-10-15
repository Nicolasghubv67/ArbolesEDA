package material.tree.narytree;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import material.Position;
import material.tree.BreadthFirstTreeIterator;

/**
 *
 * @author mayte
 * @param <E>
 */
public class LinkedTree<E> implements NAryTree<E> {

    private TreeNode<E> checkPosition(Position<E> p) {
        if (p == null || !(p instanceof TreeNode))
            throw new RuntimeException();
        return (TreeNode<E>) p;
    }

    private void reasignParent(List<TreeNode<E>> children, TreeNode<E> nodo1) {
        
        for (TreeNode<E> node : children) {
            node.parent = nodo1;
        }
    }

    private LinkedTree<E> checkTree(NAryTree<E> t) {
        if (t == null || !(t instanceof LinkedTree))
            throw new RuntimeException();
        return (LinkedTree<E>) t;
    }

    private class TreeNode<T> implements Position<T>{
        
        private T element;
        private TreeNode<T> parent;
        private List<TreeNode<T>> children;

        public TreeNode(T element, TreeNode<T> parent, List<TreeNode<T>> children) {
            this.element = element;
            this.parent = parent;
            this.children = children;
        }

        @Override
        public String toString() {
            
            String toStringChildren = "";
            for (TreeNode<T> treeNode : children) {
                toStringChildren = toStringChildren + treeNode.toString2() + ", ";
            }
            
            return "TreeNode{" + "Elemento=" + element + ", parent=" + parent.toStringN1() + ", children=" + toStringChildren + '}';
        }
        
        private String toStringN1() {
            return "Elemento: [" + element + "]";
        }
        
        private String toString2() {
            return "[" + element + "]";
        }


        @Override
        public T getElement() {
            return element;
        }
        
        public void setElement(T element){
            this.element = element;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        public void setParent(TreeNode<T> parent) {
            this.parent = parent;
        }

        public List<TreeNode<T>> getChildren() {
            return children;
        }

        public void setChildren(List<TreeNode<T>> children) {
            this.children = children;
        }

    }
    
    private TreeNode<E> root;
    
    @Override
    public Position<E> addRoot(E e) {
        if (root != null)
            throw new RuntimeException();
        root = new TreeNode<>(e,null,new ArrayList<>());
        return root;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        TreeNode<E> nodo = checkPosition(p);
        TreeNode<E> hijo = new TreeNode<>(element,nodo, new ArrayList<>());
        nodo.getChildren().add(hijo);
        return hijo;
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        TreeNode<E> nodo = checkPosition(p);
        TreeNode<E> hijo = new TreeNode<>(element,nodo, new ArrayList<>());
        nodo.getChildren().add(n, hijo);
        return hijo;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        TreeNode<E> nodo1 = checkPosition(p1);
        TreeNode<E> nodo2 = checkPosition(p2);
        TreeNode<E> parent1 = nodo1.parent, parent2 = nodo2.parent;
        List<TreeNode<E>> children1 = nodo1.children, children2 = nodo2.children;
        
        nodo1.children = children2;
        nodo2.children = children1;
        if (parent1 == nodo2){            
            fatherAndChild(parent2, nodo1, nodo2, children2);              
        }else if (parent2 == nodo1){
            fatherAndChild(parent1, nodo2, nodo1, children1);
        }else{    
            parentToChild(parent2, nodo1, nodo2);
            parentToChild(parent1, nodo2, nodo1);
            nodo1.parent = parent2;
            nodo2.parent = parent1;
        }
        reasignParent(nodo1.children,nodo1);
        reasignParent(nodo2.children,nodo2);
        
    }

    private void fatherAndChild(TreeNode<E> parent2, TreeNode<E> nodo1, TreeNode<E> nodo2, List<TreeNode<E>> children2) {
        //el nodo2 es padre del nodo 1
        
        parentToChild(parent2, nodo1, nodo2);
        modifyChildrenList(children2, nodo1, nodo2);
        nodo1.parent = parent2;
        nodo2.parent = nodo1;
    }

    private void modifyChildrenList(List<TreeNode<E>> children2, TreeNode<E> nodo1, TreeNode<E> nodo2) {
        int int1 = children2.indexOf(nodo1);//posicion que ocupaba el nodo1
        children2.remove(nodo1);//borramos el nodo1
        children2.add(int1, nodo2);//el nodo2 ocupa la posición del nodo1
    }

    private void parentToChild(TreeNode<E> parent2, TreeNode<E> nodo1, TreeNode<E> nodo2) {
        if (parent2 == null){//el nodo2 era la raiz
            root = nodo1;
        }else{
            modifyChildrenList(parent2.children,nodo2, nodo1); 
            
        }
    }

    @Override
    public E replace(Position<E> p, E e) {
        TreeNode<E> nodo = checkPosition(p);
        E aux = nodo.getElement();
        nodo.setElement(e);
        return aux;
    }

    @Override
    public void remove(Position<E> p) {
        TreeNode<E> nodo = checkPosition(p);
        if (nodo == root)
            root = null;
        else{
            nodo.parent.children.remove(nodo);
        }
    }

    @Override
    public NAryTree<E> subTree(Position<E> v) {
        TreeNode<E> nodo = checkPosition(v);
        LinkedTree<E> t = new LinkedTree<>();
        remove(nodo);
        nodo.parent = null;
        t.root = nodo;
        
        return t;
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        TreeNode<E> nodo = checkPosition(p);
        LinkedTree<E> tree = checkTree(t);
        nodo.children.add(tree.root);
        tree.root.parent = nodo;
        tree.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Position<E> root() {
        if (root == null)
            throw new RuntimeException();
        return root;
    }
    
    @Override
    public Position<E> parent(Position<E> v) {
        TreeNode<E> nodo = checkPosition(v);
        if (nodo == root)
            throw new RuntimeException();
        return nodo.parent;
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        TreeNode<E> nodo = checkPosition(v);
        return Collections.unmodifiableCollection(nodo.children);
    }

    @Override
    public boolean isInternal(Position<E> v) {
        TreeNode<E> nodo = checkPosition(v);
        return !nodo.children.isEmpty();
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        TreeNode<E> nodo = checkPosition(v);
        return nodo.children.isEmpty();
    }

    @Override
    public boolean isRoot(Position<E> v) {
        TreeNode<E> nodo = checkPosition(v);
        return nodo == root;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new BreadthFirstTreeIterator<>(this);
    }
 
    public int size(){
        int cont = 0;
        if (root != null)
            for (Position<E> thi : this) {
                cont++;
            }
        return cont;    
        }
    
    }
