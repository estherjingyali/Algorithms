import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        BTree bTree = new BTree();

        int minDepth = bTree.minDepth(root);
        int countNodes = bTree.countNodes(root);

        System.out.println(minDepth);
        System.out.println(countNodes);

        List<List<Integer>> result = new ArrayList<>();
        while (root != null) {
            List<Integer> leaves = new ArrayList<>();
            root = bTree.removeLeaves(root, leaves);
            result.add(leaves);
        }
        List<List<Integer>> collector = new ArrayList<>();
        for (List<Integer> leaves : result) {
            collector.add(leaves);
        }
        System.out.println(collector);

        List<Integer> largestValues = bTree.largestValues(root);
        for (Integer val : largestValues) {
            System.out.print(val);
        }

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(7);
        root1.left.right.left = new TreeNode(4);
        root1.left.right.right = new TreeNode(2);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(8);

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(1);
        root2.left.left = new TreeNode(6);
        root2.left.right = new TreeNode(7);
        root2.left.right.left = new TreeNode(4);
        root2.left.right.right = new TreeNode(2);
        root2.right.left = new TreeNode(0);
        root2.right.right = new TreeNode(8);

        boolean isLeafSimilar = bTree.leafSimilar(root1,root2);
        System.out.println(isLeafSimilar);

        int deepestLeavesSum = bTree.deepestLeavesSum(root2);
        System.out.println(deepestLeavesSum);

    }
}
