class Solution {
    public static void main(String[] args) {
        int [][] grid = {{0,1,0,0}, {1,1,1,0}, {0,1,0,0}, {1,1,0,0}};
        System.out.println(new Solution().islandPerimeter(grid));

    }
    int count;
    public int islandPerimeter(int[][] grid) {
        count = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    dfs(i, j, grid);
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, int [][] grid) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            count++;
            return ;
        }
        else if(grid[i][j] == 2) {
            return ;
        }

        grid[i][j] = 2;

        dfs(i-1, j, grid);
        dfs(i+1, j, grid);
        dfs(i, j-1, grid);
        dfs(i, j+1, grid);
    }
}
