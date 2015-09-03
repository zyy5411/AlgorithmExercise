package com.huawei;

import java.util.Arrays;

public class TheLightGame {

	int lightNum;

	int aliveLight(int n) {
		lightNum = n;
		boolean isLigthed[] = new boolean[lightNum];
		Arrays.fill(isLigthed, false);
		for (int i = 2; i <= n; i++) {
			int incread = 1;
			while (i * incread < lightNum) {
				isLigthed[i * incread] = !isLigthed[i * incread];
				incread++;
			}
		}
		int count = 0;
		for (int i = 0; i < lightNum; i++) {
			if (isLigthed[i]) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		TheLightGame t = new TheLightGame();
		System.out.println(t.aliveLight(3));
	}

}
