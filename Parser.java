

import java.util.LinkedList;
/**
 * @author ravi.bhargava
 * 
 * expr → term { add-op term }
 * term → factor { mult-op factor }
 * factor → number
 * add-op → + | -
 * mult-op → * | /
 * number → 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 * 
 *
 */


public class Parser {

	private static int valueOfExpr(LinkedList<String> ts) {
		int value = valueOfTerm(ts);
		while (ts.peek() != null && (ts.peek().equals("+") || ts.peek().equals("-"))) {
			String op = ts.poll();
			if (op.equals("+")) {
				value = value + valueOfTerm(ts);
			} else /* "-" */ {
				value = value - valueOfTerm(ts);
			}
		}
		return value;
	} 

	private static int valueOfTerm(LinkedList<String> ts) {
		int value = valueOfFactor(ts);
		while (ts.peek() != null && ts.peek().equals("*")){
			String op = ts.poll();
			if (op.equals("*")) {
				value = value * valueOfFactor(ts);
			} else /* "-" */ {
				value = value / valueOfFactor(ts);
			}
		}
		return value;
	} 

	private static int valueOfFactor(LinkedList<String> ts) {
		int value;
		String number = ts.poll();
		value = Integer.parseInt(number);
		return value;
	} 
	
	
	private static LinkedList<String> tokenize(String input) {
		LinkedList<String> list = new LinkedList<String>();
		for (int i=0;i<input.length();i++) {
			char c = input.charAt(i);
			if (c != ' ')
				list.add(""+c);
		}
		return list;
	}
	
 	public static void main(String[] args) {
		String input = "3-2+5*5";
		LinkedList<String> list = tokenize(input);
		int result = valueOfExpr(list);
		System.out.println(result);
	}
}
