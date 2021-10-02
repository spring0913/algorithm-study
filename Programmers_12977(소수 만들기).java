class Solution {
    
    private boolean[] check;
    
    public int solution(int[] nums) {
        int count = 0;
        initPrimeNumber();
        
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                for(int k=j+1; k<nums.length; k++){
                    if(!check[nums[i] + nums[j] + nums[k]]){
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    public void initPrimeNumber(){
        check = new boolean[3001];
        check[0] = check[1] = true;
        for (int i=2; i<=3000; i++){
            if (!check[i]){
                for (int j=i*2; j<=3000; j+=i){
                    check[j] = true;
                }
            }
        }
    }
}