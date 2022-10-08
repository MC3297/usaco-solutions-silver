import java.io.*;
import java.util.*;
public class ComfortableCows {
    static int[][] cow = new int[2500][2500];
    static HashSet<Integer> set = new HashSet<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void update(int a, int b) {
        cow[a][b] = Math.max(0, cow[a][b]);
        for (int p = 0; p < dx.length; p++) if (cow[a+dx[p]][b+dy[p]] != -1) {cow[a][b]++; cow[a+dx[p]][b+dy[p]]++;}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        for (int[] i: cow) Arrays.fill(i, -1);
        int[][] storage = new int[n][2];
        for (int i = 0; i < storage.length; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken())+1000;
            int b = Integer.parseInt(st.nextToken())+1000;
            storage[i][0] = a; storage[i][1] = b;
        }
        for (int i = 0; i < storage.length; i++) {
            if (set.contains(storage[i][0]*10000 + storage[i][1])) {//cow was already added, do nothing
                set.remove(storage[i][0]*10000 + storage[i][1]);
                pw.println(set.size());
                continue;
            }
            update(storage[i][0], storage[i][1]);
            LinkedList<Cow> q = new LinkedList<>();
            for (int p = 0; p < dx.length; p++) {
                if (cow[storage[i][0]+dx[p]][storage[i][1]+dy[p]] == 3) q.push(new Cow(storage[i][0]+dx[p], storage[i][1]+dy[p]));
            }
            if (cow[storage[i][0]][storage[i][1]] == 3) q.push(new Cow(storage[i][0], storage[i][1]));
            while (!q.isEmpty()) {//single path bfs
                Cow curr = q.poll();
                if (cow[curr.x][curr.y] != 3) continue;
                Cow missing = new Cow(0, 0);//cow has 3 neighbors, find missing neighbor
                for (int p = 0; p < dx.length; p++) 
                    if (cow[curr.x+dx[p]][curr.y+dy[p]] == -1) missing = new Cow(curr.x+dx[p], curr.y+dy[p]);
                update(missing.x, missing.y);//add cow
                set.add(missing.x*10000 + missing.y);
                if (cow[missing.x][missing.y] == 3) q.push(new Cow(missing.x, missing.y));
                for (int p = 0; p < dx.length; p++) //added cow could affect other cows
                    if (cow[missing.x+dx[p]][missing.y+dy[p]] == 3) q.push(new Cow(missing.x+dx[p], missing.y+dy[p]));
            }
            pw.println(set.size());
        }
        pw.close();
    }
}
class Cow {int x,y;public Cow(int ax, int ay){x = ax;y = ay;}@Override public String toString() {return x+" "+y;}}
