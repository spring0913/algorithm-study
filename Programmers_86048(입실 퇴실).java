import java.util.*;

class Solution {
    public int[] solution(int[] enter, int[] leave) {
        boolean[][] check = new boolean[1001][1001];
        ArrayList<Integer> arr = new ArrayList<>();
        int index = 0;
        for(int i=0; i<leave.length;) {
            int temp = arr.indexOf(leave[i]);
            if( temp != -1) {
                arr.remove(temp);
                i++;
            }else {
                for(int j=0; j<arr.size(); j++) {
                    check[enter[index]][arr.get(j)] = true;
                    check[arr.get(j)][enter[index]] = true;
                }
                arr.add(enter[index++]);
            }
        }
        
        int[] answer = new int[enter.length];
        for(int i=1; i<=enter.length; i++) {
            int count = 0;
            for(int j=1; j<=enter.length; j++) {
                if(check[i][j]) {
                    count++;
                }
            }
            answer[i-1] = count;
        }
        return answer;
    }
}