package Project9;

public class ThreadA implements Runnable {

	int num;
	Resource r;
	
	ThreadA(Resource _r){
		num = 0;
		r = _r;
	}
	
	public void run() {
		for(int i = 0;i < 3;i++) {
			try {
				Thread.sleep(getRandInt());
				getRandChar();
				synchronized(r) {
					if(!r.getB()) {
						r.setB(true);
					}
					else {
						r.notifyAll();
					}
					r.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//获取A到Z的随机字符
	void getRandChar() {
		num = (int) (Math.random()*3);
		r.setNum1(num);
	}
	
	//获取1到1000的随机整数
	int getRandInt() {
		int t = (int)(Math.random()*999+1);
		r.setT1(t);
		return t;
	}
}
