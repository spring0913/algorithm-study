import java.util.*;

class Solution {
    public String solution(String[] table, String[] languages, int[] preference) {
        HashMap<String, Integer> langPref = new HashMap<>();
        HashMap<String, Integer>[] langTable = new HashMap[table.length];
        String[] position = new String[5];
            
        for(int i=0; i<languages.length; i++){ 
            langPref.put(languages[i], preference[i]);
        }
        
        int max = 0;
        int index = 0;
        for(int i=0; i<table.length; i++){
            langTable[i] = new HashMap<>();
            StringTokenizer st = new StringTokenizer(table[i]);
            position[i] = st.nextToken();
            for(int j=5; j>=1; j--){
                langTable[i].put(st.nextToken(), j);
            }
            
            // (언어 선호도 x 직업군 언어 점수)의 총합
            int sum = 0;
            for(int j=0; j<languages.length; j++){
            	int pref = langPref.get(languages[j]);
                if(langTable[i].containsKey(languages[j])){
                    sum += langTable[i].get(languages[j])*pref;
                }
            }

            if(max < sum) {
                max = sum;            	
                index = i;
            }else if(max == sum){ // 총합이 동일할 경우
                if(position[index].compareTo(position[i]) > 0) {
                    index = i;
                }
            }
        }
        
        return position[index];
    }
}