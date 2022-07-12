import java.util.*;
import java.io.*;
public class revegetate {
    static ArrayList<ArrayList<Integer>> sameadj = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> diffadj = new ArrayList<>();
    static int[] visited;
    static boolean contradiction = false;
    public static void dfs(int root, int color) {//0 = unvisited, 1,2 are colors
        if (visited[root] == color) return;
        else if (visited[root] != 0) {contradiction = true; return;}
        visited[root] = color;
        for (int i: sameadj.get(root)) dfs(i, color);
        for (int i: diffadj.get(root)) dfs(i, opposite(color));
    }
    public static void main(String[] args) throws IOException{
        BufferedReader r = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new int[n+1];
        for (int i = 0; i <= n; i++) {sameadj.add(new ArrayList<>()); diffadj.add(new ArrayList<>());}
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            String s = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (s.equals("S")) {sameadj.get(a).add(b); sameadj.get(b).add(a);}
            else if (s.equals("D")) {diffadj.get(a).add(b); diffadj.get(b).add(a);}
        }
        ArrayList<Integer> answer = new ArrayList<>(); answer.add(1);
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                dfs(i, 1);
                answer.add(0);
            }
        }
        if (contradiction) pw.println(0);
        else for (int i: answer) pw.print(i);
        r.close();
        pw.close();
    }
    public static int opposite(int a) {
        if (a == 1) return 2;
        else return 1;
    }
}