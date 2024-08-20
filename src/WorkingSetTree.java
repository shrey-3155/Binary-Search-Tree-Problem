/**
 * Author: Shrey Patel
 * Banner-Id: B00960433, email id: sh644024@dal.ca
 * Assignment 2, Binary Search Tree problem
 */

import java.util.Arrays;

public class WorkingSetTree implements Searchable {

    private TreeStructure binaryTree;

    public WorkingSetTree() {
        this.binaryTree = null;
    }

    /**
     * A static class representing a node in a binary tree.
     * Each node is a object that stores a key, its left and right children, also its level and access value
     * the level of the node in the tree, and a reference to its parent node.
     */
    private static class TreeStructure {
        String key; // The value stored in the node
        int level; // Level of the node in the tree (depth)
        int accessValue; // get the access value of each node as they are added or found
        TreeStructure parent; // Reference to the parent node
        TreeStructure leftChild; // Reference to the left child node
        TreeStructure rightChild; // Reference to the right child node


        /**
         * Defining a Constructor to initialize a node with a given key.
         * Initializes leftChild and rightChild as null, level as 1,
         * and parent as null.
         * Here key The value to be stored in the node.
         */
        TreeStructure(String key) {
            this.key = key; // Initialize key
            this.leftChild = null; // Initialize left child as null
            this.rightChild = null; // Initialize right child as null
            this.level = 1; // Initialize level as 1
            this.parent = null; // Initialize parent as null
            this.accessValue = 0;//Initialize access value as 0
        }
    }

    /**
     * Inserts a new node with the given key into the binary search tree.
     * If the tree is empty, the new node becomes the root.
     * If the key already exists in the tree, the method returns false.
     * The variable key is value of the node to be inserted.
     * The output returns True if the node is successfully inserted, false if the key already exists.
     * The method to add element and
     * References: https://www.codesdope.com/course/data-structures-binary-search-trees/
     */
    @Override
    public boolean add(String key) {
        // Create a new node with the given input
        TreeStructure newNode = new TreeStructure(key);
        if (key == null) {
            return false;
        }

        // If the tree is empty, set the new node as the root
        if (binaryTree == null) {
            binaryTree = newNode; //Assigning value of root to the new node.
            binaryTree.accessValue = binaryTree.accessValue + 1; //incrementing the access Value by 1.
            return true;
        }

        // Find the parent node for the new node
        TreeStructure parent = findParentNode(key);

        // If the key already exists, return false
        if (parent == null) {
            find(key); //Call find function if duplicate value is added again and rotate the tree.
            return false; // If key already exists, return false

        }

        // Determine if the new node is a left child or a right child
        boolean isLeftChild = (key.compareToIgnoreCase(parent.key) < 0);
        if (binaryTree != null) {
            newNode.accessValue = binaryTree.accessValue + 1;
        }
        // Add the new node to the parent after checking whether it is right or left child
        if (isLeftChild) {
            parent.leftChild = newNode;
        } else {
            parent.rightChild = newNode;
        }
        newNode.parent = parent;//Assigning the new parent value in the node

        // Rotate the new node to the root for maintaining the condition of newly added value as the root of tree
        binaryTree = treeRotation(newNode);

        return true;
    }

    /**
     * Finds the parent node for the node with the given key in the binary search tree.
     * The input paramater key of the node to find the parent for.
     * Returns the parent node of the node with the given key.
     * If the key already exists in the tree, returns null.
     */
    private TreeStructure findParentNode(String key) {
        // Initialize variables to keep track of the current node and its parent
        TreeStructure temp = binaryTree; // Start from the root node
        TreeStructure parent = null; // Initialize parent to null, as it will be set during traversal
        boolean isLeftChild = false; // Flag to indicate if the current node is a left child of its parent

        // Traverse the tree to find the parent node for the new node
        while (temp != null) {
            parent = temp; // Update the parent to the current node before traversing further
            // Compare the key with the key of the current node to determine the direction of traversal
            if (key.compareToIgnoreCase(temp.key) < 0) {
                // If the key is less than the current node's key, move to the left child
                temp = temp.leftChild;
                isLeftChild = true; // Update the flag to indicate that the current node is a left child
            } else if (key.compareToIgnoreCase(temp.key) > 0) {
                // If the key is greater than the current node's key, move to the right child
                temp = temp.rightChild;
                isLeftChild = false; // Update the flag to indicate that the current node is a right child
            } else {
                // If the key already exists in the tree, return null (no parent)
                return null;
            }
        }
        // Return the parent of the node with the given key
        // If the key does not exist in the tree, this will be the last non-null node got during traversal
        return parent;
    }


