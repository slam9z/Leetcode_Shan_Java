package hack.leetcode.dev;

/*
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

 Range Sum Query 2D
 The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

 Example:
 Given matrix = [
 [3, 0, 1, 4, 2],
 [5, 6, 3, 2, 1],
 [1, 2, 0, 1, 5],
 [4, 1, 0, 1, 7],
 [1, 0, 3, 0, 5]
 ]

 sumRegion(2, 1, 4, 3) -> 8
 sumRegion(1, 1, 2, 2) -> 11
 sumRegion(1, 2, 2, 4) -> 12
 Note:
 You may assume that the matrix does not change.
 There are many calls to sumRegion function.
 You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DImmutable {
	private int[][] dp;

	public RangeSumQuery2DImmutable(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;

		int row = matrix.length;
		int col = matrix[0].length;

		dp = new int[row][col];
		dp[0][0] = matrix[0][0];

		for (int i = 1; i < row; i++) {
			dp[i][0] = dp[i - 1][0] + matrix[i][0];
		}

		for (int i = 1; i < col; i++) {
			dp[0][i] = dp[0][i - 1] + matrix[0][i];
		}

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				dp[i][j] = matrix[i][j] + dp[i][j - 1] + dp[i - 1][j]
						- dp[i - 1][j - 1];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (dp == null) {
			return 0;
		}

		if (row1 < 0 || row1 > dp.length || row2 < 0 || row2 > dp.length
				|| col1 < 0 || col1 > dp[0].length || col2 < 0
				|| col2 > dp[0].length) {
			return 0;
		}

		int res = dp[row2][col2];
		if (row1 > 0) {
			res -= dp[row1 - 1][col2];
		}
		if (col1 > 0) {
			res -= dp[row2][col1 - 1];
		}
		if (row1 > 0 && col1 > 0) {
			res += dp[row1 - 1][col1 - 1];
		}

		return res;
	}

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { -4, -5 } };
		RangeSumQuery2DImmutable test = new RangeSumQuery2DImmutable(matrix);
		test.sumRegion(0, 0, 0, 1);
	}
}
