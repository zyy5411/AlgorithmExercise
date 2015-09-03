package datastructure.graphics;

import java.util.Arrays;

import util.Tools;

public class ShortestDistance {
	int MAX_VERTICES = 6;
	int UNREACH = Integer.MAX_VALUE;
	final int cost[][] = { { 0, 50, 10, UNREACH, 45, UNREACH },
			{ UNREACH, 0, 15, UNREACH, 10, UNREACH },
			{ 20, UNREACH, 0, 15, UNREACH, UNREACH },
			{ UNREACH, 20, UNREACH, 0, 35, UNREACH },
			{ UNREACH, UNREACH, 30, UNREACH, 0, UNREACH },
			{ UNREACH, UNREACH, UNREACH, 3, UNREACH, 0 } };

	boolean isTested[] = new boolean[MAX_VERTICES];
	int shortestDis[] = new int[MAX_VERTICES];

	void shortestPath(int v) {
		Arrays.fill(isTested, false);
		shortestDis = Arrays.copyOf(cost[v], MAX_VERTICES);
		shortestPath(v, cost);
		Tools.print(shortestDis);
		shortestPathDijikstra(v, cost);
		Tools.print(shortestDis);
	}

	//单源最短路径：递归，好像是错误的！不能获得最优路径
	void shortestPath(int v, int cost[][]) {
		if (isTested[v])
			return;
		isTested[v] = true;
		for (int i = 0; i < MAX_VERTICES; i++) {
			if (cost[v][i] == UNREACH)
				continue;
			if (shortestDis[i] > shortestDis[v] + cost[v][i]) {
				shortestDis[i] = shortestDis[v] + cost[v][i];
			}
			shortestPath(i, cost);
		}
	}

	//单源最短路径：Dijikstra
	void shortestPathDijikstra(int v, int cost[][]) {
		while (-1 != (v = getLeastV(shortestDis, isTested))) {
			isTested[v] = true;
			for (int j = 0; j < MAX_VERTICES; j++)
				if (cost[v][j] != UNREACH
						&& shortestDis[j] > shortestDis[v] + cost[v][j]) {
					shortestDis[j] = shortestDis[v] + cost[v][j];
				}
		}
	}

	int getLeastV(int dis[], boolean istested[]) {
		int index = -1;
		int least = Integer.MAX_VALUE;
		for (int i = 0; i < dis.length; i++) {
			if (!istested[i] && dis[i] < least) {
				index = i;
				least = dis[i];
			}
		}
		return index;
	}

	public static void main(String[] args) {
		ShortestDistance shortestDistance = new ShortestDistance();
		shortestDistance.shortestPath(0);
	}

}
