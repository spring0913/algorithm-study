class Solution {
    public int[] solution(String s) {
        int count1 = 0;
        int count2 = 0;
        while(!s.equals("1")){
            count2 += s.length();
            s = s.replace("0","");
            count2 -= s.length();
            s = decimalToBinary(s.length());
            binaryToDecimal(s);
            count1++;
        }
        return new int[]{count1, count2};
    }
    
    public String decimalToBinary(int n){
        StringBuilder sb = new StringBuilder();
        while(n>0){
            sb.append(n%2);
            n/=2;
        }
        return sb.reverse().toString();
    }
    
    public int binaryToDecimal(String binary){
        int num = 0;
        for(int i=0; i<binary.length(); i++){
            num *= 2;
            num += binary.charAt(i)-'0';
        }
        return num;
    }
}