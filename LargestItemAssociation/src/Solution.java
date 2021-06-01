import java.util.*;

public class Solution {
    public static void main(String[] args) {
        List<PairString> ia = new ArrayList(Arrays.asList(new PairString("item1", "item2"), new PairString("item6", "item1"), new PairString("item3", "item4"), new PairString("item4", "item5"), new PairString("item4", "item2")));

        System.out.println(new Solution().LargestItemAssociation2(ia));
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

    // Union Find:
    // Use a hashmap with <Item Name, Root of this item>, and another hashmap <Item Name, Weight>
    // Iterate the input itemAssociation List, find the root of each pair
    // If the root of that pair doesn't match, do union and record the largest union
    // Return the set with largest union
    List<String> LargestItemAssociation2(List<PairString> itemAssociation) {
        TreeMap<String, String> map = new TreeMap();
        HashMap<String, Integer> weightMap = new HashMap();
        String tmp = "";
        int max = 0;
        for(PairString ps : itemAssociation) {
            String item1 = ps.first;
            String item2 = ps.second;
            map.putIfAbsent(item1, item1);
            map.putIfAbsent(item2, item2);
            String r1 = find(item1, map);
            String r2 = find(item2, map);

            if (!r1.equals(r2)) {
                weightMap.putIfAbsent(r1, 1);
                weightMap.putIfAbsent(r2, 1);
                if (r1.compareTo(r2) < 0) {
                    map.put(map.get(r2), r1);
                    weightMap.put(r1, weightMap.get(r1) + weightMap.get(r2));
                    if (weightMap.get(r1) > max || (max == weightMap.get(r1) && r1.compareTo(tmp) < 0)) {
                        max = weightMap.get(r1);
                        tmp = r1;
                    }
                } else {
                    map.put(map.get(r1), r2);
                    weightMap.put(r2, weightMap.get(r2) + weightMap.get(r1));
                    if (weightMap.get(r2) > max || (max == weightMap.get(r2) && r2.compareTo(tmp) < 0)) {
                        max = weightMap.get(r2);
                        tmp = r2;
                    }
                }
            }
        }
        List<String> res = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (find(entry.getValue(), map).equals(tmp)) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    private String find(String x, TreeMap<String, String> map) {
        if(map.get(x).equals(x)) {
            return x;
        }
        map.put(x, find(map.get(x), map));
        return map.get(x);
    }
}
