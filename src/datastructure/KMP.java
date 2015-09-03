package datastructure;

import util.Tools;

public class KMP {

	int contains(String str, String subString) {
		int[] next = new int[subString.length()];
		initNext(subString, next);
		Tools.print(next);

		int strindex = 0, subindex = 0;
		while (strindex < str.length() && subindex < subString.length()) {
			if (-1 == strindex
					|| str.charAt(subindex) == subString.charAt(strindex)) {
				strindex++;
				subindex++;
			} else {
				strindex = next[strindex];
			}
		}

		return subindex == subString.length() ? strindex - subString.length()
				: -1;
	}

	void initNext(String str, int[] next) {
		next[0] = -1;
		for (int i = 1; i < str.length(); i++) {
			int start = 0, subStart = -1;

			while (i < str.length()) {
				if (-1 == start || str.charAt(start) == str.charAt(i)) {
					start++;
					subStart++;
				} else {

				}
				//				next[i++] = count;
				//				count++;
				start++;
			}
		}
	}

	public static void main(String[] args) {
		String str = "abcadabcabced";
		String sub = "abcab";
		KMP kmp = new KMP();
		Tools.println("" + kmp.contains(str, sub));
	}

}
