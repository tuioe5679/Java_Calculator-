package event;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

//JFrame ��� ActionListerner �������̽� ��� (�̺�Ʈ ó��)
class Calculator extends JFrame implements ActionListener{

	//��ư ���� �迭 
	String s[] = {"7","8","9","/","sqrt","4","5","6","*","%","1","2",
				  "3","-","1/x","0","+/-",".","+","="};
	//��ư��ü �迭 ���� (20)
	JButton[] jb = new JButton[20];
	//�ؽ�Ʈ �ʵ� ��ü  ���� 
	JTextField jtf = new JTextField();
	//���� ��� 
	double a1;
	//"." ��ư ����ġ (if���� ���)
	boolean swt = true;
	//�ؽ�Ʈ �ʵ��� ��  ��) ��ư 9�Է� -> 9 + ��ư 1�Է� = 91
	String jtfs;    
	//������ �� 
	String operand;
	//������ ���� +,-,*,/%
	String operator;
	int count;
	
	//��ư Ŭ�� �̺�Ʈ 
	@Override
	public void actionPerformed(ActionEvent e) {
		//a�� ���ȴ� ��ư�� ���� ���� 
		String a = e.getActionCommand();
		//ss�� ���� JTextField�� �ִ� ���� ����  
		String ss = jtf.getText();
		
		//���ȴ� ��ư�� ������ switch/case�� ���� 
		switch (a) {
		
		//Ư�� ���� 
		case "Backspace":
			//����ó�� 
			try {
				//substring() ���ڿ��� ¥���� ��� 
				//�Ű����� (�����ε���,������ �ε���) 
				//��) name (0,2) -> nam
				ss = ss.substring(0,ss.length()-1);
				jtf.setText(ss);
			}
			//�ؽ�Ʈ �ʵ��� ����  null�� ��� �������� ó�� 
			catch(Exception e1) {
				jtf.setText("");
			}
			break;
		case "C":
			jtf.setText("");
			break;
			//����ó���� ��������ϴ� 
		case "+/-":
			try {
				//����ȯ (��ȯ�� �ڷ���)
				//int�� Double���� �۱⶧���� �ڵ�����ȯ�� �ȵ�  
				a1 = (int)Double.parseDouble(ss) * -1;
				jtf.setText(a1+"");
			}
			catch(Exception e1) {
				System.out.println(e1);
			}
			break;
		case "1/x":
			try {
				a1 = 1/(int)Double.parseDouble(ss) ;
				jtf.setText(a1+"");
			}
			catch(Exception e1) {
				System.out.println(e1);
			}
			break;
		case "sqrt":
			//���� Ŭ���� Math�� sqrt�Լ� (������ ��ȯ)
			a1 = (int)Math.sqrt(Double.parseDouble(ss));
			jtf.setText(a1+"");
			break;
		case ".":
			//"."�� �ѹ��� �߰��ؾ� �Ǳ⶧���� swt ������ ����Ͽ� ó�� 
			//swt�� �⺻�� true if�� ��� 
			if(swt) {
				ss+=".";
				jtf.setText(ss);
				//swt = false�� ���̻� if�� ��� �Ұ� -> "=" ��ư Ŭ���� true�� ��ȯ 
				swt = false;
			}
			break;
			//�� ��Ģ ���� ��ư�� �������� 
		case "+":
			//case�� ��Ģ ������ ��� ����(�ּ���)
			
			//operand ������ ���� �ؽ�Ʈ �ʵ尪�� �־��� 
			operand = jtf.getText();
			//�ؽ�Ʈ �ʵ�� �ʱ�ȭ 
			jtf.setText("");
			//������ ���� 
			operator = a;
			break;
		case "-":
			operand = jtf.getText();
			jtf.setText("");
			operator = a;
			break;
		case "*":
			operand = jtf.getText();
			jtf.setText("");
			operator = a;
			break;
		case "/":
			operand = jtf.getText();
			jtf.setText("");
			operator = a;
			break;
		case "%":
			operand = jtf.getText();
			jtf.setText("");
			operator = a;
			break;
		case "=":
			//"." ��밡�� 
			swt=true;
			count++;
			//operator�� ��Ģ ���� ��ȣ�� ���� if�� ���� 
			//���ڿ��� equals�Լ��� ���
			
			//����ó�� �ƹ����� ������ "="�������� �����߻��ؼ� �־��־����ϴ� 
			try {
				if(operator.equals("+")) {
					a1 = Double.parseDouble(operand) + Double.parseDouble(jtfs);
					jtf.setText(a1+"");
					operator=null;
				}
				else if(operator.equals("-")) {
					a1 = Double.parseDouble(operand) - Double.parseDouble(jtfs);
					jtf.setText(a1+"");
					operator=null;
				}
				else if(operator.equals("/")) {
					 //������� 0���� ������ ���⶧���� if������ ������ ����ó�� 
					 if(Double.parseDouble(jtfs)==0) {
						 jtf.setText("0���� ����Ұ�");
					 }
					 else {
						 a1 = Double.parseDouble(operand) / Double.parseDouble(jtfs);
						 jtf.setText(a1+"");
						 operator=null;
					 }
				}
				else if(operator.equals("*")) {
					a1 = Double.parseDouble(operand) * Double.parseDouble(jtfs);
					jtf.setText(a1+"");
					operator=null;
				}
				else if(operator.equals("%")) {
					a1 = Double.parseDouble(operand) % Double.parseDouble(jtfs);
					jtf.setText(a1+"");
					operator=null;
				}
			}catch(Exception e1) {
				System.out.println(e1);
			}
			break;	
		//case���� �ش����� �ʴ°��� ó�� 
		default:
			//ss(���簪) + a(���� ��ư��) 
			//-> "1" + "2" = 12 
			//�̷��� ó������� ���ڰ� �þ�ϴ� 
			
			//���� ������� ���»��¿��� ���ڹ�ư�� ������ 
			//������� ���ڿ��� �߰��Ǿ� ����Ǵ� ������ �־ �����߽��ϴ� 
			//count������ "=" Ŭ���� 1�� �������ϴ� 
			//�� ������ ���ؼ� ������� �Է°��� �˾Ƴ��� �׿����� ���� 
			if(count==1) {
				//���� ���� �ʱ�ȭ�Ͽ� ���ο� ������ �Է�
				jtf.setText("");
				ss = "";
				jtfs = ss + a;
				jtf.setText(jtfs);
				//ī��Ʈ�� �ʱ�ȭ 
				count=0;
			}
			else {
				jtfs = ss + a;
				jtf.setText(jtfs);
			}
		}
	}
	public Calculator() {
		//�����̳� ���� & ���̾ƿ� ����
		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(5,5));
		
