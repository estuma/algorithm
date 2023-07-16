import java.util.*;
import java.io.*;
import java.math.*;

/**
 * 백준 9663 N-Queen https://www.acmicpc.net/problem/9663
 * @author 김신영 
 * 
 * 퀸은 가로, 세로, 대각선으로 공격 가능 겹치지 않으려면 퀸을 놓은 위치를 기억하고 다음 위치에 반영
 * array[i번퀸의행] = "i번퀸의열" 0행부터 n행까치 차례로 놓을것이므로 행에 못 놓는 것은 고려할 필요 X
 * array[i] = j라고 할 때 j열에 퀸을 놓았다면 그 다음 모든 j번 열에 퀸을 놓을 수 X 
 * (i, j)의 대각선에 퀸을 놓을 수 X 
 * 0행부터 차례로 퀸을 놓음 퀸을 놓을 수 있는 위치인지 확인 
 * -> 가능하다면 재귀호출을 통해 다음 행 검사
 * -> 불가능하다면 끝
 */

public class Main {
	// 가능한 개수
	static int cnt = 0;

	// 놓을 수 있는 자리인지 확인
	public static boolean isValid(int next, int[] arr) {
		// 이전 행들 기준으로 검사, i는 이미 놓은 행까지 검사한다.
		for (int i = 0; i < next; i++) {
			// 이번에 놓을 퀸의 열값과 이전에 놓은 퀸의 열 위치가 같으면 x
			// 이번에 놓을 퀸이 이전에 놓은 퀸의 대각선에 있는 경우 x
			if (arr[next] == arr[i] || Math.abs(arr[next] - arr[i]) == (next - i)) {
				return false;
			}
		}
		return true;
	}

	// start: 현재 행 번호, 퀸을 몇 개 놓았지 그 개수와 같음
	// arr: 퀸의 위치 저장하는 배열
	public static void nQueen(int start, int[] arr) {
		// start가 n과 같아지면 퀸을 다 놓은것이므로 종료
		if (start == arr.length) {
			cnt++;
			return;
		}

		// start행에서 퀸을 놓을 수 있는 위치 모두 검사
		for (int i = 0; i < arr.length; i++) {
			arr[start] = i;
			// (start, i)에 놓는 것이 가능하면
			if (isValid(start, arr)) {
				// 다음 위치 검사
				nQueen(start + 1, arr);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// 사용자입력받기위해 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N 입력
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		nQueen(0, arr); // 0번행부터 놓음

		System.out.println(cnt);
	}
}