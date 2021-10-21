import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<priorities.length; i++){
            q.offer(new int[]{priorities[i], i});
        }
        
        int count = 0;
        while(true) {
            Iterator iter = q.iterator();
            int priority = q.peek()[0];
            boolean flag = false;
            while(iter.hasNext()){
                int[] job = (int[]) iter.next();
                // 중요도가 더 높은 작업이 있을 경우 첫 번째 원소를 마지막으로 이동
                if(priority < job[0]){
                    q.offer(q.poll());
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                count++;
                // 요청한 문서를 인쇄
                if(q.poll()[1] == location) {
                    break;
                }
            }
        }
        return count;
    }
}