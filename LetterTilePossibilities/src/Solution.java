// You have n  tiles, where each tile has one letter tiles[i] printed on it.
//
// Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

import java.util.Arrays;
import java.util.HashSet;

// Get all subsets of the input string
// For each subset, get it's number of permutations and sum up
// Return the sum up result
class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().numTilePossibilities("AAABBC"));
    }
    int sum = 0;
    public int numTilePossibilities(String tiles) {
        // Edge Case: input length is 1, return 1
        if(tiles.length() == 1) {
            return 1;
        }
        char [] arr = tiles.toCharArray();
        Arrays.sort(arr);
        findSubsets(arr, 0, new StringBuilder());
        return sum;
    }

    private void findSubsets(char [] arr, int index, StringBuilder sb) {
        if(index == arr.length) {
            if(sb.length() > 0) {
                sum += countPermutation(sb.toString().toCharArray(), 0);
            }
            return;
        }

        sb.append(arr[index]);
        findSubsets(arr, index + 1, sb);
        sb.deleteCharAt(sb.length()-1);
        while(index < arr.length - 1 && arr[index+1] == arr[index]) {
            index++;
        }
        findSubsets(arr, index + 1, sb);
    }

    private int countPermutation(char [] arr, int index) {
        if(index == arr.length-1) {
            return 1;
        }

        HashSet<Character> hs = new HashSet();
        int count = 0;
        for(int i = index; i < arr.length; i++) {
            if(!hs.contains(arr[i])) {
                hs.add(arr[i]);
                swap(arr, i, index);
                count += countPermutation(arr, index + 1);
                swap(arr, i, index);
            }
        }
        return count;
    }

    private void swap(char [] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
