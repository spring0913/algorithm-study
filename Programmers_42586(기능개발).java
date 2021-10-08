import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i=0; i<progresses.length; i++){
            q.offer((100-progresses[i]+speeds[i]-1)/speeds[i]);
        }
        
        int num = 0;
        for(int i=0; i<progresses.length;){
            num = q.peek();
            int count = 0;
            while(!q.isEmpty() && q.peek() <= num){
                q.poll();
                count++;
                i++;
            }
            answer.add(count);
        }
        return answer;
    }
}