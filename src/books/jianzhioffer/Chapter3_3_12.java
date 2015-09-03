package books.jianzhioffer;

import java.util.Arrays;

public class Chapter3_3_12 {

	void printToMaxOfNDigitsRecurse(int n) {
		char[] digit = new char[n];
		Arrays.fill(digit, '0');
		printRecurse(digit, 0, n);
	}

	void printRecurse(char[] digit, int index, int n) {
		if (index >= n) {
			//			System.out.println(digit);
			return;
		}
		for (char c = '0'; c <= '9'; c++) {
			digit[index] = c;
			printRecurse(digit, index + 1, n);
		}
	}

	void printToMaxOfNDigits(int n) {
		char[] digit = new char[n];
		Arrays.fill(digit, '0');
		while (increase(digit)) {
			//			System.out.println(digit);
		}
	}

	boolean increase(char[] digit) {
		for (int i = digit.length - 1; i >= 0; i--) {
			if (digit[i] < '9') {
				digit[i]++;
				return true;
			} else {
				digit[i] = '0';
				if (0 == i)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Chapter3_3_12 c = new Chapter3_3_12();
		c.printToMaxOfNDigits(8);
		System.out.println("finish");
		c.printToMaxOfNDigitsRecurse(8);
		System.out.println("finish");
	}
}
