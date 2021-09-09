class Solution {
    
    private final char[] vowel = {'A', 'E', 'I', 'O', 'U'};
    private int answer;
    
    public int dfs(String s, String word, int count) {
        if(s.length() == 5) {
            return count;
        }
        
        for(int i=0; i<5; i++) {
            String str = s + vowel[i];
            count++;
            if(str.equals(word)) {
                answer = count;
                return count;
            }
            count = dfs(str, word, count);
        }
        return count;
    }
    
    public int solution(String word) {
        dfs("", word, 0);
        return answer;
    }
}