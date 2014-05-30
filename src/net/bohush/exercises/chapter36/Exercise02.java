package net.bohush.exercises.chapter36;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Exercise02 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel jLabell = new JLabel();
	private JLabel jLabel2 = new JLabel();
	private JLabel jLabel3 = new JLabel();
	private JLabel jLabel4 = new JLabel("0");
	
	public Exercise02() {
		setLayout(new GridLayout(4, 1));
		jLabell.setHorizontalAlignment(JLabel.CENTER);
		jLabel2.setHorizontalAlignment(JLabel.CENTER);
		jLabel3.setHorizontalAlignment(JLabel.CENTER);
		jLabel4.setHorizontalAlignment(JLabel.CENTER);
		add(jLabell);
		add(jLabel2);
		add(jLabel3);
		add(jLabel4);
		MemoryWatch memoryWatch = new MemoryWatch();
		memoryWatch.addMemoryListener(new MemoryListener() {			
			@Override
			public void sufficientMemory(MemoryEvent e) {
				jLabell.setText("sufficient");
				jLabel2.setText("Free: " + memoryToString(e.freeMemory()));
				jLabel3.setText("Total: " + memoryToString(e.totalMemory()));
			}			
			@Override
			public void insufficientMemory(MemoryEvent e) {
				jLabell.setText("insufficient");
				jLabel2.setText("Free: " + memoryToString(e.freeMemory()));
				jLabel3.setText("Total: " + memoryToString(e.totalMemory()));
			}
		});
		new FillMemory();
	}
	
	public static void main(String[] args) {
		Exercise02 frame = new Exercise02();
		frame.setSize(500, 200);
		frame.setTitle("Exercise02");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	
	static String memoryToString(long b) {
		String result = (b % 1024) + "B";
		b = b / 1024;
		result = (b % 1024) +  "KB " + result;
		b = b / 1024;
		result = (b % 1024) +  "MB " + result;
		b = b / 1024;
		result = (b % 1024) +  "GB " + result;
		return result;
	}
	
	class FillMemory implements Runnable {
		long filledSize = 0;
		
		public FillMemory() {
			Thread thread = new Thread(this);
			thread.start();
		}
		
		@Override
		public void run() {
			int size = 256 * 128; //128kb
			fillMemory(size);
		}
		
		public void fillMemory(int size) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {}
			@SuppressWarnings("unused")
			int[] array = new int[size];
			filledSize += size * 4;
			jLabel4.setText("Filled " + memoryToString(filledSize));
			
			fillMemory(size);
		}
	}

	
	static class MemoryWatch implements Runnable {
		private long lowLimit;
		private long highLimit;
		private Runtime runtime;
		
		private ArrayList<MemoryListener> memoryListenerList; 
		
		public MemoryWatch(long lowLimit, long highLimit) {
			this.lowLimit = lowLimit;
			this.highLimit = highLimit;
			runtime = Runtime.getRuntime(); 
			Thread thread = new Thread(this);
			thread.start();
		}
		
		public MemoryWatch() {
			this(1024 * 1024 * 10, 1024 * 1024 * 20);
		}

		public long getHighLimit() {
			return highLimit;
		}

		public void setHighLimit(long highLimit) {
			this.highLimit = highLimit;
		}

		public long getLowLimit() {
			return lowLimit;
		}

		public void setLowLimit(long lowLimit) {
			this.lowLimit = lowLimit;
		}
		
		public synchronized void addMemoryListener(MemoryListener listener) {
			if (memoryListenerList == null) {
				memoryListenerList = new ArrayList<MemoryListener>(2);
			}

			if (!memoryListenerList.contains(listener)) {
				memoryListenerList.add(listener);
			}
		}
		
		public synchronized void removeMemoryListener(MemoryListener listener) {
			if ((memoryListenerList != null) && (memoryListenerList.contains(listener))) {
				memoryListenerList.remove(listener);
			}
		}
		
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {}

				if (runtime.freeMemory() < lowLimit) {
					MemoryEvent e = new MemoryEvent(this, runtime.freeMemory(), runtime.totalMemory());
					fireInsufficientMemory(e);
				}
				
				if (runtime.freeMemory() > highLimit) {
					MemoryEvent e = new MemoryEvent(this, runtime.freeMemory(), runtime.totalMemory());
					fireSufficientMemory(e);
				}

			}
		}
		
		@SuppressWarnings("unchecked")
		protected void fireSufficientMemory(MemoryEvent e) {
			ArrayList<MemoryListener> list;

			synchronized (this) {
				if (memoryListenerList == null) {
					return;
				} else {
					list = (ArrayList<MemoryListener>) memoryListenerList.clone();
				}
			}

			for (int i = 0; i < list.size(); i++) {
				MemoryListener listener = (MemoryListener) list.get(i);
				listener.sufficientMemory(e);
			}
		}

		@SuppressWarnings("unchecked")
		protected void fireInsufficientMemory(MemoryEvent e) {
			ArrayList<MemoryListener> list;

			synchronized (this) {
				if (memoryListenerList == null) {
					return;
				} else {
					list = (ArrayList<MemoryListener>) memoryListenerList.clone();
				}
			}

			for (int i = 0; i < list.size(); i++) {
				MemoryListener listener = (MemoryListener) list.get(i);
				listener.insufficientMemory(e);
			}
		}

	}
	
	static class MemoryEvent extends EventObject {
		private static final long serialVersionUID = 1L;
		private long freeMemory;
		private long totalMemory;
		
		public MemoryEvent(Object source, long freeMemory, long totalMemory) {
			super(source);
			this.freeMemory = freeMemory;
			this.totalMemory = totalMemory;
		}
		
		public long freeMemory() {
			return freeMemory;
		}
		
		public long totalMemory() {
			return totalMemory;
		}
		
	}

	interface MemoryListener extends EventListener {

		public void sufficientMemory(MemoryEvent e);

		public void insufficientMemory(MemoryEvent e);

	}
}
