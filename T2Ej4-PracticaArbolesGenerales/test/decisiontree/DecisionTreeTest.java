/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decisiontree;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jvelez
 */
public class DecisionTreeTest {
    
    public DecisionTreeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addMainDecision method, of class DecisionTree.
     */
    @Test
    public void testDecisionTree() {
        System.out.println("addMainDecision");
        DecisionTree tree = new DecisionTree();
        DecisionNode mainNode = tree.addMainDecision("Hacemos un programa o lo compramos");
        ChanceNode chance1 = tree.addChance(mainNode, "Comprarlo", -5000.0);
        ChanceNode chance2 = tree.addChance(mainNode, "Programarlo", -6000.0);
        TerminalNode terminal1 = tree.addTerminal(chance1, "Sirve tal cual", 0.2, "Lo vendemos", 10000);
        DecisionNode pluginNode = tree.addDecision(chance1, "No sirve tal cual", 0.8, "Programamos plugin");
        TerminalNode terminal2 = tree.addTerminal(pluginNode, "Sí", -3000, "Lo vendemos", 10000);
        TerminalNode terminal3 = tree.addTerminal(pluginNode, "No", 0, "Lo vendemos", 5000);        
        TerminalNode terminal4 = tree.addTerminal(chance2, "Funciona", 0.7, "Lo vendemos", 10000);
        TerminalNode terminal5 = tree.addTerminal(chance2, "No funciona", 0.3, "No lo vendemos", 0);

        double result = tree.getDecisionValue(mainNode);

        assertEquals(2600.0, result,0.1);
    }

    /**
     * Test of addMainDecision method, of class DecisionTree.
     */
    @Test
    public void testDecisionTreeToString() {
        System.out.println("addMainDecision");
        DecisionTree tree = new DecisionTree();
        DecisionNode mainNode = tree.addMainDecision("Hacemos un programa o lo compramos");
        ChanceNode chance1 = tree.addChance(mainNode, "Comprarlo", -5000.0);
        ChanceNode chance2 = tree.addChance(mainNode, "Programarlo", -6000.0);
        TerminalNode terminal1 = tree.addTerminal(chance1, "Sirve tal cual", 0.2, "Lo vendemos", 10000);
        DecisionNode pluginNode = tree.addDecision(chance1, "No sirve tal cual", 0.8, "Programamos plugin");
        TerminalNode terminal2 = tree.addTerminal(pluginNode, "Sí", -3000, "Lo vendemos", 10000);
        TerminalNode terminal3 = tree.addTerminal(pluginNode, "No", 0, "Lo vendemos", 5000);        
        TerminalNode terminal4 = tree.addTerminal(chance2, "Funciona", 0.7, "Lo vendemos", 10000);
        TerminalNode terminal5 = tree.addTerminal(chance2, "No funciona", 0.3, "No lo vendemos", 0);

        String result = tree.toString();

        String expectedResult = "Hacemos un programa o lo compramos\n"
                                +"  Comprarlo, -5000.0\n"
                                +"    Sirve tal cual (0.2)\n"
                                +"      Lo vendemos, 10000\n"
                                +"    No sirve tal cual (0.8)\n"
                                +"      Programamos plugin\n"
                                +"        Sí, -3000\n"
                                +"          Lo vendemos, 10000\n"
                                +"        No, 0\n"
                                +"          Lo vendemos, 5000\n"
                                +"  Programarlo, -6000.0\n"
                                +"    Funciona (0.7)\n"
                                +"      Lo vendemos, 10000\n"
                                +"    No funciona (0.3)\n"
                                +"      No lo vendemos, 0\n";
        
        assertEquals(expectedResult, result,0.1);
    }
    
    
    
}
