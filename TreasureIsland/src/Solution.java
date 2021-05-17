import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        char [][] grid = {{'O', 'O', 'O', 'O'},
                          {'D', 'O', 'D', 'O'},
                          {'O', 'O', 'O', 'O'},
                          {'X', 'D', 'D', 'O'}};
        System.out.println(new Solution().treasureIsland(grid));
    }

    // BFS, it is possible the island cannot be reached
    // Start from top left (0, 0) and store it a queue.
    // While the queue is not empty, expand from the queue. If it's a 'X', we found it, return steps
    // Get all possible next position from top, bottom, left and right as long as its not 'D' and not visited yet
    // After all next position is generated for current round, increase the steps
    // Return -1 when the queue is empty, which means cannot reach the island
    // Time: O(mn), Space: O(mn)
    class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int treasureIsland(char [][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean [][] visited = new boolean[m][n];
        Queue<Pos> q = new LinkedList();
        q.offer(new Pos(0,0));
        visited[0][0] = true;
        int steps = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Pos cur = q.poll();
                int x = cur.x;
                int y = cur.y;
                if(grid[x][y] == 'X') {
                    return steps;
                }
                if(x > 0 && grid[x-1][y] != 'D' && !visited[x-1][y]) {
                    visited[x-1][y] = true;
                    q.offer(new Pos(x-1, y));
                }

                if(x < m - 1 && grid[x+1][y] != 'D' && !visited[x+1][y]) {
                    visited[x+1][y] = true;
                    q.offer(new Pos(x+1, y));
                }

                if(y > 0 && grid[x][y-1] != 'D' && !visited[x][y-1]) {
                    visited[x][y-1] = true;
                    q.offer(new Pos(x, y-1));
                }

                if(y < n - 1 && grid[x][y+1] != 'D' && !visited[x][y+1]) {
                    visited[x][y+1] = true;
                    q.offer(new Pos(x, y+1));
                }
            }
            steps++;
        }
        return -1;
    }
}
