import java.io.*;
import java.util.*;
public class wormsort {
    static ArrayList<ArrayList<Item>> adj = new ArrayList<>();
    static boolean[] visited;
	static int[] pos;
	static boolean[] unnecessary;
	static void deep(int root, int parent) {
		for (Item i: adj.get(root)) if (i.node != parent) deep(i.node, root);
		if (pos[root] == root) {
			boolean record = false;
			for (Item i: adj.get(root)) if (i.node != parent && !unnecessary[i.node]) record = true;
			if (!record) unnecessary[root] = true;
		}
	}
    public static void main(String[] args) throws IOException {
        Kattio kat = new Kattio("wormsort");
        int n = kat.nextInt();
        int m = kat.nextInt();
        pos = new int[n+1];
        unnecessary = new boolean[n+1];
        visited = new boolean[n+1];
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
		for (int i = 1; i <= n; i++) pos[i] = kat.nextInt();
        for (int i = 0; i < m; i++) {
            int a = kat.nextInt();
            int b = kat.nextInt();
            int w = kat.nextInt();
            adj.get(a).add(new Item(b, w, 0)); adj.get(b).add(new Item(a, w, 0));
        }
		boolean check = true;
		for (int i = 1; i < pos.length; i++) if (pos[i] != i) check = false;
		if (check) {
			kat.println(-1);
			kat.close();
			return;
		}
		//sorta dijkstra?? not adding weights tho
		PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> b.width-a.width);
		boolean[] visited = new boolean[n+1];
		HashSet<ArrayList<Integer>> set = new HashSet<>();
		for (int i = 0; i < n; i++) if (pos[i] != i) {pq.add(new Item(pos[i], 0, 0)); break;}
		while (!pq.isEmpty()) {
			Item curr = pq.poll();
			if (visited[curr.node]) continue;
			ArrayList<Integer> temp = new ArrayList<>(3);
			temp.add(curr.node); temp.add(curr.parent); temp.add(curr.width);
			set.add(temp);
			visited[curr.node] = true;
			for (Item i: adj.get(curr.node)) {
				if (!visited[i.node]) {
					pq.add(new Item(i.node, i.width, curr.node));
				}
			}
		}
		ArrayList<ArrayList<Item>> tempadj = new ArrayList<>();
		for (int i = 0; i < adj.size(); i++) {
			tempadj.add(new ArrayList<>());
			for (Item j: adj.get(i)) {
				ArrayList<Integer> temparr = new ArrayList<>();
				temparr.add(i); temparr.add(j.node); temparr.add(j.width);
				if (set.contains(temparr)) tempadj.get(i).add(j);
				temparr = new ArrayList<>();
				temparr.add(j.node); temparr.add(i); temparr.add(j.width);
				if (set.contains(temparr)) tempadj.get(i).add(j);
			}
		}
		adj = tempadj;
		//guaranteed to be a tree
		for (int i = 1; i <= n; i++) if (pos[i] != i) {deep(pos[i], 0); break;}
		//marked unnecessary nodes
		tempadj = new ArrayList<>();
		for (int i = 0; i < adj.size(); i++) {
			tempadj.add(new ArrayList<>());
			if (unnecessary[i]) continue;
			for (Item j: adj.get(i)) {
				if (!unnecessary[j.node]) tempadj.get(i).add(j);
			}
		}
		int minwidth = Integer.MAX_VALUE;
		adj = tempadj;
		for (ArrayList<Item> i: adj) {
			for (Item j: i) minwidth = Math.min(minwidth, j.width);
		}
		kat.println(minwidth);
        kat.close();
    }
}
class Item {
	int node;
	int width;
	int parent;
	public Item(int anode, int awidth, int aparent) {
		node = anode;
		width = awidth;
		parent = aparent;
	}
	public String toString() {return node+" "+width;}
}
class Kattio extends PrintWriter {
	private BufferedReader r;
	private StringTokenizer st;
	// standard input
	public Kattio() { this(System.in,System.out); }
	public Kattio(InputStream i, OutputStream o) {
		super(o);
		r = new BufferedReader(new InputStreamReader(i));
	}
	// USACO-style file input
	public Kattio(String problemName) throws IOException {
		super(problemName+".out");
		r = new BufferedReader(new FileReader(problemName+".in"));
	}
	// returns null if no more input
	public String next() {
		try {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(r.readLine());
			return st.nextToken();
		} catch (Exception e) {}
		return null;
	}
	public int nextInt() { return Integer.parseInt(next()); }
	public double nextDouble() { return Double.parseDouble(next()); }
	public long nextLong() { return Long.parseLong(next()); }
}