import java.io.*;
import java.util.*;
public class timepaint {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        String s = r.readLine();
        int[] storage = new int[n];
        for (int i = 0; i < n; i++) storage[i] = s.charAt(i)-'A';
        HashSet<Integer> pos = new HashSet<>();
        
        int[] left = new int[n];
        pos.add(storage[0]); left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (pos.contains(storage[i])) left[i] = left[i-1];
            else left[i] = left[i-1] + 1;
            HashSet<Integer> temp = new HashSet<>(); 
            for (int j: pos) if (j <= storage[i]) temp.add(j);
            pos = temp;
            pos.add(storage[i]);
        }

        int[] right = new int[n];
        pos = new HashSet<>();
        pos.add(storage[n-1]); right[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            if (pos.contains(storage[i])) right[i] = right[i+1];
            else right[i] = right[i+1] + 1;
            HashSet<Integer> temp = new HashSet<>(); 
            for (int j: pos) if (j <= storage[i]) temp.add(j);
            pos = temp;
            pos.add(storage[i]);
        }

        for (int t = 0; t < q; t++) {
            st = new StringTokenizer(r.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int ans = 0;
            try {ans += left[a-2];} catch (Exception e) {}
            try {ans += right[b];} catch (Exception e) {}
            pw.println(ans);
        }
        pw.close();
    }
}