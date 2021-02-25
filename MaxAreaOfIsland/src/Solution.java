// DFS
// Iterate the grid, if the current value is 1, there is an island. Start from that position, set the value at grid[i][j] to 0 and make current area plus 1, and try four directions: top, bottom, left and right from currrent position.
// When a dfs is finished, update the max area if the cur area is bigger.
// Return the max area in the end
// Time: O(n^2), Space: O(n^2)
class Solution {
    public static void main(String[] args) {
        int [][] m = {{0,0,1,0,0,0,0,1,0,0,0,0,0},
                    {0,0,0,0,0,0,0,1,1,1,0,0,0},
                    {0,1,1,0,1,0,0,0,0,0,0,0,0},
                    {0,1,0,0,1,1,0,0,1,0,1,0,0},
                    {0,1,0,0,1,1,0,0,1,1,1,0,0},
                    {0,0,0,0,0,0,0,0,0,0,1,0,0},
                    {0,0,0,0,0,0,0,1,1,1,0,0,0},
                    {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.print(new Solution().maxAreaOfIsland(m));
    }
    int max;
    public int maxAreaOfIsland(int[][] grid) {
        max = 0;
        if(grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    int curArea = dfs(grid, i, j);
                    max = Math.max(max, curArea);
                }
            }
        }
        return max;
    }

    public int dfs(int [][] grid, int i, int j) {
        if(isValid(grid.length, grid[0].length, i, j) && grid[i][j] == 1) {
            grid[i][j] = 0;

            return 1 + dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);
        }
        else {
            return 0;
        }
    }

    public boolean isValid(int r, int c, int i, int j) {
        if(i >= 0 && i < r && j >= 0 && j < c) {
            return true;
        }
        return false;
    }
}
