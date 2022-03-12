import java.util.*;
import java.io.*;
public class highcard {
    public static int leastGreatest(ArrayList<Integer> arr, int target) {
        int l = 0;
        int r = arr.size()-1;
        while (r-l > 1) {
            int mid = l + (r-l)/2;
            if (arr.get(mid) <= target) {
                l = mid;
            }
            else {
                r = mid;
            }
        }
        if (target < arr.get(0)) return l;
        if (target > arr.get(arr.size()-1)) return -1;
        return r;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("highcard.in"));
        PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
        int n = Integer.parseInt(file.readLine());
        ArrayList<Integer> Elsie = new ArrayList<>();
        ArrayList<Integer> Bessie = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Elsie.add(Integer.parseInt(file.readLine()));
        }
        Collections.sort(Elsie);
        int counter = 0;
        for (int i = 1; i <= 2*n; i++) {
            if (counter < Elsie.size() && i == Elsie.get(counter)) {
                counter++;
            }
            else {
                Bessie.add(i);
            }
        }
        int points = 0;
        for (int i = 0; i < Elsie.size(); i++) {
            int var = leastGreatest(Bessie, Elsie.get(i));
            if (var != -1) {
                points++;
                Bessie.remove(var);
            }
        }
        outfile.println(points);
        file.close();
        outfile.close();
    }
}