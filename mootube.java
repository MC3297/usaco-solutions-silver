import java.io.*;
import java.util.*;
public class mootube {
    static ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
    static int k;
    static int answer = 0;
    static boolean[] visited;
    public static void dfs(int root, int weight, int parent) {
        if (k <= weight) answer++;
        for (Pair i: adj.get(root)) if (i.node != parent) dfs(i.node, Math.min(weight,i.weight), root);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        StringTokenizer st = new StringTokenizer(r.readLine());
        int numnodes = Integer.parseInt(st.nextToken());
        int queries = Integer.parseInt(st.nextToken());
        visited = new boolean[numnodes+1];
        for (int i = 0; i <= numnodes; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < numnodes-1; i++) {
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(a).add(new Pair(b, w));
            adj.get(b).add(new Pair(a, w));
        }
        for (int i = 0; i < queries; i++) {
            st = new StringTokenizer(r.readLine());
            int tempk = Integer.parseInt(st.nextToken());
            int vid = Integer.parseInt(st.nextToken());
            k = tempk;
            dfs(vid, Integer.MAX_VALUE, 0);
            pw.println(answer-1);
            answer = 0;
        }
        r.close();
        pw.close();
    }
}
class Pair {
    int node;
    int weight;
    public Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
    @Override
    public String toString() {return "("+node+" "+weight+")";}
}