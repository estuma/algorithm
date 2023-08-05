import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 * D4_1218_SW�����ذ�⺻4����_��ȣ¦����
 * 17,428kb, 112 ms
 * ---------- ���̵�� ---------- 
 * ��ȣ�� ��� ¦�� �´��� �˻��ϴ°��̹Ƿ� ���� �̿�
 * �־��� ���ڿ��� ���������� �˻��ϸ�
 * 1. ���°�ȿ�̸� ���ÿ� �ְ�
 * 2. �ݴ°�ȣ�̸� ���ÿ��� ���� ���� ¦�� �´��� �˻��Ѵ�.
 * 3. ¦�� ���� ������ �ش� �׽�Ʈ���̽� -> �� ��ȿ���� ���� ó��
 * 4. �� ����ϰ� �ݺ�
 * </pre>
 * @author ��ſ�
 *
 */
public class D4_1218_SW�����ذ�⺻4����_��ȣ¦���� {

	public static void main(String[] args) throws Exception {
		// �Է¹ޱ����� ���� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// �ݴ� ��ȣ�� ���°�ȿ���� �˻��ϱ� ���� ����
		// ¦�� �Ǵ� ��ȣ���� �ε��� ���� �����ϰ� ��
		List<Character> open = Arrays.asList('[', '{', '(', '<');
		List<Character> close = Arrays.asList(']', '}', ')', '>');
		
		// �׽�Ʈ���̽� 10��
		for (int test_case = 1; test_case <= 10; test_case++) {
			// ���� ����
			Stack<Character> stack = new Stack<>();
			// 1. ù�� �Է�: ���ڿ� ����
			int n = Integer.parseInt(br.readLine());
			// 2. �ι�° �� �Է� : ��ȣ���ڿ�
			String str = br.readLine();
			// ��ȿ���� �ƴ��� ������ ����
			int answer = -1;

			// ���ڿ� ���̸�ŭ �˻�
			for (int i = 0; i < str.length(); i++) {
				// ���� �˻��ϴ� ��ġ�� ��ȣ�� ���� ��ȣ�̸�
				if (open.contains(str.charAt(i))) {
					// ���ÿ� �ִ´�.
					stack.push(str.charAt(i));
				} 
				// ���� �˻��ϴ� ��ġ�� ��ȣ�� �ݴ� ��ȣ�̸� ���ÿ��� ���� �ϳ� �̾Ƽ� ���Ѵ�.
				else {
					// ���ÿ��� ������ ���� ���ÿ� ���� ����ִ��� Ȯ��
					if (stack.size() > 0) {
						// ���ÿ��� ���� �ϳ� ������
						char tmp = stack.pop();
						// ����Ʈ�� �ε����� ���Ͽ� ���� ¦�� �´��� �˻��Ѵ�.
						if (open.indexOf(tmp) != close.indexOf(str.charAt(i))) {
							// ���� ������ ���� 0���� �����ϰ� �ݺ��� ����
							answer = 0;
							break;
						}
					} else {	// ���ÿ� ���� ������ ¦�� ���� �ʴ� ���̹Ƿ� ���� ���������� ���� 0���� �����ϰ� �ݺ��� ����
						answer = 0;
						break;
					}
				}
			}
			
			// answer ������ 0�� �ƴ϶�� ��ȿ���� ���� ��츦 �������� ���� ���̹Ƿ� �� 1
			if (answer == -1) {
				answer = 1;
			}

			// �� ���
			System.out.println("#" + test_case + " " + answer);
		}
	}

}
