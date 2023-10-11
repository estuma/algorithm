import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_17472_다리만들기
 * ---------- ----------
 * N*M 나라
 * 다리의 길이는 2 이상, 일직선
 * 모든 섬을 연갈하는 다리 길이의 최솟값, 연결할 수 없으면 -1 출력
 * ---------- ----------
 * 1. 섬 개수 세기
 * 2. 다리 연결하는 경우의수 -> 순열로 만들기
 * 3. 다리 연결 -> BFS 탐색....
 * 
 * </pre>
 * 
 * @author 김신영
 *
 */

public class BJ_17472_다리만들기2_김신영 {

    static int N, M, min, number;
    static int[] order, dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };
    static int[][] arr;
    static List<int[]>[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 섬 개수 세기
        number = 0;
        boolean[][] isVisited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1 && !isVisited[i][j]) {
                    cntIsland(i, j, ++number, isVisited);
                }
            }
        }

        // 섬 좌표 넣을 리스트
        list = new ArrayList[number + 1];
        for (int i = 0; i < number + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != 0) {
                    list[arr[i][j]].add(new int[] { i, j });
                }
            }
        }
        
        for (int i = 0; i < list.length; i++) {
            System.out.print(i + ": ");
            for (int[] tmp : list[i]) {
                System.out.print(Arrays.toString(tmp) + " ");
            }
            System.out.println();
        }
        
        // 2. 다리 연결하는 경우의수 -> 최소신장트리 / 간선으로
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // 다리 연결 간선 가중치
        for (int i = 1; i <= number; i++) {
            for (int j = i + 1; j <= number; j++) {
                if (i != j) {
                    int tmp = bridge(i, j);
                    System.out.println(i + " -> " + j + " " + tmp);
                    
                    if (tmp != -1) {
                        pq.add(new int[] { i, j, tmp });
                        pq.add(new int[] { j, i, tmp });
                    }
                }
            }
        }

        int cnt = 0;
        int answer = 0;
        boolean[] isUsed = new boolean[number + 1];
        while (cnt < number - 1) {
        	if(pq.isEmpty()) {
        		System.out.println("empty");
        		break;
        	}
        	
//        	System.out.println(Arrays.toString(isUsed));
        	
            int[] curr = pq.poll();
            
//            System.out.println(Arrays.toString(curr));

            // 방문한 적 있으면 continue
            if (isUsed[curr[1]]) {
                continue;
            }

            System.out.println("-> " + Arrays.toString(curr));
            answer += curr[2];
            isUsed[curr[0]] = true;
//            isUsed[curr[1]] = true;
            cnt++;
        }

        System.out.println(cnt == number - 1 ? answer : -1);
    }

    public static int bridge(int from, int to) {
        int distance = Integer.MAX_VALUE;
        for (int[] curr : list[from]) {
            for (int d = 0; d < 4; d++) {
            	
                for (int i = 1; i < 10; i++) {
                    int newR = curr[0] + dr[d] * i;
                    int newC = curr[1] + dc[d] * i;

                    // 범위
                    if (newR < 0 || newR >= N || newC < 0 || newC >= M) {
                        break;
                    }

                    // 목적지가 아닌데 0도 아니면 섬 -> 갈 수 없음
                    if (arr[newR][newC] != to && arr[newR][newC] != 0) {
                        break;
                    }

                    // 목적지에 도달했고, 길이가 1보다 긴 경우 갱신
                    if (arr[newR][newC] == to) {
                        if (i > 2) {
                            distance = Math.min(distance, i);
                            break;
                        } else {
                            break;
                        }
                    }
                }
                
            }
        }

        return (distance == Integer.MAX_VALUE) ? -1 : distance - 1;
    }

    public static void cntIsland(int r, int c, int number, boolean[][] isVisited) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { r, c });
        isVisited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            arr[curr[0]][curr[1]] = number;

            for (int d = 0; d < 4; d++) {
                int newR = curr[0] + dr[d];
                int newC = curr[1] + dc[d];

                if (newR < 0 || newR >= N || newC < 0 || newC >= M || isVisited[newR][newC]) {
                    continue;
                }

                isVisited[newR][newC] = true;
                if (arr[newR][newC] == 1) {
                    queue.add(new int[] { newR, newC });
                }
            }
        }
        
        
    }

}