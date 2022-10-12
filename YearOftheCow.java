import java.io.*;
import java.util.*;
public class YearOftheCow {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken())-1;
        int[] storage = new int[n];
        for (int i = 0; i < n; i++) storage[i] = Integer.parseInt(r.readLine());
        Arrays.sort(storage);
        ArrayList<loco> dists = new ArrayList<>();
        dists.add(new loco(-1, storage[0]-storage[0]%12));
        for (int i = 0; i < n-1; i++) {
            dists.add(new loco(i, (storage[i+1] - storage[i+1]%12)-(storage[i] - storage[i]%12)));
        }
        Collections.sort(dists, Comparator.comparingInt(a -> a.dist));
        HashSet<Integer> indices = new HashSet<>();
        for (int i = n-1; i >= n-k; i--) indices.add(dists.get(i).index);
        int curryear = storage[n-1] - storage[n-1]%12 + 12;
        long total = 0;
        for (int i = n-1; i >= 0; i--) {
            if (curryear < storage[i]) continue;
            if (indices.contains(i)) total += 12;
            else total += curryear-(storage[i]-storage[i]%12);
            curryear = storage[i]-storage[i]%12;
        }
        if (!indices.contains(-1)) total += curryear;
        pw.println(total);
        pw.close();
    }
}
class loco {int index,dist;public loco(int aindex, int adist){index = aindex; dist = adist;}public String toString(){return index+" "+dist;}}