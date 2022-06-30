import java.io.*;
import java.util.*;
public class swap {
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static HashMap<Integer, ArrayList<Integer>> componentcycle = new HashMap<>();
    static boolean[] visited;
    static int[] cc;
    static int[] position;
    static int currentcomponent = 0;
    public static void reverse(int[] arr, int l, int r) {
        for (int i = l; i <= (l+r)/2; i++) {
            int temp = arr[i];
            arr[i] = arr[r-i+l];
            arr[r-i+l] = temp;
        }
    }
    public static void dfs(int root) {
        if (visited[root]) return;
        visited[root] = true;
        cc[root] = currentcomponent;
        position[root] = componentcycle.get(currentcomponent).size();
        componentcycle.get(currentcomponent).add(root);
        for (int i: adj.get(root)) dfs(i);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("swap.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] storage = new int[m][2];
        visited = new boolean[n];
        position = new int[n];
        cc = new int[n];
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            storage[i][0] = Integer.parseInt(st.nextToken())-1;
            storage[i][1] = Integer.parseInt(st.nextToken())-1;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        for (int i = 0; i < storage.length; i++) {
            reverse(arr, storage[i][0], storage[i][1]);
        }
        for (int i = 0; i < arr.length; i++) {
            adj.get(i).add(arr[i]);
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                currentcomponent++;
                componentcycle.put(currentcomponent, new ArrayList<>());
                dfs(i);
            }
        }
        for (int i = 0; i < n; i++) {
            int times = (k+position[i])%componentcycle.get(cc[i]).size();
            pw.println(componentcycle.get(cc[i]).get(times)+1);
        }
        r.close();
        pw.close();
    }
}
