import java.util.*;

class Solution {
    
    private final int[] dh = {-1, 0, 1, 0};
    private final int[] dw = {0, 1, 0, -1};
    
    public int[] solution(int m, int n, int[][] picture) {
        boolean[][] visited = new boolean[m][n];
        int area = 0;
        int max = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(picture[i][j] != 0 && !visited[i][j]){
                    area++;
                    max = Math.max(max, bfs(picture, visited, i, j));
                }
            }
        }
        return new int[]{area, max};
    }
    
    public int bfs(int[][] picture, boolean[][] visited, int m, int n){
        Queue<int[]> q = new LinkedList<>();
        
        int count = 1;
        q.offer(new int[]{m, n});
        visited[m][n] = true;
        
        while(!q.isEmpty()){
            int h = q.peek()[0];
            int w = q.peek()[1];
            q.poll();
            
            for(int i=0; i<4; i++){
                int nh = h + dh[i];
                int nw = w + dw[i];
                
                if(nh >= 0 && nh < picture.length && nw >= 0 && nw < picture[0].length){
                    if(!visited[nh][nw] && picture[nh][nw] == picture[m][n]){
                        visited[nh][nw] = true;
                        count++;
                        q.offer(new int[]{nh, nw});
                    }
                }
            }
        }
        return count;
    }
}