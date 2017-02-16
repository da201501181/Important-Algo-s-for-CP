import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Don Li
 */
public class Main {
    
    int m;
    String[] essay;
    
    int n;
    int cnt = 0;
    Map<String, Integer> map;
    String[] words;
    
    int[][] G;
    int[] nr, len;
    boolean[] vis;
    
    void solve() {
        m = in.nextInt();
        essay = new String[m];
        for (int i = 0; i < m; i++) essay[i] = in.nextToken().toLowerCase();
        
        n = in.nextInt();
        cnt = 0;
        map = new HashMap<>();
        words = new String[2 * n];
        int[] from = new int[n], to = new int[n];
        for (int i = 0; i < n; i++) {
            String s = in.nextToken().toLowerCase(), t = in.nextToken().toLowerCase();
            if (!map.containsKey(s)) {
                int id = cnt++;
                map.put(s, id);
                words[id] = s;
            }
            if (!map.containsKey(t)) {
                int id = cnt++;
                map.put(t, id);
                words[id] = t;
            }
            from[i] = map.get(t);
            to[i] = map.get(s);
        }
        
        G = build_graph(cnt, from, to);
        System.out.println(cnt);
        nr = new int[cnt];
        len = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j < words[i].length(); j++) if (words[i].charAt(j) == 'r') nr[i]++;
            len[i] = words[i].length();
        }
        
        Integer[] ids = new Integer[cnt];
        for (int i = 0; i < cnt; i++) ids[i] = i;
        Arrays.sort(ids, (p, q) -> {
            if (nr[p] != nr[q]) return Integer.compare(nr[p], nr[q]);
            return Integer.compare(len[p], len[q]);
        });
        
        vis = new boolean[cnt];
        for (int i = 0; i < cnt; i++) {
            if (!vis[ids[i]]) dfs(ids[i], ids[i]);
        }
        
        long totR = 0, totLen = 0;
        for (String s : essay) {
            int u = map.getOrDefault(s, -1);
            if (u >= 0) {
                totR += nr[u];
                totLen += len[u];
            } else {
                for (int i = 0; i < s.length(); i++) if (s.charAt(i) == 'r') totR++;
                totLen += s.length();
            }
        }
        
        out.printf("%d %d%n", totR, totLen);
    }
    
    void dfs(int u, int anc) {
        vis[u] = true;
        nr[u] = nr[anc];
        len[u] = len[anc];
        for (int v : G[u]) {
            if (!vis[v]) {
                dfs(v, anc);
            }
        }
    }
    
    int[][] build_graph(int n, int[] from, int[] to) {
        int[][] G = new int[n][];
        int[] cnt = new int[n];
        for (int i = 0; i < from.length; i++) {
            cnt[from[i]]++;
        }
        for (int i = 0; i < n; i++) G[i] = new int[cnt[i]];
        for (int i = 0; i < from.length; i++) {
            G[from[i]][--cnt[from[i]]] = to[i];
        }
        return G;
    }
    
    public static void main(String[] args) {
        in = new FastScanner(new BufferedReader(new InputStreamReader(System.in)));
        out = new PrintWriter(System.out);
       new Main().solve();
        out.close();
    }
    
    static FastScanner in;
    static PrintWriter out;
    
    static class FastScanner {
        BufferedReader in;
        StringTokenizer st;
        
        public FastScanner(BufferedReader in) {
            this.in = in;
        }
        
        public String nextToken() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        public int nextInt() {
            return Integer.parseInt(nextToken());
        }
        
        public long nextLong() {
            return Long.parseLong(nextToken());
        }
        
        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
}