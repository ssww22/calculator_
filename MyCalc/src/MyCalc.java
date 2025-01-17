import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.IntPredicate;

public class MyCalc extends JFrame {
	
	static JLabel label;
	static JLabel info;
	double result = 0;//결과
	String math = "";//연산자
	double num = 0;//
	String tostring = "";
	
	

	
	public MyCalc() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container MyCon = getContentPane();
		MyCon.setLayout(new BorderLayout(5, 5));
		MyCon.setBackground(Color.LIGHT_GRAY);
		
		//입력 값과 결과값이 출력되는 위 패널 생성 후 콘테이너에 추가
		UpPanel UP = new UpPanel();
		MyCon.add(UP, BorderLayout.EAST);
		//버튼을 넣을 아래 패널 생성 후 콘테이너에 추가
		DownPanel DP = new DownPanel();
		MyCon.add(DP, BorderLayout.SOUTH);
		
		setSize(800, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyCalc();
	}
	
	
	class UpPanel extends JPanel {
		public UpPanel() {
			setLayout(new GridLayout(3, 1));
			setBackground(Color.LIGHT_GRAY);
			info = new JLabel("");
			label = new JLabel("0");
			
			info.setFont(new Font("맑은 고딕", 0, 40));
			info.setForeground(Color.BLACK);
			info.setHorizontalAlignment(SwingConstants.RIGHT);
			
			label.setFont(new Font("맑은 고딕", Font.BOLD , 55));
			label.setForeground(Color.BLACK);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			
			add(info);
			add(label);
		}
	}
	
