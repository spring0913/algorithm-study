class Solution {
    private int[] answer;
    
    public int[] solution(int[][] arr) {
        answer = new int[2];
        dfs(arr, 0, 0, arr.length);
        return answer;
    }
    
    public void dfs(int[][] arr, int h, int w, int size){
        int count = 0;
        for(int i=h; i<h+size; i++){
            for(int j=w; j<w+size; j++){
                if(arr[i][j] == 0){
                    count++;
                }
            }
        }
        if(count == 0){
            answer[1]++;
            return;
        }
        
        if(count == size*size){
            answer[0]++;
            return;
        }
        
        dfs(arr, h, w, size/2);
        dfs(arr, h, w+size/2, size/2);
        dfs(arr, h+size/2, w, size/2);
        dfs(arr, h+size/2, w+size/2, size/2);
    }
}