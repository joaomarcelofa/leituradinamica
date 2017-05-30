package threads;

public class Temporizador implements Runnable {

	private int time;
	
	@Override
	public void run() {
		
		try {
			this.wait();
			Thread.sleep(this.time);
		
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		finally {
			notify();
		}
	}
	
	public void setTimer(int time) {
		this.time = time;
	}

}
