import java.io.*;
import java.util.*;
public class soulmates {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int numtestcases = Integer.parseInt(r.readLine());
        for (long q = 0; q < numtestcases; q++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long finalans = Integer.MAX_VALUE;
            long ans = 0;
            while (a != 1) {
                if (a < b) finalans = Math.min(ans + answer(a,b), finalans);
                if (a%2 == 1) {a++; ans++;}
                a /= 2; ans++;
            }
            if (a == b) pw.println(0);
            else pw.println(finalans);
        }
        pw.close();
    }
    public static int answer(long a, long b) {//assume a < b
        int ans = 0;
        long poweroftwo = 1;
        while (a*2 < b) {
            a *= 2;
            ans++;
            poweroftwo *= 2;
        }
        long difference = b - a;
        while (poweroftwo != 0) {
            ans += difference/poweroftwo;
            difference -= difference/poweroftwo * poweroftwo;
            poweroftwo /= 2;
        }
        return ans;
    }
}
