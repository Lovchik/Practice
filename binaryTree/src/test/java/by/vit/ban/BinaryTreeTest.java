package by.vit.ban;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class BinaryTreeTest {
    @Test
    public void binaryTheeElementWithTwoChildTest() {
        BinaryTree binaryTree = BinaryTree.createBinaryTree(Arrays.asList(56, 23, 41, 67, 32, 82, 26, 58, 45, 87, 86, 95, 1));
        Assert.assertEquals(5, binaryTree.getCountNodesWithTwoHeirs());
    }

    @Test
    public void sameBinaryTreeElementsTest() {
        BinaryTree binaryTree = BinaryTree.createBinaryTree(Arrays.asList(56, 23, 41, 67, 32, 82, 26, 58, 45, 87, 86, 95, 1, 23));
        Assert.assertEquals(5, binaryTree.getCountNodesWithTwoHeirs());
    }

    @Test
    public void negativeBinaryTreeElementsTest() {
        BinaryTree binaryTree = BinaryTree.createBinaryTree(Arrays.asList(56, -23, 41, 67, -32, 82, -26, 58, 45, 87, 86, -95, 1, 23));
        Assert.assertEquals(5, binaryTree.getCountNodesWithTwoHeirs());
    }

    @Test(expected = NullPointerException.class)
    public void nullableParamInputTreeTest() {
        BinaryTree binaryTree = BinaryTree.createBinaryTree(null);
        binaryTree.getCountNodesWithTwoHeirs();
    }

    @Test
    public void nullableTreeTest() {
        BinaryTree binaryTree = new BinaryTree();
        Assert.assertEquals(0, binaryTree.getCountNodesWithTwoHeirs());
    }

    @Test(expected = NullPointerException.class)
    public void nullableElementOfTreeTest() {
        BinaryTree binaryTree = BinaryTree.createBinaryTree(Arrays.asList(null, 23, 41, 67, 32, 82, 26, 58, 45, 87, 86, 95, 1));
        binaryTree.getCountNodesWithTwoHeirs();
    }
}
