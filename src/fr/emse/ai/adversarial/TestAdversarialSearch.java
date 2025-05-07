package fr.emse.ai.adversarial;

import fr.emse.ai.util.SimpleTwoPlyGameTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test class for the MiniMaxAlgo and AlphaBetaAlgo implementations.
 * This class creates a game tree based on the example from the lecture
 * and runs both algorithms on it to compare their performance.
 */
public class TestAdversarialSearch {
    
    /**
     * Main method to run the test.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create the example game tree
        SimpleTwoPlyGameTree gameTree = createExampleGameTree();
        
        // Test the minimax algorithm
        System.out.println("Testing Minimax Algorithm:");
        MiniMaxAlgo minimax = new MiniMaxAlgo();
        int minimaxValue = minimax.minimax(gameTree);
        System.out.println("Minimax value for the root node: " + minimaxValue);
        System.out.println("Number of nodes expanded: " + minimax.getExpandedNodes());
        System.out.println();
        
        // Reset the game tree
        gameTree = createExampleGameTree();
        
        // Test the alpha-beta algorithm
        System.out.println("Testing Alpha-Beta Algorithm:");
        AlphaBetaAlgo alphaBeta = new AlphaBetaAlgo();
        int alphaBetaValue = alphaBeta.alphaBeta(gameTree);
        System.out.println("Alpha-Beta value for the root node: " + alphaBetaValue);
        System.out.println("Number of nodes expanded: " + alphaBeta.getExpandedNodes());
        System.out.println();
        
        // Compare the results
        System.out.println("Comparison:");
        System.out.println("Both algorithms found the same optimal value: " + (minimaxValue == alphaBetaValue));
        System.out.println("Minimax expanded " + minimax.getExpandedNodes() + " nodes");
        System.out.println("Alpha-Beta expanded " + alphaBeta.getExpandedNodes() + " nodes");
        
        // Calculate the efficiency improvement
        double improvement = 100.0 * (1.0 - (double) alphaBeta.getExpandedNodes() / minimax.getExpandedNodes());
        System.out.println("Alpha-Beta is " + String.format("%.2f", improvement) + "% more efficient");
    }
    
    /**
     * Creates the example game tree from the lecture.
     * The tree structure is as follows:
     *                  1(MAX)
     *                 /     \
     *            2(MIN)     3(MIN)
     *           / | \       / \
     *     4(5) 5(7) 6(8) 7(2) 8(5)
     * 
     * @return The root node of the example game tree
     */
    private static SimpleTwoPlyGameTree createExampleGameTree() {
        // Create the leaf nodes (level 2)
        SimpleTwoPlyGameTree node4 = new SimpleTwoPlyGameTree(5, false);
        SimpleTwoPlyGameTree node5 = new SimpleTwoPlyGameTree(7, false);
        SimpleTwoPlyGameTree node6 = new SimpleTwoPlyGameTree(8, false);
        SimpleTwoPlyGameTree node7 = new SimpleTwoPlyGameTree(2, false);
        SimpleTwoPlyGameTree node8 = new SimpleTwoPlyGameTree(5, false);
        
        // Create the MIN nodes (level 1)
        List<SimpleTwoPlyGameTree> children2 = Arrays.asList(node4, node5, node6);
        SimpleTwoPlyGameTree node2 = new SimpleTwoPlyGameTree(null, false, children2);
        
        List<SimpleTwoPlyGameTree> children3 = Arrays.asList(node7, node8);
        SimpleTwoPlyGameTree node3 = new SimpleTwoPlyGameTree(null, false, children3);
        
        // Create the MAX node (root)
        List<SimpleTwoPlyGameTree> children1 = Arrays.asList(node2, node3);
        SimpleTwoPlyGameTree node1 = new SimpleTwoPlyGameTree(null, true, children1);
        
        return node1;
    }
}
