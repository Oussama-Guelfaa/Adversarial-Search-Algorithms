# Exercise 2: Alpha-Beta Pruning Algorithm Implementation

## Overview

This exercise involves implementing the Alpha-Beta pruning algorithm for a simple two-ply game tree. Alpha-Beta pruning is an optimization technique for the Minimax algorithm that reduces the number of nodes evaluated in the search tree by eliminating branches that cannot influence the final decision.

## Implementation Details

The implementation is in the `AlphaBetaAlgo.java` file, which contains the following key components:

### Class Structure

```java
public class AlphaBetaAlgo {
    private int expandedNodes;
    
    public AlphaBetaAlgo() { ... }
    public int alphaBeta(SimpleTwoPlyGameTree tree) { ... }
    private int alphaBetaValue(SimpleTwoPlyGameTree node, int alpha, int beta) { ... }
    public int getExpandedNodes() { ... }
    public Metrics getMetrics() { ... }
}
```

### Key Methods

1. **alphaBeta(SimpleTwoPlyGameTree tree)**: The main method that applies the Alpha-Beta pruning algorithm to a given game tree and returns the optimal value for the root node.

2. **alphaBetaValue(SimpleTwoPlyGameTree node, int alpha, int beta)**: A recursive method that calculates the Alpha-Beta value for a node in the game tree, using alpha and beta parameters to prune branches.

3. **getExpandedNodes()**: Returns the number of nodes expanded during the search, which is useful for performance analysis.

4. **getMetrics()**: Returns metrics about the search, including the number of expanded nodes.

### Algorithm Explanation

The Alpha-Beta pruning algorithm works as follows:

1. If the current node is a leaf node (terminal state), return its value.
2. If the current node is a MAX node:
   - Initialize the value to negative infinity.
   - For each child of the node, recursively calculate the Alpha-Beta value.
   - Update the value to be the maximum of the current value and the child's value.
   - Update alpha to be the maximum of alpha and the value.
   - If beta <= alpha, prune the remaining branches (break the loop).
   - Return the value.
3. If the current node is a MIN node:
   - Initialize the value to positive infinity.
   - For each child of the node, recursively calculate the Alpha-Beta value.
   - Update the value to be the minimum of the current value and the child's value.
   - Update beta to be the minimum of beta and the value.
   - If beta <= alpha, prune the remaining branches (break the loop).
   - Return the value.

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

When running the Alpha-Beta pruning algorithm on the example game tree, the following results were obtained:

- Alpha-Beta value for the root node: 5
- Number of nodes expanded: 7

This means that the optimal value for the root node is 5, and the algorithm was able to determine this value by exploring only 7 nodes in the tree, thanks to pruning.

## Conclusion

The Alpha-Beta pruning algorithm successfully finds the same optimal value as the Minimax algorithm but does so more efficiently by pruning branches that cannot affect the final decision. This efficiency improvement becomes more significant for larger game trees, making Alpha-Beta pruning a valuable optimization for game-playing algorithms.