    /**
     * Performs the rotations operation on the given node with respect to the given root in a binary search tree.
     * Rotating moves the specified node towards the root of the tree by performing rotations.
     * The parameter node The node to be rotated.
     * the function returns a node that has been rotated to the root position.
     * References: The below algorithm for rotation with some changes according to the assignment requirement is referred from below:
     * https://algorithmtutor.com/Data-Structures/Tree/Splay-Trees/
     * The theory and functionality of rotation is referred from a youtube channel:
     * https://www.youtube.com/watch?v=1HeIZNP3w4A
     */
    private TreeStructure treeRotation(TreeStructure node) {
        //iterating till the parent becomes null
        while (node.parent != null) {
            TreeStructure parent = node.parent; // Get the parent of the current node
            TreeStructure grandParent = parent.parent; // Get the grandparent of the current node

            if (grandParent == null) { // Single rotation case
                // Determine whether to perform a right or left rotation based on the node's position relative to its parent
                if (node == parent.leftChild) {
                    node = zigRotation(parent); // Perform a right rotation
                } else {
                    node = zagRotation(parent); // Perform a left rotation
                }
            } else { // Double rotation case
                // Determine which type of double rotation to perform based on the node's position relative to its parent and grandparent
                if (node == parent.leftChild && parent == grandParent.leftChild) {
                    node = zigRotation(grandParent); // Perform a right rotation on the grandparent
                    node = zigRotation(parent); // Perform a right rotation on the parent
                } else if (node == parent.rightChild && parent == grandParent.rightChild) {
                    node = zagRotation(grandParent); // Perform a left rotation on the grandparent
                    node = zagRotation(parent); // Perform a left rotation on the parent
                } else if (node == parent.rightChild && parent == grandParent.leftChild) {
                    node = zagRotation(parent); // Perform a left rotation on the parent
                    node = zigRotation(grandParent); // Perform a right rotation on the grandparent
                } else {
                    node = zigRotation(parent); // Perform a right rotation on the parent
                    node = zagRotation(grandParent); // Perform a left rotation on the grandparent
                }
            }
        }
        return node; // Return the node that has been rotated to the root position
    }
    /**
     * Performs a right rotation on the given node in a binary search tree.
     * The parameter node is to perform the right rotation on.
     * Returns the new root of the subtree after the rotation.
     */

    private TreeStructure zigRotation(TreeStructure node) {
        // Store the left child of the node
        TreeStructure leftChild = node.leftChild;

        // Update parent references
        leftChild.parent = node.parent; // Update the parent of the left child to the parent of the original node
        node.parent = leftChild; // Update the parent of the original node to the left child

        // Perform the rotation
        node.leftChild = leftChild.rightChild; // Update the left child of the original node to the right child of the left child
        if (leftChild.rightChild != null) {
            leftChild.rightChild.parent = node; // Update the parent reference of the new left child of the original node
        }
        leftChild.rightChild = node; // Update the right child of the left child to the original node

        // Update parent's child reference
        if (leftChild.parent != null) {
            // Update the appropriate child reference of the parent of the left child
            if (leftChild.parent.leftChild == node) {
                leftChild.parent.leftChild = leftChild;
            } else {
                leftChild.parent.rightChild = leftChild;
            }
        }
        // Return the new root of the subtree
        return leftChild;
    }

