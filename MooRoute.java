import java.io.*;
import java.util.*;
public class MooRoute {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        int[] stor = new int[n+1];
        StringTokenizer st = new StringTokenizer(r.readLine());
        int sum = 0;
        for (int i = 0; i < n; i++) {
            stor[i] = Integer.parseInt(st.nextToken());
            sum += stor[i];
        }
        ArrayList<Character> ans = new ArrayList<>();
        int i = 0;
        while (sum > 0) {
            while (i < n && stor[i] > 0) {
                stor[i]--;
                sum--;
                i++;
                ans.add('R');
            }
            i--;
            while (i >= 0 && stor[i] >= stor[i+1]) {
                stor[i]--;
                sum--;
                i--;
                ans.add('L');
            }
            i++;
        }
        for (char c: ans) pw.print(c);
        pw.close();
    }
}