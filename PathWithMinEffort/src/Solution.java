// You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
//
//A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
//
//Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

// Binary Search + DFS
// According to constraints, we know the max absolute path is between 0 to 100000
// Use BS to check all possible answers between 0 and 100000, if there is a path that requires less than mid to get to the destination, we update the result and keep checking 0 - mid. Else, just check mid + 1 to right
// Time: O(mn), Space: O(mn)

class Solution {
    public static void main(String[] args) {
        int [][] m = {{1,2,2},{3,8,2},{5,3,5}};
        System.out.print(new Solution().minimumEffortPath(m));
    }
    int [][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int minimumEffortPath(int[][] heights) {
        // Edge Case:
        if(heights.length == 1 && heights[0].length == 1) {
            return 0;
        }
        int left = 0;
        int right = 1000000;
        int res = right;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(dfs(heights, mid)) {
                res = Math.min(res, mid);
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return res;
    }

    public boolean dfs(int [][] m, int mid) {
        int row = m.length;
        int col = m[0].length;
        boolean [][] visited = new boolean[row][col];
        return canReachDes(0, 0, m, visited, row, col, mid);
    }

    public boolean canReachDes(int i, int j, int [][] m, boolean [][] visited, int r, int c, int mid) {
        if(i == r - 1 && j == c - 1) {
            return true;
        }

        visited[i][j] = true;
        // Try all 4 directions:
        for(int [] dir : dirs) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            // Check if next move is valid and hasn't visited yet
            if(isValid(r, c, nextI, nextJ) && !visited[nextI][nextJ]) {
                int curDiff = Math.abs(m[i][j] - m[nextI][nextJ]);
                if(curDiff <= mid) {
                    if(canReachDes(nextI, nextJ, m, visited, r, c, mid)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean isValid(int r, int c, int i, int j) {
        if(i < 0 || i >= r || j < 0 || j >= c) {
            return false;
        }
        return true;
    }
}