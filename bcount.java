import java.util.*;
import java.io.*;
public class bcount {
    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        StringTokenizer st1 = new StringTokenizer(file.readLine());
        int n = Integer.parseInt(st1.nextToken());
        int q = Integer.parseInt(st1.nextToken());
        int[] currvalues = new int[4];
        int[][] prefix = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            int x = Integer.parseInt(file.readLine());
            currvalues[x]++;
            prefix[i][0] = currvalues[1]; prefix[i][1] = currvalues[2]; prefix[i][2] = currvalues[3];
        }
        for (int i = 0; i < q; i++) {
            st1 = new StringTokenizer(file.readLine());
            int y = Integer.parseInt(st1.nextToken())-1;
            int x = Integer.parseInt(st1.nextToken());
            outfile.print(prefix[x][0] - prefix[y][0]);
            outfile.print(' ');
            outfile.print(prefix[x][1] - prefix[y][1]);
            outfile.print(' ');
            outfile.println(prefix[x][2] - prefix[y][2]);
        }
        file.close();
        outfile.close();
    }
}