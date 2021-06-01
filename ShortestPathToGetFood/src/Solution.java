// You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.
//
//You are given an m x n character matrix, grid, of these different types of cells:
//
//'*' is your location. There is exactly one '*' cell.
//'#' is a food cell. There may be multiple food cells.
//'O' is free space, and you can travel through these cells.
//'X' is an obstacle, and you cannot travel through these cells.
//You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
//
//Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.

import java.util.LinkedList;
import java.util.Queue;

// BFS:
// Iterate the input grid to find the '*' as the starting point
// Start from the position of '*' do BFS:
// Expand from the queue. If cur position is a '#', we found the food, return the number of steps
// Else, try all possible 4 directions as long as it's not out of bound, visited or a 'X', add them to the queue.
// Increase the steps by 1 after expanding a round of all possible next positions
// After the BFS, return -1 since no food can be found
// Time: O(n^2), Space: O(n^2)
class Solution {
    public static void main(String[] args) {
        char [][] grid = {
                {'X','X','X','X','X','X','X','X'},
                {'X','*','O','X','O','#','O','X'},
                {'X','O','O','X','O','O','X','X'},
                {'X','O','O','O','O','#','O','X'},
                {'X','X','X','X','X','X','X','X'}};
        System.out.println(new Solution().getFood(grid));
    }
    class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int getFood(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '*') {
                    Queue<Pos> q = new LinkedList();
                    q.offer(new Pos(i, j));
                    boolean [][] visited = new boolean[m][n];
                    visited[i][j] = true;
                    int steps = 0;
                    while(!q.isEmpty()) {
                        int size = q.size();
                        for(int k = 0; k < size; k++) {
                            Pos cur = q.poll();
                            int x = cur.x;
                            int y = cur.y;
                            if(grid[x][y] == '#') {
                                return steps;
                            }

                            if(x > 0 && grid[x-1][y] != 'X' && !visited[x-1][y]) {
                                q.offer(new Pos(x-1, y));
                                visited[x-1][y] = true;
                            }

                            if(x < m - 1 && grid[x+1][y] != 'X' && !visited[x+1][y]) {
                                q.offer(new Pos(x+1, y));
                                visited[x+1][y] = true;
                            }

                            if(y > 0 && grid[x][y-1] != 'X' && !visited[x][y-1]) {
                                q.offer(new Pos(x, y-1));
                                visited[x][y-1] = true;
                            }

                            if(y < n - 1 && grid[x][y+1] != 'X' && !visited[x][y+1]) {
                                q.offer(new Pos(x, y+1));
                                visited[x][y+1] = true;
                            }
                        }
                        steps++;
                    }
                }
            }
        }
        return -1;
    }
}
