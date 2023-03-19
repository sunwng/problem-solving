import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] given;
    static int[] sorted;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        given = new int[N];
        sorted = new int[N];
        String[] givenString = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            given[i] = Integer.parseInt(givenString[i]);
            sorted[i] = Integer.parseInt(givenString[i]);
        }
        Arrays.sort(sorted);
        Map<Integer, Integer> rankCheck = new HashMap<>();
        rankCheck.put(sorted[0], 0);
        int prev = sorted[0];
        for (int i = 1; i < N; i++) {
            if (prev == sorted[i]) continue;
            rankCheck.put(sorted[i], rankCheck.size());
            prev = sorted[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(rankCheck.get(given[i]));
            sb.append(" ");
        }
        System.out.println(sb);
        String fa = "jfklad";
        List<String> test = new ArrayList<>();
    }
}