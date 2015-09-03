package books.jianzhioffer;

import java.util.Arrays;

import util.Tools;

public class Question33 {

	String getMinCompose(int[] arr) {
		CompareString[] strArr = new CompareString[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strArr[i] = new CompareString(arr[i]);
		}
		Arrays.sort(strArr);
		StringBuilder sb = new StringBuilder();
		for (CompareString s : strArr) {
			sb.append(s);
		}

		return sb.toString();
	}

	class CompareString implements Comparable<CompareString> {

		String str;

		public CompareString(int i) {
			this.str = String.format("%d", i);
			Tools.println(":" + str);
		}

		@Override
		public int compareTo(CompareString paramT) {
			String newStr = str + paramT.str;
			return newStr.compareTo(paramT.str + str);
		}

		@Override
		public String toString() {
			return str;
		}

	}

	public static void main(String[] args) {
		int[] arr = { 3, 32, 31, 351, 360, 378 };
		Question33 q = new Question33();
		System.out.println(q.getMinCompose(arr));
	}
}
