// Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.
// The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.
// Each node has labels in the set {0, 1, ..., edges.length}.

// DFS
// Convert the input to HashMap<Node number, List of neighbors of this node>
// Use dfs to find a farthest edge node.
// From the previous found edge node, run dfs again to find the farthest edge. This time we have the diameter
// dfs function:
// a. Update the farNode if current len is greater than res, also update res.
// b. Use a HashSet<Node number> to prevent duplication path. At each cur node, add itself to HashSet and loop all its
// neighbors which is not in the hashset yet to run dfs().

// Time: O(n), Space: O(n)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int [][] edges = {{0,1}, {1,2}, {2,3}, {1,4}, {4,5}};
        System.out.print(new Solution().treeDiameter(edges));
    }

    int farNode = -1;
    int res = 0;

    public int treeDiameter(int[][] edges) {
        HashMap<Integer, List<Integer>> map = new HashMap();
        for(int [] edge: edges) {
            map.putIfAbsent(edge[0], new ArrayList());
            map.putIfAbsent(edge[1], new ArrayList());

            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        dfs(map, 0, new HashSet(), 0);
        dfs(map, farNode, new HashSet(), 0);

        return res;
    }

    public void dfs(HashMap<Integer, List<Integer>> map, int curNode, HashSet<Integer> visited, int len) {
        if(len > res) {
            res = len;
            farNode = curNode;
        }
        visited.add(curNode);
        for(Integer nei : map.get(curNode)) {
            if(!visited.contains(nei)) {
                dfs(map, nei, visited, len + 1);
            }
        }
    }
}
