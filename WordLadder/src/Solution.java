// Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

// Only one letter can be changed at a time.
// Each transformed word must exist in the word list.

// Use a HashSet<String> to store all those wordList, and another HashSet<String> visited to prevent duplicate
// Edge case: If the endWord is not in hashset, return 0
// Create a Queue<String> initialize with beginWord and visited, with a counter variable = 1
// In a while loop, check the size of queue, and expand cur word from the queue. If it equals to endWord, return counter.
// Else, get all possible word changes from current word that exist in the hashSet and not in visited into the queue.
// When loop of size times finished, counter plus 1 (Into BFS next level)
// Keep doing until queue is empty, return 0

// Time: O(n*k) while k is the length of word, Space O:(n)

import java.util.*;

class Solution {
    public static void main(String[] args) {
        List<String> list = new ArrayList(Arrays.asList("hot","dot","dog","lot","log","cog"));
        System.out.print(new Solution().ladderLength("hit", "cog", list));
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet();
        for(String word: wordList) {
            wordSet.add(word);
        }
        if(!wordSet.contains(endWord)) {
            return 0;
        }

        HashSet<String> visited = new HashSet();
        Queue<String> q = new LinkedList();
        q.offer(beginWord);
        visited.add(beginWord);
        int counter = 1;

        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                String cur = q.poll();
                if(cur.equals(endWord)) {
                    return counter;
                }
                getAllWords(cur, q, visited, wordSet);
            }
            counter++;
        }
        return 0;
    }

    public void getAllWords(String s, Queue<String> q, HashSet<String> visited, HashSet<String> wordSet) {
        for(int i = 0; i < s.length(); i++) {
            char [] arr = s.toCharArray();
            for(char j = 'a'; j <= 'z'; j++) {
                arr[i] = j;
                String temp = new String(arr);

                if(wordSet.contains(temp) && !visited.contains(temp)) {
                    visited.add(temp);
                    q.offer(temp);
                }
            }
        }
    }
}