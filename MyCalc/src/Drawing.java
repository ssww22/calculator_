import java.awt.BasicStroke;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.mariuszgromada.math.mxparser.*;


public class Drawing extends JFrame {
	public Drawing() {
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("그래프 그리기");
		this.setSize(500,500);
		setVisible(true);
		Container c = getContentPane();
		Background bg = new Background();
		
		c.add(bg);
		
		
	}
	public static void main(String[] args) {
		new Drawing();
	}
	
	
	class Background extends JPanel {

		 public void paint(Graphics g) {

			Graphics2D g2=(Graphics2D)g;
		    g2.translate(getWidth() / 2, getHeight() / 2);                // 원점을 (300, 300)로 이동시킨다.
		    
		    
		 // 점선 그리기
            float dash3[] = {3, 3f};
            g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, dash3, 0));
            for (int i = -500; i <= 500; i += 20) {
                g2.draw(new Line2D.Float(-500, i, 500, i)); // x축과 평행선
                g2.draw(new Line2D.Float(i, -500, i, 500)); // y축과 평행선
            }

            // 굵은 실선으로 x축과 y축 그리기
            float dash0[] = {1, 0f};
            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, dash0, 0));
            g2.draw(new Line2D.Float(-500, 0, 500, 0)); // x축
            g2.draw(new Line2D.Float(0, -500, 0, 500)); // y축
		    
		    
		   
		    double x1, x2;
		    for (double ii = -500; ii <= 500; ii = ii + 1 )
		    	{
		     x1 = ii - 1;
		     x2 = ii;
		     g2.draw(new Line2D.Float((float)x1, (float)-fn(x1), (float)x2, (float)-fn(x2)));     
		    		}
		   		}

		 public double fn(double x) {
			 return ExpressionEvaluator.evaluateExpression(Graph.exp, x);
		   }	
	}
	
	public class ExpressionEvaluator {
	    
	    // 수식을 받아서 계산 결과를 반환하는 메서드
	    public static double evaluateExpression(String expression, double xValue) {
	        // Expression 객체 생성
	        Expression exp = new Expression(expression);
	        // 변수 x 설정
	        Argument x = new Argument("x = " + xValue);
	        exp.addArguments(x);
	        // 수식 계산
	        double result = exp.calculate();
	        return result;
	    }
	}

}

