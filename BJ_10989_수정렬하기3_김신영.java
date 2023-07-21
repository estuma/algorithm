import java.util.*;
import java.io.*;

/**
 * 백준 10989 수 정렬하기3 https://www.acmicpc.net/problem/10989
 * 480568KB, 1668ms
 * @author 김신영 
 * 수의 개수가 많고 범위가 작으므로 Counting 정렬을 사용할 수 있다.
 */

public class Main {

	public static void main(String[] args) throws IOException {
		// 사용자 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 정렬하기위한 배열
		int[] arr = new int[10001];
		// 결과값 저장
		StringBuilder sb = new StringBuilder();

		// 읽어들인 숫자를 인덱스로 사용하여 배열에 숫자 카운트
		for(int i = 0; i < n; i++) {
			arr[Integer.parseInt(br.readLine())]++;
		}

		// 배열을 처음부터 끝까지 검사
		for(int i = 0; i < 10001; i++) {
			// arr[i]가 1 이상이면 결과 문자열에 추가하고 값을 1 감소시킨다. 
			while(arr[i] > 0) {
				sb.append(i).append('\n');
				arr[i]--;
			}
		}

		// 결과 출력
		System.out.println(sb);
	}
}