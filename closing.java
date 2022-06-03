import java.util.*;
import java.io.*;
public class closing {
    static HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
    static HashMap<Integer, Boolean> visited = new HashMap<>();
    static HashSet<Integer> set = new HashSet<>();

    public static void add(int x, int y) {
        if (!adj.containsKey(x)) adj.put(x, new ArrayList<>());
        if (!adj.containsKey(y)) adj.put(y, new ArrayList<>());
        adj.get(x).add(y);
        adj.get(y).add(x);
    }
    public static boolean isReachable(int root) {
        //reset the visited
        for (int i: visited.keySet()) visited.put(i, false);
        dfs(root);
        for (int i: visited.keySet()) {
            if (!visited.get(i) && set.contains(i)) return false;
        }
        return true;
    }
    public static void dfs(int root) {
        if (visited.get(root) || !set.contains(root)) return;
        visited.put(root, true);
        for (int i: adj.get(root)) dfs(i);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader r = new BufferedReader(new FileReader("closing.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            add(x, y);
            visited.put(x, false);
            visited.put(y, false);
        }
        for (int i = 1; i <= n; i++) set.add(i);
        if (isReachable(1)) pw.println("YES");
            else pw.println("NO");
        for (int i = 0; i < n-1; i++) {
            int num = Integer.parseInt(r.readLine());
            set.remove(num);
            if (isReachable(nextVal())) pw.println("YES");
            else pw.println("NO");
        }
        r.close();
        pw.close();
    }
    public static int nextVal() {
        return set.iterator().next();
    }
}