// There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.
//
//You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.
//
//It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.
//
//Return the original array nums. If there are multiple solutions, return any of them.

// Use HashMap to create adjacency list for each pairs
// Find a starting point, which has only 1 neighbor in adjacency list
// Start from the starting point, use BFS to traverse all points and record in an array
// Return the final array
// Time: O(V+E), Space: O(V+E)

import java.util.*;

class Solution {
    public static void main(String[] args) {
        int [][] p = {{4,-2},{1,4},{-3,1}};
        int [] res = new Solution().restoreArray(p);
        for(int n : res) {
            System.out.print(n + " ");
        }

    }
    public int[] restoreArray(int[][] adjacentPairs) {
        HashMap<Integer, List<Integer>> map = new HashMap();
        for(int [] pair : adjacentPairs) {
            int p1 = pair[0];
            int p2 = pair[1];
            map.computeIfAbsent(p1, x -> new ArrayList()).add(p2);
            map.computeIfAbsent(p2, x -> new ArrayList()).add(p1);
        }
        int start = 0;
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if(entry.getValue().size() == 1) {
                start = entry.getKey();
                break;
            }
        }
        int [] res = new int[adjacentPairs.length+1];
        int index = 0;
        HashSet<Integer> visited = new HashSet();
        Queue<Integer> q = new LinkedList();
        q.offer(start);
        visited.add(start);
        while(!q.isEmpty()) {
            int cur = q.poll();
            res[index++] = cur;
            for(Integer nei: map.get(cur)) {
                if(!visited.contains(nei)) {
                    visited.add(nei);
                    q.offer(nei);
                }
            }
        }
        return res;
    }
}
