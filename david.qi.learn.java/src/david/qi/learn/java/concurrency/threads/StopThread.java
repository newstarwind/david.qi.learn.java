package david.qi.learn.java.concurrency.threads;

import java.util.concurrent.TimeUnit;

public class StopThread {
	private static boolean stopRequested;
	
	
	private static synchronized void requestStop() {
		stopRequested = true;
	}
	
	//注意写方法和读方法都被同步, 只同步写方法还不够.
	private static synchronized boolean stopRequested() {
		return stopRequested;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			@Override public void run() {
				int i = 0;
				while(!stopRequested())
					i++;
				System.out.println(i);
			}
		});
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(2);
		requestStop();
	}
}
