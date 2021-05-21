package by.vit.ban;


import java.util.List;

/**
 * Class which count elements of binary tree with 2 child
 */
public class BinaryTree extends Tree {
    BinaryTree() {

    }

    /**
     * Create Binary tree from list.
     *
     * @param input list of elements binary tree
     * @return the binary tree with elements from list.
     */
    public static BinaryTree createBinaryTree(List<Integer> input) {
        BinaryTree binaryTree = new BinaryTree();
        for (Integer integer : input) {
            binaryTree.add(integer);
        }
        return binaryTree;
    }

    /**
     * walks the tree and counts the number of items with two child
     *
     * @return count of Tree elements with two child for current tree.
     */
    public static int getCountNodesWithTwoHeirs(Tree tree) {
        int nodeCount = 0;
        if (tree == null) {
            return nodeCount;
        }
        if (tree.left != null) {
            nodeCount += getCountNodesWithTwoHeirs(tree.left);
        }
        if (tree.right != null) {
            nodeCount += getCountNodesWithTwoHeirs(tree.right);
        }
        if ((tree.left != null) && (tree.right != null)) {
            return nodeCount + 1;
        }
        return nodeCount;
    }

    /**
     * Add element in binary tree to the right place
     *
     * @param actual binary tree
     * @param value  value of element
     * @return the stringBuilder number with the ending.
     */
    public Tree addElement(Tree actual, int value) {
        if (actual == null) {
            return new Tree(value);
        }
        if (value < actual.value) {
            actual.left = addElement(actual.left, value);
        }
        if (value > actual.value) {
            actual.right = addElement(actual.right, value);
        }
        return actual;
    }

    /**
     * Add element in binary tree
     *
     * @param value value of element
     */
    public void add(int value) {
        addElement(this, value);
    }

    /**
     * Get count of elevents
     *
     * @return count of Tree elements with two child for current tree.
     */
    public int getCountNodesWithTwoHeirs() {
        return getCountNodesWithTwoHeirs(this);
    }
}