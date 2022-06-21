import java.util.*;
import java.io.*;
public class moocast {
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static boolean[] visited;
    static int tempttotal = 0;
    public static boolean isReachable(int[] a, int[] b) {//a -> b, a can reach b
        int distSquared = (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
        if (distSquared <= a[2]*a[2]) return true;
        return false;
    }
    public static void dfs(int root) {
        if (visited[root]) return;
        tempttotal++;
        visited[root] = true;
        for (int i: adj.get(root)) dfs(i);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader r = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        int n = Integer.parseInt(r.readLine());
        int[][] storage = new int[n][3];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            storage[i][0] = Integer.parseInt(st.nextToken());
            storage[i][1] = Integer.parseInt(st.nextToken());
            storage[i][2] = Integer.parseInt(st.nextToken());
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isReachable(storage[i], storage[j]) && i != j) {
                    adj.get(i).add(j);
                }
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                tempttotal = 0;
                visited = new boolean[n];
                dfs(i);
                max = Math.max(tempttotal, max);
            }
        }
        pw.println(max);
        r.close();
        pw.close();
    }
}