    /**
     * Performs a left rotation on the given node in a binary search tree.
     * Takes input of the node to perform the left rotation on.
     * Returns the new root of the subtree after the rotation.
     */
    private TreeStructure zagRotation(TreeStructure node) {
        // Store the right child of the node
        TreeStructure rightChild = node.rightChild;

        // Update parent references
        rightChild.parent = node.parent; // Update the parent of the right child to the parent of the original node
        node.parent = rightChild; // Update the parent of the original node to the right child
        // Perform the rotation
        node.rightChild = rightChild.leftChild; // Update the right child of the original node to the left child of the right child
        if (rightChild.leftChild != null) {
            rightChild.leftChild.parent = node; // Update the parent reference of the new right child of the original node
        }
        rightChild.leftChild = node; // Update the left child of the right child to the original node

        // Update parent's child reference
        if (rightChild.parent != null) {
            // Update the appropriate child reference of the parent of the right child
            if (rightChild.parent.leftChild == node) {
                rightChild.parent.leftChild = rightChild;
            } else {
                rightChild.parent.rightChild = rightChild;
            }
        }

        // Return the new root of the subtree
        return rightChild;
    }


    /**
     * Checks if a given key is present in the binary search tree rooted at the specified node.
     * Takes node as input which is root node of the binary search tree.
     * Other input is the key to search for in the tree.
     * Function returns true if the key is found in the tree, false otherwise.
     */
    private boolean isElementPresent(TreeStructure node, String key) {
        // Iterate through the tree until reaching a leaf node or finding the key
        while (node != null) {
            // If the key is less than the current node's key, move to the left child
            if (key.compareToIgnoreCase(node.key) < 0) {
                node = node.leftChild;
            }
            // If the key is greater than the current node's key, move to the right child
            else if (key.compareToIgnoreCase(node.key) > 0) {
                node = node.rightChild;
            }
            // If the key matches the current node's key, return true
            else {
                // Key found
                return true;
            }
        }
        // Key not found in the tree
        return false;
    }

    /**
     * Searches for a given key in the binary search tree.
     * If the key is found, the method performs the rotation operation to bring the accessed node closer to the root.
     * input parameter key is to search for.
     * The function returns true if the key is found and false otherwise.
     */
    @Override
    public boolean find(String key) {
        if (binaryTree == null || key == null) {
            return false; // Return false if the tree is empty or the key is null
        }
        TreeStructure temp = binaryTree;
        TreeStructure parent = null;
        TreeStructure lastAccessed = null;
        while (temp != null) {
            parent = temp;
            if (key.compareToIgnoreCase(temp.key) < 0) {
                temp = temp.leftChild; // Move to the left child if the key is less than the current node's key
            } else if (key.compareToIgnoreCase(temp.key) > 0) {
                temp = temp.rightChild; // Move to the right child if the key is greater than the current node's key
            } else {
                // Key found
                lastAccessed = temp; // Store the last accessed node
                break;
            }
        }
        if (lastAccessed != null) {
            temp.accessValue = binaryTree.accessValue + 1;
            // Key found, perform rotation operation to bring the accessed node closer to the binaryTree
            binaryTree = treeRotation(lastAccessed);
            return true;
        } else {
            return false; // Key not found
        }
    }