	class DownPanel extends JPanel{
		public DownPanel() {
			JButton[] bt = new JButton[28];
			setLayout(new GridLayout(7, 4, 5, 5));
			setBackground(Color.DARK_GRAY);
			
			bt[0] = new JButton("×");
			bt[1] = new JButton("÷");
			bt[2] = new JButton("AC");
			bt[3] = new JButton("CE");

			bt[4] = new JButton("7");
			bt[5] = new JButton("8");
			bt[6] = new JButton("9");
			bt[7] = new JButton("√");
			
			bt[8] = new JButton("4");
			bt[9] = new JButton("5");
			bt[10] = new JButton("6");
			bt[11] = new JButton("-");
			
			bt[12] = new JButton("1");
			bt[13] = new JButton("2");
			bt[14] = new JButton("3");
			bt[15] = new JButton("+");
			
			bt[16] = new JButton("＾2");
			bt[17] = new JButton("0");
			bt[18] = new JButton(".");
			bt[19] = new JButton("=");
			
			bt[20] = new JButton("sin()");
			bt[21] = new JButton("cos()");
			bt[22] = new JButton("tan()");
			bt[23] = new JButton("π");
			
			bt[24] = new JButton("csc()");
			bt[25] = new JButton("sec()");
			bt[26] = new JButton("cot()");
			bt[27] = new JButton("Graph");
			
			
			
			
			for(int i = 0; i < 28; i++) {
				
				bt[i].setFont(new Font("맑은 고딕", 0, 20));
				bt[i].setForeground(Color.BLACK);
				bt[i].setBackground(new Color(240, 240, 240));
				
				
				if(3 < i && i < 15 && i % 4 != 3) {
					bt[i].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JButton b = (JButton)e.getSource();
							if(label.getText() == "0") {
								label.setText("");
							}
							String labeltext = label.getText();
							String text = b.getText();
							String newtext = labeltext + text;
							int n = newtext.length();
							if(n <= 10) {
								label.setText(newtext);
							}
							if(info.getText().contains("=") == true) {
								info.setText("");
								label.setText(b.getText());
								result = 0;
								num = 0;
							}
						}
					});
				}else if(i == 17) {
					bt[i].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							JButton b = (JButton)e.getSource();
							String labeltext = label.getText();
							String text = b.getText();
							String newtext = labeltext + text;
							if(labeltext == "0") {
								label.setText("0");
							}else {
								label.setText(newtext);
							}
							
						}
					});
				}else if(i == 19) {
					bt[i].setBackground(new Color(78, 197, 255));
					bt[i].addActionListener(new Result());
				}else if(i % 4 == 3 || i < 3 || 15 < i) {
					bt[i].setBackground(new Color(218, 218, 218));
					if(i == 2) {
							bt[i].addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int n = label.getText().length();
								if(n >= 0) {
									label.setText("0");
									info.setText("");
								}
								result = 0;
							}
						});
					}else if(i == 3) {
						bt[i].addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								int n = label.getText().length();
								if(n > 0) {
									setBackSpace(getBackSpace().substring(0, getBackSpace().length() - 1));
								}
								if(label.getText() == "") {
									label.setText("0");
								}
							}
						});
					}else if(i == 18) {
						bt[i].addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								JButton b = (JButton)e.getSource();
								String labeltext = label.getText();
								String text = b.getText();
								String newtext = labeltext + text;
								int n = newtext.length();
								if(label.getText().contains(b.getText()) == false && n < 10) {
										label.setText(newtext);
								}
								}
						});
					}else if(i==20) {
					    bt[20].addActionListener(new ActionListener() {
					        @Override
					        public void actionPerformed(ActionEvent e) {
					            // 현재 입력된 값을 가져옴
					            double value = Double.parseDouble(label.getText());
					            // sin 계산
					           result = Math.sin(value);
					            // 결과 표시
					            label.setText(Double.toString(result));
					            // info 라벨에 수식 표시
					            info.setText("sin(" + value + ")");
					        }
					    });
					        }else if(i==21) {
							    bt[21].addActionListener(new ActionListener() {
							        @Override
							        public void actionPerformed(ActionEvent e) {
							            // 현재 입력된 값을 가져옴
							            double value = Double.parseDouble(label.getText());
							            // sin 계산
							            result = Math.cos(value);
							            // 결과 표시
							            label.setText(Double.toString(result));
							            // info 라벨에 수식 표시
							            info.setText("cos(" + value + ")");
							        }
							    });
							        }else if(i==22) {
									    bt[22].addActionListener(new ActionListener() {
									        @Override
									        public void actionPerformed(ActionEvent e) {
									            // 현재 입력된 값을 가져옴
									            double value = Double.parseDouble(label.getText());
									            // sin 계산
									            result = Math.tan(value);
									            // 결과 표시
									            label.setText(Double.toString(result));
									            // info 라벨에 수식 표시
									            info.setText("tan(" + value + ")");
									        }
									    });
									        }
							        else if(i==23) {
							        	 bt[i].addActionListener(new ActionListener() {
						                        @Override
						                        public void actionPerformed(ActionEvent e) {
						                            if (label.getText().equals("0")) {
						                                label.setText("");
						                            }
						                            String labeltext = label.getText();
						                            String newtext = labeltext + "π";
						                            int n = newtext.length();
						                            if (n <= 10) {
						                                label.setText(newtext);
						                                result = (Double.parseDouble(labeltext))*3.14;
						                                label.setText(Double.toString(result));
											            // info 라벨에 수식 표시
											            info.setText(labeltext+"π");
						                                
						                                
						                            }
						                            if (info.getText().contains("=")) {
						                                info.setText("");
						                                label.setText(String.valueOf(Math.PI));
						                                result = 0;
						                                num = 0;
						                            }
						                        }
						                    });
									       
									        }
							        else if(i==24) {
									    bt[24].addActionListener(new ActionListener() {
									        @Override
									        public void actionPerformed(ActionEvent e) {
									            // 현재 입력된 값을 가져옴
									            double value = Double.parseDouble(label.getText());
									            // sin 계산
									            double result = 1/Math.sin(value);
									            // 결과 표시
									            label.setText(Double.toString(result));
									            // info 라벨에 수식 표시
									            info.setText("csc(" + value + ")");
									        }
									    });
									        }
							        else if(i==25) {
									    bt[25].addActionListener(new ActionListener() {
									        @Override
									        public void actionPerformed(ActionEvent e) {
									            // 현재 입력된 값을 가져옴
									            double value = Double.parseDouble(label.getText());
									            // sin 계산
									            double result = 1/Math.cos(value);
									            // 결과 표시
									            label.setText(Double.toString(result));
									            // info 라벨에 수식 표시
									            info.setText("sec(" + value + ")");
									        }
									    });
									        }
							        else if(i==26) {
									    bt[26].addActionListener(new ActionListener() {
									        @Override
									        public void actionPerformed(ActionEvent e) {
									            // 현재 입력된 값을 가져옴
									            double value = Double.parseDouble(label.getText());
									            // sin 계산
									            double result = 1/Math.tan(value);
									            // 결과 표시
									            label.setText(Double.toString(result));
									            // info 라벨에 수식 표시
									            info.setText("cot(" + value + ")");
									        }
									    });
									        }else if(i==27) {
									        	bt[27].addActionListener(new ActionListener() {
	    			                            @Override
									    			public void actionPerformed(ActionEvent e) {
	    			                            	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    			                            	 // Graph 클래스의 인스턴스 생성
	    			                                Graph graph = new Graph();
	    			                                // Graph 창을 보이게 함
	    			                                graph.setVisible(true);
	    			                                dispose();
	    			                            }
									    		});
									    		
									    	}
											   
											        
				
					else {
						bt[i].addActionListener(new Result());
					}
				}
				add(bt[i]);
			}
		}		
	}
	
	public class Result implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			String labeltext = label.getText();
			String text = b.getText();
			String newtext = labeltext + text;
			int n = newtext.length();
			if(text != "＾2") {
				num = Double.parseDouble(label.getText().substring(0, n - 1));				
			}
			if(math == "+") {
				result += num;
				math = "";
			}else if(math == "-") {
				result -= num;
				math = "";
			}else if(math == "×") {
				result *= num;
				math = "";
			}else if(math == "÷") {
				result /= num;
				math = "";
			}
			if(math == "") {
				math = b.getText();			
			}
			
			
			if(info.getText() == "" && text != "＾2" && text != "=") {
				info.setText(newtext);
				result = num;
				label.setText("0");
			}else if(info.getText() != "" && text != "＾2" && text != "="){
				result = (Math.round(result*1000000000)/1000000000.0);
				if(result % 1 == 0) {
					info.setText((int) result + text);
					label.setText("0");
				}else {
					info.setText(result + text);
					label.setText("0");
				}
			}
			
			
			if(text == "＾2") {
				num = Double.parseDouble(label.getText().substring(0, n - 2));
				math = "";
				if(info.getText() == "") {
					result = (Math.round((Math.pow(num, 2))*1000000000)/1000000000.0);
					if(result % 1 == 0) {
						info.setText("pow(" + (int) num + ")");
						label.setText(String.valueOf((int) result));
					}else {
						info.setText("pow(" + num + ")");
						label.setText(String.valueOf(result));
					}
				}else {
					if(result % 1 == 0) {
						info.setText("pow(" + (int) result + ")");
						result = (Math.round((Math.pow(result, 2))*1000000000)/1000000000.0);
						label.setText(String.valueOf((int) result));
					}else {
						info.setText("pow(" + result + ")");
						result = (Math.round((Math.pow(result, 2))*1000000000)/1000000000.0);
						label.setText(String.valueOf(result));
					}
				}
			}
			
			
			if(text == "√") {
				math = "";
				if(info.getText() == "") {
					result = (Math.round((Math.sqrt(num))*1000000000)/1000000000.0);
					if(result % 1 == 0) {
						info.setText("sqrt(" + (int) num + ")");
						label.setText(String.valueOf((int) result));
					}else {
						info.setText("sqrt(" + num + ")");
						label.setText(String.valueOf(result));
					}
				}else {
					if(Math.sqrt(result) % 1 == 0) {
						info.setText("sqrt(" + (int) result + ")");
						result = (Math.round((Math.sqrt(result))*1000000000)/1000000000.0);
						label.setText(String.valueOf((int) result));
					}else {
						info.setText("sqrt(" + result + ")");
						result = (Math.round((Math.sqrt(result))*1000000000)/1000000000.0);
						label.setText(String.valueOf(result));
					}
				}
			}
		
		 
			
			if(text == "=") {
				math = "";
				if(info.getText() != "" && label.getText() != "" && info.getText().contains(text) == false) {
					if(result % 1 == 0) {
						info.setText(info.getText() + label.getText() + text);
						label.setText(String.valueOf((int) result));
					}else {
						info.setText(info.getText() + label.getText() + text);
						label.setText(String.valueOf(result));
					}
				}
			}
		}
	}
	
	
	
	public double Calculator(String ss) {
		return 0;
	}
	private void setBackSpace(String bs) {
		label.setText(bs);
	}
	
	private String getBackSpace() {
		return label.getText();
	}

	
}