package books.beautyofprogram.chapter3;

import java.util.Arrays;

import util.Timer;
import util.Tools;

public class Exercise3_3 {

	int[][] recordArr;

	int count = 0;
	int hit = 0;

	int calculateStringDistance(String a, String b) {
		//		long t1;
		int distance;

		//优化版本
		recordArr = new int[a.length()][b.length()];
		Arrays.fill(recordArr, -1);
		Timer timer = Timer.getInstance();
		timer.start();
		distance = calculate2(a, 0, b, 0);
		//		System.out.println("time:" + (Tools.getNanoTime() - t1));
		timer.clickAndPrintInterval("");
		System.out.println(distance);
		Tools.print("count:" + count + ",hit:" + hit);

		//普通版本
		timer.start();
		//		t1 = Tools.getNanoTime();
		distance = calculate(a, 0, b, 0);
		//		System.out.println("time:" + (Tools.getNanoTime() - t1));
		timer.clickAndPrintInterval("");
		System.out.println(distance);

		return distance;
	}

	int calculate(String a, int aStart, String b, int bStart) {
		int aLen = a.length() - aStart - 1, bLen = b.length() - bStart - 1;
		if (aLen <= 0 || bLen <= 0) {
			return Math.max(aLen, bLen);
		}
		if (a.charAt(aStart) == b.charAt(bStart)) {
			return calculate(a, aStart + 1, b, bStart + 1);
		}
		int distance1 = calculate(a, aStart + 1, b, bStart);
		int distance2 = calculate(a, aStart, b, bStart + 1);
		int distance3 = calculate(a, aStart + 1, b, bStart + 1);

		return 1 + Math.min(Math.min(distance1, distance2), distance3);
	}

	/**
	 * 优化版本
	 * */
	int calculate2(String a, int aStart, String b, int bStart) {
		int aLen = a.length() - aStart - 1, bLen = b.length() - bStart - 1;
		if (aLen <= 0 || bLen <= 0) {
			return Math.max(aLen, bLen);
		}
		if (a.charAt(aStart) == b.charAt(bStart)) {
			return getDistance(a, aStart + 1, b, bStart + 1);
		}
		int distance1 = getDistance(a, aStart + 1, b, bStart);
		int distance2 = getDistance(a, aStart, b, bStart + 1);
		int distance3 = getDistance(a, aStart + 1, b, bStart + 1);

		return 1 + Math.min(Math.min(distance1, distance2), distance3);
	}

	int getDistance(String a, int aStart, String b, int bStart) {
		count++;
		if (recordArr[aStart][bStart] != -1) {
			hit++;
			return recordArr[aStart][bStart];
		}
		int dis = calculate2(a, aStart, b, bStart);
		recordArr[aStart][bStart] = dis;
		return dis;
	}

	public static void main(String[] args) {
		String a = "fsddaaabcdd", b = "fdabcddd";
		new Exercise3_3().calculateStringDistance(a, b);
	}

}
