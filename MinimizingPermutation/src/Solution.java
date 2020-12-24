import java.util.LinkedList;
import java.util.Queue;
// In this problem, you are given an integer N, and a permutation, P of the integers from 1 to N, denoted as (a_1, a_2, ..., a_N). You want to rearrange the elements of the permutation into increasing order, repeatedly making the following operation:
// Select a sub-portion of the permutation, (a_i, ..., a_j), and reverse its order.
// Your goal is to compute the minimum number of such operations required to return the permutation to increasing order.

public class Solution {
    public static void main(String[] args) {
        int [] input = new int[]{5, 3, 1, 2, 4};
        System.out.print(new Solution().minOperations(input));
    }
    // BFS Solution
    // At each round, get all the possible ways to reverse the current array and add to queue
    // If the cur array is in incresing order, we found the answer
    // Use a queue to store the initial state as the input array.
    // While the queue is not empty, check how many in queue first since each round we check every arrays in queue currently
    // Then, get all combination of indice to reverse the current array and add into the queue
    // After adding all array combinations at each level, count + 1
    // Return the count variable at the end
    // Time: O(n^2)
    public boolean isIncreasing(int [] a) {
        for(int i = 1; i < a.length; i++) {
            if(a[i] <= a[i-1]) {
                return false;
            }
        }
        return true;
    }

    public void reverse(int [] a, int i, int j) {
        while(i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
    }

    int minOperations(int[] arr) {
        // Write your code here
        int count = 0;
        boolean found = false;
        Queue<int []> q = new LinkedList();
        q.offer(arr.clone());
        while(!q.isEmpty()) {
            int size = q.size();
            for(int n = 0; n < size; n++) {
                int [] cur = q.poll();

                if(isIncreasing(cur)) {
                    found = true;
                    break;
                }
                for(int i = 0; i < arr.length-1; i++) {
                    for(int j = i+1; j < arr.length; j++) {
                        int [] temp = cur.clone();
                        reverse(temp, i, j);
                        q.offer(temp);
                    }
                }
            }
            if(found) {
                break;
            }
            count++;
        }
        return count;
    }
}
