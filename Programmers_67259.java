import java.util.*;

class Road{
	int h;
	int w;
	int cost;
	int dir;
	
	public Road(int h, int w, int cost, int dir) {
		this.h = h;
		this.w = w;
		this.cost = cost;
		this.dir = dir;
	}
}

class Solution {
    
    private int dh[] = {-1, 0, 1, 0};
	private int dw[] = {0, 1, 0, -1};
	private final int ALL = -1, U = 0, R = 1, D = 2, L = 3;
    private int answer = Integer.MAX_VALUE;
	
	public int calculateCost(int[][] map, Road start) {
		int n = map.length;
		
		Queue<Road> q = new LinkedList<>();
		
		q.offer(start);
		map[0][0] = 100;
		
		while(!q.isEmpty()) {
			int h = q.peek().h;
			int w = q.peek().w;
			int cost = q.peek().cost;
			int dir = q.peek().dir;
			q.poll();

			for(int i=0; i<4; i++) {
				int nh = h+dh[i];
				int nw = w+dw[i];
				
				if(nh >=0 && nh < n && nw >=0 && nw < n && map[nh][nw] != 1) {
					int nCost = 0;
					if(dir == i || dir == ALL) {
						nCost = cost + 100;
					}else {
						nCost = cost + 600;
					}
					if(map[nh][nw] == 0 || map[nh][nw] >= nCost) {
						q.offer(new Road(nh, nw, nCost, i));
						map[nh][nw] = nCost;
					}
				}
			}
		}
        
        return map[n-1][n-1];
	}
    
    public int solution(int[][] board) {
        int n = board.length;
        
		return calculateCost(board, new Road(0,0,0,ALL));
    }
}