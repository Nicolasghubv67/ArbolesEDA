package material.tree.util;

import java.util.ArrayList;
import java.util.Collections;
import material.Position;
import material.tree.Tree;

/**
 * @param <E>
 */
public class HeightCalculator<E> {
    
    private int profundidadNodo(Position<E> p, Tree<E> t){
        
        Iterable<? extends Position<E>> children;
        ArrayList<Integer> profundidadHijos = new ArrayList<>();
        
        if (t.isLeaf(p))
            return 0;
        else{ //si no devolvemos 1 más el máximo de cacular la profundidad de sus hijos.
            children = t.children(p);
            for (Position<E> position : children) {
                profundidadHijos.add(profundidadNodo(position, t) + 1);
            }
            return Collections.max(profundidadHijos);
        }
    }

    public int calculate(Tree<E> t) {
        if (t.isEmpty()){
            throw new RuntimeException("El árbol vacío no tiene altura mi pana");
        }else{
            Position<E> raiz = t.root();
            return profundidadNodo(raiz, t);
        }
        
    }
    
}
