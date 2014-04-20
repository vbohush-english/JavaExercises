package net.bohush.exercises.chapter32;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;


public class Exercise17 {

	public static void main(String[] args) {
		int size = 2000;
		double[][] a = new double[size][size];
		double[][] b = new double[size][size];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				a[i][j] = Math.random();
				b[i][j] = Math.random();
			}
		}
		long time = System.currentTimeMillis();
		parallelMultiplyMatrix(a, b);
		System.out.println((System.currentTimeMillis() - time)	+ " msec - parallelMultiplyMatrix()");
		time = System.currentTimeMillis();
		multiplyMatrix(a, b);
		System.out.println((System.currentTimeMillis() - time) + " msec - addMatrix()");
	}

	public static double[][] parallelMultiplyMatrix(double[][] a, double[][] b) {
		double[][] c = new double[a.length][a.length];

		RecursiveAction mainTask = new MultiplyMatrix(a, b, c, 0, a.length, 0, a.length);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mainTask);
		return c;
	}
	
	private static class MultiplyMatrix extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		private final static int THRESHOLD = 100;
		private double[][] a;
		private double[][] b;
		private double[][] c;
		private int x1;
		private int x2;
		private int y1;		
		private int y2;

		public MultiplyMatrix(double[][] a, double[][] b, double[][] c, int x1, int x2, int y1, int y2) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;			
			this.y2 = y2;			
		}
		
		@Override
		protected void compute() {
			if (((x2 - x1) < THRESHOLD) || ((y2 - y1) < THRESHOLD)) {
				for (int i = x1; i < x2; i++) {
					for (int j = y1; j < y2; j++) {
						c[i][j] = a[i][0] * b[0][j];
						for (int k = 1; k < a.length; k++) {
							c[i][j] += a[i][k] * b[k][j];	
						}	
					}
				}
			} else {
				int midX = (x1 + x2) / 2;
				int midY = (y1 + y2) / 2;
				
				invokeAll(
						new MultiplyMatrix(a, b, c, x1, midX, y1, midY),
						new MultiplyMatrix(a, b, c, midX, x2, y1, midY),
						new MultiplyMatrix(a, b, c, x1, midX, midY, y2),
						new MultiplyMatrix(a, b, c, midX, x2, midY, y2));
			}
		}
	}
	
	public static double[][] multiplyMatrix(double[][] a, double[][] b) {
		double[][] result = new double[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				result[i][j] = a[i][0] * b[0][j];
				for (int k = 1; k < a.length; k++) {
					result[i][j] += a[i][k] * b[k][j];	
				}				
			}
		}
		return result;
	}
}
