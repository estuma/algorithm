import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * <pre>
 * ��ȣ������
 * ù ��° ���� 1 ���ҽ�Ű�� �� �ڷ� ������, 2 ���ҽ�Ű�� �� �ڷ� ������ ���� 5���� ����
 * ���ڰ� ������ �� ���� 0���� �۾����� ��� ���� 0�̰�, ���α׷��� �����
 * ť�� ����ؼ� ����Ŭ 5���� �ݺ��ǰ� �տ��� ���� �ڷ� �ִ� �۾� �ݺ��ϸ� �ȴ�.
 * </pre>
 * @author ��ſ�
 *
 */
public class D3_��ȣ������_��ſ� {
	
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		
		for(int test_case = 1; test_case <= 10; test_case++) {
            T = sc.nextInt();
            // ��ȣ�� �����ϴ� ������ �����ϱ� ���� ť ����
            Queue<Integer> queue = new ArrayDeque<>();
            // ���� ������ ����
            StringBuilder sb = new StringBuilder();
            
            // �Է°� �ޱ�
            for(int i = 0; i < 8; i++) { 
            	queue.offer(sc.nextInt()); 
            }
            
            // 0�� ���ö����� �ݺ�
            boolean zero = false;
            while(!zero) {
            	// ����Ŭ 5 �ݺ�
                for(int i = 1; i <= 5; i++) {
                	// ���� �տ� ���� ����
                    int tmp = queue.poll();
                    // ���� ����Ŭ�� ������°��ŭ �� ���� �ٽ� �ڷ� �ִ´�.
                    if(tmp - i > 0)		// ���� 0���� ũ�� ��� ����
                    	queue.offer(tmp - i);
                    else {				// 0���� ���� ���� ������ ��ȣ������ �����Ѵ�.
                        queue.offer(0);
                        zero = true;
                        break;
                    }
                }
            }
            
            // ť ��ȸ�ϸ� �� ����
            while(queue.size() > 0) {
            	sb.append(queue.poll()+ " ");
            }
            
            System.out.println("#" + T + " " + sb);
		}
	}
}