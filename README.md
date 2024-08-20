# Binary-Search-Tree-Problem
In the binary search tree problem, we are trying to implement few functions which makes  sure that when an input string is added, found or deleted from a binary tree then the rotations  are done accordingly and a new binary search tree is returned. The **Binary Search Tree Problem** involves designing and implementing a dynamic binary search tree (BST) that adjusts its structure based on recent operations. The tree is designed to prioritize recently accessed elements, ensuring that they reside near the root for efficient search operations. This project showcases how a tree's structure can be dynamically adjusted to optimize access time for frequently used elements.

## Project Overview

In this project, the BST is structured to allow for the addition, search, and deletion of string elements. The core functionality includes:

- **Addition**: When a new string element is added, it becomes the root of the tree after appropriate rotations.
- **Search**: When an element is searched for, it is promoted to the root, again by performing necessary rotations.
- **Deletion**: When an element is deleted, it is moved to a leaf position through rotations, and its most recently accessed child becomes the successor.
- **Serialization**: The tree can be serialized into an array format where the parent, left child, and right child are stored in specific array indices.
- **Rebuilding**: The tree can be rebuilt from its serialized array representation, reconstructing the original parent-child relationships.

The dynamic nature of this BST makes it particularly useful in scenarios where certain elements are frequently accessed, as it optimizes the structure to ensure quick access to these elements.

## Key Features

- **Dynamic Rotations**: The tree structure adjusts dynamically with every add, search, or delete operation, promoting the accessed or added element to the root.
- **Serialization**: Converts the tree into an array format for storage or transmission, making it easy to save and restore the tree's structure.
- **Rebuilding**: Reconstructs the tree from its serialized form, ensuring that the original structure and access patterns are preserved.

## System Modules

### 1. **WorkingSetTree Class**
- The main class responsible for managing the binary search tree. It implements the core operations of adding, finding, deleting, serializing, and rebuilding the tree.

- Methods include:
    - `add(String key)`: Adds a string key to the tree, promoting it to the root after rotations. Returns true if successful and false if the key is already present or an error occurs.
    - `find(String key)`: Searches for a string key in the tree. If found, promotes the key to the root. Returns true if the key is found, false otherwise.
    - `remove(String key)`: Removes a string key from the tree. The key is moved to a leaf position before being deleted. Returns true if the key is successfully removed, false otherwise.
    - `size()`: Returns the number of elements currently stored in the tree.
    - `levelOf(String key)`: Returns the level (distance from the root) of the node containing the given key, or 0 if the key is not found.
    - `serialize()`: Converts the tree into an array format, storing the parent at index `x`, the left child at `2x`, and the right child at `2x+1`.
    - `rebuild(String[] keys)`: Reconstructs the tree from a serialized array representation, ensuring the correct parent-child relationships are restored.

### 2. **Searchable Interface**
- The `Searchable` interface defines the contract for the tree operations. The `WorkingSetTree` class implements this interface, providing concrete implementations of the methods. The interface methods are overridden in the `WorkingSetTree` class to execute the required operations.

## Data Structures

- **Array**: The primary data structure used throughout the implementation. The tree is serialized into an array for easy storage and rebuilding.

    - **Serialize Method**: Converts the binary search tree into an array. The size of the array is determined based on the maximum depth of the tree.
    - **Rebuild Method**: Reconstructs the binary search tree from the serialized array. Parent-child relationships are determined based on array indices.

- **No Complex Data Structures**: The problem constraints focus on simplicity and functional correctness rather than efficiency. Therefore, arrays are the primary data structure used, ensuring the implementation is straightforward and easy to understand.

## Constraints and Considerations

- **Time Complexity**: The problem emphasizes correctness over efficiency, so the solution may have higher time complexity, especially since only arrays are used for operations.
- **Duplicate Values**: The tree does not support duplicate values, even if they differ by case sensitivity.
- **Static Array Sizes**: The tree cannot scale to arbitrary sizes due to the use of static array sizes in some parts of the implementation.

## Conclusion

The **Binary Search Tree Problem** project demonstrates how to implement a dynamic binary search tree that adjusts its structure based on recent operations. By prioritizing recently accessed elements, the tree optimizes search operations, making it suitable for scenarios where certain elements are frequently accessed. The project's use of simple data structures like arrays ensures that the solution is both functional and easy to implement, while the dynamic nature of the tree provides a powerful tool for managing data in an unbalanced BST.

## Flowchart
- [Binary Tree Problem Flowchart](BSTAdd.drawio.png)