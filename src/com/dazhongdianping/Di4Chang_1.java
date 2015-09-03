package com.dazhongdianping;

import java.util.Scanner;

public class Di4Chang_1 {

	long run(long from, long to) {
		from = (long) Math.sqrt(from);
		to = (long) Math.sqrt(to);
		for (long i = from; i < to + 1; i++) {

		}

		return 0;
	}

	public static void main(String[] args) {
		Di4Chang_1 d = new Di4Chang_1();
		Scanner scanner = new Scanner(System.in);
		long from = scanner.nextLong();
		long to = scanner.nextLong();
		d.run(from, to);
	}

}
