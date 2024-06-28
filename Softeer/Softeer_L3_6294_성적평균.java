import java.io.*;
import java.util.*;

/**
* https://softeer.ai/practice/6294
* 실행 시간 535 ms 메모리 66.95 MB
* 
* 누적합 문제
* String.format()!!
**/

public class Main {

    static int N, K;
    static int[] score, dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        score = new int[N+1];
        dp = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            dp[i] = dp[i-1] + score[i];
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            double avg = (dp[b] - dp[a-1]) / (double)(b - a + 1);
            sb.append(String.format("%.2f", avg)).append("\n");
        }

        System.out.println(sb);
    }
}
