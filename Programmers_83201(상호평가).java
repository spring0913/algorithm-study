class Solution {
    public String solution(int[][] scores) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<scores.length; i++){
            int max = 0;
            int min = 101;
            int sum = 0;
            double avg = 0;
            for(int j=0; j<scores.length; j++){
                if(i != j){
                    max = Math.max(max, scores[j][i]);
                    min = Math.min(min, scores[j][i]);
                    sum += scores[j][i];
                }
            }
            
            if(scores[i][i] > max || scores[i][i] < min){
                avg = sum / (scores.length-1);
            }else{
                sum += scores[i][i];
                avg = sum / scores.length;
            }
            sb.append(getGrade(avg));
        }
        
        return sb.toString();
    }
    
    public char getGrade(double avg){
        if(avg >= 90){
            return 'A';
        }else if(avg >= 80){
            return 'B';
        }else if(avg >= 70){
            return 'C';
        }else if(avg >= 50){
            return 'D';
        }else{
            return 'F';
        }
    }
    
}