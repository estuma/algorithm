import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
* https://softeer.ai/practice/7727
* 실행 시간 200 ms 메모리 14.84 MB
* 
* m명의 친구들이 3초 동안 최대로 얻을 수 있는 열매 수확량
**/

public class Main {

    static int N, M, max = 0;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static int[][] arr;
    static boolean[][][] isVisited;
    static List<int[]> friends;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        friends = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            friends.add(new int[]{r, c});
        }

        isVisited = new boolean[N][N][M + 1];
        for (int friend = 0; friend < friends.size(); friend++) {
            isVisited[friends.get(friend)[0]][friends.get(friend)[1]][friend] = true;
        }
        dfs(friends.get(0)[0], friends.get(0)[1], 0, 0);

        System.out.println(max);
    }

    public static void dfs(int r, int c, int friend, int cnt) {
        if (cnt == 3) {
            if (friend == M - 1) {
                cal();
            } else {
                dfs(friends.get(friend + 1)[0], friends.get(friend + 1)[1], friend + 1, 0);
            }

            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nextR = r + dr[dir];
            int nextC = c + dc[dir];

            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || isVisited[nextR][nextC][friend]) {
                continue;
            }

            isVisited[nextR][nextC][friend] = true;
            dfs(nextR, nextC, friend, cnt + 1);
            isVisited[nextR][nextC][friend] = false;
        }

    }

    public static void cal() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 방문 위치 수확
                for (int k = 0; k < M; k++) {
                    if (isVisited[i][j][k]) {
                        sum += arr[i][j];
                        break;
                    }
                }

            }
        }

        max = Math.max(max, sum);
    }
}
