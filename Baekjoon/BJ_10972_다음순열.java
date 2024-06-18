import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10972
 * 13,508KB	96ms
 * 
 * -----
 * nextPermutation 복습
 */

public class BJ_10972_다음순열 {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 입력으로 주어진 순열의 다음에 오는 순열을 출력
        if (nextPermutation(arr)) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(arr[i]).append(" ");
            }

            System.out.println(sb);
        } else {
            System.out.println(-1);
        }

    }

    public static boolean nextPermutation(int[] p) {
        int i = p.length - 1;
        while (i > 0 && p[i-1] >= p[i]) i--;

        if (i == 0) {
            return false;
        }

        int j = p.length - 1;
        while(p[i-1] >= p[j]) j--;

        swap(p, i-1, j);

        int k = p.length - 1;
        while(i < k) {
            swap(p, i++, k--);
        }

        return true;
    }

    public static void swap(int[] p, int a, int b) {
        int tmp = p[a];
        p[a] = p[b];
        p[b] = tmp;
    }
}