		//3���� ������ JPanel ���� & ���̾ƿ� ����(GridLayout)
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		
		jp1.setLayout(new GridLayout(1,1,5,5));
		jp2.setLayout(new GridLayout(1,2,5,5));
		jp3.setLayout(new GridLayout(4,5,5,5));
		
		//jtf = JTextField ��ü (�ؽ�Ʈ �Է�â)
		//jp1��ü�� jtf �߰� 
		//jtf ���� 
		jp1.add(jtf);
		jtf.setText("");
		jtf.setEditable(false);
		jtf.setHorizontalAlignment(JTextField.RIGHT);
		
		//�� �гο� ����� Border(�׵θ�) ���� 
		jp1.setBorder(new TitledBorder("��� ���â"));
		jp2.setBorder(new TitledBorder("����� ��ư"));
		jp3.setBorder(new TitledBorder("���� �Է� ��ư"));
		
		//JButton ��ü ���� (BackSpace,C)
		JButton jpbtn1 = new  JButton("Backspace");
		JButton jpbtn2 = new  JButton("C");
		
		//jp2�� ��ư �߰� 
		jp2.add(jpbtn1);
		jp2.add(jpbtn2);
		
		//�� ��ư�� �̺�Ʈ �߰� 
		jpbtn1.addActionListener(this);
		jpbtn2.addActionListener(this);
		
		//BackSpace,C�� ������ ��ư�� jp3�� for���� ��ü �迭�� ����Ͽ� �߰� 
		for(int i=0;i<s.length;i++) {
			//1. jb ��ü �迭 ���� 
			//2. jb �迭�� ������� ��ư ��ü�� �߰� 
			//3. �Ű������δ� ���� ������ ���ڿ��� ������� �Ѱ��� 
			//4. �� ��ư���� �̺�Ʈ �߰� 
			jb[i] = new JButton(s[i]);
			jp3.add(jb[i]);
			jb[i].addActionListener(this);
		}
		
		//�����̳ʿ� �г���ġ ���� ��,�߰�,�Ʒ� 
		ct.add(jp1,BorderLayout.NORTH);
		ct.add(jp2,BorderLayout.CENTER);
		ct.add(jp3,BorderLayout.SOUTH);
	}
}

public class Example {

	public static void main(String[] args) {
		//��ü ���� -> ������ ȣ�� -> ���� ���� 
		Calculator c = new Calculator();
		c.setSize(400,300);
		//â ��� (false�� â�� �ƿ� ����� �ȵ�)
		c.setVisible(true);
		//â�� �ݾ����� ���� ��ü�� ���� ��Ű�°� �����ϴ� 
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
