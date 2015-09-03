package books.jianzhioffer;

import java.util.Stack;

public class Question22 {

	boolean isLeagalOrder(int[] pushOrder, int[] popOrder) {
		int pushIndex = 0, popIndex = 0;
		int[] stack = new int[pushOrder.length];
		int stackIndex = -1;
		while (true) {
			if (pushIndex < pushOrder.length
					&& pushOrder[pushIndex] == popOrder[popIndex]) {
				pushIndex++;
				popIndex++;
			} else {
				if (stackIndex >= 0
						&& pushOrder[stackIndex] == popOrder[popIndex]) {
					stackIndex--;
					popIndex++;
				} else {
					if (pushIndex == pushOrder.length)
						return false;
					stackIndex++;
					stack[stackIndex] = pushOrder[pushIndex];
					pushIndex++;
				}
			}
			if (popIndex == popOrder.length)
				return true;
		}
	}

	boolean isLeagalOrder1(int[] pushOrder, int[] popOrder) {
		Stack<Integer> stack = new Stack<Integer>();
		int pushIndex = 0, popIndex = 0;
		while (true) {
			if (pushIndex < pushOrder.length) {
				stack.push(pushOrder[pushIndex]);
				pushIndex++;
			}
			if (stack.peek() == popOrder[popIndex]) {
				stack.pop();
				popIndex++;
			} else {
				if (pushIndex == pushOrder.length)
					return false;
			}
			if (popIndex == popOrder.length) {
				return true;
			}
		}
	}

	public static void main(String[] args) {
		Question22 q = new Question22();
		System.out.println(q.isLeagalOrder(new int[] { 1, 2, 3, 4, 5 },
				new int[] { 4, 5, 3, 2, 1 }));
		System.out.println(q.isLeagalOrder1(new int[] { 1, 2, 3, 4, 5 },
				new int[] { 4, 1, 3, 2, 1 }));
	}
}
