// Given a set of distinct integers, nums, return all possible subsets (the power set).

// Base Case: index equals to array length
// At each level, try add number at current index, call recursion with index + 1. Then, remove it and call recursion with index + 1
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int [] nums = new int[]{1, 2, 3};
        List<List<Integer>> res = new Solution().subsets(nums);
        for(List<Integer> l : res) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> subsets(int [] nums) {
        List<List<Integer>> res = new ArrayList();
        List<Integer> list = new ArrayList();
        dfs(0, nums, list, res);

        return res;
    }

    public void dfs(int index, int [] nums, List<Integer> list, List<List<Integer>> res) {
        if(index == nums.length) {
            res.add(new ArrayList(list));
            return ;
        }

        list.add(nums[index]);
        dfs(index + 1, nums, list, res);
        list.remove(list.size()-1);
        dfs(index + 1, nums, list, res);
    }
}