    /**
     * Removes a node with the specified key from the binary search tree.
     * If the key is null or not found in the tree, returns false.
     * Otherwise, removes the node and adjusts the tree structure accordingly.
     * Returns true upon successful removal.
     * input key is the key of the node to be removed
     * function returns true if the node is successfully removed, false otherwise
     */
    @Override
    public boolean remove(String key) {
        if (key == null || binaryTree == null) {
            return false;
        }

        // Check if the key exists in the tree
        if (!isElementPresent(binaryTree, key)) {
            return false; // Key not found, return false
        }

        // Find the node to be deleted
        TreeStructure node = findNode(binaryTree, key);

        // If the node to be deleted is the binaryTree
        if (node == binaryTree) {
            // Case 1: binaryTree has no children
            if (node.leftChild == null && node.rightChild == null) {
                binaryTree = null; // Tree becomes empty
            }
            // Case 2: binaryTree has one child
            else if (node.leftChild == null) {
                binaryTree = node.rightChild; // Replace root with its right child
                node.rightChild.parent = null;
            } else if (node.rightChild == null) {
                binaryTree = node.leftChild; // Replace root with its left child
                node.leftChild.parent = null;
            }
            // Case 3: Root has two children
            else {
                TreeStructure successor = findMax(node.leftChild);
                if (successor.parent != node) {
                    // If the successor is not the immediate left child of the root
                    rotateToLeaf(binaryTree, successor, successor.rightChild);
                    successor.rightChild = node.rightChild;
                    successor.rightChild.parent = successor;
                }
                // Promote the successor to the root position
                binaryTree = successor;
                // Adjust the left child of the root
                binaryTree.leftChild = node.leftChild;
                binaryTree.leftChild.parent = binaryTree;
            }
        } else {
            // For non-root nodes, use the existing logic
            TreeStructure parent = node.parent;
            // Case 1: Node has no children
            if (node.leftChild == null && node.rightChild == null) {
                if (parent.leftChild == node) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
            }
            // Case 2: Node has one child
            else if (node.leftChild == null) {
                if (parent.leftChild == node) {
                    parent.leftChild = node.rightChild;
                    node.rightChild.parent = parent;
                } else {
                    parent.rightChild = node.rightChild;
                    node.rightChild.parent = parent;
                }
            } else if (node.rightChild == null) {
                if (parent.leftChild == node) {
                    parent.leftChild = node.leftChild;
                    node.leftChild.parent = parent;
                } else {
                    parent.rightChild = node.leftChild;
                    node.leftChild.parent = parent;
                }
            }
            // Case 3: Node has two children
            else {
                TreeStructure successor = findMax(node.leftChild);
                if (successor.parent != node) {
                    // If the successor is not the immediate left child of the node
                    rotateToLeaf(binaryTree, successor, successor.rightChild);
                    successor.rightChild = node.rightChild;
                    successor.rightChild.parent = successor;
                }
                if (parent.leftChild == node) {
                    parent.leftChild = successor;
                } else {
                    parent.rightChild = successor;
                }
                successor.leftChild = node.leftChild;
                successor.leftChild.parent = successor;
                successor.rightChild = node.rightChild;
                successor.leftChild = null;
            }
        }
        return true; // Key removed successfully
    }

    /**
     * Finds the node containing the maximum value in the subtree rooted at the given node.
     * Input parameter node is the root of the subtree to search
     * Function returns the node containing the maximum value, or null if the subtree is empty
     */
    private TreeStructure findMax(TreeStructure node) {
        if (node == null) {
            return null; // Return null if the tree is empty
        }
        // Traverse to the rightmost node, which contains the maximum value
        while (node.rightChild != null) {
            node = node.rightChild;
        }
        return node; // Return the node containing the maximum value
    }

    /**
     * Replaces a node to be removed with a specified replacement node.
     * Adjusts parent-child relationships and promotes the replacement node if its access value is higher.
     * Input parameter root is root of the tree containing the node to be replaced
     * Inout parameter nodeToRemove which is the node to be removed
     * Another input parameter is nodeToReplaceWith which is the replacement node
     * References: The algorithm to swap the parent child relationship is referred from below
     * https://www.codesdope.com/course/data-structures-binary-search-trees/
     */
    private void rotateToLeaf(TreeStructure root, TreeStructure nodeToRemove, TreeStructure nodeToReplaceWith) {
        if (nodeToRemove.parent == null) {
            // nodeToRemove is the root
            root = nodeToReplaceWith;
        } else if (nodeToRemove == nodeToRemove.parent.leftChild) {
            // nodeToRemove is the left child
            nodeToRemove.parent.leftChild = nodeToReplaceWith;
        } else {
            // nodeToRemove is the right child
            nodeToRemove.parent.rightChild = nodeToReplaceWith;
        }
        if (nodeToReplaceWith != null) {
            nodeToReplaceWith.parent = nodeToRemove.parent;
        }
        // Compare access values of nodeToRemove and nodeToReplaceWith, and make nodeToReplaceWith the parent if it has a higher access value
        if (nodeToReplaceWith != null && nodeToRemove.accessValue < nodeToReplaceWith.accessValue) {
            nodeToRemove.parent = nodeToReplaceWith;
        }
    }

    /**
     * Finds and returns the node with the specified key in the binary search tree.
     * Input parameter node is the root of the subtree to search
     * Input parameter key is the key of the node to find
     * Function returns the node with the specified key, or null if not found
     */
    private TreeStructure findNode(TreeStructure node, String key) {
        // Base case: If the tree is empty or the node is null, return null
        if (node == null) {
            return null;
        }
        // Compare the key with the current node's key to determine the direction of traversal
        // If the key is less than the current node's key, move to the left subtree
        if (key.compareToIgnoreCase(node.key) < 0) {
            return findNode(node.leftChild, key);
        }
        // If the key is greater than the current node's key, move to the right subtree
        else if (key.compareToIgnoreCase(node.key) > 0) {
            return findNode(node.rightChild, key);
        }
        // If the key is equal to the current node's key, return the current node
        else {
            return node;
        }
    }

