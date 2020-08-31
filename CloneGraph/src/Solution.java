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

        Node res = new Solution().cloneGraphDFS(a);
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

    // BFS
    // Use a Queue to run BFS. Use a HashMap<old node, new node> to prevent duplicate
    // Initiate with the input node into the queue and add to hashmap
    // Expand from the queue and iterate the cur node's neighbors. If the neighbor node is not in the map, add to the map and offer to the queue.
    // Get the copied node from map and add neighbor node to its neighbors arraylist
    // Repeat above until the queue is empty. Return the input node from hashmap

    // Time: O(n+k) while k is the number of neighbor nodes, Space: O(n)
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

    // DFS
    // Use a HashMap<old node, new node> to prevnet duplicates
    // At each level, add the current node to hashmap. Loop its neighbors, if the neighbor node is not in the hashmap, call recursion function with neighbor node as the input
    // After recursion, add the neighbor node to the new node in the hashmap
    // Return the new node from the hashmap

    // Time: O(n+k) while k is the number of edges, Space: O(n)

    public Node cloneGraphDFS(Node node) {
        if(node == null) {
            return node;
        }
        HashMap<Node, Node> map = new HashMap();
        dfs(node, map);
        return map.get(node);
    }

    public void dfs(Node node, HashMap<Node, Node> map) {
        map.put(node, new Node(node.val));
        for(Node nei : node.neighbors) {
            if(!map.containsKey(nei)) {
                dfs(nei, map);
            }
            map.get(node).neighbors.add(map.get(nei));
        }
    }
}
