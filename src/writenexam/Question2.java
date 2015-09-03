package writenexam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.Tools;

/**
 * 找出0-100里，任意2个素数之和不能够到达的数
 */
public class Question2 {

	//找到a<b<c && a+b=c
	void printIt(int n) {
		boolean[] isSushu = new boolean[n + 1];
		Arrays.fill(isSushu, true);
		for (int i = 2; i <= n; i++) {
			if (isSushu[i])
				for (int t = 2; t * i <= n; t++) {
					isSushu[t * i] = false;
				}
		}
		//		for (int i = 1; i <= n; i++) {
		//			if (isSushu[i])
		//				Tools.println(":" + i);
		//		}
		List<Integer> suShu = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++)
			if (isSushu[i])
				suShu.add(i);
		Tools.print(suShu);
		boolean noreachsum[] = new boolean[n + 1];
		Arrays.fill(noreachsum, true);
		for (int i = 0; i < suShu.size(); i++)
			for (int j = i; j < suShu.size(); j++) {
				if (suShu.get(i) + suShu.get(j) <= n) {
					noreachsum[suShu.get(i) + suShu.get(j)] = false;
				}
			}
		for (int i = 1; i <= n; i++) {
			if (noreachsum[i])
				Tools.print(" " + i);
		}
	}

	public static void main(String[] args) {
		new Question2().printIt(100);
	}

}
