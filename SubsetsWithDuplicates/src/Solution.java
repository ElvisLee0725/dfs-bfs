// Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

// DFS -> Sort the input array first
// Base Case: if index equals to the length of input array, record and return
// At each level, add the num at current index first. Call recursion with index + 1. 
// Then, keep moving the index if the next number is the same as current number.
// Remove the last input and call recursion with index + 1.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Time: O(2^n), Space: O(n)
class Solution {
    public static void main(String[] args) {
        int [] nums = new int[]{2, 1, 2};
        List<List<Integer>> res = new Solution().subsetsWithDup(nums);
        for(List<Integer> l : res) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> list = new ArrayList();
        Arrays.sort(nums);
        dfs(0, nums, list, res);
        return res;
    }

    public void dfs(int index, int [] nums, List<Integer> list, List<List<Integer>> res) {
        if(index == nums.length) {
            res.add(new ArrayList(list));
            return;
        }

        list.add(nums[index]);
        dfs(index + 1, nums, list, res);

        while(index + 1 < nums.length && nums[index] == nums[index+1]) {
            index++;
        }
        list.remove(list.size()-1);
        dfs(index + 1, nums, list, res);
    }
}