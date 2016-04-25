package hack.leetcode.dev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example:

 Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

 0 0 0
 0 0 0
 0 0 0
 Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

 1 0 0
 0 0 0   Number of islands = 1
 0 0 0
 Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

 1 1 0
 0 0 0   Number of islands = 1
 0 0 0
 Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

 1 1 0
 0 0 1   Number of islands = 2
 0 0 0
 Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

 1 1 0
 0 0 1   Number of islands = 3
 0 1 0
 We return the result as an array: [1, 1, 2, 3]
 */
public class NumberOfIslandII {
	int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		List<Integer> res = new ArrayList<Integer>();

		if (m == 0 || n == 0 || positions == null || positions.length == 0) {
			return res;
		}

		int[] partents = new int[m * n];
		Arrays.fill(partents, -1);

		int count = 0;

		for (int[] position : positions) {
			int parent = n * position[0] + position[1];
			partents[parent] = parent;
			count++;

			for (int[] dir : dirs) {
				int x = position[0] + dir[0];
				int y = position[1] + dir[1];

				int idx = x * n + y;
				if (x < 0 || y < 0 || x >= m || y >= n || partents[idx] == -1) {
					continue;
				}

				int findParent = findIsLand(partents, idx);
				if (findParent != parent) {
					partents[findParent] = parent;
					count--;
				}
			}
			res.add(count);
		}

		return res;
	}

	private int findIsLand(int[] parents, int idx) {
		while (parents[idx] != idx) {
			idx = parents[idx];
		}

		return idx;
	}
}
