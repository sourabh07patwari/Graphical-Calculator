package calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class Cal_GUI {

	public JFrame frame = new JFrame();
	public JTextField txt_Cal;
	public JTextField txt_Eqn;
	public DefaultListModel<String> Eqn_List;
	public String ANS;
	public String Color_CBox;
	private JTextField txt_XRange;
	private JTextField txt_YRange;
	private ArrayList<Double> xPoints = new ArrayList<Double>();
	private ArrayList<Double> yPoints = new ArrayList<Double>();
	private ArrayList<Double> plotX = new ArrayList<Double>();
	private ArrayList<Double> plotY = new ArrayList<Double>();
	public int errorTrig = 0;
	public int errorMain = 0;
	public double xLow;
	public double xHigh;
	public double yLow;
	public double yHigh;
	Cal_Methods calculators = new Cal_Methods();
	ValidateEqn validateEqn;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public Cal_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 1168, 668);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_Eqn = new JPanel();
		panel_Eqn.setBounds(6, 6, 425, 253);
		frame.getContentPane().add(panel_Eqn);
		panel_Eqn.setLayout(null);
		
		JPanel panel_Cal = new JPanel();
		panel_Cal.setBounds(6, 271, 425, 365);
		frame.getContentPane().add(panel_Cal);
		panel_Cal.setLayout(null);
		
		JPanel panel_Graph = new JPanel();
		panel_Graph.setBounds(443, 6, 718, 630);
		frame.getContentPane().add(panel_Graph);
		panel_Graph.setLayout(null);
		
		JLabel lblYaxis = new JLabel("Y-axis");
		lblYaxis.setBounds(360, 6, 48, 16);
		panel_Graph.add(lblYaxis);
		
		JLabel lblXaxis = new JLabel("X-axis");
		lblXaxis.setBounds(665, 298, 48, 16);
		panel_Graph.add(lblXaxis);
		
		JLabel labelCenter = new JLabel("(0,0)");
		labelCenter.setBounds(360, 298, 34, 16);
		panel_Graph.add(labelCenter);
		
		txt_Cal = new JTextField();
		txt_Cal.setHorizontalAlignment(SwingConstants.RIGHT);
		txt_Cal.setBounds(6, 6, 413, 41);
		panel_Cal.add(txt_Cal);
		txt_Cal.setColumns(10);

//      -------------------------------ROW 1-----------------------------------------------
		
		JButton btnDel = new JButton("<-");
		btnDel.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String BckSpc = null;
				if(txt_Cal.getText().length() > 0)
				{
					StringBuilder NewText = new StringBuilder(txt_Cal.getText());
					if(NewText.charAt(txt_Cal.getText().length()-1)>=42 && NewText.charAt(txt_Cal.getText().length()-1)<=47)
					{
//						Delete the last element of Operators_Cal
						calculators.del_Operators();
					}
					NewText.deleteCharAt(txt_Cal.getText().length()-1);
					BckSpc = NewText.toString();
					txt_Cal.setText(BckSpc);
				}
			}
		});
		btnDel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnDel.setBounds(6, 59, 50, 50);
		panel_Cal.add(btnDel);
		
		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnAdd.getText();
				txt_Cal.setText(Cal_String);
				calculators.add_OperatorsCal("+");
			}
		});
		btnAdd.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnAdd.setBounds(68, 59, 50, 50);
		panel_Cal.add(btnAdd);
		
		JButton btnSub = new JButton("-");
		btnSub.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnSub.getText();
				txt_Cal.setText(Cal_String);
				calculators.add_OperatorsCal("-");
			}
		});
		btnSub.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnSub.setBounds(130, 59, 50, 50);
		panel_Cal.add(btnSub);
		
		JButton btnDiv = new JButton("/");
		btnDiv.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnDiv.getText();
				txt_Cal.setText(Cal_String);
				calculators.add_OperatorsCal("/");
			}
		});
		btnDiv.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnDiv.setBounds(192, 59, 50, 50);
		panel_Cal.add(btnDiv);
		
		JButton btnSin = new JButton("sin");
		btnSin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnSin.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btnSin.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnSin.setBounds(254, 59, 50, 50);
		panel_Cal.add(btnSin);
		
