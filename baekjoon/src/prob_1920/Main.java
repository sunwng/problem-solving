package prob_1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        String[] temp = br.readLine().split(" ");
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        Arrays.sort(arr);
        int testSize = Integer.parseInt(br.readLine());
        temp = br.readLine().split(" ");
        for (int i = 0; i < testSize; i++) {
            int target = Integer.parseInt(temp[i]);
            if (Arrays.binarySearch(arr, target) >= 0) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}