package bj_22233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int keywordNum = Integer.parseInt(temp[0]);
        int textNum = Integer.parseInt(temp[1]);
        HashSet<String> repo = new HashSet<>();

        for (int i = 0; i < keywordNum; i++) {
            repo.add(br.readLine());
        }
        for (int i = 0; i < textNum; i++) {
            String[] textKeywords = br.readLine().split(",");
            for (String curKeyword : textKeywords) {
                repo.remove(curKeyword);
            }
            System.out.println(repo.size());
        }
    }
}