import java.util.*;

class Solution {
    
    class Member {
        int money;
        Member parent;
        
        public Member() {
            money = 0;
        }
        
        void addParent(Member parent) {
            this.parent = parent;
        }
    }
    
    int N; // 사람수
    int M; // 판매수
    
    void addMoney(Member member, int money) {
        int pay = (int) Math.floor(money / 10);
        if (member.parent == null || pay == 0) {
            member.money += money;
            return;
        }
        
        addMoney(member.parent, pay);
        member.money += (money - pay);
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        N = enroll.length;
        M = seller.length;
        Member center = new Member();
        Map<String, Member> map = new HashMap<>();
        List<String> names = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(enroll[i])) {
                map.put(enroll[i], new Member());
                names.add(enroll[i]);
            }
            if ("-".equals(referral[i])) {
                map.get(enroll[i]).addParent(center);
            } else {
                map.get(enroll[i]).addParent(map.get(referral[i]));
            }
        }
        
        for (int i = 0; i < M; i++) {
            addMoney(map.get(seller[i]), amount[i] * 100);
        }
        
        int[] answer = new int[names.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = map.get(names.get(i)).money;
        }
        
        return answer;
    }
}