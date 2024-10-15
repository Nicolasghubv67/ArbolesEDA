package decisiontree;

/**
 *
 * @author jvelez
 */
public class DecisionTree {
    
    /**
     * Add the root of the decision tree.
     * @param text 
     * @return The decision node 
     */
    public DecisionNode addMainDecision(String text) {
        throw new RuntimeException("Not implemented yet");
    }
    
    /**
     * Add a decision node to the tree.
     * @param parent The parent of the node
     * @param edgeText The text of the node
     * @param cost The associated cost
     * @param nodeText The text of the node
     * @return The new decision node.
     */
    public DecisionNode addDecision(DecisionNode parent, String edgeText, double cost, String nodeText) {
        throw new RuntimeException("Not implemented yet");
    }
    
    /**
     * Add a decision node to the tree.
     * @param parent The parent of the node
     * @param edgeText The text of the node
     * @param probability The associated probability
     * @param nodeText The text of the node
     * @return The new decision node.
     */
    public DecisionNode addDecision(ChanceNode parent, String edgeText, double probability, String nodeText) {
        throw new RuntimeException("Not implemented yet");
    }
    
    /**
     * Add a chance node to the tree.
     * @param parent The parent of the node
     * @param edgeText The text of the node
     * @param cost The associated cost
     * @return The new decision node.
     */
    public ChanceNode addChance(DecisionNode parent, String edgeText, double cost) {
        throw new RuntimeException("Not implemented yet");
    }
    
    /**
     * Add a chance node to the tree.
     * @param parent The parent of the node
     * @param edgeText The text of the node
     * @param probability The associated probability
     * @return The new decision node.
     */
    public ChanceNode addChance(ChanceNode parent, String edgeText, double probability) {
        throw new RuntimeException("Not implemented yet");
    }

    /**
     * Add a terminal node to the tree.
     * @param parent The parent of the node
     * @param edgeText The text of the node
     * @param cost The associated cost
     * @param nodeText The text of the node
     * @param profit Expected profit
     * @return The new decision node.
     */
    public TerminalNode addTerminal(DecisionNode parent, String edgeText, double cost, String nodeText, double profit) {
        throw new RuntimeException("Not implemented yet");
    }
    
    /**
     * Add a terminal node to the tree.
     * @param parent The parent of the node
     * @param edgeText The text of the node
     * @param probability The associated probability
     * @param nodeText The text of the node
     * @param profit Expected profit
     * @return The new decision node.
     */
    public TerminalNode addTerminal(ChanceNode parent, String edgeText, double probability, String nodeText, double profit) {
        throw new RuntimeException("Not implemented yet");
    }
    
    /**
     * 
     * @param node
     * @return The value calculated for a decision node
     */
    public double getDecisionValue(DecisionNode node) {
        throw new RuntimeException("Not implemented yet");        
    }
    
    /**
     * Draw the tree.
     * @return A string with the tree.
     */
    public String toString() {
        throw new RuntimeException("Not implemented yet");                
    }
}
