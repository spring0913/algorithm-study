import java.util.*;

class Node{
    int prev;
    int next;
    boolean isUsed;
    
    public Node(int prev, int next, boolean isUsed) {
        this.prev = prev;
        this.next = next;
        this.isUsed = isUsed;
    }
}

class Solution {
    private ArrayList<Node> list;
    private Stack<Integer> stack;
    private int current;
    
    public void move(int num) {
        int index = current;
        for(int i=0; i<Math.abs(num); i++) {
            if(num > 0) {
                index = list.get(index).next;
            }else {
                index = list.get(index).prev;
            }
        }
        
        current = index;
    }
    
    public void remove() {       
        stack.push(current);
        list.get(current).isUsed = false;
        
        int cursor = current;
        int prev = list.get(current).prev;
        int next = list.get(current).next;
        
        if(next != -1) {
            list.get(next).prev = prev;
        }
        
        if(prev != -1) {
            list.get(prev).next = next;
        }
        
        if(list.get(cursor).next == -1) {
            current = prev;
        }else {
            current = next;
        }
    }
    
    public void rollback() {
        int index = stack.pop();
        list.get(index).isUsed = true;
        
        int prev = list.get(index).prev;
        int next = list.get(index).next;
        if (next != -1) {
            list.get(next).prev = index;
        }
        if (prev != -1) {
            list.get(prev).next = index;
        }
    }
    
    public void exec(String cmd) {
        String[] c = cmd.split(" ");
        
        switch(c[0].charAt(0)) {
            case 'D':
                move(Integer.parseInt(c[1]));
                break;
            case 'U':
                move(-1*Integer.parseInt(c[1]));
                break;
            case 'C':
                remove();
                break;
            case 'Z':
                rollback();
                break;
        }
    }
    
    public String solution(int n, int k, String[] cmd) {        
        list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            list.add(new Node(i-1, i+1, true));
        }
        list.get(n-1).next = -1;
        
        stack = new Stack<>();
        current = k;
        
        for(int i=0; i<cmd.length; i++) {
            exec(cmd[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            if(list.get(i).isUsed) {
                sb.append("O");
            }else {
                sb.append("X");
            }
        }
        
        return sb.toString();
    }
}