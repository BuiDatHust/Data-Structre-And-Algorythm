package CodingInterviewLeetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeProb {
    //    Maximum Depth of Binary Tree, time: O(n) ,space: O(h)
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static boolean isValidBST(TreeNode root, TreeNode parent) {
        if (root == null) {
            return true;
        }
        if (root.left != null && (root.left.val >= root.val || root.left.val >= parent.val)) {
            return false;
        }
        if (root.right != null && (root.right.val <= root.val || root.right.val <= parent.val)) {
            return false;
        }

        return isValidBST(root.right, root) && isValidBST(root.left, root);
    }

    public static int maxLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode leftNode = root.left;
        if (leftNode == null) {
            return 0;
        }

        while (leftNode.right != null) {
            leftNode = leftNode.right;
        }
        return leftNode.val;
    }

    //    Symmetric Tree, time: O(n), space: O(n), H is depth of bstree
    public static boolean isSymmetric(TreeNode root) {
        return isSymmetricRecursive(root.left, root.right);
    }

    public static boolean isSymmetricRecursive(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode != null && rightNode == null || leftNode == null && rightNode != null) {
            return false;
        }
        if (leftNode.val != rightNode.val) {
            return false;
        }

        return isSymmetricRecursive(leftNode.left, rightNode.right) && isSymmetricRecursive(leftNode.right, rightNode.left);

    }

    //    Binary Tree Level Order Traversal, time: O(n), space:O(n)
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> bfsQueue = new LinkedList<>();

        if (root != null) {
            bfsQueue.add(root);
        }
        while (!bfsQueue.isEmpty()) {
            List<Integer> q = new ArrayList<>();
            int len = bfsQueue.size();
            for (int i = 0; i < len; i++) {
                TreeNode top = bfsQueue.poll();
                q.add(top.val);
                if (top.left != null) {
                    bfsQueue.add(top.left);
                }
                if (top.right != null) {
                    bfsQueue.add(top.right);
                }
            }
            if (q.size() > 0) {
                result.add(q);
            }
        }

        return result;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3,null,null)));

//        System.out.println(isSymmetric(root));

    }
}
