import java.util.*;
import java.io.*;
public class rental {
    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("rental.in"));
        PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
        StringTokenizer st1 = new StringTokenizer(file.readLine());
        int numcows = Integer.parseInt(st1.nextToken());
        int buymilk = Integer.parseInt(st1.nextToken());
        int rentcow = Integer.parseInt(st1.nextToken());
        long[] cows = new long[numcows];
        long[] eachcowvalue = new long[numcows];
        long[][] milkbuy = new long[buymilk][2];
        long[] rent = new long[rentcow];
        for (int i = 0; i < numcows; i++) {
            cows[i] = Integer.parseInt(file.readLine());
        }
        for (int i = 0; i < buymilk; i++) {
            st1 = new StringTokenizer(file.readLine());
            milkbuy[i][0] = Integer.parseInt(st1.nextToken());
            milkbuy[i][1] = Integer.parseInt(st1.nextToken());
        }
        for (int i = 0; i < rentcow; i++) {
            rent[i] = Integer.parseInt(file.readLine());
        }
        Arrays.sort(cows);
        Arrays.sort(milkbuy, Comparator.comparingDouble(o -> o[1]));
        Arrays.sort(rent);
        long[][] temparr = milkbuy.clone();
        int currindex = temparr.length-1;
        for (int i = numcows-1; i >= 0; i--) {
            long currval = cows[i];
            while (currval != 0 && currindex < temparr.length && currindex >= 0) {
                long decreased = Math.min(temparr[currindex][0], currval);
                currval -= decreased;
                temparr[currindex][0] -= decreased;
                eachcowvalue[i] += decreased * temparr[currindex][1];
                if (temparr[currindex][0] <= 0) currindex--;
            }
        }
        int rentindex = rent.length-1;
        for (int i = 0; i < eachcowvalue.length; i++) {
            if (rentindex >= 0 && rent[rentindex] > eachcowvalue[i]) {
                eachcowvalue[i] = rent[rentindex];
                rentindex--;
            }
        }
        long total = 0;
        for (long i: eachcowvalue) {
            total += i;
        }
        outfile.println(total);
        file.close();
        outfile.close();
    }
}