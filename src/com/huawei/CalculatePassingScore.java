package com.huawei;

import java.util.Arrays;

import util.Tools;

public class CalculatePassingScore {

	int getPassingScore(int[] scores) {
		if (scores.length <= 0)
			return 60;
		Arrays.sort(scores);
		if (scores[0] >= 60)
			return 60;
		int score = 60;
		while (score >= 10) {
			int i = 0;
			for (; i < scores.length; i++) {
				if (scores[i] >= score)
					break;
			}
			Tools.println(";;" + i);
			if ((scores.length - i) * 10 / scores.length >= 6) {
				return score;
			}
			score -= 10;
		}

		return 10;
	}

	public static void main(String[] args) {
		int arr[] = new int[] { 61, 40, 49, 30, 20, 10, 70, 80, 90, 99 };
		CalculatePassingScore c = new CalculatePassingScore();
		System.out.println(c.getPassingScore(arr));

	}
}
