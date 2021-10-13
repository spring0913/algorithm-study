import java.util.*;

class Tuple{
    private ArrayList<String> arr;
    
    public Tuple(ArrayList<String> arr) {
        this.arr = arr;
    }
    
    @Override
    public boolean equals(Object obj) {
        Tuple set = (Tuple)obj;
        return set.arr.equals(this.arr);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(arr);
    }
}

class Solution {
   
    private ArrayList<ArrayList<String>> key;
    private ArrayList<ArrayList<Integer>> candidateKey;
    
    public int solution(String[][] relation) {
        key = new ArrayList<>();
        candidateKey = new ArrayList<>();
        HashSet<Tuple> set = new HashSet<>();
        int len = relation[0].length;
        for(int i=1; i<=len; i++){
            boolean[] visited = new boolean[len];
            int[] arr = new int[len];
            dfs(relation, arr, visited, 0, i);
        }
        return candidateKey.size();
    }
    
    public void dfs(String[][] relation, int[] arr, boolean[] visited, int index, int r){
        if(index == r){
            ArrayList<Integer> key = new ArrayList<>();
            for(int i=0; i<r; i++) {
                key.add(arr[i]);
            }
            // 튜플을 유일하게 식별할 수 있는 키 값인지 확인
            if(check(relation, key)) {
                boolean flag = false;
                // 최소성을 만족하는 키 값인지 확인
                for(int j=0; j<candidateKey.size(); j++) {
                    if(key.containsAll(candidateKey.get(j))) {
                        flag = true;
                        break;
                    }
                }
                if(!flag) {
                    candidateKey.add(key);
                }
            }
            return;
        }
        
        for(int i=0; i<arr.length; i++){
            if(!visited[i]){
                arr[index] = i;
                visited[i] = true;
                dfs(relation, arr, visited, index+1, r);
                visited[i] = false;
            }
        }
    }
    
    public boolean check(String[][] relation, ArrayList<Integer> key) {
        HashSet<Tuple> set = new HashSet<>();
        for(int i=0; i<relation.length; i++) {
            ArrayList<String> arr = new ArrayList<>();
            for(int j=0; j<key.size(); j++) {
                arr.add(relation[i][key.get(j)]);
            }
            set.add(new Tuple(arr));
        }
        return (set.size() == relation.length)? true : false;
    }
    
}