    /**
     * Calculates the number of nodes in the binary search tree.
     * No input parameter required
     * It returns The number of nodes in the binary search tree.
     */
    @Override
    public int size() {
        if (binaryTree == null) {
            return 0; // If the tree is empty, return 0
        }
        TreeStructure[] maxTreeSize = new TreeStructure[10000]; // Assuming maximum 10000 nodes as static
        int top = -1; // Stack pointer
        int size = 0; // Variable to store the size of the tree
        maxTreeSize[++top] = binaryTree; // Push the root node onto the stack
        while (top >= 0) { // While the stack is not empty
            TreeStructure current = maxTreeSize[top--]; // Pop the top node from the stack
            size++; // Increment the size counter for each visited node
            if (current.rightChild != null) {
                maxTreeSize[++top] = current.rightChild; // Push the right child onto the stack if it exists
            }
            if (current.leftChild != null) {
                maxTreeSize[++top] = current.leftChild; // Push the left child onto the stack if it exists
            }
        }
        return size; // Return the total size of the tree
    }

    /**
     * Retrieves the level of a specified key in the binary search tree.
     * input parameter key whose level is to be retrieved.
     * function returns The level of the specified key in the binary search tree, or 0 if the key is not found.
     */
    @Override
    public int levelOf(String key) {
        if (key == null) {
            return 0;
        }
        // Call the recursive helper method to find the level of the key starting from the binaryTree
        return nodeLevel(binaryTree, key, 1);
    }

    /**
     * Recursively finds the level of the specified key in the binary search tree.
     * The input parameter node  is the current node being examined.
     * The input parameter key is the key whose level is to be found.
     * The input parameter initialLevel The level of the current node in the binary search tree.
     * The function returns the level of the specified key in the binary search tree, or 0 if the key is not found.
     */
    private int nodeLevel(TreeStructure node, String key, int initialLevel) {
        while (node != null) {
            if (key.compareToIgnoreCase(node.key) < 0) {
                node = node.leftChild; // Move to the left child if the key is less than the current node's key
            } else if (key.compareToIgnoreCase(node.key) > 0) {
                node = node.rightChild; // Move to the right child if the key is greater than the current node's key
            } else {
                // Key found, return the current level
                return initialLevel;
            }
            initialLevel = initialLevel+1; // Increment the level as we traverse down the tree
        }
        return 0; // Key not found, return 0
    }


    /**
     * Rebuilds a binary search tree from the given array of keys.
     * input parameter keys is The array of keys representing the binary tree
     * the function returns True if the rebuild operation was successful, false otherwise
     */
    public boolean rebuild(String[] keys) {
        // Check for invalid input
        if (keys == null || keys.length < 2) {
            return false;
        }
        //Check if the first element is null
        if (keys[1] == null) {
            return false;
        }

        // Rebuild the binary search tree from the array
        if (rebuildFromIndex(keys, 1)) {
            binaryTree = buildTreeFromArray(keys, 1);
            return true;
        }

        // If the rebuild operation fails, return false
        return false;
    }

    /**
     * Builds a binary tree from the given array of keys.
     * input parameters keys is the array of keys representing the binary tree
     * Another parameter index is the index to start building the tree from
     * Function returns the root node of the constructed binary tree
     */
    private TreeStructure buildTreeFromArray(String[] keys, int index) {
        // Base case: if the index is out of bounds or the current node is null, return null
        if (index >= keys.length || keys[index] == null) {
            return null;
        }

        // Create a new node with the key at the current index
        TreeStructure newNode = new TreeStructure(keys[index]);

        // Recursively build the left and right subtrees
        newNode.leftChild = buildTreeFromArray(keys, 2 * index);
        newNode.rightChild = buildTreeFromArray(keys, 2 * index + 1);

        // Set parent references for the children nodes
        if (newNode.leftChild != null) {
            newNode.leftChild.parent = newNode;
        }
        if (newNode.rightChild != null) {
            newNode.rightChild.parent = newNode;
        }

        return newNode;
    }

