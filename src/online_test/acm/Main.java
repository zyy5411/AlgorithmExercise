package online_test.acm;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

enum DIRECTION {
	UP, DOWN, LEFT, RIGHT,NULL
};

public class Main {

//	static long time1 ;
	void run() throws FileNotFoundException {
		 Scanner scanner = new Scanner(System.in);
//		Scanner scanner = new Scanner(new File("main.txt"));
		Board board = new Board();
		int time = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if ("".equals(line.trim()))
				continue;
			if (time++ > 0) {
				System.out.println("\n");
			}
			board.initALine(1, line);
			for (int i = 2; i <= 6; i++)
				board.initALine(i, scanner.nextLine());
			int count = scanner.nextInt();
			while (count-- > 0) {
				int i = scanner.nextInt();
				int j = scanner.nextInt();
				board.startAt(i - 1, j - 1);
			}
			printResult(board);
		}
	}

	private void printResult(Board board) {
		for (int j = 0; j < 6; j++) {
			int[] arr = board.array[j];
			for (int i = 0; i < 6; i++) {
				System.out.print(arr[i]);
				System.out.print(i < 5 ? " " : "");
			}
			System.out.print(j < 5 ? "\n" : "");
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
//		Main.time1 = Tools.getMilionTime();
		new Main().run();
//		System.out.println("\ntime:"+(Tools.getMilionTime()-time1));
	}

}

class Board {
	int[][] array = new int[6][6];
	Queue<Water> waters = new LinkedList<Water>();
	List<Integer> splitList = new ArrayList<Integer>();
	int time = 0;

	void initALine(int column, String line) {
		String[] sp = line.split(" ");
		for (int i = 0; i < 6; i++)
			array[column - 1][i] = Integer.parseInt(sp[i]);
	}

	void startAt(int i, int j) {
		putAt(i, j);
		while (!waters.isEmpty()) {
			waters.add(Water.create(0, 0, DIRECTION.NULL));
			while(true){
				Water water = waters.poll();
				if (water == null)
					continue;
				if(water.dir == DIRECTION.NULL)
					break;
				if (array[water.i][water.j] == 0) {
					Water w = water.move();
					if (null != w) {
						waters.add(w);
					}
				} else if (array[water.i][water.j] == 4) {
					splitList.add(water.i);
					splitList.add(water.j);
					array[water.i][water.j]++;
				} else {
					array[water.i][water.j]++;
				}
			}

			for (int index = 0; index < splitList.size(); index += 2) {
//				if (array[splitList.get(index)][splitList.get(index + 1)] >= 4) {
					array[splitList.get(index)][splitList.get(index + 1)] = 0;
					splitWater(splitList.get(index), splitList.get(index + 1));
//				}
			}
			splitList.clear();

		}
	}

	// put 以0开始
	void putAt(int i, int j) {
		if (array[i][j] == 4) {
			splitWater(i, j);
		} else {
			array[i][j]++;
		}
	}

	// 向四周发散水珠
	void splitWater(int i, int j) {
		array[i][j] = 0;
		waters.add(Water.create(i - 1, j, DIRECTION.UP));
		waters.add(Water.create(i + 1, j, DIRECTION.DOWN));
		waters.add(Water.create(i, j + 1, DIRECTION.RIGHT));
		waters.add(Water.create(i, j - 1, DIRECTION.LEFT));
	}
}

class Water {
	public int i, j;
	public int second;

	DIRECTION dir;

	private Water(int i, int j, DIRECTION dir) {
		this.i = i;
		this.j = j;
		this.dir = dir;
	}

	public static Water create(int i, int j, DIRECTION dir) {
		if (i < 0 || i > 5 || j < 0 || j > 5) {
			return null;
		}
		return new Water(i, j, dir);
	}

	Water move() {
		switch (dir) {
		case UP:
			i--;
			return testPosition(i) ? this : null;
		case DOWN:
			i++;
			return testPosition(i) ? this : null;
		case RIGHT:
			j++;
			return testPosition(j) ? this : null;
		case LEFT:
			j--;
			return testPosition(j) ? this : null;
		}
		return null;
	}

	boolean testPosition(int i) {
		if (i < 0 || i > 5)
			return false;
		return true;
	}
}
