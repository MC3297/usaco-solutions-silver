import java.io.*;
import java.util.*;
public class DanceMooves {
    static HashMap<Integer, HashSet<Integer>> componenttoset = new HashMap<>();
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static int[] cc;
    static int currentcomponent = 1;
    static boolean[] visited;
    public static void dfs(int root) {
        if (visited[root]) return;
        visited[root] = true;
        cc[root] = currentcomponent;
        addToHashMapSet(currentcomponent, root);
        for (int i: adj.get(root)) dfs(i);
    }
    public static void addToHashMapSet(int key, int val) {
        if (!componenttoset.containsKey(key)) componenttoset.put(key, new HashSet<>());
        componenttoset.get(key).add(val);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {adj.add(new ArrayList<>());}
        visited = new boolean[n+1];
        cc = new int[n+1];
        int[][] storage = new int[k][2];
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) arr[i] = i+1;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(r.readLine());
            storage[i][0] = Integer.parseInt(st.nextToken())-1;
            storage[i][1] = Integer.parseInt(st.nextToken())-1;
        }
        for (int i = 0; i < storage.length; i++) {//an iteration of k
            int temp = arr[storage[i][0]];
            arr[storage[i][0]] = arr[storage[i][1]];
            arr[storage[i][1]] = temp;
        }
        for (int i = 0; i < arr.length; i++) adj.get(i+1).add(arr[i]);//an iteration of n
        for (int i = 1; i <= n; i++) {//an iteration of n
            if (!visited[i]) {
                dfs(i);
                currentcomponent++;
            }
        }
        for (int i = 0; i < arr.length; i++) arr[i] = i+1;
        for (int i = 0; i < storage.length; i++) {//an iteration of k
            componenttoset.get(cc[arr[storage[i][0]]]).add(storage[i][1]+1);
            componenttoset.get(cc[arr[storage[i][1]]]).add(storage[i][0]+1);
            int temp = arr[storage[i][0]];
            arr[storage[i][0]] = arr[storage[i][1]];
            arr[storage[i][1]] = temp;
        }
        for (int i = 1; i <= n; i++) pw.println(componenttoset.get(cc[i]).size());//an iteration of n
        r.close();
        pw.close();
    }
}
