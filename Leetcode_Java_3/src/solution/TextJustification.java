package solution;

import java.util.ArrayList;
import java.util.List;

/*
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.
 */
public class TextJustification {
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> res = new ArrayList<String>();
		if (words == null || words.length == 0) {
			return res;
		}

		if (maxWidth == 0) {
			res.add("");
			return res;
		}

		int index = 0;
		int n = words.length;

		while (index < n) {
			int count = words[index].length();
			int last = index + 1;

			while (last < words.length) {
				if (words[last].length() + count + 1 > maxWidth) {
					break;
				}
				count += words[last].length() + 1;
				last++;
			}

			StringBuilder sb = new StringBuilder();
			int diff = last - index - 1;

			if (last == words.length || diff == 0) {
				for (int i = index; i < last; i++) {
					sb.append(words[i] + " ");
				}
				sb.deleteCharAt(sb.length() - 1);
				for (int i = sb.length(); i < maxWidth; i++) {
					sb.append(" ");
				}
			} else {
				// middle justified
				int spaces = (maxWidth - count) / diff;
				int r = (maxWidth - count) % diff;
				for (int i = index; i < last; i++) {
					sb.append(words[i]);
					if (i < last - 1) {
						for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
							sb.append(" ");
						}
					}
				}
			}
			res.add(sb.toString());
			index = last;
		}

		return res;
	}

	public List<String> fullJustify2(String[] words, int L) {
		ArrayList<String> res = new ArrayList<String>();
		if (words.length == 0) {
			return res;
		}
		if (L == 0) {
			res.add("");
			return res;
		}

		String spaces[] = new String[L + 1];
		spaces[0] = "";
		for (int i = 1; i < spaces.length; i++) {
			spaces[i] = spaces[i - 1] + " ";
		}

		int buffer = -1;
		int start = 0;
		int end = 0;
		while (end < words.length) {
			if (buffer + words[end].length() + 1 <= L) {
				buffer += words[end].length() + 1;
				end++;
				if (end != words.length) {
					continue;
				}
			}
			int len = 0;
			for (int i = start; i < end; i++) {
				len += words[i].length();
			}
			int averLen = L - len;
			int notEven = 0;
			if (start + 1 < end) {
				averLen = (L - len) / (end - start - 1);
				notEven = (L - len) - averLen * (end - start - 1);
			}

			StringBuilder sb = new StringBuilder();
			sb.append(words[start]);
			for (int i = start + 1; i < end; i++) {
				if (i <= start + notEven) {
					sb.append(spaces[1]);
				}
				sb.append(spaces[averLen]).append(words[i]);
			}

			if (start + 1 == end) {
				sb.append(spaces[L - words[start].length()]);
			}
			res.add(sb.toString());

			start = end;
			buffer = -1;
		}

		while (res.get(res.size() - 1).contains("  ")) {
			res.set(res.size() - 1, res.get(res.size() - 1).replace("  ", " "));
		}
		res.set(res.size() - 1, res.get(res.size() - 1) + spaces[L - res.get(res.size() - 1).length()]);
		return res;
	}

	public static void main(String[] args) {
		TextJustification test = new TextJustification();
		String[] words = { "Don't", "go", "around", "saying", "the", "world", "owes", "you", "a", "living;", "the",
				"world", "owes", "you", "nothing;", "it", "was", "here", "first." };
		System.out.println(test.fullJustify(words, 30));
	}
}
