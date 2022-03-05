import java.util.*;
import java.io.*;
public class haybales {
    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("haybales.in"));
        PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        
        StringTokenizer st1 = new StringTokenizer(file.readLine());
        int numhaybales = Integer.parseInt(st1.nextToken());
        int queries = Integer.parseInt(st1.nextToken());
        int[] places = new int[numhaybales];
        st1 = new StringTokenizer(file.readLine());
        for (int i = 0; i < numhaybales; i++) {
            places[i] = Integer.parseInt(st1.nextToken());
        }
        Arrays.sort(places);
        for (int i = 0; i < queries; i++) {
            st1 = new StringTokenizer(file.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int aindex = 0;
            int bindex = 0;
            if (a > places[places.length-1]) {
                aindex = places.length;
            }
            else {
                aindex = BinarySearchv2(places, a);
            }
            if (b > places[places.length-1]) {
                bindex = places.length;
            }
            else {
                bindex = BinarySearch(places, b);
            }
            outfile.println(bindex-aindex);
        }
        file.close();
        outfile.close();
    }
    public static int BinarySearch(int[] arr, int target) {
        if (target >= arr[arr.length-1]) {
            return arr.length;
        }
        
        int l = 0;
        int r = arr.length-1;
        while (r-l> 1) {
            int mid = (r+l)/2;
            if (arr[mid] <= target) { //rounds it up or down
                l = mid;
            }
            else {
                r = mid;
            }
        }
        return r;
    }
    public static int BinarySearchv2(int[] arr, int target) {
        if (target >= arr[arr.length-1]) {
            return arr.length;
        }
        if (target < arr[0]) {
            return 0;
        }
        int l = 0;
        int r = arr.length-1;
        while (r-l> 1) {
            int mid = (r+l)/2;
            if (arr[mid] <= target) { //rounds it up or down
                l = mid;
            }
            else {
                r = mid;
            }
        }
        if (arr[l] != target) {
            return l+1;
        }
        return l;
    }
}