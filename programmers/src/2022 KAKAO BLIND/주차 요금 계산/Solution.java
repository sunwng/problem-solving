import java.util.*;
class Solution {
    int basicM;
    int basicP;
    int extraM;
    int extraP;
    int last = (23 * 60) + 59;

    class Info {
        boolean check;
        int timeSum;
        int timeIn;
        Info(int timeIn) {
            this.check = true;
            this.timeIn = timeIn;
            this.timeSum = 0;
        }
    }

    public int pay(int diff) {
        int out = 0;
//        int diff = timeOut - timeIn;
        out += basicP;
        if (diff > basicM) {
            out += Math.ceil((double) (diff - basicM) / extraM) * extraP;
        }
        return out;
    }


    public int[] solution(int[] fees, String[] records) {
        basicM = fees[0];
        basicP = fees[1];
        extraM = fees[2];
        extraP = fees[3];

        HashMap<String, Info> info = new HashMap<>();
        for (String recordI : records) {
            String[] recordIArr = recordI.split(" ");
            String carNum = recordIArr[1];
            if (recordIArr[2].equals("IN")) {
                String[] time = recordIArr[0].split(":");
                int total = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                info.putIfAbsent(carNum, new Info(0));
                info.get(carNum).timeIn += total;
                info.get(carNum).check = true;
            } else {
                String[] time = recordIArr[0].split(":");
                int timeOut = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                info.get(carNum).timeSum += (timeOut - info.get(carNum).timeIn);
                info.get(carNum).timeIn = 0;
                info.get(carNum).check = false;
            }
        }
        List<String> sorter = new ArrayList<>(info.keySet());
        Collections.sort(sorter);
        int[] answer = new int[sorter.size()];
        int i = 0;
        for (String carI : sorter) {
            if (info.get(carI).check) {
                info.get(carI).timeSum += (last - info.get(carI).timeIn);
            }
            answer[i] = pay(info.get(carI).timeSum);
            i++;
        }
        return answer;
    }
}