// Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbors of it if they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighbors if they share one edge.
// Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
// A binary matrix is a matrix with all cells equal to 0 or 1 only.
// A zero matrix is a matrix with all cells equal to 0.

// DFS:
// Try all possible combinations to flip each cell in the matrix. If the matrix becomes zero matrix, update the minimum flip first. Keep trying all other ways
// Base Case: matrix becomes zero matrix, update the min flip
// At each level, try all possible flips that haven't tried yet

class Solution {
    public static void main(String[] args) {
        int [][] mat = {{0,0},{0,1}};
        System.out.println(new Solution().minFlips(mat));
    }
    int minFlip;
    public int minFlips(int[][] mat) {
        minFlip = Integer.MAX_VALUE;
        boolean [][] visited = new boolean[mat.length][mat[0].length];
        // Number flips, matrix, matrix to record cell that I have flipped
        dfs(0, mat, visited);
        return minFlip == Integer.MAX_VALUE ? -1 : minFlip;
    }

    private boolean isZeroMat(int [][] m) {
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[0].length; j++) {
                if(m[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private void dfs(int flip, int [][] mat, boolean [][] visited) {
        if(isZeroMat(mat)) {
            minFlip = Math.min(minFlip, flip);
            return;
        }

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if(!visited[i][j]) {
                    visited[i][j] = true;
                    mat[i][j] = 1 - mat[i][j];
                    // Flip top
                    if(i > 0) {
                        mat[i-1][j] = 1 - mat[i-1][j];
                    }

                    // Flip bottom
                    if(i < mat.length - 1) {
                        mat[i+1][j] = 1 - mat[i+1][j];
                    }

                    // Flip left
                    if(j > 0) {
                        mat[i][j-1] = 1 - mat[i][j-1];
                    }

                    // Flip right
                    if(j < mat[0].length - 1) {
                        mat[i][j+1] = 1 - mat[i][j+1];
                    }

                    dfs(flip + 1, mat, visited);

                    visited[i][j] = false;
                    mat[i][j] = 1 - mat[i][j];
                    // Flip top back
                    if(i > 0) {
                        mat[i-1][j] = 1 - mat[i-1][j];
                    }

                    // Flip bottom back
                    if(i < mat.length - 1) {
                        mat[i+1][j] = 1 - mat[i+1][j];
                    }

                    // Flip left back
                    if(j > 0) {
                        mat[i][j-1] = 1 - mat[i][j-1];
                    }

                    // Flip right back
                    if(j < mat[0].length - 1) {
                        mat[i][j+1] = 1 - mat[i][j+1];
                    }
                }
            }
        }
    }
}
