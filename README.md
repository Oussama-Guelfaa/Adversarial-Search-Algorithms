# Adversarial Search Algorithms - Project Overview

## Introduction

This project implements and analyzes various adversarial search algorithms used in game-playing AI. The focus is on understanding how to implement search algorithms like Minimax and Alpha-Beta pruning, and how to model adversarial games to solve them efficiently.

## Project Architecture

The project is organized into several packages:

```
chapter5-src/
├── src/
│   └── fr/
│       └── emse/
│           └── ai/
│               ├── adversarial/
│               │   ├── core/
│               │   │   └── Metrics.java
│               │   ├── nim/
│               │   │   ├── NimGame.java
│               │   │   └── NimGameplay.java
│               │   ├── ticTacToe/
│               │   │   ├── TicTacToeGame.java
│               │   │   └── TicTacToeGameplay.java
│               │   ├── AdversarialSearch.java
│               │   ├── AlphaBetaAlgo.java
│               │   ├── AlphaBetaSearch.java
│               │   ├── Game.java
│               │   ├── IterativeDeepeningAlphaBetaSearch.java
│               │   ├── MiniMaxAlgo.java
│               │   ├── MinimaxSearch.java
│               │   └── TestAdversarialSearch.java
│               └── util/
│                   └── SimpleTwoPlyGameTree.java
└── README_Exercise*.md
```

### Key Components

1. **Game Interface**: The `Game<STATE, ACTION, PLAYER>` interface defines the essential elements of a game, including states, actions, players, and utility functions.

2. **AdversarialSearch Interface**: The `AdversarialSearch<STATE, ACTION>` interface defines the methods for making decisions in adversarial games.

3. **Search Algorithms**:
   - `MinimaxSearch`: Implementation of the standard Minimax algorithm
   - `AlphaBetaSearch`: Implementation of Alpha-Beta pruning
   - `IterativeDeepeningAlphaBetaSearch`: Implementation of Iterative Deepening Alpha-Beta search

4. **Custom Implementations**:
   - `MiniMaxAlgo`: Custom implementation of Minimax for SimpleTwoPlyGameTree
   - `AlphaBetaAlgo`: Custom implementation of Alpha-Beta for SimpleTwoPlyGameTree

5. **Game Implementations**:
   - `NimGame`: Implementation of the Nim Game
   - `TicTacToeGame`: Implementation of Tic-Tac-Toe

6. **Utility Classes**:
   - `SimpleTwoPlyGameTree`: A simple two-ply game tree for testing algorithms
   - `Metrics`: Class for storing performance metrics

## Exercise Summaries

### Exercise 1: Minimax Algorithm Implementation

**Objective**: Implement the Minimax algorithm for a simple two-ply game tree.

**Implementation**: The `MiniMaxAlgo` class implements the Minimax algorithm for the `SimpleTwoPlyGameTree` class. It recursively explores the entire game tree, alternating between MAX and MIN nodes, to find the optimal move.

**Key Features**:
- Complete exploration of the game tree
- Node counting for performance analysis
- Value propagation from leaf nodes to the root

**Results**: When tested on the example game tree, the Minimax algorithm expanded 8 nodes and found the optimal value of 5 for the root node.

### Exercise 2: Alpha-Beta Pruning Algorithm Implementation

**Objective**: Implement the Alpha-Beta pruning algorithm for a simple two-ply game tree.

**Implementation**: The `AlphaBetaAlgo` class implements the Alpha-Beta pruning algorithm for the `SimpleTwoPlyGameTree` class. It optimizes the Minimax algorithm by pruning branches that cannot influence the final decision.

**Key Features**:
- Pruning of irrelevant branches
- Maintenance of alpha and beta values
- More efficient exploration than Minimax

**Results**: When tested on the example game tree, the Alpha-Beta algorithm expanded only 7 nodes (compared to 8 for Minimax) and found the same optimal value of 5 for the root node.

