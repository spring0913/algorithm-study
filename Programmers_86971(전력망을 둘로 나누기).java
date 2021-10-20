import java.util.*;

class Solution {
    
    private ArrayList<Integer>[] wireList;
    
    public int solution(int n, int[][] wires) {
        wireList = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            wireList[i] = new ArrayList<>();
        }
        
        for(int i=0; i<wires.length; i++){
            wireList[wires[i][0]].add(wires[i][1]);
            wireList[wires[i][1]].add(wires[i][0]);
        }
        
        int min = n;
        for(int i=1; i<=n; i++){
            int abs = Math.abs(2*bfs(n, i)-n);
            min = Math.min(min, abs);  
        }
            
        return min;
    }
    
    public int bfs(int n, int visit){
        boolean[] visited = new boolean[n+1];
        visited[visit] = true;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        int count = 1;
        
        while(!q.isEmpty()){
            int next = q.poll();
            for(int i=0; i<wireList[next].size(); i++){
                int num = wireList[next].get(i);
                if(!visited[num]){
                    q.offer(num);
                    visited[num] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}