package solution;

public class SingleNumber {
	public int singleNumber(int[] A) {
		int res = A[0];
		for (int i = 1; i < A.length; i++) {
			res = res ^ A[i];
		}
		return res;
	}
}
