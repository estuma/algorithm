import java.util.*;
import java.io.*;

/**
 * 
 * <pre>
 * 메모리 bit 하나를 설정하면 끝까지 같은 값으로 덮어씌워짐
 * 원래 상태가 주어질 때 초기 상태로 돌아가는 데 몇 번 수정해야하는지?
 * 이전값 -> '0'으로 할당
 * index 0부터 검사하며 이전 값과 다른 값이 나오는 경우 세기
 * </pre>
 * @author 김신영
 */

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			String memory = sc.next(); // 복구해야할 메모리
			int answer = 0; // 정답

			// 초기화상태 = 모든 비트 0 -> 초기값 0
			char pre = '0';
			for (int i = 0; i < memory.length(); i++) {
				// 이전 값과 다른 경우 카운트하고 이전 값 재설정
				if (pre != memory.charAt(i)) {
					answer++;
					pre = memory.charAt(i);
				}
			}

			// 값 출력
			System.out.println("#" + test_case + " " + answer);
		}
	}
}