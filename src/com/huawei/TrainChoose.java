package com.huawei;


/***
 * http://company.dajie.com/huawei/job/nj/topic/206708/detail
 * @author Administrator
 *
 */
public class TrainChoose {

	String[][] lines = {
			{ "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "T1",
					"A10", "A11", "A12", "A13", "T2", "A14", "A15", "A16",
					"A17" },
			{ "B1", "B2", "B3", "B4", "B5", "T1", "B6", "B7", "B8", "B9",
					"B10", "T2", "B11", "B12", "B13", "B14", "B15" } };
	int least = Integer.MAX_VALUE;

	int leastStage(String from, String to) {
		if (from.equals(to))
			return 1;
		if (from.contains("T"))
			leastDFS(to, lines[0], -1, findIndex(lines[0], from), 1);
		leastDFS(to, lines[from.charAt(0) - 'A'], -1,
				findIndex(lines[from.charAt(0) - 'A'], from), 1);

		System.out.println(least);
		return least;
	}

	int findIndex(String[] line, String str) {
		for (int i = 0; i < line.length - 1; i++)
			if (line[i].equals(str))
				return i;
		return -1;
	}

	int leastDFS(String to, String[] path, int direction, int index, int count) {

		if (path[0].contains("A")) {
			if (index == path.length)
				index = 0;
			else if (index == -1)
				index = path.length - 1;
		}
		if (count > lines[0].length + lines[1].length || index < 0
				|| index >= path.length)
			return 0;
		String from = path[index];
		if (from.equals(to)) {
			least = Math.min(least, count);
			return count;
		}
		//		Tools.println(from);
		if (from.contains("T")) {
			leastDFS(to, lines[0], 0, findIndex(lines[0], from) - 1, count + 1);
			leastDFS(to, lines[0], 1, findIndex(lines[0], from) + 1, count + 1);
			leastDFS(to, lines[1], 0, findIndex(lines[1], from) - 1, count + 1);
			leastDFS(to, lines[1], 1, findIndex(lines[1], from) + 1, count + 1);
		} else {

			if (0 == direction || direction == -1) {
				leastDFS(to, path, 0, index - 1, count + 1);
			}
			if (1 == direction || direction == -1) {
				leastDFS(to, path, 1, index + 1, count + 1);
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		TrainChoose t = new TrainChoose();
		t.leastStage("A1", "A17");
	}
}
