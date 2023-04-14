package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTreeAlgorythm {
    public static String depthFirstValueTraversal(Node node){
        Stack st  = new Stack<Node>();
        String result = "";
        st.push(node);
        while(!st.isEmpty()){
            Node currentNode = (Node) st.pop();
            result+= " " + currentNode.data;
            if(currentNode.right != null){
                st.push(currentNode.right);
            }
            if(currentNode.left != null){
                st.push(currentNode.left);
            }
        }
        System.out.println(result);
        return result;
    }

    public static String breadthFirstValueTraversal(Node node){
        String result = "";
        Queue<Node> q = new LinkedList<Node>();
        q.add(node);

        while(!q.isEmpty()){
            Node currentNode = q.remove();
            result += " " + currentNode.data;
            if(currentNode.right != null){
                q.add(currentNode.right);
            }
            if(currentNode.left != null){
                q.add(currentNode.left);
            }
        }
        System.out.println(result);
        return result;
    }

    public static Boolean isTreeIncludeTarget(Node node, String target){
        if( node==null) return false;
        if(node.data == target) {
            return true;
        }
        return isTreeIncludeTarget(node.right, target) || isTreeIncludeTarget(node.left, target);
    }

    public static Integer sumDataTree(Node<Integer> node) {
        if(node == null) return 0;
        return node.data + sumDataTree(node.right) + sumDataTree(node.left);
    }

    public static Integer maxRootToLeafPath(Node<Integer> node){
        if(node == null) {
            return -1111111111;
        }
        if( node.right == null && node.left == null){
            return node.data;
        }
        Integer maxPathChild = Math.max(maxRootToLeafPath(node.left), maxRootToLeafPath(node.right));
        return maxPathChild + node.data;
    }

    public static void main(String[] args) {

        Node root = new Node<Integer>(3);
        root.right = new Node<Integer>(4);
        root.left = new Node<Integer>(11);
        root.right.left = new Node<Integer>(4);
        root.left.right = new Node<Integer>(-2);
        root.left.left = new Node<Integer>(1);

        BinaryTreeAlgorythm.depthFirstValueTraversal(root);
        BinaryTreeAlgorythm.breadthFirstValueTraversal(root);
        System.out.println(BinaryTreeAlgorythm.maxRootToLeafPath(root));
    }
}
