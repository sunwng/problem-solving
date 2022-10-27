package bj_1157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String words = br.readLine().toUpperCase();
        int[] count = new int[26];
        for (int i = 0; i < words.length(); i++) {
            int idx = words.charAt(i) - 65;
            count[idx]++;
        }
        int max = 0;
        char result = ' ';
        for (int i = 0; i < 26; i++) {
            if (count[i] > max) {
                max = count[i];
                result = (char) (i + 65);
            } else if (count[i] == max) {
                result = '?';
            }
        }
        System.out.println(result);
    }
}