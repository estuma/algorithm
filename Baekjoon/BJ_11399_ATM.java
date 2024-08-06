import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [김신영, 실버4]BJ_11399_ATM.java
 * https://www.acmicpc.net/problem/11399
 * 11,924KB	72ms
 */
public class BJ_11399_ATM {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int ans = 0, sum = 0;
        for (int i = 0; i < N; i++) {
            ans += sum + arr[i];
            sum += arr[i];
        }

        System.out.println(ans);

    }
}
