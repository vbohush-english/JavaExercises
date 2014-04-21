package net.bohush.exercises.chapter32;

import javax.swing.*;

import java.awt.*;

public class Exercise19 extends JApplet {

	private static final long serialVersionUID = 1L;
	private SortPanel[] sortPanels = new SortPanel[3];
	private int size = 50;
	private int sleepTime = 20;
	

	public Exercise19() {
		setLayout(new  GridLayout(0, 3, 0, 0));
		int[] list = new int[size];
		for (int i = 0; i < list.length; i++) {
			list[i] = i + 1;
		}
		for (int i = 0; i < list.length; i++) {
			int index = (int) (Math.random() * list.length);
			int temp = list[i];
			list[i] = list[index];
			list[index] = temp;
		}
		sortPanels[0] = new SelectionSortPanel(" Selection Sort ", list, sleepTime);
		sortPanels[1] = new InsertionSortPanel(" Insertion Sort ", list, sleepTime);
		sortPanels[2] = new BubbleSortPanel(" Bubble Sort ", list, sleepTime);
		
		for (int i = 0; i < sortPanels.length; i++) {
			add(sortPanels[i]);				
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise19");
		JApplet applet = new Exercise19();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	static abstract class SortPanel extends JPanel implements Runnable {
		private static final long serialVersionUID = 1L;
		protected static final int BORDER_WIDTH = 10;
		private static final Dimension PREFFERED_DIMENSION = new Dimension(340, 340);
		protected int size;
		protected int[] list;
		protected int sleepTime;
		private String name;
		
		public SortPanel(String name, int[] list, int sleepTime) {
			this.name = name;
			this.size = list.length;
			this.sleepTime = sleepTime;
			this.list = java.util.Arrays.copyOf(list, size);
			setBackground(Color.BLACK);
			Thread thread = new Thread(this);
			thread.start();
		}
		
		@Override
		public Dimension getPreferredSize() {
			return PREFFERED_DIMENSION;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			//draw border
			g.setColor(Color.WHITE);
			g.drawRect(BORDER_WIDTH, BORDER_WIDTH, getWidth() - 2 * BORDER_WIDTH, getHeight() - 2 * BORDER_WIDTH);
			
			//draw title
			Font nameFont = new Font("Monospaced", Font.BOLD, 16);
			FontMetrics nameFontMetrix = getFontMetrics(nameFont);		
			g.setColor(Color.BLACK);
			g.fillRect((getWidth() - nameFontMetrix.stringWidth(name)) / 2, 0, nameFontMetrix.stringWidth(name), BORDER_WIDTH + nameFontMetrix.getAscent() / 3);
			g.setColor(Color.WHITE);
			g.setFont(nameFont);
			g.drawString(name, (getWidth() - nameFontMetrix.stringWidth(name)) / 2, BORDER_WIDTH + nameFontMetrix.getAscent() / 3);

		}

		@Override
		public abstract void run();

	}
	
	class SelectionSortPanel extends SortPanel {
		private static final long serialVersionUID = 1L;
		private int redColumn = -1;
		private int blueColumn = -1;
		private int greenColumn = -1;
		
		public SelectionSortPanel(String name, int[] list, int sleepTime) {
			super(name, list, sleepTime);
		}

		@Override
		public void run() {
			try {
				for (int i = 0; i < list.length - 1; i++) {
					int currentMinIndex = i;
					redColumn = currentMinIndex;
					for (int j = i + 1; j < list.length; j++) {
						blueColumn = j;
						repaint();
						Thread.sleep(3 * sleepTime);
						if (list[currentMinIndex] > list[j]) {
							currentMinIndex = j;
							redColumn = currentMinIndex;
							repaint();
						}
					}

					if (currentMinIndex != i) {
						int tmp = list[currentMinIndex];
						list[currentMinIndex] = list[i];
						list[i] = tmp;
						repaint();
						Thread.sleep(4 * sleepTime);
					}
					greenColumn++;
					repaint();
				}
				greenColumn++;
				redColumn = -1;
				blueColumn = -1;
			} catch (InterruptedException e) {
			}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
			int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
			for (int i = (greenColumn == -1 ? 0 : greenColumn); i < list.length; i++) {
				g.setColor(Color.WHITE);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
			}
			for (int i = 0; i <= greenColumn; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
			}
			if(redColumn != -1) {
				g.setColor(Color.RED);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			}
			if(blueColumn != -1) {
				g.setColor(Color.BLUE);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
			}

		}

	}

	class InsertionSortPanel extends SortPanel {
		private static final long serialVersionUID = 1L;
		private int redColumn = -1;
		private int blueColumn = -1;
		private int greenColumn = -1;
		
		public InsertionSortPanel(String name, int[] list, int sleepTime) {
			super(name, list, sleepTime);
		}

		@Override
		public void run() {
			try {
				for (int i = 1; i < list.length; i++) {
					greenColumn = i;
					Thread.sleep(3 * sleepTime);
					repaint();
					redColumn = greenColumn;
					blueColumn = -1;
					int k;
					for (k = i - 1; k >= 0 && list[k] > list[k + 1]; k--) {
						redColumn = k + 1;
						blueColumn = k;
						repaint();
						Thread.sleep(4 * sleepTime);
						int tmp = list[k + 1]; 
						list[k + 1] = list[k];
						list[k] = tmp;
					}
					redColumn = k + 1;
					blueColumn = k;
					repaint();
				}
				redColumn = -1;
				blueColumn = -1;
			} catch (InterruptedException e) {
			}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
			int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
			for (int i = (greenColumn == -1 ? 0 : greenColumn); i < list.length; i++) {
				g.setColor(Color.WHITE);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
			}
			for (int i = 0; i <= greenColumn; i++) {
				g.setColor(Color.GREEN);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
			}
			if(redColumn != -1) {
				g.setColor(Color.RED);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			}
			if(blueColumn != -1) {
				g.setColor(Color.BLUE);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
			}
		}

	}

	class BubbleSortPanel extends SortPanel {
		private static final long serialVersionUID = 1L;
		private int redColumn = -1;
		private int blueColumn = -1;
		private int greenColumn = -1;
		
		public BubbleSortPanel(String name, int[] list, int sleepTime) {
			super(name, list, sleepTime);
		}

		@Override
		public void run() {
			try {
				boolean needNextPass = true;
				for (int k = 1; k < list.length && needNextPass; k++) {
					needNextPass = false;
					for (int i = 0; i < list.length - k; i++) {
						redColumn = i;
						blueColumn = i + 1;
						repaint();
						Thread.sleep(3 * sleepTime);
						if (list[i] > list[i + 1]) {
							redColumn = i + 1;
							blueColumn = -1;
							int temp = list[i];
							list[i] = list[i + 1];
							list[i + 1] = temp;
							repaint();
							Thread.sleep(4 * sleepTime);
							needNextPass = true;
						}
					}
					greenColumn = size - k;
				}
				greenColumn = 0;
				redColumn = -1;
				blueColumn = -1;
			} catch (InterruptedException e) {
			}
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int columnWidth = (getWidth() - 4 * BORDER_WIDTH) / size;
			int columnHeight = (getHeight() - 4 * BORDER_WIDTH) / size;
			for (int i = 0; i < (greenColumn == -1 ? list.length : greenColumn); i++) {
				g.setColor(Color.WHITE);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
			}
			if(greenColumn != -1) {
				for (int i = greenColumn; i < list.length; i++) {
					g.setColor(Color.GREEN);
					g.fillRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);
					g.setColor(Color.BLACK);
					g.drawRect(2 * BORDER_WIDTH + columnWidth * i, getHeight() - list[i] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[i] * columnHeight);			
				}
			}
			if(redColumn != -1) {
				g.setColor(Color.RED);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * redColumn, getHeight() - list[redColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[redColumn] * columnHeight);
			}
			if(blueColumn != -1) {
				g.setColor(Color.BLUE);
				g.fillRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
				g.setColor(Color.BLACK);
				g.drawRect(2 * BORDER_WIDTH + columnWidth * blueColumn, getHeight() - list[blueColumn] * columnHeight - 2 * BORDER_WIDTH, columnWidth, list[blueColumn] * columnHeight);
			}
		}

	}


}