//		-------------------------------ROW 2--------------------------------
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Append the Character text of button to the TextField txt_Cal 
				String Cal_String =  txt_Cal.getText() + btn7.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn7.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btn7.setBounds(6, 121, 50, 50);
		panel_Cal.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btn8.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn8.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btn8.setBounds(68, 121, 50, 50);
		panel_Cal.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btn9.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn9.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btn9.setBounds(130, 121, 50, 50);
		panel_Cal.add(btn9);
		
		JButton btnMul = new JButton("*");
		btnMul.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnMul.getText();
				txt_Cal.setText(Cal_String);
				calculators.add_OperatorsCal("*");
			}
		});
		btnMul.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnMul.setBounds(192, 121, 50, 50);
		panel_Cal.add(btnMul);
		
		JButton btnCos = new JButton("cos");
		btnCos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnCos.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btnCos.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnCos.setBounds(254, 121, 50, 50);
		panel_Cal.add(btnCos);
		
//		---------------------------ROW 3-------------------------------------
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btn4.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn4.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btn4.setBounds(6, 183, 50, 50);
		panel_Cal.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btn5.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn5.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btn5.setBounds(68, 183, 50, 50);
		panel_Cal.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btn6.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn6.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btn6.setBounds(130, 183, 50, 50);
		panel_Cal.add(btn6);
		
		JButton btnPow = new JButton("^");
		btnPow.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnPow.getText();
				txt_Cal.setText(Cal_String);
				calculators.add_OperatorsCal("^");
			}
		});
		btnPow.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnPow.setBounds(192, 183, 50, 50);
		panel_Cal.add(btnPow);
		
		JButton btnTan = new JButton("tan");
		btnTan.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnTan.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btnTan.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnTan.setBounds(254, 183, 50, 50);
		panel_Cal.add(btnTan);
		
//		-------------------------ROW 4------------------------------------
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btn1.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn1.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btn1.setBounds(6, 245, 50, 50);
		panel_Cal.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btn2.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn2.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btn2.setBounds(68, 245, 50, 50);
		panel_Cal.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btn3.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn3.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btn3.setBounds(130, 245, 50, 50);
		panel_Cal.add(btn3);
		
		JButton btnSqrt = new JButton("Sqrt");
		btnSqrt.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String sqrt = "\u221A";
				String Cal_String =  txt_Cal.getText() + sqrt;
				txt_Cal.setText(Cal_String);
			}
		});
		btnSqrt.setFont(new Font("AppleGothic", Font.PLAIN, 16));
		btnSqrt.setBounds(192, 245, 50, 50);
		panel_Cal.add(btnSqrt);
		
		JButton btnLn = new JButton("ln");
		btnLn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnLn.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btnLn.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		btnLn.setBounds(254, 245, 50, 50);
		panel_Cal.add(btnLn);
		
//		-----------------------ROW 5-----------------------------------------
		
		JButton btnDot = new JButton(".");
		btnDot.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				char dot = 46;
				String Cal_String =  txt_Cal.getText() + dot;
				txt_Cal.setText(Cal_String);
			}
		});
		btnDot.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnDot.setBounds(6, 307, 50, 50);
		panel_Cal.add(btnDot);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btn0.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btn0.setFont(new Font("AppleGothic", Font.BOLD, 23));
		btn0.setBounds(68, 307, 50, 50);
		panel_Cal.add(btn0);
		
		JButton btnE = new JButton("e");
		btnE.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String Cal_String =  txt_Cal.getText() + btnE.getText();
				txt_Cal.setText(Cal_String);
			}
		});
		btnE.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnE.setBounds(130, 307, 50, 50);
		panel_Cal.add(btnE);
		
		JButton btnPi = new JButton("pi");
		btnPi.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String pi = "\u03c0";
				String Cal_String =  txt_Cal.getText() + pi;
				txt_Cal.setText(Cal_String);
			}
		});
		btnPi.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		btnPi.setBounds(192, 307, 50, 50);
		panel_Cal.add(btnPi);
		
		JButton btnAns = new JButton("ANS");
		btnAns.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
