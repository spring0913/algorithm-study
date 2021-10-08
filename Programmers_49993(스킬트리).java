class Solution {
    public int solution(String skill, String[] skill_trees) {
        int count = 0;
        for(int i=0; i<skill_trees.length; i++){
            if(check(skill_trees[i], skill)) {
                count++;
            }
        }
        return count;
    }
    
    public boolean check(String skillTree, String skill) {
        int index = 0;
        for(int i=0; i<skillTree.length(); i++){
            for(int j=0; j<skill.length(); j++) {
                if(skillTree.charAt(i) == skill.charAt(j)){
                    if(j > index) {
                        return false;
                    }
                    index++;
                }
            }
        }
        return true;
    }
}