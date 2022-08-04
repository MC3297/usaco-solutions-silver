import java.io.*;
import java.util.*;
public class Cowntagion {
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static boolean[] visited;
    static int totalmoves = 0;
    public static void dfs(int root, int numcows) {
        if (visited[root]) return;
        visited[root] = true;
        while (numcows <= adj.get(root).size()-1) {numcows *= 2; totalmoves++;}
        if (adj.get(root).size() != 0) totalmoves += adj.get(root).size()-1;
        for (int i: adj.get(root)) dfs(i, 1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        visited = new boolean[n+1];
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj.get(a).add(b); adj.get(b).add(a);
        }
        adj.get(1).add(0);
        dfs(1, 1);
        pw.println(totalmoves);
        r.close();
        pw.close();
    }
}
