import java.util.*;
import java.io.*;
public class herding {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("herding.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));
        int n = Integer.parseInt(br.readLine());
        int[] storage = new int[n];
        for (int i = 0; i < n; i++) storage[i] = Integer.parseInt(br.readLine());
        Arrays.sort(storage);
        //calculate min
        int windowcow = 0;
        int l = 0; int r = 0;
        for (l = 0; l < storage.length; l++) {
            while (r+1 < storage.length && storage[r+1] - storage[l] + 1 <= n) r++;
            windowcow = Math.max(windowcow, r-l+1);
        }
        if (storage[n-2]-storage[0]+1 == n-1 && storage[n-1]-storage[n-2]>2 || storage[n-1]-storage[1]+1 == n-1 && storage[1]-storage[0]>2) {
			pw.println(2);//fml
		}
        else pw.println(n-windowcow);
        //calculate max
        ArrayList<Integer> diff = new ArrayList<>();
        for (int i = 0; i < storage.length-1; i++) diff.add(storage[i+1] - storage[i]-1);
        //pw.println(diff);
        pw.println(sum(diff) - Math.min(diff.get(0), diff.get(diff.size()-1)));
        br.close();
        pw.close();
    }
    public static long sum(ArrayList<Integer> arr) {
        long sum = 0;
        for (int i: arr) sum += i;
        return sum;
    }
}