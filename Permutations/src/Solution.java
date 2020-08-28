// Given a collection of distinct integers, return all possible permutations.

// Base Case: index equals to input array length, convert current array to ArrayList and record
// At each level, from index to the last index, do swap (i and index) and call recursion with index + 1. swap back

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Integer [] nums = new Integer[]{1, 2, 3};
        List<List<Integer>> res = new Solution().permutations(nums);
        for(List<Integer> l : res) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> permutations(Integer [] nums) {
        List<List<Integer>> res = new ArrayList();

        dfs(0, nums, res);
        return res;
    }

    public void dfs(int index, Integer [] nums, List<List<Integer>> res) {
        if(index == nums.length) {
            res.add(new ArrayList(Arrays.asList(nums)));
            return ;
        }

        for(int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            dfs(index + 1, nums, res);
            swap(nums, i, index);
        }
    }

    public void swap(Integer [] n, int i, int j) {
        Integer temp = n[i];
        n[i] = n[j];
        n[j] = temp;
    }
}
