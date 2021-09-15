//You are given an m x n grid grid of values 0, 1, or 2, where:
//
//        each 0 marks an empty land that you can pass by freely,
//        each 1 marks a building that you cannot pass through, and
//        each 2 marks an obstacle that you cannot pass through.
//        You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.
//
//        Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.
//
//        The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
//
//        The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
//

/*
BFS Solution:
- Use a steps matrix to store the min step from a 1 to position [i, j] with value 0
- Iterate the matrix and start from each cell with value 1, do BFS
- Expand each neighbor level by level, at each expanded position, increase the step at each steps[x][y]
- Iterate the steps matrix, if there is a 0, that position cannot be reached, set it to max value
- Finally, iterate the stpes matrix and get the cell with min value
- Time: O(MN), Space: O(MN)
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().shortestDistance(new int[][]
                {{1,0,2,0,1}, {0,0,0,0,0}, {0,0,1,0,0}}));
    }
    class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int shortestDistance(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int [][] steps = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    Queue<Pos> queue = new LinkedList<>();
                    queue.offer(new Pos(i, j));
                    boolean [][] visited = new boolean[row][col];
                    int step = 0;
                    while(!queue.isEmpty()) {
                        int size = queue.size();
                        for(int k = 0; k < size; k++) {
                            Pos cur = queue.poll();
                            int x = cur.x;
                            int y = cur.y;
                            if(grid[x][y] == 0 && steps[x][y] != Integer.MAX_VALUE) {
                                steps[x][y] += step;
                            }

                            // Top
                            if(x - 1 >= 0 && grid[x-1][y] == 0 && !visited[x-1][y]) {
                                visited[x-1][y] = true;
                                queue.offer(new Pos(x-1, y));
                            }

                            // Down
                            if(x + 1 < row && grid[x+1][y] == 0 && !visited[x+1][y]) {
                                visited[x+1][y] = true;
                                queue.offer(new Pos(x+1, y));
                            }

                            // Left
                            if(y - 1 >= 0 && grid[x][y-1] == 0 && !visited[x][y-1]) {
                                visited[x][y-1] = true;
                                queue.offer(new Pos(x, y-1));
                            }

                            // Right
                            if(y + 1 < col && grid[x][y+1] == 0 && !visited[x][y+1]) {
                                visited[x][y+1] = true;
                                queue.offer(new Pos(x, y+1));
                            }
                        }
                        step++;
                    }
                    for(int r = 0; r < row; r++) {
                        for(int c = 0; c < col; c++) {
                            if(grid[r][c] == 0 && !visited[r][c]) {
                                steps[r][c] = Integer.MAX_VALUE;
                            }
                        }
                    }
                }
            }
        }

        int minDist = Integer.MAX_VALUE;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 0) {
                    minDist = Math.min(minDist, steps[i][j]);
                }
            }
        }
        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }
}
