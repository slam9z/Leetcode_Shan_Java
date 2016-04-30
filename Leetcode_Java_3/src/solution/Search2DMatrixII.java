package solution;

/*
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.
 For example,

 Consider the following matrix:

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 Given target = 20, return false.
 */
public class Search2DMatrixII {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}

		int row = matrix.length;
		int col = matrix[0].length;

		int rowIdx = 0;
		int colIdx = col - 1;

		while (rowIdx < row && colIdx >= 0) {
			if (matrix[rowIdx][colIdx] == target) {
				return true;
			} else if (matrix[rowIdx][colIdx] > target) {
				colIdx--;
			} else {
				rowIdx++;
			}
		}

		return false;
	}
}
