class Solution {
    public String solution(String s) {
        String[] numbers = s.split(" ");
        int max = Integer.parseInt(numbers[0]);
        int min = Integer.parseInt(numbers[0]);
        for(int i=0; i<numbers.length; i++){
            int num = Integer.parseInt(numbers[i]);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        return min + " " + max;
    }
}