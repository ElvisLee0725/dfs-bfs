import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<String> res = new ArrayList();
        StringBuilder sb = new StringBuilder();
        HashSet<Character> visited = new HashSet();
        visited.add('A');
        dfs(origin, sb, visited, res, 'D');
        System.out.println(res);
    }

    private void dfs(Vertex cur, StringBuilder sb, HashSet<Character> visited, List<String> res, char dest) {
        if(cur.id == dest) {
            sb.append(dest);
            res.add(sb.toString());
            sb.deleteCharAt(sb.length()-1);
        }

        sb.append(cur.id);

        for(Vertex v : cur.edges) {
            if(!visited.contains(v.id)) {
                visited.add(v.id);
                dfs(v, sb, visited, res, dest);
                visited.remove(v.id);
            }
        }

        sb.deleteCharAt(sb.length()-1);
    }
}
