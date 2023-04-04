import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String test = "tc";
        Map<String, Integer> test2 = new HashMap<>();
        test2.put("t", 0);
        System.out.println(test2.get(test.charAt(0)));
    }
}