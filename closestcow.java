import java.io.*;
import java.util.*;
public class closestcow {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
    
        StringTokenizer st = new StringTokenizer(r.readLine());
        int numpatches = Integer.parseInt(st.nextToken());
        int numnhojcows = Integer.parseInt(st.nextToken());
        int johncows = Integer.parseInt(st.nextToken());
        long[][] grasspatch = new long[numpatches][4];
        for (int i = 0; i < numpatches; i++) {
            st = new StringTokenizer(r.readLine());
            grasspatch[i][0] = Integer.parseInt(st.nextToken());
            grasspatch[i][1] = Integer.parseInt(st.nextToken());
        }
        int[] nhojcows = new int[numnhojcows];
        for (int i = 0; i < numnhojcows; i++) nhojcows[i] = Integer.parseInt(r.readLine());
        Arrays.sort(grasspatch, Comparator.comparingDouble(o -> o[0]));
        Arrays.sort(nhojcows);
        int index = 0;
        for (int i = 0; i < grasspatch.length; i++) {
            while (index+1 < numnhojcows && Math.abs(nhojcows[index+1]-grasspatch[i][0]) < Math.abs(nhojcows[index]-grasspatch[i][0])) index++;
            long diff = Math.abs(nhojcows[index]-grasspatch[i][0]);
            grasspatch[i][2] = grasspatch[i][0]-diff;
            grasspatch[i][3] = grasspatch[i][0]+diff;
        }
        ArrayList<Long> answers = new ArrayList<>();
        Triplet curr = new Triplet(grasspatch[0][2], grasspatch[0][3], grasspatch[0][1]);
        for (int i = 0; i < grasspatch.length-1; i++) {
            if ((curr.l <= grasspatch[i+1][2] && grasspatch[i+1][2] < curr.r)|| (grasspatch[i+1][2] <= curr.l && curr.l < grasspatch[i+1][3])) {
                curr = new Triplet(Math.max(curr.l, grasspatch[i+1][2]), Math.min(curr.r, grasspatch[i+1][3]), curr.val+grasspatch[i+1][1]);
            }
            else {
                answers.add(curr.val);
                curr = new Triplet(grasspatch[i+1][2], grasspatch[i+1][3], grasspatch[i+1][1]);
            }
        }
        answers.add(curr.val);
        Collections.sort(answers, Collections.reverseOrder());
        long sum = 0;
        for (int i = 0; i < Math.min(johncows, answers.size()); i++) sum += answers.get(i);
        pw.println(sum);
        r.close();
        pw.close();
    }
}
class Triplet {long l;long r;long val; public Triplet(long al,long ar,long aval){l=al;r=ar;val=aval;}}