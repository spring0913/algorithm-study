import java.util.*;

class Solution {

    private Map<String, Integer> map;
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        map = new HashMap<>();
        for(int i=0; i<orders.length; i++){
            // 요리 개수에 따라 한 주문에서 가능한 모든 메뉴 조합 추가
            for(int j=0; j<course.length; j++){
                char[] arr = new char[course[j]];
                char[] order = orders[i].toCharArray();
                Arrays.sort(order);
                combination(arr, 0, order, course[j], 0);
            }
        }
        
        ArrayList<String> keySetList = new ArrayList<>(map.keySet());
        Collections.sort(keySetList, (o1, o2)->(map.get(o2).compareTo(map.get(o1))));
        
        ArrayList<String> answer = new ArrayList<>();
        for(int i=0; i<course.length; i++){
            int max = 0;
            for(String key : keySetList){
                if(map.get(key) >= 2 && map.get(key) >= max && key.length() == course[i]){
                    answer.add(key);
                    max = map.get(key);
                }
            }
        }
        Collections.sort(answer);
        return answer;
    }
    
    public void combination(char[] arr, int index, char[] order, int r, int target) {
        if(r == 0) {
            String key = new String(arr);
            map.put(key, map.getOrDefault(key, 0) + 1);
            return;
        }
        
        if(target == order.length) {
            return;
        }
        
        arr[index] = order[target];
        combination(arr, index+1, order, r-1, target+1);
        combination(arr, index, order, r, target+1);
    }
}