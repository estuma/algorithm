import java.util.*;

/**
 * 프로그래머스 네트워크 https://school.programmers.co.kr/learn/courses/30/lessons/43162
 * 평균 0.10ms, 77MB
 * @author 김신영
 * 네트워크가 몇 개로 연결되어있는지?
 * dfs를 통해 문제 해결
 * 노드의 시작 지점을 기준으로 n번 노드와 연결되어있는지 확인 후 (1인지)
 * 연결되어있다면 그 노드를 기준으로 계속 탐색한다. 
 * 노드 1 기준으로 탐색이 끝나고 만약 방문하지 않은 노드가 있다면 cnt++하고 그 노드 기준으로 탐색한다. ... 계속
 */

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[computers.length];
        int cnt = 0;

        for(int i = 0; i < n; i++) {
            // 방문한적 없으면 그 노드 기준으로 연결된 노드 탐색
        	// 방문한 적 없는 노드가 있다는 것은 연결되지 않았다는것이므로 카운트한다.
            if(visited[i] == false) {
                dfs(i, computers, visited);
                cnt++;
            }   
        }
        
        return cnt;
    }
    
    public void dfs(int start, int[][] computers, boolean[] visited){
        // 방문표시
    	visited[start] = true;
        // start 노드와 연결되어있는 노드 검사
        for(int i = 0; i < computers.length; i++) {
            // 방문한적 없고, 연결되어있으면 
            if(visited[i] == false && computers[start][i] == 1) {
                dfs(i, computers, visited);
            }
        }
    }
    
}
