import java.util.*;

public class threadHandler {

	private LinkedList<String> Wqueue;
	private boolean status;  
	private int threadlen;  

	public threadHandler() {
		Wqueue = new LinkedList<String>();
		status = false;
		threadlen = 0;
	}

	public synchronized void add(String s) {
		Wqueue.add(s);
		threadlen++;
		notifyAll();
	}

	public synchronized String remove() {
		String s;
		while (!status && threadlen == 0) {
			try {
				wait();
			} catch (Exception e) {};
		}
		if (threadlen > 0) {
			s = Wqueue.remove();
			threadlen--;
			notifyAll();
		} else
			s = null;
		return s;
	}

	public synchronized void finish() {
		status = true;
		notifyAll();
	}
}