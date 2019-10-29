package Project9;

public class Project9 {

	static Resource r = new Resource();
	
	public static void main(String[] args) throws InterruptedException {
		
		//创建线程
		Thread a = new Thread(new ThreadA(r));
		Thread b = new Thread(new ThreadB(r));
		Thread c = new Thread(new ThreadC(r));
		
		//开始进程
		a.start();
		b.start();
		c.start(); 
	}
	
	//获取Resource对象
	static Resource getResource() {
		return r;
	}
	
}
