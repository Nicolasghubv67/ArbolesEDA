
package material.tree.BinaryTree;
import java.util.Iterator;
import java.util.Stack;
import material.Position;

/**
 *
 * @author mayte
 */
public class InorderBinaryTreeIterator<T> implements Iterator<Position<T>> {

    private BinaryTree<T> t;
    private Stack<Position<T>> pila;
    
    public InorderBinaryTreeIterator(BinaryTree <T> tree) {
       this(tree, tree.root());
    }

    public InorderBinaryTreeIterator(BinaryTree <T> tree, Position<T> node) {
      this.t = tree;
      pila = new Stack<>();
      
      leftSubTree(node);
    }
    @Override
    public boolean hasNext() {
        return !pila.isEmpty();
    }

    @Override
    public Position<T> next() {
        Position<T> p = pila.pop();
        if (t.hasRight(p))
            pila.add(t.right(p));
        return p;
    }

    private void leftSubTree(Position<T> node) {
        pila.add(node);
        if (t.hasLeft(node))
            leftSubTree(t.left(node));
    }
    
}



