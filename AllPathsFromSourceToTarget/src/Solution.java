// Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from
// node 0 to node n - 1, and return them in any order.
// The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is
// a directed edge from node i to node graph[i][j]).

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int [][] graph = {{1,2},{3},{3},{}};
        List<List<Integer>> res = new Solution().allPathsSourceTarget(graph);
        for(List<Integer> list : res) {
            for(Integer n : list) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

    // DFS solution:
    // Start from node 0, at each level, try all possible next nodes by adding that node to cur list and call recursion function
    // When returned, remove the previous node from list
    // Base Case: When current node equals to n - 1, mean reach the destination, add cur list to result list and return
    // Time: O(n^n), Space: O(n)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> list = new ArrayList();
        list.add(0);
        dfs(0, list, res, graph);
        return res;
    }

    public void dfs(int curNode, List<Integer> list, List<List<Integer>> res, int [][] graph) {
        if(curNode == graph.length - 1) {
            res.add(new ArrayList(list));
            return ;
        }

        for(int i = 0; i < graph[curNode].length; i++) {
            list.add(graph[curNode][i]);
            dfs(graph[curNode][i], list, res, graph);
            list.remove(list.size()-1);
        }
    }
}
