package solution;

/*
 * Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a")-> false
isMatch("aa","aa") -> true
isMatch("aaa","aa") -> false
isMatch("aa", "a*") -> true
isMatch("aa", ".*") -> true
isMatch("ab", ".*") -> true
isMatch("aab", "c*a*b") -> true

https://leetcode.com/discuss/93024/easy-dp-java-solution-with-detailed-explanation
 */
public class RegularExpressionMatching {
	public static boolean isMatch(String s, String p) {
		if(p.isEmpty()){
			return s.isEmpty();
		}
		
		if(p.length() == 1 || p.charAt(1) != '*'){
			if (s.isEmpty() || ( p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
				return false;
			}else{
				return isMatch(s.substring(1), p.substring(1));
			}
		}
		
		// if p.length() > 2
		while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
			if (isMatch(s, p.substring(2))) {
				return true;
			}
			s = s.substring(1);
		}
		
		return isMatch(s, p.substring(2));
	}

	/*
	 * 	1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
		2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
		3, If p.charAt(j) == '*': 
			   here are two sub conditions:
			               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
			               2   if p.charAt(j-1) == s.charAt(i) or p.charAt(i-1) == '.':
			                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
			                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
			                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
	 */
	public static boolean isMatchDP(String s, String p) {
		if (s == null || p == null) {
			return false;
		}

		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;

		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*' && dp[0][i - 1]) {
				dp[0][i + 1] = true;
			}
		}

		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
					dp[i + 1][j + 1] = dp[i][j];
				}
				if (p.charAt(j) == '*') {
					if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
						dp[i + 1][j + 1] = dp[i + 1][j - 1];
					} else {
						dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j] || dp[i + 1][j - 1];
					}
				}
			}
		}

		return dp[s.length()][p.length()];
	}
	
	public static void main(String[] args){
		isMatchDP("aa", "*a");
	}
}
