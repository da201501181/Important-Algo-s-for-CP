    	
    		
    		    /*
    		     * Author- Priyam Vora
    		     * BTech 2nd Year DAIICT
    		     */
    		     
/* 
 Eulerian Path is a path in graph that visits every edge exactly once.
 Eulerian Circuit is an Eulerian Path which starts and ends on the same vertex.
 Steps to check Euleran circuit or Path
 1) All vertices with non-zero degree are connected. 
 We don’t care about vertices with zero degree because they don’t belong to Eulerian Cycle or Path 
 2) For Eulerian Path-  If zero or two vertices have odd degree and all other vertices have even degree
 For Eulerian Cycle- All vertices have even degree
 */    		            
    		    import java.io.*;
    		    import java.math.*;
    		    import java.util.*;
    		    import javax.print.attribute.SetOfIntegerSyntax;


    		     
    		    public class Euler_Circuit{
    		    	private static InputStream stream;
    		    	private static byte[] buf = new byte[1024];
    		    	private static int curChar;
    		    	private static int numChars;
    		    	private static SpaceCharFilter filter;
    		    	private static PrintWriter pw;
    		     
    		        public static void main(String[] args) {
    		        	InputReader(System.in);
    		    		pw = new PrintWriter(System.out); 
    		            new Thread(null ,new Runnable(){
    		               public void run(){
    		                   try{
    		                       soln();
    		                       pw.close();
    		                   } catch(Exception e){
    		                       e.printStackTrace();
    		                   }
    		               }
    		           },"1",1<<26).start();
    		       }
    		    
    		    	
    		
 //----------------------------------------My Code------------------------------------------------//
    		   static LinkedList<Integer> adj[];
    		   static boolean Visited[];
    		   static int deg[];
    		   static int V;
    		   private static void soln() {
   
    			   
    			    V=nextInt();
    			   int m=nextInt();
    			   buildGraph();
    			   for(int i=0;i<m;i++){
    				   int v=nextInt();
    				   int w=nextInt();
    				   adj[v].add(w);
    				   adj[w].add(v);
    				   deg[v]++;
    				   deg[w]++;
    			   }
    			   
    			   if(isConnected() && isEulerian()){
    				   pw.println("YES");
    			   }else{
    				   pw.println("NO");
    			   }
   		    
    		    }
     //Check if condition-2 is satisfied
    		   private static boolean isEulerian(){
    			   int odd=0;
    			   for(int i=1;i<=V;i++){
    				   if(adj[i].size()%2!=0){
    					   odd++;
    				   }
    			   }
    			   if(odd==0 || odd==2){
    				   return true;
    			   }
    			   return false;
    		   }
    // Check if the Graph is connected
    		   private static boolean isConnected(){
    			   int flag=1;
    			   for(int i=1;i<=V;i++){
    				   if(adj[i].size()>0){ // dfs from vertex with first non zero degree
    					   dfs(i);
    					   flag=0;
    					   break;
    				   }
    			   }
    			   if(flag==1){
    				   return true;
    			   }
    			   for(int i=1;i<=V;i++){
    				   if(adj[i].size()>0 && Visited[i]==false)//check if every vertex with non zero degree is connected
    					   return false;
    			   }
    			return true;
    		   }
    	//Mark all the connected vertices true
    		   private static void dfs(int curr){
    			   Visited[curr]=true;
    			   for(int x:adj[curr]){
    				   if(!Visited[x])
    					   dfs(x);
    			   }
    		   }
    		   
    		   private static void buildGraph(){
		    		
    			   adj=new LinkedList[V+1];
		    		Visited=new boolean[V+1];
		    		deg=new int[V+1];
		    		for(int i=0;i<=V;i++){
		    			adj[i]=new LinkedList<Integer>();
		    		}
		    		
		    	}
 //-----------------------------------------The End--------------------------------------------------------------------------//
    		   
    		   
    		// To Get Input
		    	// Some Buffer Methods
		     
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
