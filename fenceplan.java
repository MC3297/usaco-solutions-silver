import java.io.*;
import java.util.*;
public class fenceplan {
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    static int currentcomponent = 1;
    static int[] cc;
    static boolean[] visited;
    static int[][] storage;
    public static void dfsComponents(int root) {
        if (visited[root]) return;
        visited[root] = true;
        cc[root] = currentcomponent;
        for (int i: adj.get(root)) dfsComponents(i);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader r = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());//nodes
        int m = Integer.parseInt(st.nextToken());//edges
        storage = new int[n][2];
        cc = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(r.readLine());
            storage[i][0] = Integer.parseInt(st.nextToken());//x
            storage[i][1] = Integer.parseInt(st.nextToken());//y
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            adj.get(x).add(y); adj.get(y).add(x);
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsComponents(i);
                currentcomponent++;
            }
        }
        int[][] xy = new int[currentcomponent][4]; //xy[i][0] is min x, 1 is max x, 2 is min y, 3 is max y
        for (int i = 0; i < xy.length; i++) {
            xy[i][0] = Integer.MAX_VALUE;
            xy[i][2] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < cc.length; i++) {
            xy[cc[i]][0] = Math.min(xy[cc[i]][0], storage[i][0]);
            xy[cc[i]][1] = Math.max(xy[cc[i]][1], storage[i][0]);
            xy[cc[i]][2] = Math.min(xy[cc[i]][2], storage[i][1]);
            xy[cc[i]][3] = Math.max(xy[cc[i]][3], storage[i][1]);
        }
        long area = Long.MAX_VALUE;
        for (int i = 1; i < xy.length; i++) {
            int currarea = xy[i][1] - xy[i][0] + xy[i][3] - xy[i][2];
            area = Math.min(area, currarea*2);
        }
        pw.println(area);
        r.close();
        pw.close();
    }
}