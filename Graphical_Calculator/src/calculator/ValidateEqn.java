package calculator;

import java.util.ArrayList;

public class ValidateEqn 
{
	public String txtString;
	public String trigsctl = new String();
	public int trigFlag;
	public int errorTrig = 0;
	public int errorMain = 0;
	public double xLow;
	public double xHigh;
	public double yLow;
	public double yHigh;
	public char PrevChar = ' ';
	public String txtTrig = new String();
	public ArrayList<String> trigNum = new ArrayList<String>();
	public ArrayList<String> trigOperators = new ArrayList<String>();
	public String txtFinal = new String();
	public ArrayList<String> finalNum = new ArrayList<String>();
	public ArrayList<String> finalOperators = new ArrayList<String>();
	public CalculateEqn calculateEqn;
	public ArrayList<Double> xPoints = new ArrayList<Double>();
	public ArrayList<Double> yPoints = new ArrayList<Double>();
	
	public void validateXY() 
	{
		// TODO Auto-generated method stub
		String txt = txtString;
		convTrigEqn(txt);
		System.out.println("Original Equation: " + txt);
		System.out.println("Final Equation: " + txtFinal);
		System.out.println("TRIG FLAG: " + trigFlag);
		System.out.println("Equation inside trignometry: " + txtTrig);
		if (trigFlag == 1){
			errorTrig = chkError_Trig(txtTrig);
			System.out.println("Error for Trignometry: " + errorTrig);
		}
		errorMain = chkError_Main(txtFinal);
		System.out.println("Error for Main Equation: " + errorMain);
		calculateEqn = new CalculateEqn();
		setterCal();
		calculateEqn.calculateXY();
		xPoints = calculateEqn.getXPoints();
		yPoints = calculateEqn.getYPoints();
	}
	
	public void setterCal()
	{
		calculateEqn.setErrorMain(errorMain);
		calculateEqn.setErrorTrig(errorTrig);
		calculateEqn.setFinalNum(finalNum);
		calculateEqn.setFinalOperators(finalOperators);
		calculateEqn.setTrigFlag(trigFlag);
		calculateEqn.setTrigNum(trigNum);
		calculateEqn.setTrigOperators(trigOperators);
		calculateEqn.setTrigsctl(trigsctl);
		calculateEqn.setXHigh(xHigh);
		calculateEqn.setXLow(xLow);
		calculateEqn.setYHigh(yHigh);
		calculateEqn.setYLow(yLow);
	}

//---------------------------------Setters-----------------------------------
	
	public void setTxtString(String txtString){
		this.txtString = txtString;
	} public void setXHigh(double xHigh){
		this.xHigh = xHigh;
	} public void setXLow(double xLow){
		this.xLow = xLow;
	} public void setYHigh(double yHigh){
		this.yHigh = yHigh;
	} public void setYLow(double yLow){
		this.yLow = yLow;
	}
	
// ---------------------------------Getters----------------------------------
	
	public int getTrigFlag(){
		return trigFlag;
	} public int getErrorTrig(){
		return errorTrig;
	} public int getErrorMain(){
		return errorMain;
	} public ArrayList<Double> getXPoints(){
		return xPoints;
	} public ArrayList<Double> getYPoints(){
		return yPoints;
	}
	
//	-------------------------------Methods------------------------------------
	
