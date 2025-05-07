# Exercise 5: Game Tree Exploration in Different Algorithms

## Overview

This document explains how the exploration of the game tree is performed in the three adversarial search algorithms provided in the framework: Minimax, Alpha-Beta pruning, and Iterative Deepening Alpha-Beta pruning. We'll analyze how each algorithm explores the game tree in the context of the Nim Game.

## 1. Minimax Algorithm

The `MinimaxSearch` class implements the standard Minimax algorithm, which explores the entire game tree to find the optimal move.

### Game Tree Exploration Process

1. **Starting Point**: The algorithm begins at the current game state and considers all possible actions.

2. **Recursive Exploration**: For each possible action, it recursively explores the game tree by alternating between MAX and MIN levels:
   - At MAX levels, it calls `minValue()` for each child state
   - At MIN levels, it calls `maxValue()` for each child state

3. **Complete Exploration**: The algorithm explores the entire game tree down to the terminal states (where the game is over).

4. **Node Expansion**: Each time a node is visited, the `expandedNodes` counter is incremented:
   ```java
   expandedNodes++;
   ```

5. **Terminal Evaluation**: When a terminal state is reached, the utility value is returned:
   ```java
   if (game.isTerminal(state))
       return game.getUtility(state, player);
   ```

6. **Value Propagation**: The utility values are propagated back up the tree:
   - MAX nodes take the maximum value of their children
   - MIN nodes take the minimum value of their children

7. **Decision Making**: The algorithm selects the action with the highest utility value:
   ```java
   if (value > resultValue) {
       result = action;
       resultValue = value;
   }
   ```

### Characteristics in Nim Game

In the Nim Game, Minimax will explore all possible ways to remove 1, 2, or 3 sticks at each turn, alternating between players, until reaching terminal states (no sticks left). This results in a complete exploration of the game tree, which can be very large for games with many possible states.

## 2. Alpha-Beta Pruning Algorithm

The `AlphaBetaSearch` class implements the Alpha-Beta pruning algorithm, which is an optimization of Minimax that reduces the number of nodes explored by pruning branches that cannot influence the final decision.

### Game Tree Exploration Process

1. **Starting Point**: Similar to Minimax, the algorithm begins at the current game state and considers all possible actions.

2. **Pruning Parameters**: The algorithm maintains two values:
   - `alpha`: The best value that the MAX player can guarantee so far
   - `beta`: The best value that the MIN player can guarantee so far

3. **Recursive Exploration with Pruning**: For each possible action, it recursively explores the game tree, but with pruning:
   - At MAX nodes, if the current value is greater than or equal to beta, the remaining branches are pruned:
     ```java
     if (value >= beta)
         return value;
     ```
   - At MIN nodes, if the current value is less than or equal to alpha, the remaining branches are pruned:
     ```java
     if (value <= alpha)
         return value;
     ```

4. **Partial Exploration**: Unlike Minimax, Alpha-Beta does not explore the entire game tree. It prunes branches that cannot affect the final decision.

5. **Node Expansion**: Each time a node is visited, the `expandedNodes` counter is incremented, just like in Minimax.

6. **Value Propagation with Updates**: As values are propagated back up the tree, alpha and beta are updated:
   - At MAX nodes: `alpha = Math.max(alpha, value);`
   - At MIN nodes: `beta = Math.min(beta, value);`

7. **Decision Making**: The algorithm selects the action with the highest utility value, just like Minimax.

### Characteristics in Nim Game

In the Nim Game, Alpha-Beta will explore fewer nodes than Minimax by pruning branches that cannot lead to a better outcome. For example, if removing 1 stick leads to a guaranteed win, the algorithm might not explore what happens if 2 or 3 sticks are removed.

## 3. Iterative Deepening Alpha-Beta Search

The `IterativeDeepeningAlphaBetaSearch` class implements an advanced version of Alpha-Beta pruning that gradually increases the search depth and includes additional optimizations.

### Game Tree Exploration Process

1. **Depth-Limited Search**: Unlike the previous algorithms, this one does not search all the way to terminal states. Instead, it limits the search to a certain depth:
   ```java
   if (game.isTerminal(state) || depth >= currDepthLimit) {
       return eval(state, player);
   }
   ```

2. **Iterative Deepening**: The algorithm starts with a small depth limit and gradually increases it:
   ```java
   do {
       incrementDepthLimit();
       // Perform depth-limited search
       // ...
   } while (!exit && maxDepthReached && !hasSafeWinner(resultValue));
   ```

3. **Time-Limited Search**: The algorithm stops if it exceeds a specified time limit:
   ```java
   if (results != null && System.currentTimeMillis() > startTime + maxTime) {
       exit = true;
       break;
   }
   ```

4. **Evaluation Function**: For non-terminal states at the depth limit, an evaluation function is used:
   ```java
   protected double eval(STATE state, PLAYER player) {
       if (game.isTerminal(state)) {
           return game.getUtility(state, player);
       } else {
           maxDepthReached = true;
           return (utilMin + utilMax) / 2;
       }
   }
   ```

5. **Action Ordering**: The algorithm allows for customizing the order in which actions are considered, which can improve pruning efficiency:
   ```java
   for (ACTION action : orderActions(state, game.getActions(state), player, depth)) {
       // ...
   }
   ```

6. **Early Termination**: The algorithm can terminate early if a clear best action is found:
   ```java
   if (!exit && results.size() == 1 && this.isSignificantlyBetter(resultValue, secondBestValue))
       break;
   ```

### Characteristics in Nim Game

In the Nim Game, Iterative Deepening Alpha-Beta will start by exploring the game tree to a shallow depth, then gradually increase the depth. This allows it to find good moves quickly and refine them as time permits. For a game like Nim with a relatively small branching factor, it might be able to search the entire game tree even with depth limits, but for larger games, the depth limit and time limit would prevent complete exploration.

## Comparison of Node Expansion

When running these algorithms on the Nim Game, we can observe the number of nodes expanded by each:

```
Metrics for Minimax : {expandedNodes=X}
Metrics for AlphaBeta : {expandedNodes=Y}
Metrics for IDAlphaBetaSearch : {expandedNodes=Z}
```

Typically, we would expect:
- Minimax to expand the most nodes
- Alpha-Beta to expand fewer nodes than Minimax
- Iterative Deepening Alpha-Beta to expand more nodes than Alpha-Beta (due to repeated searches at increasing depths) but potentially make better decisions with limited time

## Conclusion

The three algorithms represent a progression in sophistication for game tree exploration:

1. **Minimax**: Complete but potentially inefficient exploration of the entire game tree.

2. **Alpha-Beta**: More efficient exploration by pruning branches that cannot affect the final decision.

3. **Iterative Deepening Alpha-Beta**: Practical exploration that balances search depth, time constraints, and decision quality.

Each algorithm makes different trade-offs between exploration thoroughness, efficiency, and practical constraints like time limits. The choice of algorithm depends on the specific requirements of the game and the available computational resources.
