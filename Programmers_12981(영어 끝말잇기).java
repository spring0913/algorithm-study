import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        HashSet<String> set = new HashSet<>();
        int[] answer = new int[2];
        int index = 0;
        for(int i=0; i<words.length-1; i++){
            if(words[i].charAt(words[i].length()-1) != words[i+1].charAt(0)
              || set.contains(words[i+1])){
                answer[0] = (i+1)%n+1;
                answer[1] = (i+1)/n+1;
                break;
            }else{
                set.add(words[i]);
            }
        }
        return answer;
    }
}