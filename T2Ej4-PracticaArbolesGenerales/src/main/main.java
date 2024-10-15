package main;

import material.Position;
import material.tree.narytree.LinkedTree;
import material.tree.util.HeightCalculator;

public class main {
    public static void main(String[] args) {
        System.out.println("Main si compila");
        
        int calculate;
        HeightCalculator hC = new HeightCalculator();
        
        LinkedTree<String> arbolLinked = new LinkedTree<>();
        
        //calculate = hC.calculate(arbolLinked);
        
        Position<String> root = arbolLinked.addRoot("A");
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con root: " + calculate);
        
        Position<String> node1 = arbolLinked.add("B", root);
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con B añadido: " + calculate);
        
        Position<String> node2 = arbolLinked.add("C", root);
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con C añadido: " + calculate);
        
        Position<String> node3 = arbolLinked.add("D", node1);
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con D añadido: " + calculate);
        
        Position<String> node4 = arbolLinked.add("E", node2);
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con E añadido: " + calculate);
        
        Position<String> node5 = arbolLinked.add("F", node2);
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con F añadido: " + calculate);
        
        Position<String> node6 = arbolLinked.add("G", node2);
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con G añadido: " + calculate);
        
        Position<String> node7 = arbolLinked.add("H", node5);
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con H añadido: " + calculate);
        
        Position<String> node8 = arbolLinked.add("I", node5);
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con I añadido: " + calculate);
        
        arbolLinked.remove(node5);
        
        calculate = hC.calculate(arbolLinked);
        System.out.println("Con F eliminado: " + calculate);
        
        
        
        
        
    }
}
