import java.util.*;
/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 */

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

/*
DFS:
- Serialize:
    - Traverse the tree with DFS
    - Add current node's val and "," to stringbuilder
    - Get current node's children and run DFS
    - Append "e," means it's the end (no more child for current node)
    - Edge Case: When root is null, just add "#" and return
    
- Deserialze:
    - Convert the string into a queue by split "," 
    - Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
    - Get the first item from queue. 
    - Edge Case: It's "#", just return null
    - Build its children List and call dfs when queue is not empty and the first is not "e"
    - Poll out the first from queue since its a "e"
    - Return current node

Time: O(N), Space: O(N)
*/
class Codec {
    public static void main(String[] args) {

    }
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }

    public void helper(Node root, StringBuilder sb) {
        if(root == null) {
            sb.append("#");
            return ;
        }
        sb.append(root.val).append(',');
        for(Node child : root.children) {
            helper(child, sb);
        }
        sb.append("e,");
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deHelper(queue);
    }

    public Node deHelper(Queue<String> queue) {
        String cur = queue.poll();
        if(cur.equals("#")) {
            return null;
        }
        int curVal = Integer.parseInt(cur);
        List<Node> children = new ArrayList();
        while(!queue.isEmpty() && !queue.peek().equals("e")) {
            children.add(deHelper(queue));
        }

        Node node = new Node(curVal, children);
        // Poll out the "e"
        queue.poll();
        return node;
    }
}