import java.io.BufferedReader;
import java.io.InputStreamReader;


public class LegoExt {

	static int MOD = 1000000007;
	public static long power( long num, int p) {
		 
		  if(p == 1) return num;
		 
		   long number = num;
		  for(int i=2;i<=p;i++) {
		    num *= number;
		    num %= MOD;
		  }
		  return num;
		}
	public static void main(String[] args) throws Exception {
		 
		  int N, M;
		 
		  long[] T = new long[1001];
		  long[] S = new long[1001];
		  long[] P = new long[1001];
		 
		  T[0] = T[1] = 1;
		  T[2] = 2 ;
		  T[3] =  4;
		  T[4] = 8 ;
		 
		  P[0] = P[1] = 1;
		 
		  
		 
		  for(int i=5;i<=1000;i++)
		  {
		    T[i] = (T[i-1] + T[i-2] + T[i-3] + T[i-4])%MOD;
		  }
		 
		  S[0] = 1;
		  S[1] = 1;
		 
		  long sum;
		  int Tt;
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          String line = br.readLine();
           Tt = Integer.parseInt(line);
		 
		  for(int t=0;t<Tt;t++) {
			  line = br.readLine();
              String[] inputStr = line.split(" ");
               N = Integer.parseInt(inputStr[0]);
               M = Integer.parseInt(inputStr[1]);
		 
		    for(int i=0;i<=M;i++) //find all the possible ways of building height N
		      {P[i] =  power(T[i],N);
		    	//System.out.println("P["+i+"]="+P[i]);
		      }
		    for(int i=2;i<=M;i++) {
		      sum = 0;
		      for(int j=1;j<i;j++) {
			sum += (S[j]*P[i-j])%MOD;
		      }
		      
		      sum %= MOD;
		      S[i] = (P[i] - sum);
		      S[i] = S[i]%MOD;
		    }
		    if (S[M] < 0)
		      S[M] += MOD;
		    System.out.println( S[M] );
		  }
		 
		 
		}
	
}
