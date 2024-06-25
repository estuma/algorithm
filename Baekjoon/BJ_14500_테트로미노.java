import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [김신영, 골드4]BJ_14500_테트로미노.java
 * https://www.acmicpc.net/problem/14500
 * 31556KB	624ms
 * ----- ----- -----
 * 모든 경우 구현해서 풂
 * 배열 돌리기
 * rotated[j][row - 1 - i] = arr[i][j];
 * 배열 대칭
 * reflected[i][col - 1 - j] = arr[i][j];
 *
 * DFS로 풀면 된다고 하네요..
 */

public class BJ_14500_테트로미노 {

    static int N, M, max;
    static int[][] value;
    static int[][][] tetrominos = {
            { {1, 1, 1, 1} },
            { {1, 1}, {1, 1} },
            { {1, 0}, {1, 0}, {1, 1} },
            { {0, 1}, {0, 1}, {1, 1} },
            { {1, 1, 1}, {0, 1, 0} },
            { {1, 1, 0}, {0, 1, 1} },
            { {0, 1, 1}, {1, 1, 0} }
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        value = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                value[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = 0;
        for (int[][] tetromino : tetrominos) {
            for (int rotation = 0; rotation < 4; rotation++) {
                check(tetromino);
                tetromino = rotate(tetromino);
            }
            
            // 대칭 검사
            tetromino = reflect(tetromino);
            for (int rotation = 0; rotation < 4; rotation++) {
                check(tetromino);
                tetromino = rotate(tetromino);
            }
            
        }

        System.out.println(max);
    }

    public static void check(int[][] tetromino) {
        for (int i = 0; i <= N - tetromino.length; i++) {
            for (int j = 0; j <= M - tetromino[0].length; j++) {
                sum(tetromino, i, j);
            }
        }
    }

    public static void sum(int[][] tetromino, int row, int col) {
        int sum = 0;

        for (int i = 0; i < tetromino.length; i++) {
            for (int j = 0; j < tetromino[0].length; j++) {
                if (tetromino[i][j] == 1) {
                    sum += value[row + i][col + j];
                }
            }
        }

        max = Math.max(max, sum);
    }

    public static int[][] rotate(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;

        int[][] rotated = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rotated[j][row - 1 - i] = arr[i][j];
            }
        }

        return rotated;
    }

    public static int[][] reflect(int[][] arr) {
        int row = arr.length;
        int col = arr[0].length;
        int[][] reflected = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                reflected[i][col - 1 - j] = arr[i][j];
            }
        }

        return reflected;
    }
}
