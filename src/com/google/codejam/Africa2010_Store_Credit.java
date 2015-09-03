package com.google.codejam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Africa2010_Store_Credit {

	String basepath = "C:\\Users\\Administrator\\Desktop\\google\\";
	Scanner scanner;
	FileWriter fileWriter;
	int index = 1;

	public Africa2010_Store_Credit(String inputFile, String outputFile)
			throws IOException {
		scanner = new Scanner(new File(basepath + inputFile));
		fileWriter = new FileWriter(new File(basepath + outputFile));
	}

	String getReverse(String str) {
		String[] arr = str.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; i--) {
			sb.append(arr[i]);
			if (i != 0)
				sb.append(" ");
		}
		return sb.toString();
	}

	String[] getIndexs(int sum, int n, int[] arr) {
		int price1, price2;
		for (int i = 0; i < n; i++) {
			if ((price1 = arr[i]) > sum)
				continue;
			price2 = sum - price1;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] == price2) {
					return new String[] { "" + (i + 1), "" + (j + 1) };
				}
			}
		}
		return new String[] { "null", "null" };
	}

	void run() throws IOException {
		int n = Integer.parseInt(scanner.nextLine());
		while (n-- > 0 && scanner.hasNext()) {
			int sum = readNextLineInt(scanner);
			int count = readNextLineInt(scanner);
			String arr = scanner.nextLine();
			String[] indexs = getIndexs(sum, count, toIntegerArr(count, arr));
			writeLine(indexs);
			System.out.println(arr);
			//			String reverseStr = getReverse(line);
			//			writeLine(reverseStr);
		}
		fileWriter.close();
	}

	int[] toIntegerArr(int count, String str) {
		System.out.println(str);
		String[] strArr = str.split(" ");
		int[] intarr = new int[count];
		for (int i = 0; i < strArr.length; i++) {
			System.out.println(strArr[i]);
			intarr[i] = Integer.parseInt(strArr[i]);
		}
		return intarr;
	}

	int readNextLineInt(Scanner scanner) {
		String line = scanner.nextLine();
		return Integer.parseInt(line);
	}

	void writeLine(String... str) throws IOException {
		StringBuilder line = new StringBuilder("Case #" + index + ": ");
		for (String s : str) {
			line.append(s);
			line.append(" ");
		}
		line.replace(line.length() - 1, line.length(), "\n");
		fileWriter.write(line.toString());
		index++;
	}

	public static void main(String[] args) throws IOException {
		Africa2010_Store_Credit a = new Africa2010_Store_Credit(
				"A-large-practice.in", "A-large-practice.out");
		a.run();

	}
}
