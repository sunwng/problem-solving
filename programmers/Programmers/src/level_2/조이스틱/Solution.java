class Solution {
    public int solution(String name) {
        char[] words = new char[name.length()];
        int answer = 0;
        int minLen = name.length() - 1;

        for (int i = 0; i < name.length(); i++) {
            words[i] = name.charAt(i);
        }
        for (int i = 0; i < name.length(); i++) {
            answer += Math.min(words[i]-'A', 'Z' + 1 - words[i]);

            int indexA = i + 1;
            while (indexA < name.length() && words[indexA] == 'A') {
                indexA ++;
            }
            minLen = Math.min(minLen, i * 2 + (name.length() - indexA));
            minLen = Math.min(minLen, (name.length() - indexA) * 2 + i);
        }
        answer += minLen;
        return answer;
    }
}