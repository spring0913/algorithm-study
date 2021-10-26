import java.util.*;

class Solution {

    private HashMap<String, ArrayList<Integer>> map;
    
    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        int[] answer = new int[query.length];
        
        for(int i=0; i<info.length; i++){
            dfs("", 0, info[i].split(" "));
        }
        
        Iterator<String> it = map.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            Collections.sort(map.get(key));
        }
        
        for(int i=0; i<query.length; i++){
            String[] split = query[i].split(" and | ");
            String key = split[0] + split[1] + split[2] + split[3];
            
            if(map.containsKey(key)){
                int score = Integer.parseInt(split[4]);
                answer[i] = lowerBound(map.get(key), score);
            }
        }
        return answer;
    }
    
    private void dfs(String str, int index, String[] info){
        if(index == 4){
            ArrayList<Integer> arr = new ArrayList<>();
            if(map.containsKey(str)){
                arr = map.get(str);
            }       
            arr.add(Integer.parseInt(info[4]));
            map.put(str, arr);
            return;
        }
        
        dfs(str+"-", index+1, info);
        dfs(str+info[index], index+1, info);
    }
    
    private int lowerBound(ArrayList<Integer> arr, int n){
        int left = 0, right = arr.size()-1;
        while(left <= right){
            int mid = (left + right)/2;
            if(arr.get(mid) < n){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return arr.size()-left;
    }
}