    /**
     * Rebuilds the binary search tree from the given array of keys starting from the specified index.
     * Input parameter keys is the array of keys representing the binary tree
     * The other parameter index is index to start rebuilding the tree from
     * Function returns true if the rebuild operation was successful, false otherwise
     */
    private boolean rebuildFromIndex(String[] keys, int index) {
        int length = keys.length;

        // Loop through all nodes in the tree
        while (index < length) {
            String parentKey = keys[index];
            // Check if the current node is null
            if (parentKey == null) {
                // Check if the current node has non-null children
                if ((2 * index < length && keys[2 * index] != null) ||
                        (2 * index + 1 < length && keys[2 * index + 1] != null)) {
                    return false; // Null node should not have non-null children
                }
            } else {
                // Check if the left child violates the BST property
                if (2 * index < length && keys[2 * index] != null &&
                        parentKey.compareToIgnoreCase(keys[2 * index]) <= 0) {
                    return false;
                }
                // Check if the right child violates the BST property
                if (2 * index + 1 < length && keys[2 * index + 1] != null &&
                        parentKey.compareToIgnoreCase(keys[2 * index + 1]) >= 0) {
                    return false;
                }
            }

            // Move to the next node by incrementing index
            index = index + 1;
        }

        return true; // All nodes processed, BST property holds
    }

    /**
     * Serializes the binary search tree into an array representation.
     * The function returns an array representing the serialized binary search tree.
     */
    @Override
    public String[] serialize() {
        if (binaryTree == null) {
            return new String[]{null}; // Return an array with null at entry 0 for an empty tree
        }
        // Calculate the maximum depth of the tree to determine array size
        int maxDepth = calculateMaxDepth(binaryTree);
        String[] result = new String[(int) Math.pow(2, maxDepth)]; // Initialize result array
        Arrays.fill(result, null);  // Fill the array with null initially
        // Start serializing from the root at index 1
        serializeExecute(binaryTree, 1, result);
        return result;
    }

    /**
     * Calculates the maximum depth of the binary search tree.
     * input parameter root is the root of the binary search tree.
     * function returns the maximum depth of the tree.
     */
    private int calculateMaxDepth(TreeStructure root) {
        if (root == null) {
            return 0;
        }
        TreeStructure[] currentLevel = new TreeStructure[1]; // Array to store nodes at the current level
        currentLevel[0] = root;
        int maxDepth = 0;
        while (currentLevel.length != 0) {
            maxDepth=maxDepth+1; // Increment the depth for each level
            TreeStructure[] nextLevel = new TreeStructure[currentLevel.length * 2]; // Array for next level nodes
            int nextIndex = 0; // Index to keep track of the next available position in the next level array

            // Iterate over nodes at the current level
            for (TreeStructure node : currentLevel) {
                // Add left child to next level array if exists
                if (node != null && node.leftChild != null) {
                    nextLevel[nextIndex++] = node.leftChild;
                }
                // Add right child to next level array if exists
                if (node != null && node.rightChild != null) {
                    nextLevel[nextIndex++] = node.rightChild;
                }
            }

            // Set next level array as current level array, trimming any excess null entries
            currentLevel = Arrays.copyOf(nextLevel, nextIndex);
        }

        return maxDepth;
    }

    /**
     * Executes the serialization process recursively.
     * input parameter node The current node being serialized.
     * input parameter index is the index in the array where the node's key is to be stored.
     * the function returns the array representing the serialized binary search tree.
     */
    private void serializeExecute(TreeStructure node, int index, String[] serializeArray) {
        if (node == null || index >= serializeArray.length) {
            return; // Base case: stop recursion if node is null or index exceeds array size
        }
        // Store the key of the current node at the specified index
        serializeArray[index] = node.key;
        // If the left child exists, recursively serialize it
        if (node.leftChild != null) {
            serializeExecute(node.leftChild, 2 * index, serializeArray);
        }
        // If the right child exists, recursively serialize it
        if (node.rightChild != null) {
            serializeExecute(node.rightChild, 2 * index + 1, serializeArray);
        }
    }



}
