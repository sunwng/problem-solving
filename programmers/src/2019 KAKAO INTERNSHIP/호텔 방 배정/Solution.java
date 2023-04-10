import java.util.*;

class Solution {
    
    Map<Long, Long> roomTable;
    
    long getEmptyRoom(long room) {
        if (!roomTable.containsKey(room)) {
            roomTable.put(room, room + 1);
            return room;
        }
        
        long emptyRoom = getEmptyRoom(roomTable.get(room));
        roomTable.put(room, emptyRoom);
        
        return emptyRoom;
    }
    
    public long[] solution(long k, long[] room_number) {
        int N = room_number.length;
        long[] answer = new long[N];
        roomTable = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            answer[i] = getEmptyRoom(room_number[i]);
        }
        
        return answer;
    }
}