// DFS
// Iterate the grid matrix, if the current position grid[i][j] is not 0, start dfs
// At each position, check if it is a valid position and it has value greater than 0? If so, calculate the sum of gold by adding the value at grid[i][j]. If the cur sum is greater than global max, update it. Also, update grid[i][j] to -1 so I won't revisit it again.
// Try all 4 directions from current i, j
// After trying 4 directions, change the value grid[i][j] back to its original value
// Time: O((mn)^2), Space: O(mn)
class Solution {
    public static void main(String[] args) {
        int [][] m = {{1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}};
        System.out.print(new Solution().getMaximumGold(m));
    }
    int max;
    public int getMaximumGold(int[][] grid) {
        max = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] > 0) {
                    dfs(grid, i, j, 0);
                }
            }
        }
        return max;
    }

    public void dfs(int [][] grid, int i, int j, int curSum) {
        if(isValid(grid.length, grid[0].length, i, j) && grid[i][j] > 0) {
            curSum += grid[i][j];
            max = Math.max(max, curSum);

            int temp = grid[i][j];
            grid[i][j] = -1;
            dfs(grid, i+1, j, curSum);
            dfs(grid, i-1, j, curSum);
            dfs(grid, i, j+1, curSum);
            dfs(grid, i, j-1, curSum);
            grid[i][j] = temp;
        }
    }

    public boolean isValid(int r, int c, int i, int j) {
        if(i >= 0 && i < r && j >= 0 && j < c) {
            return true;
        }
        return false;
    }
}
