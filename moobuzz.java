import java.util.*;
import java.io.*;
public class moobuzz {
    public static void main(String[] args) throws IOException{
        BufferedReader r = new BufferedReader(new FileReader("moobuzz.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
        int n = Integer.parseInt(r.readLine());
        pw.println(n/8 * 15 + function(n%8));
        r.close();
        pw.close();
    }
    public static int function(int x) {
        if (x == 0) return -1;
        for (int i = 1; i <= 15; i++) {
            if (i%3 != 0 && i%5 != 0) {
                x--;
                if (x == 0) return i;
            }
        }
        return 69;
    }
}