import java.io.*;
import java.util.*;

public class Main {

	private static int R, C, T;
	private static int dh[] = {-1, 0, 1, 0};
	private static int dw[] = {0, 1, 0, -1};
	private static int answer = 0;
	private static String[] map;
	private static int maxSweetPotato = 0;
	
	public static void dfs(int h, int w, boolean[][] visited, int sweetPotato, int depth) {
		if(depth == T) {
			answer = Math.max(answer, sweetPotato);
			return;
		}
		
		if(sweetPotato == maxSweetPotato) {
			answer = maxSweetPotato;
			return;
		}
		
		for(int i=0; i<4; i++) {
			int nh = h+dh[i];
			int nw = w+dw[i];
			
			if(nh >= 0 && nh < R && nw >= 0 && nw < C) {
				if(map[nh].charAt(nw) == 'S' && !visited[nh][nw]) {
					visited[nh][nw] = true;
					dfs(nh, nw, visited, sweetPotato+1, depth+1);
					visited[nh][nw] = false;
				}else if(map[nh].charAt(nw) != '#') {
					dfs(nh, nw, visited, sweetPotato, depth+1);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new String[R];
		for(int i=0; i<R; i++) {
			map[i] = br.readLine();
		}
		boolean[][] visited = new boolean[R][C];
		int h = 0, w = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i].charAt(j) == 'G') {
					h = i;
					w = j;
				}else if(map[i].charAt(j) == 'S') {
					maxSweetPotato++;
				}
			}
		}
		
		dfs(h, w, visited, 0, 0);
		
		System.out.println(answer);
	}
}
