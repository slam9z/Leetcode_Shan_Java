package hack.leetcode.dev;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/*
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Therefore, return the max sliding window as [3,3,5,5,6,7].

 Note: 
 You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array
 */

public class SlidingWindowMaximum {
	public int [] maxSlidingWindow(int[] nums, int k) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		if (nums == null || nums.length == 0 || k < 0) {
			return new int[0];
		}

		Deque<Integer> deque = new LinkedList<Integer>();
		for (int i = 0; i < k; i++) {
			while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
				deque.removeLast();
			}
			deque.addLast(i);
		}

		for (int i = k; i < nums.length; i++) {
			res.add(nums[deque.peekFirst()]);

			while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
				deque.removeFirst();
			}

			while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
				deque.removeLast();
			}

			deque.addLast(i);
		}

		res.add(nums[deque.peekFirst()]);

		int[] finalRes = new int[res.size()];

		for (int i = 0; i < finalRes.length; i++) {
			finalRes[i] = res.get(i);
		}

		return finalRes;
	}
}