//				String ANS =  txt_Cal.getText();
				txt_Cal.setText("ANS");
			}
		});
		btnAns.setFont(new Font("AppleGothic", Font.PLAIN, 18));
		btnAns.setBounds(254, 307, 50, 50);
		panel_Cal.add(btnAns);
		
//		------------------------CLEAR AND EQUAL------------------------------------
		
		JButton btnClr = new JButton("CLR");
		btnClr.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				txt_Cal.setText("");
			}
		});
		btnClr.setFont(new Font("AppleGothic", Font.BOLD, 23));
		btnClr.setBounds(342, 59, 75, 50);
		panel_Cal.add(btnClr);
		
		JButton btnEquals = new JButton("=");
		btnEquals.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					calculators.set_TextCal(txt_Cal.getText());
					ANS = calculators.resultCal();
					txt_Cal.setText(ANS);
					System.out.println("Action Performed!!!");
				}
				catch(Exception exception)
				{
					System.out.println(exception);
					txt_Cal.setText("Error!!!");
					calculators.del_Operators_All();
				}
				
			}
		});
		btnEquals.setFont(new Font("AppleGothic", Font.BOLD, 30));
		btnEquals.setBounds(342, 121, 75, 236);
		panel_Cal.add(btnEquals);
		
//		--------------------------EQUATION PANEL---------------------------------
		
		JLabel lblEqn = new JLabel("Equation:");
		lblEqn.setHorizontalAlignment(SwingConstants.CENTER);
		lblEqn.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		lblEqn.setBounds(6, 6, 75, 19);
		panel_Eqn.add(lblEqn);
		
		JLabel lblYFx = new JLabel("y = f(x) = ");
		lblYFx.setHorizontalAlignment(SwingConstants.CENTER);
		lblYFx.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		lblYFx.setBounds(6, 31, 88, 25);
		panel_Eqn.add(lblYFx);
		
		txt_Eqn = new JTextField();
		txt_Eqn.setFont(new Font("AppleGothic", Font.PLAIN, 23));
		txt_Eqn.setText("Enter f(x) here...");
		txt_Eqn.setBounds(112, 21, 307, 41);
		panel_Eqn.add(txt_Eqn);
		txt_Eqn.setColumns(10);
		
		JComboBox<String> cBox_Color = new JComboBox<String>();
		cBox_Color.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int index = cBox_Color.getSelectedIndex();
				if(index == 0)
				{
					Color_CBox = "BLACK";
					txt_Eqn.setForeground(Color.BLACK);
				}
				if(index == 1)
				{
					Color_CBox = "RED";
					txt_Eqn.setForeground(Color.RED);
				}
				if(index == 2)
				{
					Color_CBox = "GREEN";
					txt_Eqn.setForeground(Color.GREEN);
				}
			}
		});
		cBox_Color.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		cBox_Color.setModel(new DefaultComboBoxModel<String>(new String[] {"Black", "Red", "Green"}));
		cBox_Color.setSelectedIndex(0);
		cBox_Color.setBounds(317, 71, 102, 27);
		panel_Eqn.add(cBox_Color);
		
		JLabel lblColor = new JLabel("Select Color:");
		lblColor.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		lblColor.setBounds(213, 71, 92, 25);
		panel_Eqn.add(lblColor);
		
		JLabel lblHistory = new JLabel("History:");
		lblHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistory.setFont(new Font("AppleGothic", Font.PLAIN, 15));
		lblHistory.setBounds(6, 138, 61, 25);
		panel_Eqn.add(lblHistory);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 146, 340, 101);
		panel_Eqn.add(scrollPane);
		
		Eqn_List = new DefaultListModel<String>(); 
		
		JList<String> list = new JList<String>(Eqn_List);
		scrollPane.setViewportView(list);
		list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		
		
		JButton btnLoad_Eqn = new JButton("Load");
		btnLoad_Eqn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int i = list.getSelectedIndex();
				final String LoadText = (String) Eqn_List.get(i);
				txt_Eqn.setText(LoadText); 
			}
		});
		btnLoad_Eqn.setFont(new Font("AppleGothic", Font.PLAIN, 13));
		btnLoad_Eqn.setBounds(6, 175, 61, 29);
		panel_Eqn.add(btnLoad_Eqn);
		
		JButton btnErase_Plot = new JButton("Erase");
		btnErase_Plot.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Graphics g = panel_Graph.getGraphics();
				Graphics2D gg;
				gg = (Graphics2D) g;
				gg.clearRect(0, 0, 718, 630);
				int height = panel_Graph.getHeight();
				int width = panel_Graph.getWidth();
				gg.setColor(Color.BLACK);
				gg.drawLine((width/2), 0, (width/2), 630);
				gg.setColor(Color.BLACK);
				gg.drawLine(0, (height/2), 718, (height/2));
				gg.drawString("X-axis", 665, 310);
				gg.drawString("Y-axis", 360, 16);
				gg.drawString("(0,0)", 360, 310);
			}
		});
		btnErase_Plot.setFont(new Font("AppleGothic", Font.PLAIN, 12));
		btnErase_Plot.setBounds(106, 71, 88, 27);
		panel_Eqn.add(btnErase_Plot);
		
		JButton btnPlot_Eqn = new JButton("Plot");
		btnPlot_Eqn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
					//--------Declarations-----------------------------
					double prevX = 0, prevY = 0;
					plotX = new ArrayList<Double>();
					plotY = new ArrayList<Double>();
					xPoints = new ArrayList<Double>();
					yPoints = new ArrayList<Double>();
					String x_Range, y_Range;
					int height = panel_Graph.getHeight();
					int width = panel_Graph.getWidth();
					final String InputText = txt_Eqn.getText();
					//---------Setting Values of Validation Class------
					validateEqn = new ValidateEqn();
					validateEqn.setTxtString(txt_Eqn.getText());
					x_Range = txt_XRange.getText();
					y_Range = txt_YRange.getText();
					calculateRange(x_Range, y_Range);
					validateEqn.setXHigh(xHigh);
					validateEqn.setXLow(xLow);
					validateEqn.setYHigh(yHigh);
					validateEqn.setYLow(yLow);
					validateEqn.validateXY();
					errorMain = validateEqn.getErrorMain();
					errorTrig = validateEqn.getErrorTrig();
					xPoints = validateEqn.getXPoints(); 
					yPoints = validateEqn.getYPoints();
					if(errorMain==0 && errorTrig ==0)
					{
						Eqn_List.addElement(InputText);
						convertXYPlot();
						Graphics g = panel_Graph.getGraphics(); //Graphics 
						Graphics2D gg;
						gg = (Graphics2D) g;
						//---------Implementation---------------------------
//						gg.clearRect(0, 0, 718, 630);
						gg.setColor(Color.BLACK);
						gg.drawString("X-axis", 665, 310);
						gg.drawString("Y-axis", 360, 16);
						gg.drawString("(0,0)", 360, 310);
						gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
						gg.setColor(Color.BLACK);
						gg.drawLine((width/2), 0, (width/2), 630);
						gg.setColor(Color.BLACK);
						gg.drawLine(0, (height/2), 718, (height/2));
						
						if(Color_CBox.equals("BLACK")){
							gg.setColor(Color.BLACK);
						} else if(Color_CBox.equals("RED")){
							gg.setColor(Color.RED);
						} else if(Color_CBox.equals("GREEN")){
							gg.setColor(Color.GREEN);
						}
						
						for (int i=0; i<plotX.size(); i++)
						{
							if(i==0){}
							else
							{
								gg.draw(new Line2D.Double(prevX, height-prevY, plotX.get(i), height-plotY.get(i)));
							}
							prevX = plotX.get(i);
							prevY = plotY.get(i);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(frame, "Entered Equation is Incorrect!!!");
					}
			}
		});
		btnPlot_Eqn.setFont(new Font("AppleGothic", Font.PLAIN, 12));
		btnPlot_Eqn.setBounds(6, 71, 88, 27);
		panel_Eqn.add(btnPlot_Eqn);
		
		JButton btnDel_Eqn = new JButton("Del");
		btnDel_Eqn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int i = list.getSelectedIndex();
				if(i !=- 1)
				{
					Eqn_List.remove(i);
				}
			}
		});
		btnDel_Eqn.setFont(new Font("AppleGothic", Font.PLAIN, 13));
		btnDel_Eqn.setBounds(6, 218, 61, 29);
		panel_Eqn.add(btnDel_Eqn);
		
		JLabel lblX = new JLabel("X-Range:");
		lblX.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblX.setBounds(6, 110, 61, 23);
		panel_Eqn.add(lblX);
		
		JLabel lblY = new JLabel("Y-Range:");
		lblY.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblY.setBounds(222, 109, 61, 25);
		panel_Eqn.add(lblY);
		
		txt_XRange = new JTextField();
		txt_XRange.setBounds(79, 108, 126, 26);
		panel_Eqn.add(txt_XRange);
		txt_XRange.setColumns(10);
		
		txt_YRange = new JTextField();
		txt_YRange.setBounds(293, 108, 126, 26);
		panel_Eqn.add(txt_YRange);
		txt_YRange.setColumns(10);
		
	}
	
	private void convertXYPlot() 
	{
		// TODO Auto-generated method stub
		for (int i=0; i<yPoints.size(); i++)
		{
			if(yPoints.get(i)>yHigh){
				yPoints.add(i,yHigh);
				yPoints.remove(i+1);
			} else if (yPoints.get(i)<yLow){
				yPoints.add(i,yLow);
				yPoints.remove(i+1);
			}
			
			double newx = (xPoints.get(i)/xHigh)*359 + 359;
			double newy = (yPoints.get(i)/yHigh)*315 + 315;
			plotX.add(newx);
			plotY.add(newy);
		}
	}
	
	private void calculateRange(String x_Range, String y_Range) 
	{
		// TODO Auto-generated method stub
//		Implement a Check Range Function 
		int rangeError = 0;
		
		if(!(x_Range.contains(",") && x_Range.contains("[") && x_Range.contains("]") && y_Range.contains(",") && y_Range.contains("[") && y_Range.contains("]"))){
			rangeError = 1;
		} 
		
		for(int i=1; i<x_Range.length()-1; i++){
			if((x_Range.charAt(i)>=58 && x_Range.charAt(i)<=127) || (x_Range.charAt(i)>=32 && x_Range.charAt(i)<=43) || x_Range.charAt(i)=='/'){
				rangeError = 1;
			}
		}
		
		if(rangeError == 0)
		{
			String strx = x_Range.substring(1, x_Range.length()-1);
			String sbx[] = strx.split(",");
			String stry = y_Range.substring(1, y_Range.length()-1);
			String sby[] = stry.split(",");
			xLow = Double.parseDouble(sbx[0]);
			xHigh = Double.parseDouble(sbx[1]);
			yLow = Double.parseDouble(sby[0]);
			yHigh = Double.parseDouble(sby[1]);
		}
		else
		{
			JOptionPane.showMessageDialog(frame, "Wrong format of Range!!!");
		}
	}

}
