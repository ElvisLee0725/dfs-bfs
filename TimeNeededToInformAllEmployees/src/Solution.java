import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// DFS:
// Find the path that has the longest time from root to leaf
// Use a HashMap to store <Manager ID, List of subordinates>
// From the head, at each employee, sum up inform time and get all its subordinates to call dfs()
// Base Case: When cur employee has no subordinates, update the global max and return
// Time: O(n), Space: O(n)
class Solution {
    public static void main(String[] args) {
        int n = 11;
        int headID = 4;
        int [] manager = {5,9,6,10,-1,8,9,1,9,3,4};
        int [] informTime = {0,213,0,253,686,170,975,0,261,309,337};
        System.out.print(new Solution().numOfMinutes(n, headID, manager, informTime));
    }
    int max;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        max = 0;
        if(n <= 1) {
            return 0;
        }
        HashMap<Integer, List<Integer>> map = new HashMap();
        for(int i = 0; i < manager.length; i++) {
            map.computeIfAbsent(manager[i], x -> new ArrayList()).add(i);
        }

        // cur employee, inform time sum, subordinate map, informTime
        dfs(headID, 0, map, informTime);
        return max;
    }

    public void dfs(int cur, int sumTime, HashMap<Integer, List<Integer>> map, int [] informTime) {
        if(!map.containsKey(cur)) {
            max = Math.max(max, sumTime);
            return ;
        }

        sumTime += informTime[cur];
        for(Integer sub : map.get(cur)) {
            dfs(sub, sumTime, map, informTime);
        }
    }
}
