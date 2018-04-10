package calculator;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class CalculateEqn 
{
	public int trigFlag = 0;
	public int errorTrig = 0;
	public int errorMain = 0;
	public double xLow = 0;
	public double xHigh = 0;
	public double yLow = 0;
	public double yHigh = 0;
	public String trigsctl = new String();
	public ArrayList<String> trigNum = new ArrayList<String>();
	public ArrayList<String> trigOperators = new ArrayList<String>();
	public ArrayList<String> finalNum = new ArrayList<String>();
	public ArrayList<String> finalOperators = new ArrayList<String>();
	public ArrayList<Double> xPoints = new ArrayList<Double>();
	public ArrayList<Double> yPoints = new ArrayList<Double>();

//	----------------------------------Setters-------------------------------------
	
	public void setTrigFlag(int trigFlag){
		this.trigFlag = trigFlag;
	} public void setErrorTrig(int errorTrig){
		this.errorTrig = errorTrig;
	} public void setErrorMain(int errorMain){
		this.errorMain = errorMain;
	} public void setXLow(double xLow){
		this.xLow = xLow;
	} public void setYLow(double yLow){
		this.yLow = yLow;
	} public void setXHigh(double xHigh){
		this.xHigh = xHigh;
	} public void setYHigh(double yHigh){
		this.yHigh = yHigh;
	} public void setTrigsctl(String trigsctl){
		this.trigsctl = trigsctl;
	} public void setTrigNum(ArrayList<String> trigNum){
		this.trigNum = trigNum;
	} public void setTrigOperators(ArrayList<String> trigOperators){
		this.trigOperators = trigOperators;
	} public void setFinalNum(ArrayList<String> finalNum){
		this.finalNum = finalNum;
	} public void setFinalOperators(ArrayList<String> finalOperators){
		this.finalOperators = finalOperators;
	}
	
//	-----------------------------------Getters------------------------------------
	
	public ArrayList<Double> getXPoints(){
		return xPoints;
	} public ArrayList<Double> getYPoints(){
		return yPoints;
	}
	
//	----------------------------------Functions-------------------------------------
	
	public void calculateXY()
	{
		System.out.println("----------------------------------Class Calculate Equation-----------------------------------");
		if(errorTrig == 0 && errorMain == 0)
		{
			if(trigFlag==0)
			{
				ArrayList<Integer> indexX = new ArrayList<Integer>();
				for(int i=0; i<finalNum.size(); i++)
				{
					String temp = finalNum.get(i);
					if(temp.equals("x"))
					{
						indexX.add(i);
						System.out.println("Positions x at: " + (i+1));
					}
				}
				for (double i=xLow; i<=xHigh; i=i+0.05)
				{
					xPoints.add(i);
					ArrayList<String> temp = finalNum;
					for (int j=0; j<indexX.size(); j++)
					{
						temp.add(indexX.get(j), Double.toString(i));
						temp.remove(indexX.get(j)+1);
					}
					String inputString = input_string(temp, finalOperators);
					double result = cal_func(inputString);
					yPoints.add(result);
				}
				System.out.println("------------------------------XY Points--------------------------------------");
//				for(int i=0; i<xPoints.size();i++)
//				{
//					System.out.println("("+ xPoints.get(i) +", " + yPoints.get(i) + ")");
//				}
			}
			else
			{
				System.out.println("Done... Now Check.....");
				ArrayList<Integer> indexX = new ArrayList<Integer>();
				ArrayList<Integer> indexTrig = new ArrayList<Integer>();
				ArrayList<Integer> indexXFinal = new ArrayList<Integer>();
				
				for(int i=0; i<trigNum.size(); i++)
				{
					String temp = trigNum.get(i);
					if(temp.equals("x"))
					{
						indexX.add(i);
						System.out.println("Positions xTrig at: " + (i+1));
					}
				}
				
				for(int i=0; i<finalNum.size(); i++)
				{
					String temp = finalNum.get(i);
					if(temp.equals("x"))
					{
						indexXFinal.add(i);
						System.out.println("Positions xFinal at: " + (i+1));
					}
					if(temp.equals(trigsctl))
					{
						indexTrig.add(i);
						System.out.println("Positions of "+ trigsctl +" at: " + (i+1));
					}
				}
				
				for (double i=xLow; i<=xHigh; i=i+0.05)
				{
					xPoints.add(i);
					ArrayList<String> temp = trigNum;
					ArrayList<String> temp2 = finalNum;
					
					for (int j=0; j<indexX.size(); j++)
					{
						temp.add(indexX.get(j), Double.toString(i));
						temp.remove(indexX.get(j)+1);
					}
					
					String inputString = input_string(temp, trigOperators);
					double result = cal_func(inputString);
					
					for (int j=0; j<indexXFinal.size(); j++)
					{
						temp2.add(indexXFinal.get(j), Double.toString(i));
						temp2.remove(indexXFinal.get(j)+1);
					}
					
					double resultTrig = 0;
					for (int j=0; j<indexTrig.size(); j++)
					{
						if(trigsctl.equals("sin")){
							resultTrig = Math.sin(result);
						} else if (trigsctl.equals("cos")){
							resultTrig = Math.cos(result);
						} else if (trigsctl.equals("tan")){
							resultTrig = Math.tan(result);
						} else if (trigsctl.equals("ln")){
							resultTrig = Math.log(result);
						}
						temp2.add(indexTrig.get(j), Double.toString(resultTrig));
						temp2.remove(indexTrig.get(j)+1);
					}
					
					String inputStringFinal = input_string(temp2, finalOperators);
					double resultFinal = cal_func(inputStringFinal);
					yPoints.add(resultFinal);
				}
				
				System.out.println("------------------------------XY Points--------------------------------------");
//				for(int i=0; i<xPoints.size();i++)
//				{
//					System.out.println("("+ xPoints.get(i) +", " + yPoints.get(i) + ")");
//				}
			}
		}
		else
		{
			System.out.println("Error in the equation.....");
		}
	}
	
	private String input_string(ArrayList<String> Num, ArrayList<String> Operators) {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();

		int i = 0, j = 0, k = 0;
		while (i < Num.size() + Operators.size()) {
			if (i % 2 == 0) {
				if (i == Num.size() + Operators.size() - 1) {
					sb.append(Num.get(j++));
				} else {
					sb.append(Num.get(j++) + " ");
				}
			} else {

				sb.append(Operators.get(k++) + " ");
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
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	private double cal_func(String s) {
		// TODO Auto-generated method stub

		StringTokenizer st = new StringTokenizer(s);
		//System.out.println(s);

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
	
}

