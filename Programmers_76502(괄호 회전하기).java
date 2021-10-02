import java.util.*;

class Solution {
    public int solution(String s) {
        int count = 0;
        for(int i=0; i<s.length(); i++){
            count = (check(s) == true) ? count+1 : count;
            s = s.substring(1, s.length()) + s.charAt(0);
        }
        return count;
    }
    
    public boolean check(String s){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                
                if((c == ')' && stack.peek() == '(') || (c == ']' && stack.peek() == '[') 
                   || (c == '}' && stack.peek() == '{')){
                   stack.pop(); 
                }else{
                    return false;
                }            
            }
        }
        
        return (stack.isEmpty()) ? true : false;
    }
}