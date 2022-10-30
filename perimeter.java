import java.util.*;
import java.io.*;
public class perimeter {
    static int comp = 1;
    static int n;
    static int temp;
    static char[][] grid;
    static int[][] cc;
    static int curr = 0;
    static void dfs(int r, int c) {
        if (!(1 <= r && r <= n && 1 <= c && c <= n)) return;
        if (grid[r][c] != '#') return;
        grid[r][c] = '$';
        temp += calc(r, c);
        cc[r][c] = comp;
        curr++;
        dfs(r+1, c);
        dfs(r-1, c);
        dfs(r, c+1);
        dfs(r, c-1);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader r = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
        n = Integer.parseInt(r.readLine());
        grid = new char[n+2][n+2];
        cc = new int[n+2][n+2];
        for (int i = 0; i <= n+1; i++) grid[i][0] = grid[0][i] = grid[i][n+1] = grid[n+1][i] = '.';
        for (int i = 1; i <= n; i++) {
            String s = r.readLine();
            for (int j = 0; j < n; j++) grid[i][j+1] = s.charAt(j);
        }
        HashMap<Integer, Integer> size = new HashMap<>();
        HashMap<Integer, Integer> peri = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i][j] == '#') {
                    curr = temp = 0;
                    dfs(i, j);
                    size.put(comp, curr);
                    peri.put(comp, temp);
                    comp++;
                }
            }
        }
        int area = 1;
        int perimeter = 4;
        for (int i: size.keySet()) {
            if (size.get(i) > area) {
                area = size.get(i);
                perimeter = peri.get(i);
            }
            else if (size.get(i) == area) perimeter = Math.min(perimeter, peri.get(i));
        }
        pw.println(area + " " + perimeter);
        r.close();
        pw.close();
    }
    static int calc(int r, int c) {
        int ans = 0;
        if (grid[r+1][c] == '.') ans++;
        if (grid[r-1][c] == '.') ans++;
        if (grid[r][c+1] == '.') ans++;
        if (grid[r][c-1] == '.') ans++;
        return ans;
    }
}