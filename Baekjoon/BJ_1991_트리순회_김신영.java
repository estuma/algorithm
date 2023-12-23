import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <pre>
 * BJ_1991_트리순회
 * 전위순회, 중위순회, 후위순회한 결과 출력
 * ---------- ----------  
 * Node 클래스를 만들어 이진트리의 자식노드를 저장한다.
 * 1. 전위순회: 루트에서 왼쪽자식방향으로 탐색하며 먼저 값을 출력해준다. 왼쪽-> 오른쪽 탐색
 * 2. 중위순회: 왼쪽 -> 루트 -> 오른쪽
 * 3. 후위순회: 왼쪽 -> 오른쪽 -> 루트
 * </pre>
 * @author 김신영
 *
 */
public class BJ_1991_트리순회_김신영 {
	
	// 자식노드 저장하기 위한 클래스
	public static class Node {
		String data;
		String left;
		String right;
		
		Node(String data, String left, String right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	static StringBuilder sb = new StringBuilder();
	static Map<String, Node> map = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		// 한줄씩 읽으며 자식노드 설정
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String data = st.nextToken();
			map.put(data, new Node(data, st.nextToken(), st.nextToken()));
		}
		
		
		preorder("A");	// 전위
		sb.append("\n");
		inorder("A");	// 중위
		sb.append("\n");
		postorder("A");	// 후위
		
		System.out.println(sb.toString());
	}
	
	public static void preorder(String start) {
		// 루트(자신) 출력 -> 왼쪽 자식 모두 탐색 -> 오른쪽 자식 모두 탐색
		sb.append(map.get(start).data);
		if(!map.get(start).left.equals(".")) {
			preorder(map.get(start).left);
		}
		if(!map.get(start).right.equals(".")) {
			preorder(map.get(start).right);
		}
	}
	
	public static void inorder(String start) {
		// 왼쪽 자식 모두 탐색 -> 자기 출력 -> 오른쪽 자식 모두 탐색
		if(!map.get(start).left.equals(".")) {
			inorder(map.get(start).left);
		}
		sb.append(map.get(start).data);
		if(!map.get(start).right.equals(".")) {
			inorder(map.get(start).right);
		}
	}
	
	public static void postorder(String start) {
		//왼쪽 자식을 모두 탐색 -> 오른쪽 자식 모두 탐색 -> 루트(자신) 마지막 출력
		if(!map.get(start).left.equals(".")) {
			postorder(map.get(start).left);
		} 
		if(!map.get(start).right.equals(".")) {
			postorder(map.get(start).right);
		}
		sb.append(map.get(start).data);
	}
}
