# Exercise 3: Comparison of Minimax and Alpha-Beta Algorithms

## Overview

This exercise involves comparing the performance of the Minimax and Alpha-Beta pruning algorithms by counting the number of nodes explored by each algorithm when applied to the same game tree.

## Implementation Details

The comparison is implemented in the `src/fr/emse/ai/adversarial/TestAdversarialSearch.java` file, which creates an example game tree and runs both algorithms on it.

Both algorithms (`src/fr/emse/ai/adversarial/MiniMaxAlgo.java` and `src/fr/emse/ai/adversarial/AlphaBetaAlgo.java`) have been modified to count the number of nodes they explore during the search process. This is done by incrementing a counter each time a node is visited in the recursive search functions.

### Counting Mechanism

In both `MiniMaxAlgo.java` and `AlphaBetaAlgo.java`, the following mechanism is used to count expanded nodes:

```java
// In the recursive search methods
expandedNodes++;
```

The counter is reset at the beginning of each search:

```java
// At the start of minimax() or alphaBeta()
expandedNodes = 0;
```

And the count can be retrieved after the search:

```java
public int getExpandedNodes() {
    return expandedNodes;
}
```

## Test Results

The comparison was performed using the `TestAdversarialSearch.java` class, which creates the example game tree and runs both algorithms on it. The results are as follows:

### Minimax Algorithm
- Minimax value for the root node: 5
- Number of nodes expanded: 8

### Alpha-Beta Pruning Algorithm
- Alpha-Beta value for the root node: 5
- Number of nodes expanded: 7

### Comparison
- Both algorithms found the same optimal value: true
- Minimax expanded 8 nodes
- Alpha-Beta expanded 7 nodes
- Alpha-Beta is 12.50% more efficient

## Analysis

The Alpha-Beta pruning algorithm explores fewer nodes than the Minimax algorithm because it can prune branches that cannot affect the final decision. In this specific example, Alpha-Beta was able to prune 1 node, resulting in a 12.50% efficiency improvement.

The pruning occurs because Alpha-Beta keeps track of the best values that each player can guarantee (alpha for the maximizer and beta for the minimizer). When it determines that a branch cannot lead to a better outcome than what has already been found, it skips exploring that branch further.

## Conclusion

The Alpha-Beta pruning algorithm is more efficient than the Minimax algorithm as it explores fewer nodes while still finding the same optimal value. This efficiency improvement becomes more significant for larger game trees, making Alpha-Beta pruning a valuable optimization for game-playing algorithms.

In the worst case, Alpha-Beta still explores the same number of nodes as Minimax, but in practice, it often provides substantial savings, especially when the moves are ordered optimally (best moves considered first).
