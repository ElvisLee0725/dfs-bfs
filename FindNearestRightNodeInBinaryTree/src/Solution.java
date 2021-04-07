// Given the root of a binary tree and a node u in the tree, return the nearest node on the same level that is to the right of u, or return null if u is the rightmost node in its level.

import java.util.LinkedList;
import java.util.Queue;

// BFS:
// Use BFS to expand the tree level by level from the root. 
// At each level, if the expaned node equals u, then we check if it's the last node in that level? If so, there is no node to the right of it, return null. Else, return the next node in queue
// Time: O(n), Space: O(n)
class Solution {
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if(cur.val == u.val) {
                    if(i == size - 1) {
                        return null;
                    }
                    else {
                        return q.poll();
                    }
                }
                if(cur.left != null) {
                    q.offer(cur.left);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return null;
    }
}