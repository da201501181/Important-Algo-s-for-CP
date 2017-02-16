    	
    		
    		    /*
    		     * Author- Priyam Vora
    		     * BTech 2nd Year DAIICT
    		     */
    		     
    		            
    		    import java.io.*;
    		    import java.math.*;
    		    import java.util.*;
    		    import javax.print.attribute.SetOfIntegerSyntax;


    		     
    		    public class BITmodify2{
    		    	private static InputStream stream;
    		    	private static byte[] buf = new byte[1024];
    		    	private static int curChar;
    		    	private static int numChars;
    		    	private static SpaceCharFilter filter;
    		    	private static PrintWriter pw;
    		    	private static long count = 0,mod=1000000007;
    		    	private static TreeSet<Integer>ts[]=new TreeSet[200000];
    		    	private static HashSet hs=new HashSet();
    		     
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

    		        static int n;
    		        static long []a;
    		        static long[]bit;
    		        //----------------------------------------My Code------------------------------------------------//
    		           		   private static void soln() {

    		           			   
    		           			   n=nextInt();
    		           			   int q=nextInt();
    		           			   a=nextLongArray(n);
    		           			   bit=new long[n+1];
    		           			 MakeBIT();

    		           			 while(q-->0){
    		           				int op=nextInt();
    		           				 if(op==2){
    		           					
    		           					 Update(nextInt(),nextLong());
    		           				 }else{

    		           					 int l=nextInt();
    		           					 int r=nextInt();
    		           					 l--;
    		           					pw.println(getSum(l, r));
    		           					
    		           					}
    		           			 }
    		           			 
//-----------------------------------------The End--------------------------------------------------------------------------//
    		           		    
    		           		    }
    		           		   private static void MakeBIT(){
    		           			   for(int i=0;i<n;i++){
    		           				   String s=Long.toString(a[i]);
    		           				   if((s.charAt(0)==s.charAt(s.length()-1)) && a[i]>=0){
    		           				   int index=(i+1);
    		           				while(index<=n){
    	    		           			 bit[index]++;
    	       		           		  
    	    		           			 index=getNext(index);   
    	    		           		   }
    		           				   }
    		           			   }
    		           		   }
    		           		   private static void Update(int index, long change){
    		           		int op=0;
    		           		int temp=index;
    		           		if(checkround(a[index-1] ) &&  (!checkround(change)))op=-1;
    		           		else if((!checkround(a[index-1]) && checkround(change)))op=1;
    		           			// pw.println(index+" "+change);
    		           		if(op>0){		
    		           		while(index<=n){
    		           			 bit[index]+=op;
       		           			 index=getNext(index);   
    		           		   }
    		           		}
    		           			a[temp-1]=change;
    		           		   }
    		           		   private static boolean checkround(long num){
    		           			   String s=Long.toString(num);
    		           			   if(s.charAt(0)==s.charAt(s.length()-1) && num>=0)
    		           				   return true;
    		           			   return false;
    		           			   
    		           		   }
    		           		
    		           		   private static long getSum(int l,int r){
    		           			long tot=0;
    		           			   while(r>0){
    		           				 tot+=bit[r];
    		           				 r=getParent(r);
    		           			 }
    		           			 while(l>0){
    		           				tot-=bit[l];
    		           				 l=getParent(l);
    		           			 }
    		           			   return tot;
    		           		   }
    		           		   private static int getNext(int index){
    		           			  return index + (index & -index);
    		           		   }
    		           		   private static int getParent(int index){
    		           			 return index - (index & -index);
    		           		   }
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
    		   
    		    
    			