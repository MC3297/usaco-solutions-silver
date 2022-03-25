import java.util.*;
import java.io.*;
public class milkvisits {
    public static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    public static String s;
    public static boolean[] visited;
    public static int[] keeptrack;
    public static char[] chartracks;
    public static void dfs(int root, int parent) { //0, 0, 0
        if (visited[root]) {return;}
        visited[root] = true;
        if (s.charAt(root) == s.charAt(parent)) {keeptrack[root] = keeptrack[parent];chartracks[root] = s.charAt(parent);}
        else {keeptrack[root] = root;chartracks[root] = s.charAt(root);}
        for (int i = 0; i < adj.get(root).size(); i++) {
            dfs(adj.get(root).get(i), root);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("milkvisits.in"));
        PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        StringTokenizer st1 = new StringTokenizer(file.readLine());
        int numfarms = Integer.parseInt(st1.nextToken());
        int numqueries = Integer.parseInt(st1.nextToken());
        visited = new boolean[numfarms];
        for (int i = 0; i < numfarms; i++) {
            adj.add(new ArrayList<>());
        }
        s = file.readLine();
        keeptrack = new int[numfarms];
        chartracks = new char[numfarms];
        for (int i = 0; i < numfarms-1; i++) {
            st1 = new StringTokenizer(file.readLine());
            int x = Integer.parseInt(st1.nextToken())-1;
            int y = Integer.parseInt(st1.nextToken())-1;
            adj.get(x).add(y);
            adj.get(y).add(x);
        }
        dfs(0, 0);
        int[] arr = new int[numqueries];
        for (int i = 0; i < numqueries; i++) {
            st1 = new StringTokenizer(file.readLine());
            int a = Integer.parseInt(st1.nextToken())-1;
            int b = Integer.parseInt(st1.nextToken())-1;
            char c = st1.nextToken().charAt(0);
            if (keeptrack[a] != keeptrack[b]) arr[i] = 1;
            else if (keeptrack[a] == keeptrack[b] && chartracks[a] == c) arr[i] = 1;
            else arr[i] = 0;
        }
        for (int i: arr) outfile.print(i);
        file.close();
        outfile.close();
    }
}