import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ 17406 배열돌리기4
 * 26000KB	244ms
 * ---------- ----------
 * 상하좌우 방향으로 한칸씩 배열을 돌리는 함수를 만들어 준다.
 * 명령어를 실행하는 순서를 만들기 위해 순열을 사용한다.
 * 재귀를 사용하여 순열을 만들어 준다.
 * 만약 순열 하나가 다 만들어졌다면 해당하는 순서로 명령어를 실행하는 경우 나오는 min값을 찾아준다.
 * 위 순서를 계속 반복하면 된다.
 *</pre>
 * @author 김신영
 */

public class BJ_17406_배열돌리기4_김신영 {

	static int N, M, K, min = Integer.MAX_VALUE;
	static int[][] arr, oper;
	static boolean isSelected[];
	static int[] order;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 배열 입력
		arr = new int[N + 1][M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		oper = new int[K][3];
		// 회전 연산
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			oper[i][0] = Integer.parseInt(st.nextToken()) - 1;
			oper[i][1] = Integer.parseInt(st.nextToken()) - 1;
			oper[i][2] = Integer.parseInt(st.nextToken());

		}

		order = new int[K];
		isSelected = new boolean[K];
		permutation(0);

		System.out.println(min);
	}

	// 순서뽑기 (순열 구하기)
	public static void permutation(int cnt) {
		if (cnt == K) {
			// 순열이 만들어 졌다면 원본 배열 복사하여 회전 실행
			int[][] copy = arrayCopy(arr);
			// 명령어 개수만큼 반복
			for (int i = 0; i < K; i++) {
				// s만큼 반복하여 ㅁ(미음) 모양으로 배열을 돌려준다.
				for (int j = 0; j < oper[order[i]][2]; j++) {
					copy = rotation(copy, oper[order[i]][0], oper[order[i]][1], oper[order[i]][2] - j);
				}
			}
			// 명령어를 다 실행한 뒤 배열로 값 계산
			calSum(copy);
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				order[cnt] = i;
				permutation(cnt + 1);
				isSelected[i] = false;
			}
		}
	}

	// 행의 합 구하기
	public static void calSum(int[][] copyArr) {
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += copyArr[i][j];
			}
			min = Math.min(min, sum);
		}
	}

	// 배열 돌리는 함수
	public static int[][] rotation(int[][] copyArr, int r, int c, int s) {
		// 위쪽 변
		int tmp = copyArr[r - s][c + s];
		for (int j = c + s; j > c - s; j--) {
			copyArr[r - s][j] = copyArr[r - s][j - 1];
		}

		// 오른쪽 변
		int tmp2 = copyArr[r + s][c + s];
		for (int i = r + s; i > r - s; i--) {
			copyArr[i][c + s] = copyArr[i - 1][c + s];
			if (i == r - s + 1) {
				copyArr[r - s + 1][c + s] = tmp;
			}
		}

		// 아래쪽 변
		tmp = copyArr[r + s][c - s];
		for (int j = c - s; j < c + s; j++) {
			copyArr[r + s][j] = copyArr[r + s][j + 1];
			if (j == c + s - 1) {
				copyArr[r + s][c + s - 1] = tmp2;
			}
		}

		// 왼쪽 변
		for (int i = r - s; i < r + s; i++) {
			copyArr[i][c - s] = copyArr[i + 1][c - s];
			if (i == r + s - 1) {
				copyArr[r + s - 1][c - s] = tmp;
			}
		}

		return copyArr;
	}
	
	// 배열 복사하는 함수
	public static int[][] arrayCopy(int[][] origin) {
		int[][] newArr = new int[origin.length][origin[0].length];
		for(int i = 0; i < origin.length; i++) {
			for(int j = 0; j < origin[0].length; j++) {
				newArr[i][j] = origin[i][j];
			}
		}
		return newArr;
	}

}