### Exercise 3: Comparison of Minimax and Alpha-Beta Algorithms

**Objective**: Compare the performance of the Minimax and Alpha-Beta pruning algorithms by counting the number of nodes explored.

**Implementation**: The `TestAdversarialSearch` class creates an example game tree and runs both algorithms on it, comparing their performance.

**Key Findings**:
- Both algorithms found the same optimal value (5)
- Minimax expanded 8 nodes
- Alpha-Beta expanded 7 nodes
- Alpha-Beta was 12.50% more efficient than Minimax

**Conclusion**: Alpha-Beta pruning is more efficient than Minimax as it explores fewer nodes while still finding the same optimal value. This efficiency improvement becomes more significant for larger game trees.

### Exercise 4: Analysis of the Nim Game Implementation

**Objective**: Analyze the Nim Game implementation to understand how the Game interface is used to model game rules.

**Analysis**:

1. **Player Representation**:
   - Players are represented as integers (0 and 1)
   - Player 0 is the human player, and Player 1 is the machine player

2. **State Representation**:
   - A state is represented as a List<Integer> with two elements
   - The first element (index 0) represents the current player's turn
   - The second element (index 1) represents the number of sticks remaining

3. **Action Representation**:
   - Actions are represented as integers (1, 2, or 3)
   - Each action represents the number of sticks to remove from the heap

4. **Utility Function**:
   - The utility function determines the value of a terminal state for a given player
   - It incorporates the winning strategy for Nim: leaving your opponent with a number of sticks that is 1 more than a multiple of 4

**Conclusion**: The Nim Game implementation demonstrates how a simple game can be modeled using the Game interface, with appropriate representations for states, actions, players, and a utility function that encodes the game's winning strategy.

### Exercise 5: Game Tree Exploration in Different Algorithms

**Objective**: Explain how the exploration of the game tree is performed in the three adversarial search algorithms provided in the framework.

**Analysis**:

1. **Minimax Algorithm**:
   - Explores the entire game tree recursively
   - Alternates between MAX and MIN levels
   - Propagates values back up the tree
   - In Nim, explores all possible ways to remove sticks until terminal states

2. **Alpha-Beta Pruning Algorithm**:
   - Uses alpha and beta parameters to prune branches
   - Explores fewer nodes than Minimax
   - In Nim, prunes branches that cannot lead to a better outcome

3. **Iterative Deepening Alpha-Beta Search**:
   - Uses depth-limited search with gradually increasing depth
   - Includes time limits and early termination conditions
   - Uses an evaluation function for non-terminal states at the depth limit
   - In Nim, starts with shallow searches and refines as time permits

**Conclusion**: The three algorithms represent a progression in sophistication for game tree exploration, making different trade-offs between thoroughness, efficiency, and practical constraints.

## Running the Project

To compile and run the project:

```bash
# Compile the code
javac -d bin -cp bin:src src/fr/emse/ai/adversarial/*.java

# Run the test for Minimax and Alpha-Beta
java -cp bin fr.emse.ai.adversarial.TestAdversarialSearch

# Run the Nim Game
java -cp bin fr.emse.ai.adversarial.nim.NimGameplay

# Run the Tic-Tac-Toe Game
java -cp bin fr.emse.ai.adversarial.ticTacToe.TicTacToeGameplay
```

## Conclusion

This project provides a comprehensive exploration of adversarial search algorithms and game modeling. Through the five exercises, we've implemented and analyzed different search algorithms, compared their performance, and understood how to model games using the provided interfaces.

The key insights from this project are:
1. The trade-off between exploration thoroughness and efficiency in search algorithms
2. The importance of proper game modeling for effective AI decision-making
3. The practical considerations like time limits and depth limits in real-world game-playing AI

These concepts form the foundation of adversarial search in artificial intelligence and are applicable to a wide range of games and competitive scenarios.
