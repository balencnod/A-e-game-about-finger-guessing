package Project9;

public class Resource {

	double t1,t2;
	int num1,num2;
	boolean b;
	
	Resource(){
		b = false;
		num1 = 0;
		num2 = 0;
		t1 = 0;
		t2 = 0;
	}
	
	//布尔值获取、变异器
	synchronized void setB(boolean _b) {
		b = _b;
	}
	synchronized boolean getB() {
		return b;
	}
	
	//A线程出值获取、变异器
	synchronized void setNum1(int _num1) {
		num1 = _num1;
	}
	synchronized int getNum1() {
		return num1;
	}
	
	//B线程出值获取、变异器
	synchronized void setNum2(int _num2) {
		num2 = _num2;
	}
	synchronized int getNum2() {
		return num2;
	}
	
	//A线程睡眠时间获取、变异器
	synchronized void setT1(double _t1) {
		t1 = _t1;
	}
	synchronized double getT1() {
		return t1;
	}
	
	//B线程睡眠时间获取、变异器
	synchronized void setT2(double _t2) {
		t2 = _t2;
	}
	synchronized double getT2() {
		return t2;
	}
}
