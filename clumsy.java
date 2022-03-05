import java.util.*;
import java.io.*;
public class clumsy {
    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader("clumsy.in"));
        PrintWriter outfile = new PrintWriter(new BufferedWriter(new FileWriter("clumsy.out")));
        String s = file.readLine();
        Stack<Character> stacc = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stacc.push(s.charAt(i));
            }
            else if (s.charAt(i) == ')' && !stacc.empty() && stacc.peek() == '(') {
                stacc.pop();
            }
            else {
                stacc.push(s.charAt(i));
            }
        }
        char[] storage = new char[stacc.size()];
        int counter = 0;
        int changes = 0;
        for (char i: stacc) {storage[counter] = i; counter++;}
        for (int i = 0; i < storage.length-1; i++) {
            if (storage[i] == storage[i+1] && (storage[i] == '(' || storage[i] == ')')) {changes++; storage[i] = '8'; storage[i+1] = '8';}
            else if (storage[i] == ')' && storage[i+1] == '(') {changes+=2;}
        }
        outfile.println(changes);
        file.close();
        outfile.close();
    }
}