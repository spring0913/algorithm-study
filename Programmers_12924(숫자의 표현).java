class Solution {
    public int solution(int n) {
        int count = 0;
        for(int i=1; i<=n; i++){
            int sum = 0;
            int start = i;
            while(sum <= n){
                sum += start++;
                if(sum == n){
                    count++;
                }
            }
        }
        return count;
    }
}