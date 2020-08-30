import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        a.neighbors.add(b);
        a.neighbors.add(d);
        b.neighbors.add(a);
        b.neighbors.add(c);
        c.neighbors.add(b);
        c.neighbors.add(d);
        d.neighbors.add(a);
        d.neighbors.add(c);

        Node res = new Solution().cloneGraphBFS(a);
        new Solution().printAllNeighbors(res);
    }

    public void printAllNeighbors(Node n) {
        HashSet<Node> visited = new HashSet();
        visited.add(n);
        Queue<Node> q = new LinkedList();
        q.offer(n);
        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(Node nei : cur.neighbors) {
                if(!visited.contains(nei)) {
                    q.offer(nei);
                    visited.add(nei);
                }
                System.out.print(nei.val);
                System.out.print(", ");
            }
            System.out.println();
        }
    }

    public Node cloneGraphBFS(Node node) {
        if(node == null) {
            return node;
        }

        Queue<Node> q = new LinkedList();
        q.offer(node);
        HashMap<Node, Node> map = new HashMap();
        map.put(node, new Node(node.val));

        while(!q.isEmpty()) {
            Node cur = q.poll();
            for(Node nei : cur.neighbors) {
                if(!map.containsKey(nei)) {
                    map.put(nei, new Node(nei.val));
                    q.offer(nei);
                }
                map.get(cur).neighbors.add(map.get(nei));
            }
        }

        return map.get(node);
    }
}
