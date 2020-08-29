// Given a collection of numbers that might contain duplicates, return all possible unique permutations.

// DFS
// Base Case: When index is equal to array's length, record result and return
// At each level, Use a HashSet<Integer> to prevent duplicate
// From the index to the last, if the number at cur index is not in the hashset, add to hashset and swap number at index and i, call recursion with index + 1, then swap back

// Time: O(n^n), Space: O(n)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        int [] nums = new int[]{1, 1, 2};
        List<List<Integer>> res = new Solution().permuteUnique(nums);
        for(List l : res) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        Integer [] arr = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }

        dfs(0, arr, res);
        return res;
    }

    public void dfs(int index, Integer [] arr, List<List<Integer>> res) {
        if(index == arr.length) {
            res.add(new ArrayList(Arrays.asList(arr)));
            return ;
        }

        HashSet<Integer> hs = new HashSet();
        for(int i = index; i < arr.length; i++) {
            if(!hs.contains(arr[i])) {
                hs.add(arr[i]);
                swap(arr, index, i);
                dfs(index + 1, arr, res);
                swap(arr, index, i);
            }
        }
    }

    public void swap(Integer [] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
