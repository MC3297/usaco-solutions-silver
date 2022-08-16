import java.io.*;
import java.util.*;
public class shuffle {
    static HashSet<Integer> visited = new HashSet<>();
    static ArrayList<Integer> answer;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    public static void dfs(int root, ArrayList<Integer> path) {
        path.add(root);
        if (visited.contains(root)) {answer = new ArrayList<>(path); return;}
        visited.add(root);
        for (int i: adj.get(root)) dfs(i, path);
        path.remove(path.size()-1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
        int n = Integer.parseInt(r.readLine());
        StringTokenizer st = new StringTokenizer(r.readLine());
        int[] storage = new int[n];
        adj.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            storage[i] = Integer.parseInt(st.nextToken());
            adj.add(new ArrayList<>());
            adj.get(i+1).add(storage[i]);
        }
        int total = 0;
        for (int i = 1; i <= n; i++) {
            if (visited.contains(i)) continue;
            dfs(i, new ArrayList<>());
            HashMap<Integer, Integer> check = new HashMap<>();
            for (int j = 0; j < answer.size(); j++) {
                if (!check.containsKey(answer.get(j))) check.put(answer.get(j), j+1);
                else total += answer.size()-check.get(answer.get(j));
            }
        }
        pw.println(total);
        r.close();
        pw.close();
    }
}