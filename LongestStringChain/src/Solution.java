// Given a list of words, each word consists of English lowercase letters.

// Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".

// A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.

// Return the longest possible length of a word chain with words chosen from the given list of words.
import java.util.*;

// Create a word length list of each word, Ex.<4, <bdca>>, <3, <bca, bda>>, <2, <ba>>, <1, <a,b>> using TreeMap so the keys are sorted from big to small
// Iterate from the longest word and get the max depth. If max equals size of map, we found max! Else, size-- and keep doing. Ex. map has size 4, <1 ..>, <2, ...> <3, ...>, <4, ...>. If we got max depth of 4, this must be max
// Time: O(n^2), Space: O(n)

class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"}));
    }
    public int longestStrChain(String[] words) {
        Map<Integer, List<String>> map = new TreeMap(Collections.reverseOrder());
        for(String w : words) {
            map.computeIfAbsent(w.length(), x -> new ArrayList()).add(w);
        }

        int maxDepth = 0;
        int size = map.size();
        for(Integer len : map.keySet()) {
            for(String w : map.get(len)) {
                maxDepth = Math.max(maxDepth, dfs(w, map) + 1);
                if(maxDepth == size) {
                    return maxDepth;
                }
            }
            size--;
        }

        return maxDepth;
    }

    public int dfs(String w, Map<Integer, List<String>> map) {
        // Base Case: There is no key of cur word's length - 1. Return 0
        int nextLen = w.length()-1;
        if(nextLen == 0 || !map.containsKey(nextLen)) {
            return 0;
        }

        List<String> nextWords = map.get(nextLen);
        int depth = 0;
        for(String nextW : nextWords) {
            if(isPredecessor(nextW, w)) {
                depth = Math.max(depth, dfs(nextW, map) + 1);
            }
        }
        return depth;
    }

    private boolean isPredecessor(String w1, String w2) {
        if(w1.length() - w2.length() != -1) {
            return false;
        }

        int idx1 = 0;
        int idx2 = 0;
        int count = 0;
        while(idx1 < w1.length() && idx2 < w2.length()) {
            if(w1.charAt(idx1) != w2.charAt(idx2)) {
                idx2++;
                count++;
            }
            else {
                idx1++;
                idx2++;
            }
        }
        return count == 1 || (count == 0 && idx1 == w1.length());
    }
}
