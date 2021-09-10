import java.util.*;

class Boxer implements Comparable<Boxer>{
    private int num;
    private double winningRate;
    private int win;
    private int lose;
    private int weight;
    
    public Boxer(int num, int win1, int win2, int lose, int weight) {
        this.num = num;
        this.winningRate = (double)win1/(win1+lose);
        if(win1 == 0) {
            this.winningRate = 0;
        }
        this.win = win2;
        this.lose = lose;
        this.weight = weight;
    }

    public int getNum() {
        return num;
    }
    
    @Override
    public int compareTo(Boxer b) {
        if(this.winningRate == b.winningRate && this.win == b.win && this.weight == b.weight) {
            return Integer.compare(this.num, b.num);
        }else if(this.winningRate == b.winningRate && this.win == b.win) {
            return Integer.compare(b.weight, this.weight);
        }else if(this.winningRate == b.winningRate) {
            return Integer.compare(b.win, this.win);
        }
        
        if(this.winningRate <= b.winningRate) {
            return 1;
        }else {
            return -1;
        }
    }
}

class Solution {
    public int[] solution(int[] weights, String[] head2head) {
        ArrayList<Boxer> boxerList = new ArrayList<>();
        
        for(int i=0; i<head2head.length; i++) {
            int win1 = 0, win2 = 0, lose = 0;
            for(int j=0; j<head2head[i].length(); j++) {
                int c = head2head[i].charAt(j);
                if(c == 'L') {
                    lose++;
                }else if(c == 'W') {
                    win1++;
                    // 자신보다 몸무게가 무거운 복서를 이긴 경우
                    if(weights[i] < weights[j]) {
                        win2++;
                    }
                }
            }
            
            boxerList.add(new Boxer(i+1, win1, win2, lose, weights[i]));
        }
        Collections.sort(boxerList);
        
        int n = weights.length;
        int[] answer = new int[n];
        for(int i=0; i<n; i++){
            answer[i] = boxerList.get(i).getNum();
        }
        
        return answer;
    }
}