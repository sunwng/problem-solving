package prob_5582;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int search(String shortString, String longString) {
        int result = 0;
        int[][] map = new int[shortString.length() + 1][longString.length() + 1];
        for (int i = 0; i < shortString.length(); i++) {
            for (int j = 0; j < longString.length(); j++) {
                if (shortString.charAt(i) == longString.charAt(j)) {
                    map[i + 1][j + 1] = map[i][j] + 1;
                    result = Math.max(result, map[i + 1][j + 1]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stringA = br.readLine();
        String stringB = br.readLine();
        if (stringA.length() < stringB.length()) {
            System.out.println(search(stringA, stringB));
        } else {
            System.out.println(search(stringB, stringA));
        }
    }
}