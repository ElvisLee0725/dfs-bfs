// Start from the grid that has value 1, need to go through all those 0s in the grid, and reach 2 => we find a path. In the end need to return the number of paths
// Do dfs at the position grid[i][j] == 1
// DFS:
// Base Case:
// When the current position has value 2. Check if all 0s are walked over? If so, then the number of path + 1
// If the current position is out of the range of matrix, return
// If the current position has value -1, return
// At each position, need to check all 4 directions: top, bottom, left and right
// To track if I have walked through all 0s, calculate the number of 0s first, and also use a variable to sum up the number of 0s I've been walked.
// Time: O(4^mn), Space: O(mn)
class Solution {
    public static void main(String[] args) {
        int [][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        System.out.print(new Solution().uniquePathsIII(grid));
    }
    int numOfZeros = 0;
    int path = 0;
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int r = -1;
        int c = -1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    numOfZeros++;
                }
                if(grid[i][j] == 1) {
                    r = i;
                    c = j;
                }
            }
        }

        dfs(grid, 0, r, c);

        return path;
    }

    public void dfs(int [][] grid, int countZero, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return ;
        }
        else if(grid[i][j] == -1) {
            return ;
        }
        else if(grid[i][j] == 2) {
            if(countZero == numOfZeros) {
                path++;
            }
            return ;
        }

        if(grid[i][j] == 0) {
            countZero++;
        }
        // Check 4 directions:
        grid[i][j] = -1;
        dfs(grid, countZero, i - 1, j);
        dfs(grid, countZero, i + 1, j);
        dfs(grid, countZero, i, j - 1);
        dfs(grid, countZero, i, j + 1);
        grid[i][j] = 0;
    }
}
