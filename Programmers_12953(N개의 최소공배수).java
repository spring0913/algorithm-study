class Solution {
    public int solution(int[] arr) {
        int mul = arr[0];
        for(int i=1; i<arr.length; i++){
            mul *= arr[i] / gcd(arr[i], mul%arr[i]);
        }
        return mul;
    }
    
    public int gcd(int a, int b){
        return (b == 0) ? a : gcd(b, a%b);
    }
}