    	
    		
    		    /*
    		     * Author- Priyam Vora
    		     * BTech 2nd Year DAIICT
    		     */
    		     
    		            
    		    import java.io.*;
    		    import java.math.*;
    		    import java.util.*;
    		    import javax.print.attribute.SetOfIntegerSyntax;


    		     
    		    public class BITmodify{
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
    		        static long[][]bit;
    		        //----------------------------------------My Code------------------------------------------------//
    		           		   private static void soln() {

    		           			   
    		           			   n=nextInt();
    		           			   int q=nextInt();
    		           			   
    		           			   String s=nextLine();
    		           			   char c[]=new char[s.length()];
    		           			   c=s.toCharArray();
    		           			   bit=new long[n+1][26];
    		           			 MakeBIT(c);

    		           			 for(int i=0;i<=n;i++){
    		           				 pArray(bit[i]);
    		           			 }
    		           			 while(q-->0){
    		           				String string=nextLine();
    		           				String arr[]=string.split(" ");
    		           				 int op=Integer.parseInt(arr[0]);
    		           				 if(op==1){
    		           					
    		           					 Update(arr[2].charAt(0), Integer.parseInt(arr[1]), c);
    		           				 }else{

    		           					 int l=Integer.parseInt(arr[1]);
    		           					 int r=Integer.parseInt(arr[2]);
    		           					 l--;
    		           					
    		           					 long freq[]=getSum(l,r);
    		           					int flag=1;
    		           					 if((r-l)%2==0){
    		           					for(int i=0;i<26;i++){
    		           						if(freq[i]%2!=0){
    		           							pw.println("NO");
    		           					
    		           							flag=0;
    		           							break;
    		           						}
    		           					}
    		           					}else{
    		           						int count =0;
    		           						for(int i=0;i<26;i++){
        		           						if(freq[i]%2!=0){
        		           					//		pw.println("NO");
        		           					count++;
        		           					if(count>1)
        		           							{
       		           						pw.println("NO");
            		           					flag=0;
        		           							break;}
        		           						}
        		           					}		
    		           						}
    		           					 
    		           					 if(flag==1){
    		           						 pw.println("YES");
    		           					 }
    		           					}
    		           			 }
    		           			 
//-----------------------------------------The End--------------------------------------------------------------------------//
    		           		    
    		           		    }
    		           		   private static void MakeBIT(char[] c){
    		           			   for(int i=0;i<n;i++){
    		           				   int index=(i+1);
    		           				while(index<=n){
    	    		           			 bit[index][c[i]-'a']++;
    	       		           		  
    	    		           			 index=getNext(index);   
    	    		           		   }
    		           			   }
    		           		   }
    		           		   private static void Update(char ch,int index,char c[]){
    		           			  int temp=index;
    		           		   while(index<=n){
    		           			 bit[index][ch-'a']++;
       		           		  bit[index][c[temp-1]-'a']--;
    		           			 index=getNext(index);   
    		           		   }
    		           		   c[temp-1]=ch;
    		           		  
    		           		   }
    		           		   private static long[] getSum(int l,int r){
    		           			  long freq[]=new long[26];
    		           			 while(r>0){
    		           				 for(int i=0;i<26;i++){
    		           					 freq[i]+=bit[r][i];
    		           				 }
    		           				 r=getParent(r);
    		           			 }
    		           			 while(l>0){
    		           				 for(int i=0;i<26;i++){
    		           					 freq[i]-=bit[l][i];
    		           				 }
    		           				 l=getParent(l);
    		           			 }
    		           			 
    		           			 	return freq;
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
    		   
    		    
    			