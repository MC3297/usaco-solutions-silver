import java.util.*;
import java.io.*;
public class planting {
    static HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
    public static void add(int x, int y) {
        if (!adj.containsKey(x)) adj.put(x, new ArrayList<>());
        adj.get(x).add(y);
        if (!adj.containsKey(y)) adj.put(y, new ArrayList<>());
        adj.get(y).add(x);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader r = new BufferedReader(new FileReader("planting.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
        int n = Integer.parseInt(r.readLine());
        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            add(x, y);
        }
        int max = 0;
        for (int i: adj.keySet()) max = Math.max(max, adj.get(i).size());
        pw.println(max+1);
        r.close();
        pw.close();
    }
}