import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * 20692KB	332ms
 * ----- ----- -----
 * 맵을 상어 배열 만들어서 상어 위치 저장
 * 상어리스트만들어 상어 저장
 * 상어 차례로 이동시키며 위치 갱신해줌
 * 열 기준으로 가장 처음 나오는 상어 잡는다.
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_17143_낚시왕_김신영 {

	static int R, C, M, answer;
	static int[] dr = { 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, 1, -1 };
	static int[] row, col;

	static Shark[][] arr;
	static List<Shark> sharkList;

	static class Shark {
		int r, c, s, d, z;
		int turn = -1;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken()); // Row
		C = Integer.parseInt(st.nextToken()); // Col
		M = Integer.parseInt(st.nextToken()); // 상어 수

		arr = new Shark[R][C]; // 맵
		sharkList = new ArrayList<>();
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 상어 r
			int c = Integer.parseInt(st.nextToken()) - 1; // 상어 c
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 이동방향 1:위, 2:아래, 3:오른쪽, 4:왼쪽
			int z = Integer.parseInt(st.nextToken()); // 크기: 상어가 같은 크기를 갖는 경우는 없다.
			// 맵에 위치시키기
			sharkList.add(new Shark(r, c, s, d, z));
			arr[r][c] = sharkList.get(cnt);
			cnt++;
		}

		// 상어 이동을 위한 인덱스 만들기
		makeMoveArr();

		answer = 0;
		// 낚시왕 위치: i
		for (int i = 0; i < C; i++) {
			fishing(i);
			moveShark(i);
		}

		System.out.println(answer);
	}

	// 상어잡기
	public static void fishing(int round) {
		// 행 크기만큼 돌면서 검사
		for (int i = 0; i < R; i++) {
			// 상어가 있으면!
			if (arr[i][round] != null) {
				// 상어 잡자 -> 크기 더하기
				answer += arr[i][round].z;
				// 상어 리스트에서 삭제 후 칸에서도 지워준다.
				sharkList.remove(arr[i][round]);
				arr[i][round] = null;
				return;
			}
		}
	}

	public static void moveShark(int round) {
		int size = sharkList.size() - 1;
		// 상어 한마리씩 이동시키기
		for (int i = size; i >= 0; i--) {
			Shark curr = sharkList.get(i);

			// 새 위치 만들기
			int newR = curr.r, newC = curr.c;

			int tmp;
			if (curr.d == 1) {
				tmp = (row.length - curr.r + curr.s) % row.length;
				newR = row[tmp];
				if(tmp < R) {
					curr.d = 2;
				}
			} else if (curr.d == 2) {
				tmp = (curr.r + curr.s) % row.length;
				newR = row[tmp];
				if(tmp >= R) {
					curr.d = 1;
				}
			} else if (curr.d == 3) {
				tmp = (curr.c + curr.s) % col.length;
				newC = col[tmp];
				if(tmp >= C) {
					curr.d = 4;
				}
			} else {
				tmp = (col.length - curr.c + curr.s) % col.length;
				newC = col[tmp];
				if(tmp < C) {
					curr.d = 3;
				}
			}

			// 상어 이동시키기
			// 현재 내가 있던 칸에 다른 상어가 있는 경우:
			// 내가 이동하기 전에 내 위치로 이동해온 상어가 있는 경우이므로 null로 바꿔주면 안됨
			if (arr[curr.r][curr.c].equals(curr)) {
				arr[curr.r][curr.c] = null;
			}

			// 현재 상어의 위치 바꾸기, 현재 턴 설정하기: 같은 칸에 있는 상어 잡아먹을지말지 결정
			curr.r = newR;
			curr.c = newC;
			curr.turn = round;

			// 새 위치에 상어가 있고 현재 턴에 먼저 이동한 상어이면 크기 비교해서 하나 없애기
			if (arr[newR][newC] != null && arr[newR][newC].turn == round) {
				// 상어리스트에서 삭제
				if (arr[newR][newC].z < curr.z) {
					// 뒤에있던 상어가 먼저 이동하니까 앞 인덱스 변동 없음
					sharkList.remove(arr[newR][newC]);
					arr[newR][newC] = curr;
				} else {
					sharkList.remove(curr);
				}
			} else {
				// 다음 위치에 현재 상어 위치시키기
				arr[newR][newC] = curr;
			}

		}
	}
	
	public static void makeMoveArr() {
		row = new int[R * 2 - 2];
		col = new int[C * 2 - 2];

		for (int i = 0; i < R; i++) {
			row[i] = i;
		}
		int tmp = R - 2;
		for (int i = R; i < row.length; i++) {
			row[i] = tmp--;
		}

		for (int i = 0; i < C; i++) {
			col[i] = i;
		}
		tmp = C - 2;
		for (int i = C; i < col.length; i++) {
			col[i] = tmp--;
		}
	}

}
