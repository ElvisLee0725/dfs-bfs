// DFS:
// Iterate the board to find the starting poing to run DFS -> board[i][j] == first char of word
// Dfs function:
// Base Case: 
// If found, return
// Else if out of bound or visited, return 
// Else if board[i][j] != word.charAt(index), return 
// Else if index equals word.length() - 1, we found an answer! 

// Recursive rule:
// Add the character at cur position
// Mark cur position as visited set to '*'
// Try all 4 directions
// Backtracking -> unvisit cur position and remove last character 

class Solution {
    public boolean exist(char[][] board, String word) {
        int r = board.length;
        int c = board[0].length;
        // Edge Case:
        if(word.length() > r * c) {
            return false;
        }
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(i, j, board, 0, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean dfs(int r, int c, char [][] board, int index, String word) {
        if(index == word.length()) {
            return true;
        }
        else if(r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == '*' || board[r][c] != word.charAt(index)) {
            return false;
        }

        char cur = board[r][c];
        board[r][c] = '*';

        boolean found = dfs(r - 1, c, board, index + 1, word) ||
                dfs(r + 1, c, board, index + 1, word) ||
                dfs(r, c - 1, board, index + 1, word) ||
                dfs(r, c + 1, board, index + 1, word);

        if(!found) {
            board[r][c] = cur;
        }
        return found;
    }
}