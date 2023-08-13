import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_15686_치킨배달 https://www.acmicpc.net/problem/15686
 * 15012KB	184ms
 * ---------- ----------
 * 남겨놓을 치킨집 M개 조합 구하기
 * -> 구한 뒤 남은 치킨집으로 도시의 치킨거리 구하기 (가구 기준으로 남은 치킨집들로 치킨거리 계산하여 최솟값으로 더해준다)
 * -> 가장 작은 값으로 갱신하며 계속
 * </pre>
 * @author 김신영
 *
 */

public class BJ_15686_치킨배달_김신영 {

	static int N, M, min = Integer.MAX_VALUE;
	static List<int[]> home = new ArrayList<>();
	static List<int[]> chicken = new ArrayList<>();
	static int[] select;
	static char[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시 크기
		M = Integer.parseInt(st.nextToken()); // 남길 치킨집 개수

		// 0: 빈 칸, 1: 집, 2: 치킨집
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().replace(" ", "").toCharArray();
		}

		// 치킨집의 위치
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == '1') {
					home.add(new int[] { i, j });
				} else if (arr[i][j] == '2') {
					chicken.add(new int[] { i, j });
				}
			}
		}

		// 최대 M개의 치킨집만 남기고 나머지 치킨집 폐업시키기
		// 남겨놓을 치킨집 조합 구하기 -> 치킨거리 구하기 -> 최소이면 값 갱신
		select = new int[M];
		combination(0, 0);
		
		System.out.println(min);
	}

	public static void combination(int cnt, int start) {
		// M개 뽑은 경우 거리 구하기
		if (cnt == M) {
			cal();
			return;
		}

		// M개 뽑기
		for (int i = start; i < chicken.size(); i++) {
			select[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}

	public static void cal() {
		int minTmp = 0;
		// 집마다 거리 구하기
		for (int i = 0; i < home.size(); i++) {
			int length = Integer.MAX_VALUE;
			int row = home.get(i)[0];
			int col = home.get(i)[1];
			// 집에서 가장 가까운 치킨거리
			for (int j = 0; j < select.length; j++) {
				int cRow = chicken.get(select[j])[0]; 
				int cCol = chicken.get(select[j])[1];
				length = Math.min(length, Math.abs(row - cRow) + Math.abs(col - cCol));
			}
			// 도시의 치킨거리에 더해줌
			minTmp += length;
		}
		
		min = Math.min(min, minTmp);
	}

}
