import java.io.*;
import java.util.*;
public class angry {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
    
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int cows = Integer.parseInt(st.nextToken());
        int[] haybales = new int[num];
        for (int i = 0; i < num; i++) haybales[i] = Integer.parseInt(br.readLine());
        Arrays.sort(haybales);
        int l = 0;
        int r = (int)1E9;
        while (r-l > 1) {
            int mid = (l+r)/2;
            if (possible(haybales, cows, mid)) r = mid;
            else l = mid;
        }
        pw.println(r);
        br.close();
        pw.close();
    }
    public static boolean possible(int[] haybales, int cows, int radius) {
        int l = -1;
        int r = -1;
        for (int i = 0; i < haybales.length; i++) {
            if (l <= haybales[i] && haybales[i] <= r) continue;
            else {
                l = haybales[i];
                r = l + 2*radius;
                cows--;
            }
        }
        if (cows < 0) return false;
        return true;
    }
}
