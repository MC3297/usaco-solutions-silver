import java.util.*;
import java.io.*;
public class socdist {
    static long[][] storage;
    static final long BIG = (long)1E18;
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("socdist.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
        StringTokenizer st = new StringTokenizer(r.readLine());
        long n = Integer.parseInt(st.nextToken());
        long m = Integer.parseInt(st.nextToken());
        storage = new long[(int)m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(r.readLine());
            storage[i][0] = Long.parseLong(st.nextToken());
            storage[i][1] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(storage, Comparator.comparingDouble(o -> o[0]));
        pw.println(bsearch((long)Math.ceil(BIG/n), n));
        r.close();
        pw.close();
    }
    public static long bsearch(long ar, long n) {
        long l = 0;
        long r = ar;
        while (r-l > 1) {
            long mid = l + (r-l)/2;
            if (possible(n, mid)) l = mid;
            else r = mid; 
        }
        return l;
    }
    public static boolean possible(long n, long d) {
        long place = 0;
        int index = 0;
        while (index < storage.length && 0 < n) {
            if (storage[index][0] <= place && place <= storage[index][1]) {
                place += d;
                n--;
            }
            else if (place < storage[index][0]) {
                place = storage[index][0]+d;
                n--;
            }
            else index++;
        }
        if (n <= 0) return true;
        else return false;
    }
}