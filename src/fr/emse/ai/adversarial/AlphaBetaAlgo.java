package fr.emse.ai.adversarial;

import fr.emse.ai.adversarial.core.Metrics;
import fr.emse.ai.util.SimpleTwoPlyGameTree;

/**
 * Implementation of the Alpha-Beta pruning algorithm for the SimpleTwoPlyGameTree.
 * This algorithm is an optimization of the minimax algorithm that reduces the number
 * of nodes evaluated in the search tree by pruning branches that cannot influence
 * the final decision.
 */
public class AlphaBetaAlgo {
    
    private int expandedNodes;
    
    /**
     * Constructor for the AlphaBetaAlgo class.
     */
    public AlphaBetaAlgo() {
        this.expandedNodes = 0;
    }
    
    /**
     * Applies the alpha-beta algorithm to a given game tree.
     * 
     * @param tree The game tree to analyze
     * @return The minimax value for the root node
     */
    public int alphaBeta(SimpleTwoPlyGameTree tree) {
        // Reset the counter for expanded nodes
        expandedNodes = 0;
        
        // Calculate the alpha-beta value for the root node
        int value = alphaBetaValue(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        // Update the value of the root node
        tree.setValue(value);
        
        return value;
    }
    
    /**
     * Recursive method to calculate the alpha-beta value for a node.
     * 
     * @param node The current node in the game tree
     * @param alpha The best value that the maximizer currently can guarantee
     * @param beta The best value that the minimizer currently can guarantee
     * @return The alpha-beta value for the node
     */
    private int alphaBetaValue(SimpleTwoPlyGameTree node, int alpha, int beta) {
        // Increment the counter for expanded nodes
        expandedNodes++;
        
        // If the node is a leaf, return its value
        if (node.isLeaf()) {
            return node.getValue();
        }
        
        // Initialize the best value based on whether it's a max or min node
        int value;
        
        if (node.isMax()) {
            // Max node
            value = Integer.MIN_VALUE;
            
            // Explore all children of the node
            for (SimpleTwoPlyGameTree child : node.getChildren()) {
                // Recursively calculate the alpha-beta value for the child
                int childValue = alphaBetaValue(child, alpha, beta);
                
                // Update the value and alpha
                value = Math.max(value, childValue);
                alpha = Math.max(alpha, value);
                
                // Update the value of the child node
                child.setValue(childValue);
                
                // Prune if possible
                if (beta <= alpha) {
                    break;
                }
            }
        } else {
            // Min node
            value = Integer.MAX_VALUE;
            
            // Explore all children of the node
            for (SimpleTwoPlyGameTree child : node.getChildren()) {
                // Recursively calculate the alpha-beta value for the child
                int childValue = alphaBetaValue(child, alpha, beta);
                
                // Update the value and beta
                value = Math.min(value, childValue);
                beta = Math.min(beta, value);
                
                // Update the value of the child node
                child.setValue(childValue);
                
                // Prune if possible
                if (beta <= alpha) {
                    break;
                }
            }
        }
        
        return value;
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
