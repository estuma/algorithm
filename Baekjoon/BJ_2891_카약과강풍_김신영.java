import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2891
 * 11,516KB	76ms
 *
 * dfs로 풀었는데 greedy로 풀면 된다고 합니다..
 */

public class BJ_2891_카약과강풍 {

    static int N, S, R;
    static int[] arr;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 팀의 수 2 ≤ N ≤ 10
        S = Integer.parseInt(st.nextToken()); // 카약이 손상된 팀의 수 1 ≤ S
        R = Integer.parseInt(st.nextToken()); // 카약을 하나 더 가져온 팀의 수 R ≤ N

        arr = new int[N];
        Arrays.fill(arr, 1);

        // 카약이 손상된 팀 번호
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            arr[Integer.parseInt(st.nextToken())-1] = 0;
        }

        // 카약을 하나 더 가져온 팀 번호
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            arr[Integer.parseInt(st.nextToken())-1]++;
        }

        min = Integer.MAX_VALUE;
        dfs(0);

        System.out.println(min);

    }

    public static void dfs(int start) {

        if (start == N) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i] == 0) {
                    cnt++;
                }
            }
            min = Math.min(min, cnt);
        }

        // 카약 빌려주기
        for (int i = start; i < N; i++) {
            if (arr[i] == 2) {
                if (i - 1 >= 0 && arr[i - 1] == 0) {
                    arr[i-1] = 1;
                    dfs(i + 1);
                    arr[i-1] = 0;
                }

                else if (i + 1 < N && arr[i + 1] == 0) {
                    arr[i + 1] = 1;
                    dfs(i + 1);
                    arr[i + 1] = 0;
                }
                else {
                    dfs(i + 1);
                }
            } else {
                dfs(i + 1);
            }
        }

    }
}
