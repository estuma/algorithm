import java.io.*;
import java.util.*;

/**
* https://softeer.ai/practice/7374
* 실행 시간 70 ms 메모리 10.25 MB
* 
* 땅의 높이를 1, 2, 3으로 만들 때의 비용을 모두 계산하여 구하기
* 대각선으로 이동하며 각 지점의 세로, 가로 값 구하기
**/

public class Main {

    static int min = Integer.MAX_VALUE;
    static int[][] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[3][3];

        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 3; i++) {
            for(int hight = 1; hight <= 3; hight++) {
                calculation(i, i, hight);
            }
        }
        
        System.out.println(min);
    }

    public static void calculation(int r, int c, int hight) {
        int verticalSum = Math.abs(hight - arr[r][0]) + Math.abs(hight - arr[r][1]) + Math.abs(hight - arr[r][2]);
        int horizontalSum = Math.abs(hight - arr[0][c]) + Math.abs(hight - arr[1][c]) + Math.abs(hight - arr[2][c]);

        min = Math.min(min, verticalSum);
        min = Math.min(min, horizontalSum);
    }
}