	public void convTrigEqn(String txt)
	{
		String finaltxt = new String();
		String trigtxt = new String();
		if((txt.contains("tan")) || (txt.contains("sin")) || (txt.contains("ln")) || (txt.contains("cos")))
		{
			if(txt.contains("tan")){
				trigsctl = "tan";
			} else if(txt.contains("sin")) {
				trigsctl = "sin";
			} else if(txt.contains("ln")) {
				trigsctl = "ln";
			} else if(txt.contains("cos")) {
				trigsctl = "cos";
			}
			trigFlag = 1;
		}
		
		if(trigFlag == 1)
		{
			int flg = 0;
			for (int i = 0; i < txt.length(); i++) 
			{
				if(flg == 1 && txt.charAt(i)!=')') {
					trigtxt = trigtxt + txt.charAt(i);
				} if(flg == 0 && txt.charAt(i)=='(') {
					flg = 1;
				} if(flg == 1 && txt.charAt(i)==')') {
					flg = 0;
				} 
			}
		}
		
		if(trigFlag == 0)
		{
			finaltxt = txt;
			txtTrig = null;
			trigNum = null;
			txtFinal = finaltxt;
			String[] temp = new String[30];
			String temp2 = new String("");
			// All the operators which are in split can only be splitted
			int k = 0;
			for (int i = 0; i < txtFinal.length(); i++) 
			{
				if (txtFinal.charAt(i) == '^' || txtFinal.charAt(i) == '/' || txtFinal.charAt(i) == '*'
						|| txtFinal.charAt(i) == '+' || txtFinal.charAt(i) == '-') 
				{
					temp[k] = temp2;
					finalNum.add(temp2);
					finalOperators.add(txtFinal.charAt(i)+"");
					System.out.println("Numbers : " + temp[k]);
					System.out.println("Operator : " + txtFinal.charAt(i));
					temp2 = "";
					k++;
				} 
				else 
				{
					temp2 = temp2 + txtFinal.charAt(i);
				}

				if (txtFinal.length() - 1 == i) {
					temp[k] = temp2;
					finalNum.add(temp2);
					System.out.println("Numbers : " + temp[k]);
				}
			}
		}
		else
		{
			String temp3 = trigsctl + "(" + trigtxt + ")";
			finaltxt = txt.replace(temp3, trigsctl);
			txtFinal = finaltxt;
			String[] temp = new String[30];
			String temp2 = new String("");
			// All the operators which are in split can only be splitted
			int k = 0;
			for (int i = 0; i < txtFinal.length(); i++) 
			{
				if (txtFinal.charAt(i) == '^' || txtFinal.charAt(i) == '/' || txtFinal.charAt(i) == '*'
						|| txtFinal.charAt(i) == '+' || txtFinal.charAt(i) == '-') 
				{
					temp[k] = temp2;
					finalNum.add(temp2);
					finalOperators.add(txtFinal.charAt(i)+"");
					System.out.println("Numbers : " + temp[k]);
					System.out.println("Operator : " + txtFinal.charAt(i));
					temp2 = "";
					k++;
				} 
				else 
				{
					temp2 = temp2 + txtFinal.charAt(i);
				}

				if (txtFinal.length() - 1 == i) {
					temp[k] = temp2;
					finalNum.add(temp2);
					System.out.println("Numbers : " + temp[k]);
				}
			}
			
			temp = new String[30];
			temp2 = "";
			// All the operators which are in split can only be splitted
			k = 0;
			txtTrig = trigtxt;
			for (int i = 0; i < txtTrig.length(); i++) 
			{
				if (txtTrig.charAt(i) == '^' || txtTrig.charAt(i) == '/' || txtTrig.charAt(i) == '*') 
				{
					temp[k] = temp2;
					trigNum.add(temp2);
					trigOperators.add(txtTrig.charAt(i)+"");
					System.out.println("Numbers : " + temp[k]);
					System.out.println("Operator : " + txtTrig.charAt(i));
					temp2 = "";
					k++;
				} 
				else 
				{
					temp2 = temp2 + txtTrig.charAt(i);
				}

				if (txtTrig.length() - 1 == i) {
					temp[k] = temp2;
					trigNum.add(temp2);
					System.out.println("Numbers : " + temp[k]);
				}
			}
		}		
	}
	
