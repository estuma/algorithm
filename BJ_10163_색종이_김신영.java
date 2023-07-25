import java.util.*;
import java.io.*;

/**
 * 백준 10163 색종이 https://www.acmicpc.net/problem/10163 
 * 20000KB, 260ms
 * @author 김신영
 * 순서대로 색종이를 놓고 각 색종이가 보이는 면적 구하기
 * 판 크기가 정해져있으므로 크기만큼 배열을 만들고
 * 색종이를 놓는 순서대로 배열에 표시한다.
 * 마지막에 놓은 순서대로 보이는 면적을 세면 끝.
 */

public class Main {
	public static final int MAX = 1001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[MAX][MAX];
		StringBuilder sb = new StringBuilder();
		int[] cnt = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				// (x, y) 넓이, 높이를 입력받는다.
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int width = Integer.parseInt(st.nextToken());
				int hight = Integer.parseInt(st.nextToken());

				// 기준점 (x,y)에서 low, high만큼 배열을 채워준다.
				for (int row = y; row < y + hight; row++) {
					for (int col = x; col < x + width; col++) {
						arr[row][col] = i;
					}
				}
			}
		}

		// index: 놓은 순서
		// index번째에 놓은 색종이의 면적 구하기
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				cnt[arr[i][j]]++;
			}
		}

		// 정답 출력
		for (int i = 1; i <= N; i++) {
			sb.append(cnt[i]).append("\n");
		}

		System.out.println(sb);
	}
}