# Exercise 4: Analysis of the Nim Game Implementation

## Overview

The Nim Game is a mathematical game of strategy in which two players take turns removing objects from distinct heaps. In this implementation, there is a single heap of sticks, and players can remove 1, 2, or 3 sticks at a time. The player who removes the last stick from the pool loses the game.

This document analyzes how the Nim Game is implemented using the Game interface provided in the framework.

## Game Interface Implementation

The `NimGame` class implements the `Game<List<Integer>, Integer, Integer>` interface, which means:
- States are represented as `List<Integer>`
- Actions are represented as `Integer`
- Players are represented as `Integer`

Let's analyze each aspect of the implementation:

## 1. How are players represented?

Players are represented as integers, specifically 0 and 1:

```java
public final static Integer[] players = {0, 1};
```

- Player 0 represents the human player
- Player 1 represents the machine player

This can be seen in the `NimGameplay` class where the condition `state.get(0) == 0` is used to determine if it's the human player's turn.

## 2. What is a state of the game?

A state in the Nim Game is represented as a `List<Integer>` with two elements:

```java
initialState.add(0);    // First element: the current player (0 or 1)
initialState.add(size); // Second element: the number of sticks remaining
```

- The first element (index 0) represents the current player's turn (0 or 1)
- The second element (index 1) represents the number of sticks remaining in the heap

For example, a state `[0, 15]` means it's player 0's turn and there are 15 sticks remaining.

## 3. How are actions represented?

Actions are represented as integers (1, 2, or 3), indicating the number of sticks to remove from the heap:

```java
public List<Integer> getActions(List<Integer> state) {
    ArrayList<Integer> actions = new ArrayList<Integer>();
    for (int i = 1; i <= Math.min(3, state.get(1)); i++)
        actions.add(i);
    return actions;
}
```

The `getActions` method returns a list of possible actions (1, 2, or 3) based on the number of sticks remaining. If there are fewer than 3 sticks remaining, the possible actions are limited accordingly.

## 4. What is the meaning of the utility function?

The utility function in the Nim Game determines the value of a terminal state for a given player. The implementation is quite sophisticated:

```java
public double getUtility(List<Integer> state, Integer player) {
    if (state.get(0) == 1 - player) {
        if (state.get(1) == 1)
            return Double.POSITIVE_INFINITY;
        else if (((state.get(1) - 1) % 4) == 0)
            return 1;
        else
            return -1;
    } else {
        if (state.get(1) == 1)
            return Double.NEGATIVE_INFINITY;
        else if (((state.get(1) - 1) % 4) == 0)
            return -1;
        else
            return 1;
    }
}
```

The utility function considers:

1. Whether it's the player's turn or the opponent's turn (`state.get(0) == 1 - player`)
2. The number of sticks remaining (`state.get(1)`)
3. A mathematical pattern based on the remainder when (sticks - 1) is divided by 4

The utility values have the following meanings:

- `Double.POSITIVE_INFINITY`: A guaranteed win for the player
- `Double.NEGATIVE_INFINITY`: A guaranteed loss for the player
- `1`: A favorable position for the player
- `-1`: An unfavorable position for the player

The utility function incorporates a winning strategy for the Nim Game: if you can leave your opponent with a number of sticks that is 1 more than a multiple of 4 (i.e., 1, 5, 9, 13, 17, ...), you can force a win. This is why the condition `((state.get(1) - 1) % 4) == 0` is used to determine if a position is favorable or unfavorable.

## Conclusion

The Nim Game implementation demonstrates how a simple game can be modeled using the Game interface. The state representation, action definition, and utility function are designed to capture the essential elements of the game and enable effective adversarial search algorithms to find optimal strategies.

The implementation also showcases a key insight into the Nim Game: the winning strategy is based on leaving your opponent with a number of sticks that is 1 more than a multiple of 4. This mathematical pattern is encoded in the utility function to guide the search algorithms toward optimal play.
