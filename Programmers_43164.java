import java.util.*;

class Solution {
    
    private ArrayList<ArrayList<String>> pathList = new ArrayList<>();
    
    public void dfs(String[][] tickets, boolean[] visited, ArrayList<String> path) {
        if(path.size() == tickets.length + 1) {
            pathList.add(new ArrayList<String>(path));
            return;
        }
        
        for(int i=0; i<tickets.length; i++) {
            String nextCity = tickets[i][1];
            if(path.get(path.size()-1).equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                path.add(nextCity);
                dfs(tickets, visited, path);
                visited[i] = false;
                path.remove(path.size()-1);
            }
        }
    }
    
    public String[] solution(String[][] tickets) {
        for(int i=0; i<tickets.length; i++) {
            ArrayList<String> path = new ArrayList<>();
            boolean[] visited = new boolean[tickets.length];
            
            if(tickets[i][0].equals("ICN")) {
                visited[i] = true;
                path.add(tickets[i][0]);
                path.add(tickets[i][1]);
                dfs(tickets, visited, path);
            }
        }
        
        Collections.sort(pathList, new Comparator<ArrayList<String>>() {

            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                if(o1.size() == o2.size()) {
                    for(int i=0; i<o1.size(); i++) {
                        if(!o1.get(i).equals(o2.get(i))) {
                            return o1.get(i).compareTo(o2.get(i));
                        }
                    }
                }
                
                return Integer.compare(o1.size(), o2.size());
            }
        });
        
        int len = pathList.get(0).size();
        String[] answer = new String[len];
        for(int i=0; i<len; i++) {
            answer[i] = pathList.get(0).get(i);
        }
        
        return answer;
    }
}