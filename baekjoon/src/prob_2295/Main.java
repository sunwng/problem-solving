package prob_2295;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        List<Integer> two = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                two.add(arr[i] + arr[j]);
            }
        }
        Collections.sort(two);
        for (int i = size - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (Collections.binarySearch(two, arr[i] - arr[j]) >= 0) {
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
    }
}