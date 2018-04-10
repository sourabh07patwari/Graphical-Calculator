package calculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Cal_Methods {
	private String TextCal;
	private ArrayList<String> EqnCal = new ArrayList<String>();
	private ArrayList<String> NumCal = new ArrayList<String>();
	private ArrayList<String> OperatorsCal = new ArrayList<String>();
	private int OneChar_Flag = 0;
	private int TwoChar_Flag = 0;
	private int ThreeChar_Flag = 0;
	private String Result = null;
	private String ANS = "0";

	// ----------------------------------Getters-------------------------------------
	public String get_TextCal() {
		return this.TextCal;
	}

	public ArrayList<String> get_EqnCal() {
		return this.EqnCal;
	}

	public ArrayList<String> get_NumCal() {
		return this.NumCal;
	}

	public ArrayList<String> get_OperatorsCal() {
		return this.OperatorsCal;
	}

	public int get_OneFlag() {
		return this.OneChar_Flag;
	}

	public int get_TwoFlag() {
		return this.TwoChar_Flag;
	}

	public int get_ThreeFlag() {
		return this.ThreeChar_Flag;
	}

	public String get_Result() {
		return this.Result;
	}

	// ----------------------------------------Setters--------------------------------
	public void set_TextCal(String TextCal) {
		this.TextCal = TextCal;
	}

	public void set_EqnCal(ArrayList<String> EqnCal) {
		this.EqnCal = EqnCal;
	}

	public void set_NumCal(ArrayList<String> EqnCal) {
		this.EqnCal = EqnCal;
	}

	public void set_OperatorsCal(ArrayList<String> OperatorsCal) {
		this.OperatorsCal = OperatorsCal;
	}

	public void set_OneFlag(int OneChar_Flag) {
		this.OneChar_Flag = OneChar_Flag;
	}

	public void set_TwoFlag(int TwoChar_Flag) {
		this.TwoChar_Flag = TwoChar_Flag;
	}

	public void set_ThreeFlag(int ThreeChar_Flag) {
		this.ThreeChar_Flag = ThreeChar_Flag;
	}

	public void add_NumCal(String Num) {
		this.NumCal.add(Num);
	}

	public void add_OperatorsCal(String Op) {
		this.OperatorsCal.add(Op);
	}

	public void add_EqnCal(String Eqn) {
		this.EqnCal.add(Eqn);
	}

	// -------------------------------------DELETION------------------------------------

	public void del_Operators() {
		this.OperatorsCal.remove(OperatorsCal.size()-1);
	}

	public void del_NumCal() {
		this.NumCal.remove(NumCal.size()-1);
	}
	
	public void del_Operators_All()
	{
		this.OperatorsCal.removeAll(OperatorsCal);
	}

	// ------------------------------Methods of
	// Calculator------------------------------
	public int check_Error() {
		int error = 0;
		char PrevChar = ' ';
		for (int i = 0; i < TextCal.length(); i++) 
		{
			if (i == 0) {
			} else if ((TextCal.charAt(i) >= 42 && TextCal.charAt(i) <= 47) && (PrevChar >= 42 && PrevChar <= 47)) {
				error = 1;
				break;
			} else if (TextCal.charAt(i) == 48 && PrevChar == 47) {
				error = 1;
				break;
			} 
//			else if (((TextCal.charAt(i) >= 97 || TextCal.charAt(i) <= 122) && PrevChar == 110)
//					|| ((TextCal.charAt(i) == 115 || TextCal.charAt(i) == 116 || TextCal.charAt(i) == 108)&& PrevChar == 115)) {
//				error = 1;
//				break;
//			} 
			else if ((TextCal.charAt(i) == 40 && PrevChar == 41) || (TextCal.charAt(i) == 41 && PrevChar == 40)
					|| ((TextCal.charAt(i) >= 42 && TextCal.charAt(i) <= 47) && PrevChar == 40)) {
				error = 1;
				break;
			} else if ((TextCal.charAt(i)>=42 && TextCal.charAt(i)<=47)&&(PrevChar == 110 || PrevChar == 115)){
				error = 1;
				break;
			} else if (PrevChar == '\u221A' && (TextCal.charAt(i)>=42 && TextCal.charAt(i)<=47)){
				error = 1;
				break;
			}

			PrevChar = TextCal.charAt(i);
		}

		if ((TextCal.charAt(TextCal.length() - 1) == 40)
				|| ((TextCal.charAt(TextCal.length() - 1) >= 42) && (TextCal.charAt(TextCal.length() - 1) <= 47))) {
			error = 1;
		}

		if ((TextCal.contains("tan" + "\u03c0" + "/2")) || (TextCal.contains("/0"))) {
			error = 1;
		}

		return error;
	}

	public void replace_Fn() {
		for (int i = 0; i < NumCal.size(); i++) {
			if ((NumCal.get(i).contains("\u03c0"))) {
				String replacer = NumCal.get(i);
				String replaceString = replacer.replaceAll("\u03c0", "3.14");
				NumCal.set(i, replaceString);
			}
			if ((NumCal.get(i).contains("e"))) {
				String replacer = NumCal.get(i);
				String replaceString = replacer.replace("e", "2.718");
				NumCal.set(i, replaceString);
			}
			if ((NumCal.get(i).contains("ANS"))) {
				String replacer = NumCal.get(i);
				String replaceString = replacer.replace("ANS", ANS);
				NumCal.set(i, replaceString);
			}
		}
		
		for(int i = 0; i < NumCal.size(); i++){
			if ((NumCal.get(i).contains("\u221A"))) {
				String rep_fn = NumCal.get(i).substring(1);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.sqrt(temp3);
				NumCal.set(i, temp_res.toString());
			}
		}

		for (int i = 0; i < NumCal.size(); i++) {
			if ((NumCal.get(i).contains("sin"))) {
				String rep_fn = NumCal.get(i).substring(3);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.sin(temp3);
				NumCal.set(i, temp_res.toString());
			}

			if ((NumCal.get(i).contains("tan"))) {
				String rep_fn = NumCal.get(i).substring(3);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.tan(temp3);
				NumCal.set(i, temp_res.toString());
			}

			if ((NumCal.get(i).contains("cos"))) {
				String rep_fn = NumCal.get(i).substring(3);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.cos(temp3);
				NumCal.set(i, temp_res.toString());
			}

			if ((NumCal.get(i).contains("ln"))) {
				String rep_fn = NumCal.get(i).substring(2);
				Double temp3 = Double.parseDouble(rep_fn);
				Double temp_res = Math.log(temp3);
				NumCal.set(i, temp_res.toString());
			}
		}
	}

	public String resultCal() {
		if (check_Error() == 1) {
			Result = "Error or Infinity!!!";
			return Result;
		} else {
			String[] temp = new String[30];
			String temp2 = new String("");
			// All the operators which are in split can only be splitted
			int k = 0;
			for (int i = 0; i < TextCal.length(); i++) {
				if (TextCal.charAt(i) == '^' || TextCal.charAt(i) == '/' || TextCal.charAt(i) == '*'
						|| TextCal.charAt(i) == '+' || TextCal.charAt(i) == '-') {
					temp[k] = temp2;
					NumCal.add(temp2);
					System.out.println("Numbers : " + temp[k]);
					temp2 = "";
					k++;
				} else {
					temp2 = temp2 + TextCal.charAt(i);
				}

				if (TextCal.length() - 1 == i) {
					temp[k] = temp2;
					NumCal.add(temp2);
					System.out.println("Numbers : " + temp[k]);
				}

			}

			replace_Fn();

			String inputString = input_string();

			double result = cal_func(inputString);
			
			NumCal = new ArrayList<String>();
			OperatorsCal = new ArrayList<String>();
			
			ANS = result + "";

			return result+"";
		}
	}

	private double cal_func(String s) {
		// TODO Auto-generated method stub

		StringTokenizer st = new StringTokenizer(s);
		System.out.println(s);

		ArrayList<String> al = new ArrayList<String>();
		al.add("+");
		al.add("-");
		al.add("*");
		al.add("/");
		al.add("^");

		char sign = '+';
		double val = 0.0;

		int len = st.countTokens();
		int i = 0;

		Stack<Double> stack = new Stack<Double>();

		while (i < len) {

			String s_copy = st.nextToken();

			
			if (!al.contains(s_copy)) {
				val = Double.parseDouble(s_copy);
			}

			if ((al.contains(s_copy)) || i == len - 1) {
				
				if (sign == '-') {
					stack.push(-val);
				}
				if (sign == '+') {
					stack.push(val);
				}
				if (sign == '*') {
					stack.push(stack.pop() * val);
				}
				if (sign == '/') {
					stack.push(stack.pop() / val);
				}

				sign = s_copy.charAt(0);
				val = 0.0;

			}
			i++;
		}

		double result = 0;
		for (double k : stack) {
			result += k;
		}
		return result;
	}

	private String input_string() {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();

		int i = 0, j = 0, k = 0;
		while (i < NumCal.size() + OperatorsCal.size()) {
			if (i % 2 == 0) {
				if (i == NumCal.size() + OperatorsCal.size() - 1) {
					sb.append(NumCal.get(j++));
				} else {
					sb.append(NumCal.get(j++) + " ");
				}
			} else {

				sb.append(OperatorsCal.get(k++) + " ");
			}
			i++;
		}

		StringTokenizer st = new StringTokenizer(sb.toString());
		sb = new StringBuilder();

		String prev = "";
		boolean op_flag = false;
		int prevLength = 0;
		while (st.hasMoreTokens()) {
			String s_val = st.nextToken();
			if (s_val.equals("^")) {
				op_flag = true;
				continue;
			} else {
				if (op_flag) {
					double val1 = Double.parseDouble(sb.substring(sb.length() - prevLength - 1, sb.length() - 1));
					double val2 = Double.parseDouble(s_val);
					double ans = Math.pow(val1, val2);
					sb = new StringBuilder(sb.substring(0, sb.length() - prevLength - 1));
					sb.append(ans + " ");
					op_flag = false;
				} else {
					sb.append(s_val + " ");
				}
			}
			prev = s_val;
			prevLength = prev.length();
		}
		// System.out.println(sb.toString());
		return sb.toString();

	}
}
