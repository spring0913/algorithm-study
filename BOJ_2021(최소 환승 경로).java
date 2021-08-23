import java.util.*;
import java.io.*;

public class Main {

    private static LinkedList<Integer>[] lineList, stationList;
    private static int N, L;
    
    public static int bfs(int start, int end) {
        
        Queue<int[]> q = new LinkedList<>();
        boolean[] visitedLine = new boolean[L+1];
        
        q.offer(new int[]{start, 0});
        
        while(!q.isEmpty()) {
            int station = q.peek()[0];
            int cost = q.peek()[1];
            q.poll();
            
            if(station == end) {
                return cost;
            }
            
            for(int i=0; i<stationList[station].size(); i++) {
                int line = stationList[station].get(i);
            
                // 방문한 노선이 아니면 해당 노선의 역들을 탐색
                if(!visitedLine[line]) {
                    for(int j=0; j<lineList[line].size(); j++) {
                        visitedLine[line] = true;
                        int nStation = lineList[line].get(j);
                        
                        if(nStation == end) {
                            return cost;
                        }
                        
                        q.offer(new int[] {nStation, cost+1});                      
                    }
                }
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        stationList = new LinkedList[N+1];
        for(int i=1; i<=N; i++) {
            stationList[i] = new LinkedList<>();
        }
        
        lineList = new LinkedList[L+1];
        for(int i=1; i<=L; i++) {
            lineList[i] = new LinkedList<>();
        }
        
        for(int i=1; i<=L; i++)
        {
            st = new StringTokenizer(br.readLine());
            
            while(true) {
                int n = Integer.parseInt(st.nextToken());
                if(n == -1) {
                    break;
                }
                
                lineList[i].add(n);
                stationList[n].add(i);
            }
        }
        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(bfs(start, end));
    }

}
