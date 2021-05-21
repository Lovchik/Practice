package by.vit.ban;

/**
 * Class entity which contains information about Tree
 */
public class Tree {

    /**
     * Value of current element in tree.
     */
    protected int value;

    /**
     * Right side of tree.
     */
    protected Tree right;

    /**
     * Left side of tree.
     */
    protected Tree left;

    Tree(int value) {
        this.value = value;
        right = null;
        left = null;
    }

    public Tree() {
    }
}