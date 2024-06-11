import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.SwingConstants;
import org.mariuszgromada.math.mxparser.*;

public class Graph extends JFrame {
	
	
	float x[]=null;
	float y[]=null;
	double result[]=null;
	public static String exp;
	private JTextField textField;
	
	
	public Graph() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		this.setSize(500,157);
		c.setBackground(Color.LIGHT_GRAY);
		setLocationRelativeTo(null); // 창을 가운데로 띄움
		setVisible(true); // 창을 띄울지 말지 여부를 결정
		JPanel panel = new JPanel();
		c.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("함수 식을 입력하세요");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 17));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(76, 30, 352, 30);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("f(x) = ");
		lblNewLabel_1.setBounds(30, 37, 34, 15);
		panel_1.add(lblNewLabel_1);
	
		 
		JButton btnNewButton = new JButton("입력완료");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exp = textField.getText();
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
           	 // Graph 클래스의 인스턴스 생성
               Drawing drawing = new Drawing();
               // Graph 창을 보이게 함
               dispose();
				
			}
		});
		btnNewButton.setBounds(154, 82, 97, 23);
		panel.add(btnNewButton, BorderLayout.SOUTH);
		
		
	
	}
	
	public void main(String[] args) {
			new Graph();
	}
}

 
	