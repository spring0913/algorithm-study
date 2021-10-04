class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.toLowerCase();
        sb.append(Character.toUpperCase(s.charAt(0)));
        for(int i=1; i<s.length()-1; i++){
            if(s.charAt(i-1) == ' '){
                sb.append(Character.toUpperCase(s.charAt(i)));
            }else if(s.charAt(i) == ' '){
                sb.append(" " + Character.toUpperCase(s.charAt(i+1)));
                i++;
            }else {
                sb.append(s.charAt(i));
            }
        }
        sb.append(s.charAt(s.length()-1));
        return sb.toString();
    }
}