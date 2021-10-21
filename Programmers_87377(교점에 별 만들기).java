import java.util.*;

class Solution {
    public String[] solution(int[][] line) {        
        ArrayList<int[]> point = getIntersection(line);
        int minX = point.get(0)[0];
        int minY = point.get(0)[1];
        for(int i=0; i<point.size(); i++){
            minX = Math.min(minX, point.get(i)[0]);
            minY = Math.min(minY, point.get(i)[1]);
        }
        
        int h = 0, w = 0;
        for(int i=0; i<point.size(); i++){
            int x = Math.abs(point.get(i)[0] - minX);
            int y = Math.abs(point.get(i)[1] - minY);
            h = Math.max(h, y);
            w = Math.max(w, x);
            point.set(i, new int[]{x, y});
        }
        
        char[][] arr = new char[++h][++w];
        for(int i=0; i<h; i++){
            Arrays.fill(arr[i], '.'); 
        }
        
        for(int i=0; i<point.size(); i++){
            int x = point.get(i)[0];
            int y = point.get(i)[1];
            arr[y][x] = '*';
        }
    
        String[] answer = new String[h];
        for(int i=0; i<h; i++){
            answer[i] = new String(arr[h-i-1]);
        }
        return answer;
    }
    
    public ArrayList<int[]> getIntersection(int[][] line){
        ArrayList<int[]> point = new ArrayList<>();
        
        for(int i=0; i<line.length; i++){
            for(int j=i+1; j<line.length; j++){
                long a = line[i][0], b = line[i][1], e = line[i][2];
                long c = line[j][0], d = line[j][1], f = line[j][2];
                long denominator = a*d - b*c;
                if(denominator == 0){
                    continue;
                }
                
                long bfed = b*f - e*d;
                long ecaf = e*c - a*f;
                if(bfed % denominator != 0 || ecaf % denominator != 0){
                    continue;
                }
                
                point.add(new int[]{(int)(bfed/denominator), (int)(ecaf/denominator)});
            }
        }
        return point;
    }
}