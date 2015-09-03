package com.google.codejam.gradsof2014;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import util.Tools;

public class ExerciseD {

	String basepath = "C:\\Users\\Administrator\\Desktop\\google\\";
	Scanner scanner;
	FileWriter fileWriter;
	int index = 1;
	int[][] map;
	final int BLOCK = -1, ROAD = 0;
	int[][] steps = new int[10000][2];
	int stepIndex = 0;

	public ExerciseD(String inputFile, String outputFile) throws IOException {
		scanner = new Scanner(new File(basepath + inputFile));
		fileWriter = new FileWriter(new File(basepath + outputFile));
	}

	String findPath(char[][] arr, int startx, int starty, int endx, int endy) {
		initMap(arr);
		//		Tools.println("end:" + endx + "+," + endy);
		//		walk();
		return "";
	}

	void walk(int[][] prePosition) {

	}

	void initMap(char[][] arr) {
		int size = arr.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (arr[i][j] == '.')
					map[i][j] = 0;
				else if (arr[i][j] == '#')
					map[i][j] = -1;
			}
		}
	}

	void run() throws IOException {
		int n = Integer.parseInt(scanner.nextLine());
		while (n-- > 0 && scanner.hasNext()) {

			Tools.println("ss");
			int size = Integer.parseInt(scanner.nextLine());
			Tools.println("" + size);
			char[][] arr = new char[size][size];
			for (int i = 0; i < size; i++) {
				String line = scanner.nextLine();
				for (int j = 0; j < size; j++)
					arr[i][j] = line.charAt(j);
			}
			findPath(arr, scanner.nextInt(), scanner.nextInt(),
					scanner.nextInt(), scanner.nextInt());
			if (scanner.hasNext())
				scanner.nextLine();
		}
		fileWriter.close();
	}

	void writeLine(String str) throws IOException {
		fileWriter.write("Case #" + index + ": " + str);
		index++;
	}

	public static void main(String[] args) throws IOException {
		ExerciseD exe = new ExerciseD("D-small-attempt0000.in",
				"B-small-practice.in");
		exe.run();
	}

}
