package online_test.acm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SillyDragon {
	void run() throws NumberFormatException, IOException {
		Scanner scanner = new Scanner(new File("SillyDragon.txt"));
		int testCount = scanner.nextInt();
		while (testCount-- > 0) {
			Compate compate = new Compate(scanner);
		}

	}

	public static void main(String[] args) {
		try {
			new SillyDragon().run();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	int getInt(String str) {
		return Integer.parseInt(str.trim());
	}
}

class Compate {
	int count;
	String teamName;
	// int teamNameWinCount = 0;
	List<Result> results = new ArrayList<Result>();
	Map<String, Integer> scoreMap = new HashMap<String, Integer>();

	void addResult(String team1, String team2, int score1, int score2) {
		Result r = new Result(team1, team2, score1, score2);
		String winner = r.getWinner();
		if (winner != null) {
			if (scoreMap.containsKey(winner)) {
				scoreMap.put(winner, scoreMap.get(winner));
			} else {
				scoreMap.put(winner, 1);
			}
		}
		results.add(r);
	}

	int getScore(String teamName) {
		int count = 0;
		for (Result r : results) {
			if (teamName.equals(r.getWinner()))
				count++;
		}
		return count;
	}

	public Compate(Scanner scanner) throws NumberFormatException, IOException {
		count = scanner.nextInt();
		int c = count;
		while (c-- > 0) {
			addResult(scanner.next(), scanner.next(), scanner.nextInt(),
					scanner.nextInt());
		}
		teamName = scanner.next();
		int teamNameWinCount = getScore(teamName);
		String maxScoreTeam = getMaxScoreTeam();
		if (maxScoreTeam == null) {
			// 所有人都打成平手，有一盘输的或者平局，把它变成赢得就可以
			int out = -1, i = 0;
			for (Result r : results) {
				if (r.contains(teamName) && r.isNotWin(teamName)) {
					out = i;
					break;
				}
				i++;
			}
			System.out.println(out);
			return;
		}
		int maxScore = scoreMap.get(maxScoreTeam);
		int maxScoreCount = getMaxScoreCount(maxScore);
		if (teamNameWinCount == maxScore) {
			if (maxScoreCount == 1) {
				System.out.println(-2);
			} else {
				// 有一盘输的或者平局，把它变成赢得就可以
				int out = -1, i = 0;
				for (Result r : results) {
					if (r.contains(teamName) && r.isNotWin(teamName)) {
						out = i;
						break;
					}
					i++;
				}
				System.out.println(out);
			}
		} else if (teamNameWinCount + 1 == maxScore) {
			int out = -1, i = 0;
			if (maxScoreCount == 1) {
				for (Result r : results) {
					if (r.contains(teamName) && r.contains(maxScoreTeam)
							&& maxScoreTeam.equals(r.getWinner())) {
						out = i;
						break;
					}
					i++;
				}
				System.out.println(out);

			} else {
				System.out.println(-1);
			}
		} else {
			System.out.println(-1);
		}

	}

	private String getMaxScoreTeam() {
		Iterator<String> iterator = scoreMap.keySet().iterator();
		int maxcount = 0;
		String maxTeam = null;
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (scoreMap.get(name) > maxcount) {
				maxcount = scoreMap.get(name);
				maxTeam = name;
			}
		}
		return maxTeam;
	}

	public int getMaxScoreCount(int max) {
		Iterator<String> iterator = scoreMap.keySet().iterator();
		int count = 0;
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (scoreMap.get(name) == max)
				count++;
		}
		return count;

	}

}

class Result {
	String t1, t2;
	int s1, s2;

	public Result(String team1, String team2, int score1, int score2) {
		t1 = team1;
		t2 = team2;
		s1 = score1;
		s2 = score2;
	}

	public String getWinner() {
		return s1 > s2 ? t1 : (s1 == s2 ? null : t2);
	}

	public boolean contains(String teamName) {
		if (teamName.equals(t1) || teamName.equals(t2)) {
			return true;
		}
		return false;
	}

	public boolean isNotWin(String teamName) {
		if (getWinner() == null || !getWinner().equals(teamName)) {
			return true;
		}
		return false;
	}
}