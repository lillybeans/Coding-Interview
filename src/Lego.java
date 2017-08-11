import java.io.*;
import java.util.*;
public class Lego {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        Scanner scanner = new Scanner(System.in);
        int lines = Integer.parseInt(scanner.nextLine());
        int n,m=0; //for storing height and width
        
        for (int i=0; i<lines; i++)
        {
            String[] line = scanner.nextLine().split(" "); // e.x. '2 2'
            n=Integer.parseInt(line[0]);
            m=Integer.parseInt(line[1]);
            
            System.out.println(calculateWaysToBuild(n,m));
        }
    }
    
    public static int calculateWaysToBuild(int n, int m){
        
        int total_ways = (calculateWaysOneRow(m))^n;
        
    }
    
    public static int calculateWaysOneRow(int m){
        if (m == 1) { return 1; }
        else if (m == 2) { return 2; }
        else if (m == 3) { return 4; }
        else if (m == 4) { return 8; }
        
        //5 and above. e.x. for 5 there are only 4 ways to build it: *xxxx, **xxx, ***xx, ****x, where * = block length, x = rest
        return calculateWaysOneRow(m-1) + calculateWaysOneRow(m-2) + calculateWaysOneRow(m-3) + calculateWaysOneRow(m-4);
    }
    
    public static int calculateInvalidWays(int n, int m){
        
    }
    
}