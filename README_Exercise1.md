# Exercise 1: Minimax Algorithm Implementation

## Overview

This exercise involves implementing the Minimax algorithm for a simple two-ply game tree. The Minimax algorithm is a decision-making algorithm used in adversarial search and game theory to find the optimal move for a player, assuming that the opponent is also playing optimally.

## Implementation Details

The implementation is in the `src/fr/emse/ai/adversarial/MiniMaxAlgo.java` file, which contains the following key components:

### Class Structure

```java
public class MiniMaxAlgo {
    private int expandedNodes;

    public MiniMaxAlgo() { ... }
    public int minimax(SimpleTwoPlyGameTree tree) { ... }
    private int minimaxValue(SimpleTwoPlyGameTree node) { ... }
    public int getExpandedNodes() { ... }
    public Metrics getMetrics() { ... }
}
```

### Key Methods

1. **minimax(SimpleTwoPlyGameTree tree)**: The main method that applies the Minimax algorithm to a given game tree and returns the optimal value for the root node.

2. **minimaxValue(SimpleTwoPlyGameTree node)**: A recursive method that calculates the Minimax value for a node in the game tree.

3. **getExpandedNodes()**: Returns the number of nodes expanded during the search, which is useful for performance analysis.

4. **getMetrics()**: Returns metrics about the search, including the number of expanded nodes.

### Algorithm Explanation

The Minimax algorithm works as follows:

1. If the current node is a leaf node (terminal state), return its value.
2. If the current node is a MAX node:
   - Initialize the best value to negative infinity.
   - For each child of the node, recursively calculate the Minimax value.
   - Update the best value to be the maximum of the current best value and the child's value.
   - Return the best value.
3. If the current node is a MIN node:
   - Initialize the best value to positive infinity.
   - For each child of the node, recursively calculate the Minimax value.
   - Update the best value to be the minimum of the current best value and the child's value.
   - Return the best value.

### Example Game Tree

The algorithm was tested on the following game tree from the lecture:

```
                 1(MAX)
                /     \
           2(MIN)     3(MIN)
          / | \       / \
    4(5) 5(7) 6(8) 7(2) 8(5)
```

## Test Results

When running the Minimax algorithm on the example game tree, the following results were obtained:

- Minimax value for the root node: 5
- Number of nodes expanded: 8

This means that the optimal value for the root node is 5, and the algorithm had to explore 8 nodes in the tree to determine this value.

## Conclusion

The Minimax algorithm successfully finds the optimal value for the root node by exploring the entire game tree. However, it can be inefficient for larger trees as it explores all possible moves, even those that cannot affect the final decision. This limitation is addressed in Exercise 2 with the Alpha-Beta pruning algorithm.
