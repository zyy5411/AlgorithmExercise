package com.google.codejam.gradsof2014;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ExerciseC {

	String basepath = "C:\\Users\\Administrator\\Desktop\\google\\";
	Scanner scanner;
	FileWriter fileWriter;
	int index = 1;
	List<Integer> oddList, evenList, oddIndexs;

	public ExerciseC(String inputFile, String outputFile) throws IOException {
		scanner = new Scanner(new File(basepath + inputFile));
		fileWriter = new FileWriter(new File(basepath + outputFile));
		oddList = new ArrayList<Integer>();
		evenList = new ArrayList<Integer>();
		oddIndexs = new ArrayList<Integer>();
	}

	String getOrder(int n, String line) {
		oddList.clear();
		evenList.clear();
		oddIndexs.clear();
		String[] worths = line.split(" ");
		for (int i = 0; i < n; i++) {
			int worth = Integer.parseInt(worths[i]);
			if ((worth & 1) != 0) {
				oddList.add(worth);
				oddIndexs.add(i);
			} else {
				evenList.add(worth);
			}
		}
		Collections.sort(oddList);
		Collections.sort(evenList, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		int[] arr = new int[n];
		int index = 0, evenIndex = 0, oddIndex = 0;
		for (int i = 0; i < n; i++) {
			if (index < oddIndexs.size() && i == oddIndexs.get(index)) {
				arr[i] = oddList.get(oddIndex++);
				index++;
			} else {
				arr[i] = evenList.get(evenIndex++);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i : arr) {
			sb.append(i);
			sb.append(" ");
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		return sb.toString();
	}

	void run() throws IOException {
		int n = Integer.parseInt(scanner.nextLine());
		while (n-- > 0 && scanner.hasNext()) {
			int count = Integer.parseInt(scanner.nextLine());
			String line = scanner.nextLine();
			writeLine(getOrder(count, line));
		}
		fileWriter.close();
	}

	void writeLine(String str) throws IOException {
		fileWriter.write("Case #" + index + ": " + str + "\n");
		index++;
	}

	public static void main(String[] args) throws IOException {
		ExerciseC exe = new ExerciseC("C-large.in", "C-large-practice.out");
		exe.run();
	}

}
