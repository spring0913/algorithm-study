import java.util.*;

class Solution {    
    private final char[] operator = {'+', '-', '*'};
    private long answer = 0;
    
    public long solution(String expression) {
        char[] arr = new char[3];
        boolean[] visited = new boolean[3];
        dfs(arr, visited, 0, expression);
        return answer;
    }
    
    private void dfs(char[] arr, boolean[] visited, int index, String expression){
        if(index == 3){
            answer = Math.max(answer, solve(expression, arr));
            return;
        }
        
        for(int i=0; i<3; i++){
            if(!visited[i]){
                arr[index] = operator[i];
                visited[i] = true;
                dfs(arr, visited, index+1, expression);
                visited[i] = false;
            }
        }
    }
    
    private long solve(String expression, char[] arr){
        String[] numbers = expression.split("[^0-9]");
        ArrayList<String> operator = new ArrayList<>(Arrays.asList(expression.split("[0-9]+")));
        
        ArrayList<Long> num = new ArrayList<>();
        for(int i=0; i<numbers.length; i++){
            num.add(Long.parseLong(numbers[i]));
        }
        
        for(int i=0; i<3; i++){
            for(int j=1; j<operator.size();){
                if(operator.get(j).equals(arr[i]+"")){
                    long operand = calculate(num.get(j-1), num.get(j), arr[i]);
                    num.set(j, operand);
                    num.remove(j-1);
                    operator.remove(j);
                }else{
                    j++;
                }
            }
        }
        return Math.abs(num.get(0));
    }
    
    private long calculate(long num1, long num2, char operator) {
        switch(operator) {
            case '+':
                return num1+num2;
            case '-':
                return num1-num2;
            case '*':
                return num1*num2;
        }
        return -1;
    }
}