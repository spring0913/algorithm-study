import java.util.*;

class Solution {
    
    private static final int MUL = 65536;
    
    public int solution(String str1, String str2) {
        int answer = 0;
        
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
    
        int size1 = initMap(map1, str1.toUpperCase());
        int size2 = initMap(map2, str2.toUpperCase());
        
        if(size1 == 0 && size2 == 0){
            return MUL;
        }
        
        // 교집합 개수
        int intersection = 0;
        Iterator<String> it = map1.keySet().iterator();
        while(it.hasNext()){
            String key = it.next();
            if(map2.containsKey(key)){
                intersection += Math.min(map1.get(key), map2.get(key));
            }
        }
        
        // 합집합 개수
        int union = size1 + size2 - intersection;
        
        return (int)(intersection/(double)union*MUL);
    }
    
    private int initMap(HashMap<String, Integer> map, String str){
        int total = 0;
        for(int i=0; i<str.length()-1; i++){
            String key = str.substring(i, i+2);
            if(Character.isLetter(key.charAt(0)) && Character.isLetter(key.charAt(1))){
                map.put(key, map.getOrDefault(key, 0)+1);
                total++;
            }
        }
        return total;
    }
}