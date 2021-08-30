import java.util.*;

class Node implements Comparable<Node>{
    private int num;
    private int cost;
    
    public Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
    
    public int getNum() {
        return num;
    }
    
    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}

class Solution {
    
    private ArrayList<Node>[] graph;
    
    public int[] dijkstra(int n, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] d = new int[n+1];
        Arrays.fill(d, Integer.MAX_VALUE);
        
        pq.offer(new Node(start, 0));
        d[start] = 0;
        
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.getNum();
            int cost = node.getCost();
            
            if(d[now] < cost) {
                continue;
            }
                
            for(int i=0; i<graph[now].size(); i++) {
                int newCost = d[now] + graph[now].get(i).getCost();
                // 현재 노드를 거쳐서 다른 노드로 이동하는 비용이 더 적은 경우
                if(newCost < d[graph[now].get(i).getNum()]) {
                    d[graph[now].get(i).getNum()] = newCost;
                    pq.offer(new Node(graph[now].get(i).getNum(), newCost));
                }
            }
        }
    
        return d;
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = new ArrayList[n+1];
    
        for(int i=0; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0; i<fares.length; i++) {
            graph[fares[i][0]].add(new Node(fares[i][1], fares[i][2]));
            graph[fares[i][1]].add(new Node(fares[i][0], fares[i][2]));
        }
    
        int[] S = dijkstra(n, s);
        int[] A = dijkstra(n, a);
        int[] B = dijkstra(n, b);
        
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++) {
            min = Math.min(min, S[i] + A[i] + B[i]);
        }
        
        return min;
    }
}