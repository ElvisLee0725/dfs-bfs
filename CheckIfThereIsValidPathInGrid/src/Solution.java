// Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
//1 which means a street connecting the left cell and the right cell.
//2 which means a street connecting the upper cell and the lower cell.
//3 which means a street connecting the left cell and the lower cell.
//4 which means a street connecting the right cell and the lower cell.
//5 which means a street connecting the left cell and the upper cell.
//6 which means a street connecting the right cell and the upper cell.
// You will initially start at the street of the upper-left cell (0,0). A valid path in the grid is a path which starts from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.
//
// Notice that you are not allowed to change any street.
//
// Return true if there is a valid path in the grid or false otherwise.

// Start from position [0,0], check if I can reach [m-1][n-1]? If so, check if parent connect valid and return true or false
// Check if cur position is valid?
// If the child is already visited, return false
// At each node, check if current node can be connected from parent? If not, return false
// Else, keep calling dfs to the children that cur node can go to
// Time: O(mn), Space: O(mn)
class Solution {
    public static void main(String[] args) {
        int [][] grid = {{2,4,3},{6,5,2}};
        System.out.print(new Solution().hasValidPath(grid));
    }
    class Pos {
        int i;
        int j;
        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if(m == 1 && n == 1) {
            return true;
        }
        boolean [][] visited = new boolean[m][n];
        return dfs(grid, visited, null, new Pos(0, 0));
    }

    private boolean dfs(int [][] grid, boolean [][] visited, Pos parent, Pos cur) {
        if(cur.i == grid.length-1 && cur.j == grid[0].length-1) {
            return validConnect(parent, cur, grid[cur.i][cur.j]);
        }
        else if(cur.i < 0 || cur.i >= grid.length || cur.j < 0 || cur.j >= grid[0].length) {
            return false;
        }
        else if(visited[cur.i][cur.j]) {
            return false;
        }
        else if(parent != null && !validConnect(parent, cur, grid[cur.i][cur.j])) {
            return false;
        }

        visited[cur.i][cur.j] = true;

        Pos [] children = getChildPos(cur, grid[cur.i][cur.j]);

        return dfs(grid, visited, cur, children[0]) || dfs(grid, visited, cur, children[1]);
    }

    private boolean validConnect(Pos p, Pos c, int cStreet) {
        // Down
        if(p.i + 1 == c.i) {
            if(cStreet == 2 || cStreet == 5 || cStreet == 6) {
                return true;
            }
        }
        // Up
        else if(p.i - 1 == c.i) {
            if(cStreet == 2 || cStreet == 3 || cStreet == 4) {
                return true;
            }
        }
        // Right
        else if(p.j + 1 == c.j) {
            if(cStreet == 1 || cStreet == 3 || cStreet == 5) {
                return true;
            }
        }
        // Left
        else if(p.j - 1 == c.j) {
            if(cStreet == 1 || cStreet == 4 || cStreet == 6) {
                return true;
            }
        }
        return false;
    }

    private Pos [] getChildPos(Pos cur, int street) {
        Pos child1 = new Pos(0, 0);
        Pos child2 = new Pos(0, 0);
        switch(street) {
            case 1:
                // Right, left
                child1.i = cur.i;
                child1.j = cur.j+1;
                child2.i = cur.i;
                child2.j = cur.j-1;
                break;

            case 2:
                // Top, down
                child1.i = cur.i-1;
                child1.j = cur.j;
                child2.i = cur.i+1;
                child2.j = cur.j;
                break;

            case 3:
                // Left, down
                child1.i = cur.i;
                child1.j = cur.j-1;
                child2.i = cur.i+1;
                child2.j = cur.j;
                break;

            case 4:
                // Down, right
                child1.i = cur.i+1;
                child1.j = cur.j;
                child2.i = cur.i;
                child2.j = cur.j+1;
                break;

            case 5:
                // Left, top
                child1.i = cur.i;
                child1.j = cur.j-1;
                child2.i = cur.i-1;
                child2.j = cur.j;
                break;

            case 6:
                // Top, right
                child1.i = cur.i-1;
                child1.j = cur.j;
                child2.i = cur.i;
                child2.j = cur.j+1;
                break;

            default:

                break;
        }
        return new Pos[]{child1, child2};
    }
}
