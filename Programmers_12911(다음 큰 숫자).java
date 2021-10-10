class Solution {
    public int solution(int n) {
        int num = n+1;
        int count = getCount(n);

        while(true){
            if(getCount(num) == count){
                return num;
            }
            num++;
        }
    }
    
    public int getCount(int n){
        int count = 0;
        while(n>0){
            if(n%2 == 1){
                count++;
            }
            n/=2;
        }
        return count;
    }
}