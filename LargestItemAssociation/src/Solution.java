import java.util.*;

public class Solution {
    public static void main(String[] args) {
        List<PairString> ia = new ArrayList(Arrays.asList(new PairString("item1", "item2"), new PairString("item6", "item1"), new PairString("item3", "item4"), new PairString("item4", "item5"), new PairString("item4", "item2")));

        System.out.println(new Solution().LargestItemAssociation(ia));
    }
    // Use a HashMap to create the adjacency list for all items
    // To make the adjacency list's key sorted, use TreeMap
    // Iterate the itemAssociation List to create the adjacency list's TreeMap
    // Iterate the adjacency list and use DFS to find all associated keys of a group
    // If the current group has bigger size, store it in a temp result and keep going
    // Return the final result
    // Time: O(V+E), Space: O(V+E)
    List<String> LargestItemAssociation(List<PairString> itemAssociation) {
        TreeMap<String, List<String>> map = new TreeMap();
        for(PairString ps : itemAssociation) {
            String item1 = ps.first;
            String item2 = ps.second;
            map.computeIfAbsent(item1, x -> new ArrayList()).add(item2);
            map.computeIfAbsent(item2, x -> new ArrayList()).add(item1);
        }

        HashSet<String> hs = new HashSet();
        List<String> res = new ArrayList();
        List<String> tmp;
        for(String itemName : map.keySet()) {
            tmp = new ArrayList();
            dfs(itemName, tmp, map, hs);
            if(tmp.size() > res.size()) {
                res = new ArrayList(tmp);
            }
        }
        return res;
    }

    private void dfs(String curItem, List<String> tmp, TreeMap<String, List<String>> map, HashSet<String> hs) {
        tmp.add(curItem);
        hs.add(curItem);
        for(String nei : map.get(curItem)) {
            if(!hs.contains(nei)) {
                dfs(nei, tmp, map, hs);
            }
        }
    }
}
