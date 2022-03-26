import java.util.*;
import java.io.*;
public class div7 {
    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("div7.in"));
        PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
        int n = Integer.parseInt(file.readLine());
        long[] prefix = new long[n+1];
        long sum = 0;
        for (int i = 1; i < n+1; i++) {
            int m = Integer.parseInt(file.readLine());
            sum += m;
            prefix[i] = sum;
        }//set up prefix and io
        for (int i = 0; i < prefix.length; i++) prefix[i] %= 7;//mod everything
        int max = 0;
        for (int i = 0; i < 7; i++) {//if i appear twice, continuous sum in between is divisible by 7
            int firstoccur = -1;
            int lastoccur = -1;
            for (int j = 0; j < prefix.length; j++) {//find first occurence of i
                if (prefix[j] == i) {firstoccur = j; break;}
            }
            for (int j = prefix.length-1; j >= 0; j--) {//find last occurence of i
                if (prefix[j] == i) {lastoccur = j; break;}
            }
            max = Math.max(lastoccur - firstoccur, max);
        }
        outfile.println(max);
        file.close();
        outfile.close();
    }
}