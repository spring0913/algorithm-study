class Solution {
    public String solution(int n) {
        char[] numbers = {'4', '1', '2'};
        StringBuilder sb = new StringBuilder();
        while(n>0){
            int num = n % 3;
            if(num == 0){
                n--;
            }
            sb.insert(0, numbers[num]);
            n /= 3;
        }
        return sb.toString();
    }
}