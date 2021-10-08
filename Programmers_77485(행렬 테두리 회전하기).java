import java.util.*;

class Solution {
    private int[][] arr;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        // init
        arr = new int[rows+1][columns+1];
        int[] answer = new int[queries.length];
        int value = 1;
        for(int i=1; i<arr.length; i++){
            for(int j=1; j<arr[i].length; j++){
                arr[i][j] = value++;
            }
        }
            
        for(int i=0; i<queries.length; i++){
            LinkedList<Integer> list = new LinkedList<>();
            int r1 = queries[i][0];
            int c1 = queries[i][1];
            int r2 = queries[i][2];
            int c2 = queries[i][3];
            int min = arr[r1][c1];
                
            // 상
            for(int j=c1; j<=c2; j++){
                list.add(arr[r1][j]);
                min = Math.min(min, arr[r1][j]);
            }
            // 우
            for(int j=r1+1; j<=r2; j++){
                list.add(arr[j][c2]);
                min = Math.min(min, arr[j][c2]);
            }
            // 하
            for(int j=c2-1; j>=c1; j--){
                list.add(arr[r2][j]);
                min = Math.min(min, arr[r2][j]);
            }
            // 좌
            for(int j=r2-1; j>=r1+1; j--){
                list.add(arr[j][c1]);
                min = Math.min(min, arr[j][c1]);
            }
            
            list.addFirst(list.removeLast());
            apply(r1, c1, r2, c2, list);
            answer[i] = min;
        }
        return answer;
    }
    
    public void apply(int r1, int c1, int r2, int c2, LinkedList<Integer> list){
        int index = 0;
        // 상
        for(int j=c1; j<=c2; j++){
            arr[r1][j] = list.get(index++);
        }
        // 우
        for(int j=r1+1; j<=r2; j++){
            arr[j][c2] = list.get(index++);
        }
        // 하
        for(int j=c2-1; j>=c1; j--){
            arr[r2][j] = list.get(index++);
        }
        // 좌
        for(int j=r2-1; j>=r1+1; j--){
            arr[j][c1] = list.get(index++);
        }
    }
}