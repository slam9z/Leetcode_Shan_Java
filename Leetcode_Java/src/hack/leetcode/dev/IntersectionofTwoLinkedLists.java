package hack.leetcode.dev;

import hack.leetcode.ulti.ListNode;

/*
 * 
 * Write a program to find the node at which the intersection of two singly linked lists begins.
	 For example, the following two linked lists:
	
	 A:          a1 → a2
						 ↘
						 c1 → c2 → c3
						 ↗            
	 B:     b1 → b2 → b3
	 begin to intersect at node c1.
	Notes:
	If the two linked lists have no intersection at all, return null.
	The linked lists must retain their original structure after the function returns.
	You may assume there are no cycles anywhere in the entire linked structure.
 	Your code should preferably run in O(n) time and use only O(1) memory.
 */

public class IntersectionofTwoLinkedLists {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null && headB == null) {
			return null;
		}

		if (headA == null || headB == null) {
			return null;
		}

		ListNode tailA = null;
		ListNode tailB = null;

		ListNode pA = headA;
		ListNode pB = headB;

		while (true) {
			if (pA == null) {
				pA = headB;
			}

			if (pB == null) {
				pB = headA;
			}

			if (pA.next == null) {
				tailA = pA;
			}

			if (pB.next == null) {
				tailB = pB;
			}

			if (tailA != null && tailB != null && tailA != tailB) {
				return null;
			}

			if (pA == pB) {
				return pA;
			}

			pA = pA.next;
			pB = pB.next;
		}
	}
}
