package prob_12919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String end;
    static String start;
    static int result;

    public static String flip(String strIn) {
        StringBuilder out = new StringBuilder();
        for (int i = strIn.length() - 1; i >= 0; i--) {
            out.append(strIn.charAt(i));
        }
        return out.toString();
    }

    public static void find(String strIn) {
        if (strIn.length() == start.length()) {
            if (strIn.equals(start)) {
                result = 1;
            }
            return;
        }
        if (strIn.charAt(strIn.length() - 1) == 'A') {
            find(strIn.substring(0, strIn.length() - 1));
        }
        if (strIn.charAt(0) == 'B') {
            find(flip(strIn.substring(1, strIn.length())));
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        start = br.readLine();
        end = br.readLine();
        result = 0;
        find(end);
        System.out.println(result);
    }
}