// BFS
// Iterate from city 0 to isConnected.length-1, for each city, if it's not visited before, start from it doing BFS
// Use a queue to store cities connected to the current city
// Expand a city from queue and if its connected to cities not visited yet and not itself, add it to the queue and mark vsited
// Keep doing until the queue is empty, then we have a province. Add the count by 1
// Time: O(n^2), Space: O(n)

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        int [][] m = new int[][]{{1,1,0},{1,1,0},{0,0,1}};
        System.out.print(new Solution().findCircleNum(m));
    }
    public int findCircleNum(int[][] isConnected) {
        HashSet<Integer> visited = new HashSet();
        int count = 0;
        for(int i = 0; i < isConnected.length; i++) {
            if(!visited.contains(i)) {
                visited.add(i);
                Queue<Integer> q = new LinkedList();
                q.offer(i);
                while(!q.isEmpty()) {
                    int cur = q.poll();
                    for(int j = 0; j < isConnected[cur].length; j++) {
                        if(isConnected[cur][j] == 1 && cur != j && !visited.contains(j)) {
                            q.offer(j);
                            visited.add(j);
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }
}
