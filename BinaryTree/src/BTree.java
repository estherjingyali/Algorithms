import org.w3c.dom.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;


public class BTree {

    //111. Minimum Depth of Binary Tree
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int leftDepth = Integer.MAX_VALUE;
        int rightDepth = Integer.MAX_VALUE;

        if (root.left != null) {
            leftDepth = minDepth(root.left);
        }
        if (root.right != null) {
            rightDepth = minDepth(root.right);
        }

        return Math.min(leftDepth, rightDepth) + 1;
    }

    //222. Count Complete Tree Nodes
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = getLeftDepth(root);
        int rightDepth = getRightDepth(root);

        if (leftDepth == rightDepth) {
            return (1 << leftDepth) - 1;
        } else {
            return (1 << rightDepth) - 1 + countNodes(root.left);
        }

    }

    private int getLeftDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.left;
        }
        return depth;
    }

    private int getRightDepth(TreeNode root) {
        int depth = 0;
        while (root != null) {
            depth++;
            root = root.right;
        }
        return depth;
    }

    //366. Find Leaves of Binary Tree
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        while (root != null) {
            List<Integer> leaves = new ArrayList<>();
            root = removeLeaves(root, leaves);
            result.add(leaves);
        }
        return result;
    }

    public TreeNode removeLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return null;
        }
        root.left = removeLeaves(root.left, leaves);
        root.right = removeLeaves(root.right, leaves);
        return root;
    }

    //515. Find Largest Value in Each Tree Row
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            int maxVal = Integer.MIN_VALUE;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                maxVal = Math.max(maxVal, node.val);

                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(maxVal);
        }
        return res;
    }

    //872. Leaf-Similar Trees
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafValues1 = new ArrayList<>();
        List<Integer> leafValues2 = new ArrayList<>();

        dfs(root1, leafValues1);
        dfs(root2, leafValues2);

        return leafValues1.equals(leafValues2);
    }

    private void dfs(TreeNode node, List<Integer> leafValues) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            leafValues.add(node.val);
        }

        dfs(node.left, leafValues);
        dfs(node.right, leafValues);
    }


    //1302. Deepest Leaves Sum
    private int deepestLevel = 0;
    private int sum = 0;
    public int deepestLeavesSum(TreeNode root) {
        calculator(root, 0);
        return sum;
    }

    public void calculator(TreeNode node, int level) {
        if (node == null) {
            return;
        }

        if (level > deepestLevel) {
            deepestLevel = level;
            sum = 0;
        }

        if (level == deepestLevel) {
            sum += node.val;
        }

        calculator(node.left, level + 1);
        calculator(node.right, level + 1);
    }
}
