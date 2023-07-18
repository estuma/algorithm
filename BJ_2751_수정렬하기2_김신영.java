import java.util.*;
import java.io.*;

/**
 * 백준 2751 수 정렬하기2 https://www.acmicpc.net/problem/2751
 * @author 김신영 
 * Collections.sort()이용하여 정렬 -> TimSort로 O(nlogn)보장
 * StringBuilder와 BufferedReader를 사용하여 빠른 입출력
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 수의 개수
		ArrayList<Integer> list = new ArrayList<>(); // Collections.sort 사용 위해 list로 생성
		StringBuilder sb = new StringBuilder();	// 결과 저장할 변수
		
		// 숫자 입력받음
		for(int i = 0; i < n; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		
		// 정렬
		Collections.sort(list);
		
		// 결과 문자열 생성
		for(int i : list) {
			sb.append(i).append('\n');
		}
		
		System.out.println(sb);
	}
}