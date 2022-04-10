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

//JFrame 상속 ActionListerner 인터페이스 상속 (이벤트 처리)
class Calculator extends JFrame implements ActionListener{

	//버튼 문자 배열 
	String s[] = {"7","8","9","/","sqrt","4","5","6","*","%","1","2",
				  "3","-","1/x","0","+/-",".","+","="};
	//버튼객체 배열 선언 (20)
	JButton[] jb = new JButton[20];
	//텍스트 필드 객체  선언 
	JTextField jtf = new JTextField();
	//연산 결과 
	double a1;
	//"." 버튼 스위치 (if문에 사용)
	boolean swt = true;
	//텍스트 필드의 값  예) 버튼 9입력 -> 9 + 버튼 1입력 = 91
	String jtfs;    
	//연산할 수 
	String operand;
	//연산자 저장 +,-,*,/%
	String operator;
	int count;
	
	//버튼 클릭 이벤트 
	@Override
	public void actionPerformed(ActionEvent e) {
		//a에 눌렸던 버튼의 값을 저장 
		String a = e.getActionCommand();
		//ss에 현재 JTextField에 있는 값을 저장  
		String ss = jtf.getText();
		
		//눌렸던 버튼의 값으로 switch/case에 적용 
		switch (a) {
		
		//특수 연산 
		case "Backspace":
			//예외처리 
			try {
				//substring() 문자열을 짜를때 사용 
				//매개변수 (시작인덱스,마지막 인덱스) 
				//예) name (0,2) -> nam
				ss = ss.substring(0,ss.length()-1);
				jtf.setText(ss);
			}
			//텍스트 필드의 값이  null일 경우 공백으로 처리 
			catch(Exception e1) {
				jtf.setText("");
			}
			break;
		case "C":
			jtf.setText("");
			break;
			//예외처리는 상관없습니다 
		case "+/-":
			try {
				//형변환 (변환할 자료형)
				//int는 Double보다 작기때문에 자동형변환이 안됨  
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
			//수학 클래스 Math의 sqrt함수 (제곱근 반환)
			a1 = (int)Math.sqrt(Double.parseDouble(ss));
			jtf.setText(a1+"");
			break;
		case ".":
			//"."은 한번만 추가해야 되기때문에 swt 변수를 사용하여 처리 
			//swt의 기본값 true if문 통과 
			if(swt) {
				ss+=".";
				jtf.setText(ss);
				//swt = false로 더이상 if문 통과 불가 -> "=" 버튼 클릭시 true로 변환 
				swt = false;
			}
			break;
			//각 사칙 연산 버튼이 눌렸을때 
		case "+":
			//case문 사칙 연산은 모두 동일(주석문)
			
			//operand 변수에 현재 텍스트 필드값을 넣어줌 
			operand = jtf.getText();
			//텍스트 필드는 초기화 
			jtf.setText("");
			//연산자 저장 
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
			//"." 사용가능 
			swt=true;
			count++;
			//operator의 사칙 연산 기호에 따라 if문 실행 
			//문자열은 equals함수를 사용
			
			//예외처리 아무값도 없을때 "="눌렀을때 에러발생해서 넣어주었습니다 
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
					 //나누기는 0으로 나눌수 없기때문에 if문으로 간단한 예외처리 
					 if(Double.parseDouble(jtfs)==0) {
						 jtf.setText("0으로 연산불가");
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
		//case문에 해당하지 않는것을 처리 
		default:
			//ss(현재값) + a(누른 버튼값) 
			//-> "1" + "2" = 12 
			//이렇게 처리해줘야 숫자가 늘어납니다 
			
			//연산 결과값이 나온상태에서 숫자버튼을 누르면 
			//결과값에 문자열로 추가되어 연산되는 오류가 있어서 수정했습니다 
			//count변수는 "=" 클릭시 1이 더해집니다 
			//이 변수를 통해서 결과값과 입력값을 알아내고 그에따라 실행 
			if(count==1) {
				//현재 값은 초기화하여 새로운 값으로 입력
				jtf.setText("");
				ss = "";
				jtfs = ss + a;
				jtf.setText(jtfs);
				//카운트는 초기화 
				count=0;
			}
			else {
				jtfs = ss + a;
				jtf.setText(jtfs);
			}
		}
	}
	public Calculator() {
		//컨테이너 선언 & 레이아웃 지정
		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(5,5));
		
		//3개의 영역의 JPanel 선언 & 레이아웃 지정(GridLayout)
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		
		jp1.setLayout(new GridLayout(1,1,5,5));
		jp2.setLayout(new GridLayout(1,2,5,5));
		jp3.setLayout(new GridLayout(4,5,5,5));
		
		//jtf = JTextField 객체 (텍스트 입력창)
		//jp1객체에 jtf 추가 
		//jtf 설정 
		jp1.add(jtf);
		jtf.setText("");
		jtf.setEditable(false);
		jtf.setHorizontalAlignment(JTextField.RIGHT);
		
		//각 패널에 제목과 Border(테두리) 지정 
		jp1.setBorder(new TitledBorder("계산 결과창"));
		jp2.setBorder(new TitledBorder("지우기 버튼"));
		jp3.setBorder(new TitledBorder("계산기 입력 버튼"));
		
		//JButton 객체 생성 (BackSpace,C)
		JButton jpbtn1 = new  JButton("Backspace");
		JButton jpbtn2 = new  JButton("C");
		
		//jp2에 버튼 추가 
		jp2.add(jpbtn1);
		jp2.add(jpbtn2);
		
		//각 버튼에 이벤트 추가 
		jpbtn1.addActionListener(this);
		jpbtn2.addActionListener(this);
		
		//BackSpace,C를 제외한 버튼은 jp3에 for문과 객체 배열을 사용하여 추가 
		for(int i=0;i<s.length;i++) {
			//1. jb 객체 배열 생성 
			//2. jb 배열에 순서대로 버튼 객체를 추가 
			//3. 매개변수로는 위에 선언한 문자열을 순서대로 넘겨줌 
			//4. 각 버튼별로 이벤트 추가 
			jb[i] = new JButton(s[i]);
			jp3.add(jb[i]);
			jb[i].addActionListener(this);
		}
		
		//컨테이너에 패널위치 지정 위,중간,아래 
		ct.add(jp1,BorderLayout.NORTH);
		ct.add(jp2,BorderLayout.CENTER);
		ct.add(jp3,BorderLayout.SOUTH);
	}
}

public class Example {

	public static void main(String[] args) {
		//객체 생성 -> 생성자 호출 -> 계산기 실행 
		Calculator c = new Calculator();
		c.setSize(400,300);
		//창 출력 (false는 창이 아예 출력이 안됨)
		c.setVisible(true);
		//창을 닫았을때 실행 자체를 종료 시키는것 같습니다 
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
