import java.util.*;
// You are given a tree that contains N nodes, each containing an integer u which corresponds to a lowercase character c in the string s using 1-based indexing.
//You are required to answer Q queries of type [u, c], where u is an integer and c is a lowercase letter. The query result is the number of nodes in the subtree of node u containing c.
public class Solution {
    public static void main(String[] args) {
        int n_2 = 7, q_2 = 3;
        String s_2 = "abaacab";
        Node root_2 = new Node(1);
        root_2.children.add(new Node(2));
        root_2.children.add(new Node(3));
        root_2.children.add(new Node(7));
        root_2.children.get(0).children.add(new Node(4));
        root_2.children.get(0).children.add(new Node(5));
        root_2.children.get(1).children.add(new Node(6));
        ArrayList<Query> queries_2 = new ArrayList<>();
        queries_2.add(new Query(1, 'a'));
        queries_2.add(new Query(2, 'b'));
        queries_2.add(new Query(3, 'a'));
        int[] output_2 = new Solution().countOfNodes(root_2, queries_2, s_2);

        for(int n : output_2) {
            System.out.print(n + " ");
        }
    }


    // Iterate the whole tree and use a hashmap to store <node val, children val arraylist>
    // Iterate input queries and find the target letter in string by s.charAt(q.u - 1)
    // Count all subtree from q.u and if the child has a letter equals to the target, count++
    // Store the count number in the integer array, index move by 1 for the next query
    // Return the integer array
    // Time: O(n^2)
    public void getHashMap(Node root, HashMap<Integer, List<Integer>> map) {
        map.put(root.val, new ArrayList());

        for(Node n : root.children) {
            map.get(root.val).add(n.val);
            getHashMap(n, map);
        }
    }


    int[] countOfNodes(Node root, ArrayList<Query> queries, String s) {
        // Write your code here
        int [] res = new int[queries.size()];
        int index = 0;
        HashMap<Integer, List<Integer>> map = new HashMap();
        getHashMap(root, map);

        for(Query q : queries) {
            char target = s.charAt(q.u - 1);
            Queue<Integer> queue = new LinkedList();
            int count = 1;
            queue.offer(q.u);
            while(!queue.isEmpty()) {
                int cur = queue.poll();
                for(Integer n : map.get(cur)) {
                    if(s.charAt(n-1) == target) {
                        count++;
                    }
                    queue.offer(n);
                }
            }

            res[index] = count;
            index++;
        }

        return res;
    }
}
