package online_test.zoj;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	Stack<Character> stack;
	final char IN = 'i', OUT = 'o';
	char[] a, b;
	char[] result;
	int resultIndex;

	void printResult(String a, String b) {
		this.a = a.toCharArray();
		this.b = b.toCharArray();
		stack = new Stack<Character>();
		result = new char[2 * a.length()];
		resultIndex = 0;
		backTrack(0, 0);
	}

	private void print(char[] arr) {
		for (char t : arr)
			System.out.print(String.format("%c ", t));
		System.out.println();
	}

	void backTrack(int aIndex, int bIndex) {
		if (bIndex == b.length) {
			print(result);
			return;
		}

		//a中有剩余元素
		if (aIndex < a.length) {
			stack.push(a[aIndex]);
			result[resultIndex++] = IN;
			backTrack(aIndex + 1, bIndex);
			stack.pop();
			resultIndex--;
		}
		//如果栈顶元素与之相同
		if (!stack.isEmpty() && stack.peek() == b[bIndex]) {
			char c = stack.pop();
			result[resultIndex++] = OUT;
			backTrack(aIndex, bIndex + 1);
			//恢复
			resultIndex--;
			stack.push(c);
		}
	}

	public static void main(String[] args) {
		Main p = new Main();
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String a = scanner.next();
			String b = scanner.next();
			System.out.println("[");
			p.printResult(a, b);
			System.out.println("]");
		}

	}
}
