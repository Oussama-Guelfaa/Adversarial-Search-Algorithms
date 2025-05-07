package fr.emse.ai.adversarial;

import fr.emse.ai.adversarial.core.Metrics;
import fr.emse.ai.util.SimpleTwoPlyGameTree;

/**
 * Implementation of the Minimax algorithm for the SimpleTwoPlyGameTree.
 * This algorithm explores all possible moves and countermoves to determine
 * the best move for a player, assuming that the opponent plays optimally.
 */
public class MiniMaxAlgo {
    
    private int expandedNodes;
    
    /**
     * Constructor for the MiniMaxAlgo class.
     */
    public MiniMaxAlgo() {
        this.expandedNodes = 0;
    }
    
    /**
     * Applies the minimax algorithm to a given game tree.
     * 
     * @param tree The game tree to analyze
     * @return The minimax value for the root node
     */
    public int minimax(SimpleTwoPlyGameTree tree) {
        // Reset the counter for expanded nodes
        expandedNodes = 0;
        
        // Calculate the minimax value for the root node
        int value = minimaxValue(tree);
        
        // Update the value of the root node
        tree.setValue(value);
        
        return value;
    }
    
    /**
     * Recursive method to calculate the minimax value for a node.
     * 
     * @param node The current node in the game tree
     * @return The minimax value for the node
     */
    private int minimaxValue(SimpleTwoPlyGameTree node) {
        // Increment the counter for expanded nodes
        expandedNodes++;
        
        // If the node is a leaf, return its value
        if (node.isLeaf()) {
            return node.getValue();
        }
        
        // Initialize the best value based on whether it's a max or min node
        int bestValue = node.isMax() ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        
        // Explore all children of the node
        for (SimpleTwoPlyGameTree child : node.getChildren()) {
            // Recursively calculate the minimax value for the child
            int childValue = minimaxValue(child);
            
            // Update the best value based on whether it's a max or min node
            if (node.isMax()) {
                bestValue = Math.max(bestValue, childValue);
            } else {
                bestValue = Math.min(bestValue, childValue);
            }
            
            // Update the value of the child node
            child.setValue(childValue);
        }
        
        return bestValue;
    }
    
    /**
     * Returns the number of nodes expanded during the search.
     * 
     * @return The number of expanded nodes
     */
    public int getExpandedNodes() {
        return expandedNodes;
    }
    
    /**
     * Returns the metrics of the search.
     * 
     * @return The metrics of the search
     */
    public Metrics getMetrics() {
        Metrics metrics = new Metrics();
        metrics.set("expandedNodes", expandedNodes);
        return metrics;
    }
}
