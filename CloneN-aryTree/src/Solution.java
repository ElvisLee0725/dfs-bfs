import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {

    }

    public Node cloneTreeDFS(Node root) {
        if(root == null) {
            return root;
        }

        Node cur = new Node(root.val);

        for(Node n : root.children) {
            cur.children.add(cloneTreeDFS(n));
        }
        return cur;
    }

    public Node cloneTreeBFS(Node root) {
        if(root == null) {
            return null;
        }
        HashMap<Node, Node> map = new HashMap();
        Queue<Node> q = new LinkedList();
        q.offer(root);
        map.put(root, new Node(root.val));
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Node cur = q.poll();

                for(Node n : cur.children) {
                    q.offer(n);
                    map.put(n, new Node(n.val));
                    map.get(cur).children.add(map.get(n));
                }
            }
        }
        return map.get(root);
    }
}
