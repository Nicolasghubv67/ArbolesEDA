package material.tree.util;

import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.Collections;
import material.Position;
import material.tree.Tree;

/**

 * @param <E>
 */
public class DegreeCalculator<E> {
    
    private int calcularGradoEHijos(Position<E> p, Tree<E> t){
        
        if (!(t.isLeaf(p))){
            Iterable<? extends Position<E>> hijos = t.children(p);
            int numHijos = 0;
            ArrayList<Integer> gradoHijos = new ArrayList<>();
            for (Position<E> hijo : hijos) {
                numHijos++;
                gradoHijos.add(calcularGradoEHijos(hijo, t));
            }
            Integer mayorGradoHijos = Collections.max(gradoHijos);
            int max = max(mayorGradoHijos, numHijos);
            return max;
        }else
            return 0;
    }
    
    public int calculate(Tree <E> t) {
        
        if(t.isEmpty())
            return 0;
        else{
            return calcularGradoEHijos(t.root(), t);
        }
    }
}
