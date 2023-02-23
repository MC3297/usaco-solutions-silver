import java.io.*;
import java.util.*;
public class FollowingDirections {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(r.readLine());
        int[][] stor = new int[n+1][n+1];//0 is right, 1 is down
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(r.readLine());
            String a = st.nextToken();
            int b = Integer.parseInt(st.nextToken());
            for (int j = 0; j < a.length(); j++) {
                if (a.charAt(j) == 'R') stor[i][j] = 0;
                else stor[i][j] = 1;
            }
            stor[i][n] = b;
        }
        StringTokenizer st = new StringTokenizer(r.readLine());
        for (int i = 0; i < n; i++) {
            stor[n][i] = Integer.parseInt(st.nextToken());
        }
        //calculate currcosts
        int[][] size = new int[n+1][n+1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                size[i][j]++;
                if (stor[i][j] == 0) {//right
                    size[i][j+1]+= size[i][j];
                }
                else if (stor[i][j] == 1) {//down
                    size[i+1][j]+= size[i][j];
                }
            }
        }
        for (int[] i: size) System.out.println(Arrays.toString(i));//
        int cost = 0;
        for (int i = 0; i < n; i++) {
            cost += stor[i][n]*size[i][n] + stor[n][i]*size[n][i];
        }
        pw.println(cost);
        int queries = Integer.parseInt(r.readLine());
        for (int q = 0; q < queries; q++) {
            st = new StringTokenizer(r.readLine());
            int nrow = Integer.parseInt(st.nextToken())-1;
            int ncol = Integer.parseInt(st.nextToken())-1;
            //subtract size from original path, add size to new path
            int i = nrow;
            int j = ncol;
            while (i != n && j != n) {//subtract, follow og path
                if (stor[i][j] == 0) {
                    j++;
                }
                else if (stor[i][j] == 1) {
                    i++;
                }
                size[i][j] -= size[nrow][ncol];
            }
            i = nrow; j = ncol;
            stor[i][j] = flip(stor[i][j]);
            while (i != n && j != n) {//add, follow new path
                if (stor[i][j] == 0) {
                    j++;
                }
                else if (stor[i][j] == 1) {
                    i++;
                }
                size[i][j] += size[nrow][ncol];
            }
            int ncost = 0;
            for (int t = 0; t < n; t++) {
                ncost += stor[t][n]*size[t][n] + stor[n][t]*size[n][t];
            }
            pw.println(ncost);
        }
        pw.close();
    }
    static int flip(int a) {
        if (a == 0) return 1;
        else return 0;
    }
}