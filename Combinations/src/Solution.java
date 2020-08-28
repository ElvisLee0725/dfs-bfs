// Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

// Base Case: When list size equals to k, record list and return
// At each level, try all numbers from start to n. Add cur number to list and call recursion with cur+1 as start

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<List<Integer>> res = new Solution().combinations(4, 2);
        for(List<Integer> l : res) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> list = new ArrayList();

        dfs(1, n, k, list, res);
        return res;
    }

    public void dfs(int start, int n, int k, List<Integer> list, List<List<Integer>> res) {
        if(list.size() == k) {
            res.add(new ArrayList(list));
            return ;
        }

        for(int i = start; i <= n; i++) {
            list.add(i);
            dfs(i + 1, n, k, list, res);
            list.remove(list.size()-1);
        }
    }
}
