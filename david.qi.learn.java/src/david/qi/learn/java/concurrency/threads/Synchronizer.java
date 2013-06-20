package david.qi.learn.java.concurrency.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Synchronizer {

	public static long time(Executor executor, int concurrency, final Runnable action) throws InterruptedException{
		final CountDownLatch ready = new CountDownLatch(concurrency);
		final CountDownLatch start	  = new CountDownLatch(1);
		final CountDownLatch done  = new CountDownLatch(concurrency);
		for(int i = 0; i < concurrency; i++) {
			executor.execute(new Runnable() {
				public void run() {
					ready.countDown();  //Tell timer we're ready
					try {
						start.await();	//Wait till peers are ready
						action.run();
					}catch(InterruptedException e) {
						Thread.currentThread().interrupt();
					}finally {
						done.countDown();  //Tell timer we're done
					}
				}	
			});			
		}
		
		ready.await();				//Wait for all workers to be ready
		long startNanos = System.nanoTime();
		start.countDown(); 	// And they're off!
		done.await();				// Wait for all workers to finish.
		return System.nanoTime() - startNanos;
		
	}
	
	public static void main(String[] args) throws InterruptedException {
	    Executor executor = Executors.newCachedThreadPool();
	    int concurrency = 4;
	    long time = time(executor, concurrency, new Runnable() {
	    	public void run() {
	    		StringBuilder s = new StringBuilder("-");
	    		while(s.length() < 10) {
	    			System.out.print(s);
	    			s.append("-");
	    		}
	    		System.out.println(">");
	    	}
	    });
	    System.out.println(time);
    }
}
