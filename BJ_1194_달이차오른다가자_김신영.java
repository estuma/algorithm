import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1194_달이차오른다가자
 * 
 * ---------- ----------
 * ! 미로 탈출에 걸리는 최솟값 !
 * 빈칸: 이동 가능
 * 벽: 이동 불가능
 * 열쇠: 처음 들어가면 열쇠 집음
 * 문: 대응하는 열쇠가 있으면 이동 가능
 * 현재위치: 0
 * 출구: 1
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_1194_달이차오른다가자_김신영 {

	static int N, M;
	static char[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] start = new int[2];
		int[] end = new int[2];
		arr = new char[N][M];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == '0') {
					start[0] = i;
					start[1] = j;
				} else if (arr[i][j] == '1') {
					end[0] = i;
					end[1] = j;
				}
			}
		}

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(start);
		boolean[][] isVisited = new boolean[N][M];

		int[] dirRow = { -1, 0, 1, 0 };
		int[] dirCol = { 0, 1, 0, -1 };

		int cnt = 0;
		boolean isKey;
		while (!queue.isEmpty()) {
			int size = queue.size();
			isKey = false;

			for (int s = 0; s < size; s++) {
				int[] curr = queue.poll();

				for (int dir = 0; dir < 4; dir++) {
					int newR = curr[0] + dirRow[dir];
					int newC = curr[1] + dirCol[dir];

					if (newR < 0 || newR >= N || newC < 0 || newC >= M)
						continue;

					if (isVisited[newR][newC] || arr[newR][newC] == '#')
						continue;
					
					if(arr[newR][newC] == '1') {
						queue.clear();
						break;
					}

					if (arr[newR][newC] == '.') {
						isVisited[newR][newC] = true;
						queue.add(new int[] { newR, newC });
					}
				}
			}

			cnt++;

		}
		
		System.out.println(cnt);

	}

}
