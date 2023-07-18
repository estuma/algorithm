package hyundai;

import java.util.*;
import java.io.*;

/**
 * 백준 2750 수 정렬하기 https://www.acmicpc.net/problem/2750
 * @author 김신영 
 * Arrays.sort()이용하여 정렬
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 수의 개수
        // 입력받을 배열 생성
		int[] arr = new int[n];
        // 각각 숫자 입력받기
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
        // 정렬
		Arrays.sort(arr);
		
        // 출력하기
		for(int i : arr) {
			System.out.println(i);
		}
	}
}
