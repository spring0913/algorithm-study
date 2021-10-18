import java.util.*;

class Solution {
    
    private ArrayList<Character>[] arr;
    
    public int solution(int m, int n, String[] board) {
        arr = new ArrayList[n];
        
        for(int i=0; i<n; i++){
            arr[i] = new ArrayList<>();
            for(int j=m-1; j>=0; j--){
                arr[i].add(board[j].charAt(i));
            }
        }
        
        int answer = 0;
        while(true){
            int count = apply(scan(n, m));
            if(count == 0){
                break;
            }
            answer += count;
        };
        
        return answer;
    }

    public boolean[][] scan(int n, int m){
        boolean[][] check = new boolean[n][m];
        for(int i=0; i<n-1; i++){
            int min = Math.min(arr[i].size(), arr[i+1].size());
            for(int j=0; j<min-1; j++){
                char ch = arr[i].get(j);
                // 2x2 형태로 붙어있는 블록 위치 마킹
                if(ch == arr[i].get(j+1) && ch == arr[i+1].get(j) && ch == arr[i+1].get(j+1)){
                    check[i][j] = check[i][j+1] = check[i+1][j] = check[i+1][j+1] = true;
                }
            }
        }
        return check;
    }
    
    public int apply(boolean[][] check){
        int count = 0;
        for(int i=0; i<check.length; i++){
            for(int j=check[0].length-1; j>=0; j--){
                if(check[i][j]){
                    arr[i].remove(j);
                    count++;
                }
            }
        }

        return count;
    }
}