	public int chkError_Trig(String txt)
	{
		for (int i = 0; i < txt.length(); i++) 
		{
			if (i == 0) { } 
			else if ((txt.charAt(i) == 43) || txt.charAt(i) == 45){
				return 1;
			} else if (((txt.charAt(i) >= 42 && txt.charAt(i) <= 47) || txt.charAt(i) == 94) && ((PrevChar >= 42 && PrevChar <= 47) || PrevChar == 94)){
				return 1; 
			} else if (txt.charAt(i) == 48 && PrevChar == 47){
				return 1;
			} else if ((txt.charAt(i) == 40 && PrevChar == 41) || (txt.charAt(i) == 41 && PrevChar == 40)
					|| (((txt.charAt(i) >= 42 && txt.charAt(i) <= 47) || txt.charAt(i) == 94) && PrevChar == 40)){
				return 1;
			} else if ((txt.charAt(i)>=42 && txt.charAt(i)<=47)&&(PrevChar == 110 || PrevChar == 115)){
				return 1;
			} else if (PrevChar == '\u221A' && (txt.charAt(i)>=42 && txt.charAt(i)<=47)){
				return 1;
			} else if (((PrevChar >= 42 && PrevChar <= 47) || PrevChar == 94) && ((txt.charAt(i) >= 97 && txt.charAt(i) <= 119) || (txt.charAt(i) >= 121 && txt.charAt(i) <= 122))){
				return 1;
			} else if (((txt.charAt(i) >= 42 && txt.charAt(i) <= 47) || txt.charAt(i) == 94) && ((PrevChar >= 97 && PrevChar <= 119) || (PrevChar >= 121 && PrevChar <= 122))){
				return 1;
			} else if ((txt.charAt(i) == 120 && (PrevChar >= 48 && PrevChar <= 57)) || (PrevChar == 120 && (txt.charAt(i) >= 48 && txt.charAt(i) <= 57))){
				return 1;
			}
			PrevChar = txt.charAt(i);
		}
		
		if ((txt.charAt(txt.length() - 1) == 40)
				|| ((txt.charAt(txt.length() - 1) >= 42) && (txt.charAt(txt.length() - 1) <= 47))) {
			return 1;
		}

		if ((txt.contains("tan" + "\u03c0" + "/2")) || (txt.contains("/0"))) {
			return 1;
		}
		
		return 0;
	}
	
	public int chkError_Main(String txt_main)
	{
		String txt = new String();
		if (trigFlag == 1){
			txt = txt_main.replace(trigsctl, "x");
			System.out.println("Changed Main Equation: " + txt);
		} else{
			txt = txt_main;
		}
		for (int i = 0; i < txt.length(); i++) 
		{
			if (i == 0) { } 
			else if (((txt.charAt(i) >= 42 && txt.charAt(i) <= 47) || txt.charAt(i) == 94) && ((PrevChar >= 42 && PrevChar <= 47) || PrevChar == 94)){
				return 1; 
			} else if (txt.charAt(i) == 48 && PrevChar == 47){
				return 1;
			} else if ((txt.charAt(i) == 40 && PrevChar == 41) || (txt.charAt(i) == 41 && PrevChar == 40)
					|| (((txt.charAt(i) >= 42 && txt.charAt(i) <= 47) || txt.charAt(i) == 94) && PrevChar == 40)){
				return 1;
			} else if ((txt.charAt(i)>=42 && txt.charAt(i)<=47)&&(PrevChar == 110 || PrevChar == 115)){
				return 1;
			} else if (PrevChar == '\u221A' && (txt.charAt(i)>=42 && txt.charAt(i)<=47)){
				return 1;
			} else if (((PrevChar >= 42 && PrevChar <= 47) || PrevChar == 94) && ((txt.charAt(i) >= 97 && txt.charAt(i) <= 119) || (txt.charAt(i) >= 121 && txt.charAt(i) <= 122))){
				return 1;
			} else if (((txt.charAt(i) >= 42 && txt.charAt(i) <= 47) || txt.charAt(i) == 94) && ((PrevChar >= 97 && PrevChar <= 119) || (PrevChar >= 121 && PrevChar <= 122))){
				return 1;
			} else if ((txt.charAt(i) == 120 && (PrevChar >= 48 && PrevChar <= 57)) || (PrevChar == 120 && (txt.charAt(i) >= 48 && txt.charAt(i) <= 57))){
				return 1;
			}

			PrevChar = txt.charAt(i);
		}
		
		if ((txt.charAt(txt.length() - 1) == 40)
				|| ((txt.charAt(txt.length() - 1) >= 42) && (txt.charAt(txt.length() - 1) <= 47))) {
			return 1;
		}

		if ((txt.contains("tan" + "\u03c0" + "/2")) || (txt.contains("/0")) || txtString.contains("()")) {
			return 1;
		}
		
		return 0;
	}

}
