// Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
//
// Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

import java.util.HashSet;

// DFS
// Iterate the matrix, if there is a 1, call dfs
// Use a global StringBuilder to record the sequence of path
// At each node, add the path character to the StringBuilder
// After each node, add a '0' to the end of each path to denote backtracking
// After the dfs call, use a HashSet of strings to find if there is a duplication? If not, add it to hashset and counter increase by 1
// Return the counter at the end
// Time: O(mn), Space: O(mn)
class Solution {
    public static void main(String[] args) {
        int [][] grid = {{1,1,0,1,1},{1,0,0,0,0},{0,0,0,0,1},{1,1,0,1,1}};
        System.out.print(new Solution().numDistinctIslands(grid));
    }
    StringBuilder sb;
    public int numDistinctIslands(int[][] grid) {
        HashSet<String> hs = new HashSet();
        int row = grid.length;
        int col = grid[0].length;

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    sb = new StringBuilder();
                    dfs(grid, i, j, 'S');

                    String path = sb.toString();

                    if(!hs.contains(path)) {
                        hs.add(path);
                    }
                }
            }
        }
        return hs.size();
    }

    private void dfs(int [][] grid, int i, int j, char dir) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return ;
        }
        sb.append(dir);
        grid[i][j] = 0;

        dfs(grid, i - 1, j, 'U');
        dfs(grid, i + 1, j, 'D');
        dfs(grid, i, j - 1, 'L');
        dfs(grid, i, j + 1, 'R');

        sb.append('0');
    }
}
