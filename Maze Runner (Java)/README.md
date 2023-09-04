# Maze Runner (Java)

Maze Runner is an application developed in Java to find the path through a given maze. The uniqueness of this system is its ability to utilize different search algorithms, specifically BFS (Breadth-First Search), DFS (Depth-First Search), and A* (A-Star Search).
Mazes are amazing: keep running, find the exit if you can! If you are not too happy about getting stuck in the actual maze, you can entertain yourself with a virtual one :)

## Core Algorithms

### BFS (Breadth-First Search)
BFS explores the maze level by level, moving outwardly in concentric circles. It ensures that all nodes at the current depth level are visited before moving on to nodes at the next depth level.

### DFS (Depth-First Search)
DFS dives deeper into the maze, exploring as far as possible along a branch before backtracking. It uses a last-in, first-out approach, ensuring the most recently discovered unvisited nodes are explored first.

### A* (A-Star Search)
A* combines the strengths of BFS and heuristic-based search. It uses a heuristic (often the Euclidean or Manhattan distance) to prioritize nodes that are more likely to lead to a solution. A* is beneficial in many real-world applications because it tends to find a path quickly compared to BFS and DFS.

---

## Topics Covered

1. **ArrayList**: A resizable array that implements the List interface, allowing dynamic modification of its size.
2. **Comparable**: An interface for ordering objects based on their natural ordering.
3. **Comparator**: An external tool (often as an interface) to define a custom order for objects.
4. **Functional interfaces**: Interfaces that have just one abstract method and can have multiple default or static methods.
5. **Generics and Object**: Generics provide type safety, ensuring that the correct types of objects are used. The Object class is the root of the Java class hierarchy.
6. **Lambda expressions**: A concise way to represent functional interface instances. Lambda expressions can be used primarily to define inline implementation of functional interfaces.
7. **PriorityQueue**: A specialized queue that sorts its elements based on their natural order or based on a comparator.
8. **Stack**: A last-in, first-out (LIFO) collection of objects. Useful for DFS as it allows for backtracking.
9. **The Collections Framework overview**: A unified architecture to represent and manipulate collections, enabling them to be manipulated independently of the details of their representation.
10. **The List interface**: Part of the Java Collections Framework, the List interface extends the Collection interface and defines an ordered collection.
11. **What are collections**: Collections in Java provide a way to handle and group multiple items as a single unit, like lists, sets, and maps.

---
