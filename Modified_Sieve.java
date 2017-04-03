/*	Author - Priyam Vora
 * 	BTech 2nd Year DAIICT		
 */
			import java.io.*;
			import java.*;
			import java.util.*;
				
/*
 *  Create an boolean array for to identify number as prime. In First loop as all even
 *  Numbers except 2 are non prime so mark them as non prime and store the SPF(smallest prime factor)
 *  as 2. Then use a General sieve approach to mark all multiples of a prime as non prime and
 *  store SPF as that prime. Using this factorization can be done in O(log n)				
 */
				public class Modified_Sieve {
					private static InputStream stream;
					private static byte[] buf = new byte[1024];
					private static int curChar;
					private static int numChars;
					private static SpaceCharFilter filter;
					private static PrintWriter pw;
					private static long mod=1000000009;
				
				    public static void main(String[] args) {
				    	InputReader(System.in);
						pw = new PrintWriter(System.out); 
				        new Thread(null ,new Runnable(){
				           public void run(){
				               try{
				                   solve();//This is solution method
				                   pw.close();
				               } catch(Exception e){
				                   e.printStackTrace();
				               }
				           }
				       },"1",1<<26).start();
				   }
				    
				    
				    
				    private static void solve() throws NumberFormatException, IOException{
				    	

				    	boolean prime[]=new boolean[10000001];
				    	Arrays.fill(prime, true);
				    	int spf[]=new int[10000001];
				    	for(int i=2;i<=10000000;i+=2)
				    	{
				    		if(i!=2)
				    			prime[i]=false;
				    		spf[i]=2;
				    	}
				    	for(int j=3;j*j<=10000000;j+=2){
				    		
				    		if(prime[j]){
				    			spf[j]=j;
				    			for(int i=2;i*j<=10000000;i++){
				    				if(prime[i*j]){
				    				prime[i*j]=false;
				    				spf[i*j]=j;}
				    			}
				    		}
				    	}
				    	
				    //Factorization
				    	pw.print(1);
				    	int num=10;
				    	while(num>1){
				    		pw.print(" x "+spf[num]);
				    		num/=spf[num];
				    	}
				    	pw.println();
				    	
				    	//Output
				    	//1 x 2 x 5

				    	pw.close();
				}
				   

					
					public static void InputReader(InputStream stream1) {
						stream = stream1;
					}
				 
					private static boolean isWhitespace(int c) {
						return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
					}
				 
					private static boolean isEndOfLine(int c) {
						return c == '\n' || c == '\r' || c == -1;
					}
				 
					private static int read() {
						if (numChars == -1)
							throw new InputMismatchException();
						if (curChar >= numChars) {
							curChar = 0;
							try {
								numChars = stream.read(buf);
							} catch (IOException e) {
								throw new InputMismatchException();
							}
							if (numChars <= 0)
								return -1;
						}
						return buf[curChar++];
					}
				 
					private static int nextInt() {
						int c = read();
						while (isSpaceChar(c))
							c = read();
						int sgn = 1;
						if (c == '-') {
							sgn = -1;
							c = read();
						}
						int res = 0;
						do {
							if (c < '0' || c > '9')
								throw new InputMismatchException();
							res *= 10;
							res += c - '0';
							c = read();
						} while (!isSpaceChar(c));
						return res * sgn;
					}
				 
					private static long nextLong() {
						int c = read();
						while (isSpaceChar(c))
							c = read();
						int sgn = 1;
						if (c == '-') {
							sgn = -1;
							c = read();
						}
						long res = 0;
						do {
							if (c < '0' || c > '9')
								throw new InputMismatchException();
							res *= 10;
							res += c - '0';
							c = read();
						} while (!isSpaceChar(c));
						return res * sgn;
					}
				 
					private static String nextToken() {
						int c = read();
						while (isSpaceChar(c))
							c = read();
						StringBuilder res = new StringBuilder();
						do {
							res.appendCodePoint(c);
							c = read();
						} while (!isSpaceChar(c));
						return res.toString();
					}
				 
					private static String nextLine() {
						int c = read();
						while (isSpaceChar(c))
							c = read();
						StringBuilder res = new StringBuilder();
						do {
							res.appendCodePoint(c);
							c = read();
						} while (!isEndOfLine(c));
						return res.toString();
					}
				 
					private static int[] nextIntArray(int n) {
						int[] arr = new int[n];
						for (int i = 0; i < n; i++) {
							arr[i] = nextInt();
						}
						return arr;
					}
				 
					private static int[][] next2dArray(int n, int m) {
						int[][] arr = new int[n][m];
						for (int i = 0; i < n; i++) {
							for (int j = 0; j < m; j++) {
								arr[i][j] = nextInt();
							}
						}
						return arr;
					}
					private static char[][] nextCharArray(int n,int m){
						char [][]c=new char[n][m];
						for(int i=0;i<n;i++){
							String s=nextLine();
							for(int j=0;j<s.length();j++){
								c[i][j]=s.charAt(j);
							}
						}
						return c;
					}
				 
					private static long[] nextLongArray(int n) {
						long[] arr = new long[n];
						for (int i = 0; i < n; i++) {
							arr[i] = nextLong();
						}
						return arr;
					}
				 
					private static void pArray(int[] arr) {
						for (int i = 0; i < arr.length; i++) {
							pw.print(arr[i] + " ");
						}
						pw.println();
						return;
					}
				 
					private static void pArray(long[] arr) {
						for (int i = 0; i < arr.length; i++) {
							pw.print(arr[i] + " ");
						}
						pw.println();
						return;
					}
				 
					private static void pArray(boolean[] arr) {
						for (int i = 0; i < arr.length; i++) {
							pw.print(arr[i] + " ");
						}
						pw.println();
						return;
					}
				 
					private static boolean isSpaceChar(int c) {
						if (filter != null)
							return filter.isSpaceChar(c);
						return isWhitespace(c);
					}
				 
					private interface SpaceCharFilter {
						public boolean isSpaceChar(int ch);
					}
				
					
				}	
