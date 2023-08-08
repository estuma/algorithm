import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_16926_배열돌리기1 https://www.acmicpc.net/problem/16926
 * 44000KB	404ms
 * ---------- ----------
 * 반시계방향으로 배열을 R칸만큼 돌려야 한다.
 * 배열을 돌리기 위해서 돌아가는 한 구간을 나눠 돌려준다.
 * 각 구간을 받아 시작 인덱스를 R만큼더한 값으로 바꿔주고 다시 정답 배열에 넣어준다.
 * </pre>
 * 
 * @author 김신영
 *
 */
public class BJ_16926_배열돌리기1_김신영 {
	static int N, M, R, r;
	static int[][] arr;
	static int[][] answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		r = R % (2 * (N + M - 2));

		// 원본배열
		arr = new int[N][M];
		answer = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 더 작은 쪽을 기준으로 잡는다.
		int min = Math.min(N, M);
		for (int i = 0; i < min / 2; i++) {
			// 한줄로 만들기
			int[] line = makeOneLine(N - i * 2, M - i * 2, i, i);
			// 한줄로 만들어진 배열을 인덱스를 옮겨 다시 넣기
			makeAnswer(line, N - i * 2, M - i * 2, i, i);
		}

		// 정답 출력 만들기
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(answer[i][j] + " ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static void makeAnswer(int[] line, int n, int m, int start, int level) {
		// 길이가 0이면 돌 필요 없음
		int idx = 0;
		if (line.length > 0) {
			// 새롭게 배열의 시작위치 잡기
			idx = R % line.length;
		}
		
		// (i, i) 기준으로 넣기
		int r = start;
		int c = start;
		// 배열 길이만큼 정답배열에 입력하면 됨
		for (int i = 0; i < line.length; i++) {
			answer[r][c] = line[idx];
			idx = (idx + 1) % line.length;
			// makeOneLine 함수와 동일하게 진행
			if (i < m - 1) {
				c++;
			} else if (i < m + n - 2) {
				r++;
			} else if (i < m + n + m - 3) {
				c--;
			} else {
				r--;
			}
		}
	}

	// 돌려야하는 한 써클을 배열로 받아 일렬로 만들어준다.
	public static int[] makeOneLine(int n, int m, int start, int level) {
		int[] line = new int[2 * n + 2 * m - 4];
		// (i,i) 기준으로 읽어나감
		int r = start;
		int c = start;
		// 각 줄은 2(n-1) + 2(m-1)개임
		for (int i = 0; i < (2 * n + 2 * m - 4); i++) {
			line[i] = arr[r][c];

			// 4구간으로 나누어 계산
			if (i < m - 1) {				// 1. (start, start) 기준으로 오른쪽 끝까지
				c++;
			} else if (i < m + n - 2) {		// 2. 오른쪽 끝까지 간 뒤 아래로 끝까지
				r++;
			} else if (i < m + n + m - 3) {	// 3. 아래로 끝까지 간 뒤 왼쪽으로 끝까지
				c--;
			} else {						// 4. 왼쪽으로 끝까지 간 뒤 위로 끝까지
				r--;
			}
		}

		return line;
	}

}
