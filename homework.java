import java.util.*;
import java.io.*;
public class homework {
    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("homework.in"));
        PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
        int n = Integer.parseInt(file.readLine());
        StringTokenizer st1 = new StringTokenizer(file.readLine());
        int[] storage = new int[n];
        int totalsum = 0;
        for (int i = 0; i < n; i++) {
            storage[i] = Integer.parseInt(st1.nextToken());
            totalsum += storage[i];
        }
        HashMap<Double, ArrayList<Integer>> sums = new HashMap<>();
        int value = 0;
        int[] prefix = new int[n];
        for (int i = 0; i < storage.length; i++) {
            value += storage[i];
            prefix[i] = value;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(storage[n-1]);
        for (int i = n-2; i >= 1; i--) {
            heap.add(storage[i]);
            double average = (double)(totalsum-prefix[i-1]-heap.peek())/(n-i-1);
            if (sums.containsKey(average)) sums.get(average).add(i);
            else {sums.put(average, new ArrayList<>()); sums.get(average).add(i);}
        }
        double max = 0;
        for (double i: sums.keySet()) {
            max = Math.max(i, max);
        }
        Collections.sort(sums.get(max));
        for (int i: sums.get(max)) {
            outfile.println(i);
        }
        file.close();
        outfile.close();
    }
}