/*
- Store the position of B, S and T
- Use a priority queue to store an int array, {Mahatten Dist, move, startX, startY, boxX, boxY}, sort by index 0
- Start from {Distance between Box and T, 0, startX, startY, boxX, boxY}
- While the priority queue is not empty, do:
    - Check if cur box is at T? If so, return move
    - Try all 4 directions from the startX and startY
    - If next step is valid, check if it's the box? If so, insert into priority queue with move + 1 (Push box)
    - Else, just insert without move + 1 (Player move with empty hand)
- Return -1 when queue is empty
*/

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

class Solution {
    public static void main(String[] args) {
        char [][] grid = {{'#','#','#','#','#','#'},{'#','T','#','#','#','#'},{'#','.','.','B','.','#'},{'#','.','#','#','.','#'},{'#','.','.','.','S','#'},{'#','#','#','#','#','#'}};
        System.out.println(new Solution().minPushBox(grid));
    }
    public int minPushBox(char[][] grid) {
        int [] start = new int[2];
        int [] box = new int[2];
        int [] target = new int[2];
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
                if(grid[i][j] == 'B') {
                    box[0] = i;
                    box[1] = j;
                }
                if(grid[i][j] == 'T') {
                    target[0] = i;
                    target[1] = j;
                }
            }
        }

        PriorityQueue<int []> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int [] a, int [] b) {
                return a[0] < b[0] ? -1 : 1;
            }
        });

        int [] init = new int[]{manhattenDist(box[0], box[1], target[0], target[1]), 0, start[0], start[1], box[0], box[1]};
        pq.offer(init);
        HashSet<String> visited = new HashSet();
        int [][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while(!pq.isEmpty()) {
            int [] cur = pq.poll();

            int push = cur[1];
            int startX = cur[2];
            int startY = cur[3];
            int boxX = cur[4];
            int boxY = cur[5];
            // When box reaches the target position, return the number of pushes
            if(boxX == target[0] && boxY == target[1]) {
                return push;
            }
            String str = String.valueOf(startX) + ":" + String.valueOf(startY) + "|" + String.valueOf(boxX) + ":" + String.valueOf(boxY);

            if(!visited.contains(str)) {
                visited.add(str);
                for(int [] dir : dirs) {
                    int nextX = startX + dir[0];
                    int nextY = startY + dir[1];

                    if(isValid(nextX, nextY, grid)) {
                        int [] next = new int[6];
                        // Push the box! Find the next position after box is pushed
                        if(nextX == boxX && nextY == boxY) {
                            int nextBoxX = boxX + dir[0];
                            int nextBoxY = boxY + dir[1];

                            if(isValid(nextBoxX, nextBoxY, grid)) {
                                next = new int[]{manhattenDist(nextBoxX, nextBoxY, target[0], target[1]) + push + 1, push + 1, nextX, nextY, nextBoxX, nextBoxY};
                            }
                        }
                        // Just move the player
                        else {
                            next = new int[]{cur[0], push, nextX, nextY, boxX, boxY};
                        }
                        pq.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    public int manhattenDist(int x, int y, int tX, int tY) {
        return Math.abs(x - tX) + Math.abs(y - tY);
    }

    public boolean isValid(int x, int y, char [][] grid) {
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '#') {
            return false;
        }
        return true;
    }
}
