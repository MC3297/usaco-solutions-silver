import java.io.*;
import java.util.*;
public class convolutedintervals {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
    
        StringTokenizer st = new StringTokenizer(r.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] left = new long[m+1];
        long[] right = new long[m+1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(r.readLine());
            left[Integer.parseInt(st.nextToken())]++;
            right[Integer.parseInt(st.nextToken())]++;
        }
        long[] cleft = new long[2*m+1];
        long[] cright = new long[2*m+1];
        for (int i = 0; i < left.length; i++) {
            for (int j = 0; j < left.length; j++) {
                cleft[i + j] += left[i] * left[j];
                cright[i + j] += right[i] * right[j];
            }
        }
        long curr = 0;
        for (int i = 0; i < cleft.length; i++) {
            curr += cleft[i];
            pw.println(curr);
            curr -= cright[i];
        }
        pw.close();
    }
}
