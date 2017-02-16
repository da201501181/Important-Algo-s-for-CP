    	
    		
    		    /*
    		     * Author- Priyam Vora
    		     * BTech 2nd Year DAIICT
    		     */
    		     
    		            
    		    import java.io.*;
    		    import java.math.*;
    		    import java.util.*;
    		    import javax.print.attribute.SetOfIntegerSyntax;


    		     
    		    public class Practice{
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
    		    
    		    	public static long gcd(long x, long y) {
    		    		if (x == 0)
    		    			return y;
    		    		else
    		    			return gcd( y % x,x);
    		    	}
    		    	public static String reverseString(String s) {
    		    		StringBuilder sb = new StringBuilder(s);
    		    		sb.reverse();
    		    		return (sb.toString());
    		    	}
    		    	
    		    	public static long pow(long n, long p) {
    		    		if(p==0)
    		    			return 1;
    		    		if(p==1)
    		    			return n%mod;
    		    		if(p%2==0){
    		    			long temp=pow(n, p/2);
    		    		return (temp*temp)%mod;
    		    		}else{
    		    			 	long temp=pow(n,p/2);
    		    			 	temp=(temp*temp)%mod;
    		    			 	return(temp*n)%mod;
    		    			 	
    		    		}
    		    	}
    		     
    		    		
    		     
    		    	public static boolean isPrime(int n) {
    		    		// Corner cases
    		    		if (n <= 1)
    		    			return false;
    		    		if (n <= 3)
    		    			return true;
    		     
    		    		// This is checked so that we can skip 
    		    		// middle five numbers in below loop
    		    		if (n % 2 == 0 || n % 3 == 0)
    		    			return false;
    		     
    		    		for (int i = 5; i * i <= n; i = i + 6)
    		    			if (n % i == 0 || n % (i + 2) == 0)
    		    				return false;
    		     
    		    		return true;
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
    		
 //----------------------------------------My Code------------------------------------------------//
    		   static LinkedList<Integer> adj[];
    		   static boolean Visited[]; 
    		   static LinkedList<Integer> topo_sort;
    		   static Pair[] p,ans;
    		   private static void soln() {

    			   int m=nextInt();
    			   HashMap<String ,Integer> essay=new HashMap<String,Integer>();
    			   String s[]=new String[m];
    			   String inp=nextLine();
    			   String a[]=inp.split(" ");
    			   for(int i=0;i<m;i++){
    				   
    				   s[i]=a[i].toLowerCase();
    				   
    				   essay.put(s[i], (i+1));
    			   }
    			   HashMap<String,Integer> dict=new HashMap<String,Integer>();
    			   int n=nextInt();
    			   int node_num=1;
    			 String pairs[]=new String[n];
    			   for(int i=0;i<n;i++){
    				   String in=nextLine().toLowerCase();
    				   pairs[i]=in;
    				   String arr[]=in.split(" ");
    				 // p[i]=new Pair(arr[0], arr[1]);
    				   if(!dict.containsKey(arr[0])){
    					   dict.put(arr[0],node_num);
    					   node_num++;
    				   }
    				   if(!dict.containsKey(arr[1])){
    					   dict.put(arr[1],node_num);
    					   node_num++;
    				   }
    				   
    			   }
    			   buildGraph(node_num);
    			   for(int i=0;i<n;i++){
    				   String arr[]=pairs[i].split(" ");
    				   
    				   int from_node=dict.get(arr[0]);
    				   int to_node=dict.get(arr[1]);
    				   adj[from_node].add(to_node);
    			   }
    		//	   pw.println(node_num);
    			   topo(node_num);
    			   p=new Pair[node_num];
    			   ans=new Pair[node_num];
    			   for(String x:dict.keySet()){
    				   int nr=0;
    				   for(int i=0;i<x.length();i++){
    					   if(x.charAt(i)=='r')
    						   nr++;
    				   }
    				   p[dict.get(x)]=new Pair(x.length(), nr);
    			   ans[dict.get(x)]=new Pair(x.length(),nr);
    			   pw.println(x+" "+dict.get(x));
    			   }
    			   pw.println(topo_sort);
    			   p[0]=new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
    			   ans[0]=new Pair(Integer.MAX_VALUE,Integer.MAX_VALUE);
    			   for(int i=0;i<topo_sort.size();i++){
    				   if(!Visited[topo_sort.get(i)]){
    					   dfs(topo_sort.get(i),0);
    					//   pw.println(topo_sort.get(i));
    				   Visited=new boolean[node_num];
    				   }
    			   }

    			   long len=0,n_r=0;
    			   for(int i=0;i<m;i++){
    				   if(dict.containsKey(s[i])){
    					   len+=ans[dict.get(s[i])].len;
    					   n_r+=ans[dict.get(s[i])].no_r;
    				   }else{
    					   for(int j=0;j<s[i].length();j++){
    						   if(s[i].charAt(j)=='r')
    							   n_r++;
    					   }
    					   len+=s[i].length();
    				   }
    			   }
    		   pw.println(n_r+" "+len);
    			   
//-----------------------------------------The End--------------------------------------------------------------------------//
    		    
    		    }
    		   private static void dfs(int curr,int par){
    			   Visited[curr]=true;
    			   for(int i=0;i<adj[curr].size();i++){
    				   if(!Visited[adj[curr].get(i)]){
    					   dfs(adj[curr].get(i),curr);
    				   }
    			   }
    			   //System.out.println(0);
    			   int par_r=ans[par].no_r;
    			   
    			   int cur_r=ans[curr].no_r;
    			//   pw.println(curr+" "+ans[curr].len+" "+par+" "+ans[par].len);
    			   if(par_r<cur_r){
    				   
    			   }else if(par_r>cur_r){
    				   ans[par].len=ans[curr].len;
    				   ans[par].no_r=ans[curr].no_r;
    			   }else{
    				   if(ans[par].len>ans[curr].len){
    					   ans[par].len=ans[curr].len;
    				   }
    			   }
    			   
    		   }
    		   private static void topo(int n){
    			   n--;
    			   topo_sort=new LinkedList<Integer>();
    				Queue<Integer> q=new LinkedList<Integer>();
        		    
    			   	int indeg[]=new int[n+1];
    		    	int no_vis=0;
    		    	for(int i=1;i<=n;i++){
    		    		for(int j=0;j<adj[i].size();j++){
    		    			
    		    			indeg[adj[i].get(j)]++;
    		    		}
    		    	}
    		    	for(int i=1;i<=n;i++){
    		    		if(indeg[i]==0)
    		    		q.add(i);
    		    	}
    		    	while(!q.isEmpty()){
    		    		int v=q.poll();
    		    	
    		    		no_vis++;
    		    		topo_sort.add(v);
    		    		//pw.print(v+" ");
    		    		for(int i=0;i<adj[v].size();i++){
    		    			indeg[adj[v].get(i)]--;
    		    			if(indeg[adj[v].get(i)]==0)
    		    				q.add(adj[v].get(i));
    		    		}
    		    	}
    		    	
    		   }
    		    	private static void buildGraph(int n){
    		    		adj=new LinkedList[n];
    		    		Visited=new boolean[n];
    		    		for(int i=0;i<n;i++){
    		    			adj[i]=new LinkedList<Integer>();
    		    		}
    		    		
    		    	}
    		    }	
    		   
    		    
    			class Pair implements Comparable<Pair>{
    			   
    	
    				int len,no_r;
    				Pair(int len,int no_r){
    				this.len=len;
    				this.no_r=no_r;
    				}
					@Override
					public int compareTo(Pair o) {
						// TODO Auto-generated method stub
						return 0;
					}
    	
    		/*		public int hashCode() {
    	    			int hu = (int) (type ^ (type >>> 32));
    	    			int hv = (int) (height ^ (height >>> 32));
    	    			int hw = (int) (mass ^ (mass >>> 32));
    	    			return 31 * hu + hv + hw;
    	    		}
    				public boolean equals(Object o) {
    	    			Pair other = (Pair) o;
    	    			return type == other.type && height == other.height && mass == other.mass;
    	    		}*/
    			
    			}
    		        class Graph{
    		        	private static int V,level[][],count=-1,lev_dfs[],degree=0,no_vert_conn_comp=0;
    		        	private Stack <Integer>st=new Stack();
    		        	private static LinkedList<Integer > adj[];
    		        	private boolean[][] Visite;
    		        	private static boolean [] Visited;
    		        	private static HashSet<Integer> Vis=new HashSet();
    		        	private static Stack<Integer> topo_sort=new Stack<>();
    		        	private static HashMap<String,Integer> hm=new HashMap<>();
    		  private static HashSet<String> hs=new HashSet();
    		private static HashSet<String > exist=new HashSet();
    		private static int a[];

    		
    		    Graph(int V){
    		    V++;
    		    this.V=(V);
    		    adj=new LinkedList[V];
    		    Visite=new boolean[100][100];
    		    
    	 	    Visited=new boolean[V];
    		    level=new int[100][100];
    		    lev_dfs=new int[V]; 
    		   for(int i=0;i<V;i++)
    			   adj[i]=new LinkedList<Integer>();
    		a=new int[V+1];
    		    }
    		   
    		    void setup(int d[]){
  
    		    	a=Arrays.copyOf(d, d.length);
    		    	
    		    }
    		    void ans(){
    		    	StringBuilder sb=new StringBuilder();
    		    	for(int i=1;i<a.length;i++){
    		    		sb.append(a[i]+" ");
    		    	}
    		    	System.out.println(sb);
    		    }
    		   void addEdge(int v,int w){
    		    
    		    	if(adj[v]==null){
    		    		adj[v]=new LinkedList();
    		    	}
    		    	adj[v].add(w);
    		    
    		    	
    		    
    		    	  	
    		    }
    		  
    		    public static int BFS2(int startVert){
    		    	Visited=new boolean[V];
    		    	for(int i=1;i<V;i++){
    		    		lev_dfs[i]=-1;
    		    	}
    		    //	System.out.println(startVert);
    		    	Queue<Integer> q=new LinkedList<Integer>();
    		    	q.add(startVert);
    		    	
    		    	lev_dfs[startVert]=0;
    		    	while(!q.isEmpty()){
    		    		int top=q.poll();
    		    		
    		    		Iterator<Integer> i= adj[top].listIterator();
    		    		while(i.hasNext()){
    		    			int n=i.next();
    		    	//		System.out.println(top+" "+n);
    		    			if(!Visited[n]){
    		    				q.add(n);
    		    				Visited[n]=true;
    		    				lev_dfs[n]=lev_dfs[top]+1;
    		    			
    		    			}
    		    		}
    		    	}
    		    
    		    //	q.clear();
    		    	return -1;
    		    }
    		    public static int getAn(){
    		    	if(ans==Long.MAX_VALUE)
    		    		ans=-1;
    		    	return 0;
    		    }
    		    public int getEd(){
    		    	return degree/2;
    		    }
    		    public void get(int from,int to){
    		    	int h=lev_dfs[from]-lev_dfs[to];
    		    	if(h<=0){
    		    		System.out.println(-1);
    		    	}else{
    		    		System.out.println(h-1);
    		    	}
    		    }
    		    private static boolean check(int x,int y,char c[][]){
    			
    				if((x>=0 && y>=0) && (x<c.length && y<c[0].length) && c[x][y]!='#'){
    					
    					return true;
    				}
    				return false;
    			}
    		    public int BFS(int x,int y,int k,char[][] c)
    		    {
    		    	 LinkedList<Pair> queue = new LinkedList<Pair>();
    		        //Visited[s]=true;
    		   //     queue.add(new Pair(x,y));
    		       int count=0;
    		   level[x][y]=-1;
    		   c[x][y]='M';
    		        while (!queue.isEmpty())
    		        {
    		            Pair temp = queue.poll();
    		        
    		            
    		        }
    		        return V;
    		    }
    		    static long ans=Long.MAX_VALUE;
    		    
    		   public static void dfs2(int startVert,int endVert,long need){
    			Visited[startVert]=true;
    			//System.out.println(startVert+" "+need);
    			   for(int x:adj[startVert]){
    				if(x==endVert ){
    					String to="";
    					to+=startVert+" "+x;
    					
    					if(hs.contains(to) && startVert!=x)
    					ans=Math.min(need+1, ans);
    					else
    						ans=Math.min(need, ans);
    				}
    				else{
    					String to="";
    					to+=startVert+" "+x;
    					boolean tmp=false;
    					if(hs.contains(to) )
    						tmp=true;
    					if(!Visited[x])
    					{
    					if(tmp)
    						dfs2(x, endVert, need+1);
    					else{
    						dfs2(x, endVert, need);
    					}
    					}
    					}
    			}
    			   
    			   
    		   }
    		     public long dfs(int startVertex){
    		    //	 getAns(startVertex);
    		    	 if(!Visited[startVertex])  {
    		    	
    		   return dfsUtil(startVertex);
    		  
    		    	//return getAns();
    		    	}
    		    	 
    		    
    		     
    		    return 0;
    		    	}
    		 private long dfsUtil(int startVertex) {//0-Blue 1-Pink
    			 int c=1;
    		TreeSet<Integer> index=new TreeSet();
    		TreeSet<Integer> ele=new TreeSet(Collections.reverseOrder());
    			 long cout=0;
    		      degree=0;
    		    	Visited[startVertex]=true;
    		    	lev_dfs[startVertex]=0;
    		      st.push(startVertex);
    		      int temp=-1;
    		    	    while(!st.isEmpty()){
    		    	
    		    	int top=st.pop();
    		    //	ts.add(top);
    		    	Iterator<Integer> i=adj[top].listIterator();

      		      index.add(top);
      		      ele.add(a[top]);
    		    while(i.hasNext()){
    		    //	System.out.println(top);
    		    	int n=i.next();
    		     if( !Visited[n]){
    		    				Visited[n]=true;
    		    				st.push(n);
    		    c++;
    		     }
    		         }
    		 }
    		    	   for(int x:index){
    		    		   a[x]=ele.first();
    		    		   ele.remove(ele.first());
    		    	  }
    		 
    		 //   System.out.println(temp);
    		    return 0;
    		    }
    		        
    		        }     
    		       	        
    		    class Dsu{
    		    	private int rank[], parent[] ,n;
    		    	private static int[] parent1;
    		    	Dsu(int size){
    		    		this.n=size+1;
    		    		rank=new int[n];
    		    		//parent=new int[n];
    		    		parent=new int[n];
    		    	makeSet();
    		
    		    	}
    		    	
    		    	void makeSet(){
    		    		for(int i=0;i<n;i++){
    		    			parent[i]=i;
    		    		}
    		    	}
    		    	
    		    	int find(int x){
    		    		if(parent[x]!=x){
    		    			
    		    			parent[x]=find(parent[x]);
    		    		}
    		    		return parent[x];
    		    	}
    		    
    		    	
    		    	void union(int x,int y){
    		    		int xRoot=find(x);
    		    		int yRoot=find(y);
    		    		
    		    		if(xRoot==yRoot)
    		    			return;
    		    		if(rank[xRoot]<rank[yRoot]){
    		    		parent[xRoot]=yRoot;	
    		    		}else if(rank[yRoot]<rank[xRoot]){
    		    			parent[yRoot]=xRoot;
    		    		}else{
    		    			parent[yRoot]=xRoot;
    		    			rank[xRoot]++;
    		    		}
    		    		
    		    	}

    		     
    		    }
    			