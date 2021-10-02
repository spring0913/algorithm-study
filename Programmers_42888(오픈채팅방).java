import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        ArrayList<String[]> arr = new ArrayList<>();
        for(int i=0; i<record.length; i++){
            String[] split = record[i].split(" ");
            switch(split[0]){
                case "Enter":
                    map.put(split[1], split[2]);
                case "Leave":
                    arr.add(new String[]{split[0], split[1]});
                    break;
                case "Change":
                    map.put(split[1], split[2]);
                    break;
            }
        }
        
        String[] answer = new String[arr.size()];
        for(int i=0; i<arr.size(); i++){
            String str = "";
            if(arr.get(i)[0].equals("Enter")){
                str = "님이 들어왔습니다.";
            }else{
                str = "님이 나갔습니다.";
            }
            answer[i] = map.get(arr.get(i)[1]) + str;
        }
        return answer;
    }
}