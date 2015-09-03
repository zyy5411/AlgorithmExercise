package books.the_art_of_program.chapter1;

import util.Tools;

/**
 * 左旋字符串
 * @author Administrator
 *
 */
public class RotateString {

	int count = 0, count2 = 0;

	void rotate(char[] str, int m) {
		rotate(str, m, 0, str.length - 1);
		//		rotate2(str, m, 0, str.length - 1);
		Tools.println("exchange count=" + count);
	}

	void rotate(char[] str, int m, int start, int end) {
		int index = start, len = end - start + 1;
		m %= len;
		while (index + 2 * m - 1 <= end) {
			exchange(str, index, index + m, m);
			index += m;
		}
		int remain = end - index - m + 1;
		if (remain > 0) {
			exchange(str, index, end - remain + 1, remain);
			rotate(str, m - remain, index + remain, end);
		}
	}

	//纯递归 ，分组交换的思路
	void rotate2(char[] str, int m, int start, int end) {
		//m=len，则不左旋
		if (start >= end || m == (end - start + 1))
			return;
		int remain = end - start + 1 - m;
		if (m < remain) {
			exchange(str, start, end - m + 1, m);
			rotate2(str, m, start, start + remain - 1);
		} else if (remain < m) {
			exchange(str, start, end - remain + 1, remain);
			rotate2(str, m - remain, start + remain, end);
		} else {
			exchange(str, start, start + m, m);
		}
	}

	void exchange(char[] str, int s1, int s2, int len) {
		while (len-- > 0) {
			char t = str[s1];
			str[s1] = str[s2];
			str[s2] = t;
			++s1;
			++s2;
			count++;
		}
	}

	public static void main(String[] args) {
		RotateString r = new RotateString();
		char[] str1 = "abcdefg".toCharArray();
		r.rotate(str1, 3);
		Tools.print(str1);
	}
}
