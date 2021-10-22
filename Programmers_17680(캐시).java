import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        LinkedHashSet<String> cache = new LinkedHashSet<>();
        
        int time = 0;
        for(int i=0; i<cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
            if(cache.contains(cities[i])){
                cache.remove(cities[i]);
                time = (cacheSize == 0)? time+5 : time+1; 
            }else{
                if(cacheSize == cache.size() && cacheSize != 0){
                    cache.remove(cache.iterator().next());
                }
                time+=5;
            }
            cache.add(cities[i]);
        }
        return time;
    }
}