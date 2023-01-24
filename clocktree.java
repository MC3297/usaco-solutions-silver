import java.io.*;
import java.util.*;
public class clocktree {
    static ArrayList<Integer> adj[];
    static int[] clock, increment;
    static void dfs(int v, int p) {
        for (int i: adj[v]) if (i != p) dfs(i, v);
        int sum = 0;
        for (int i: adj[v]) sum += increment[i];
        increment[v] = (12-(clock[v] + sum)%12)%12;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("clocktree.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")));
        int n = Integer.parseInt(r.readLine());
        clock = new int[n+1];
        increment = new int[n+1];
        adj = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 1; i <= n; i++) clock[i] = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            dfs(i, 0);
            //11 if bessie didnt have to go back to root
            //0 if bessie goes back to root and makes it 12
            if (increment[i] == 0 || increment[i] == 11) {
                answer++;
            }
            increment = new int[n+1];
        }
        pw.println(answer);
        pw.close();
        r.close();
    }
}