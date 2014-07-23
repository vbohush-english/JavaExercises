package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise20 {

	public static void main(String[] args) {
		int[][] board = new int[6][7];
		int tmpNumber = 1000;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = tmpNumber++;
			}
		}
		boolean finish = false;
		int activePlayer = 1;
		printBoard(board);
		while (!finish) {
			while(!putDisk(board, activePlayer, getDisk(board, activePlayer))) {};
			printBoard(board);
			if (isConsecutiveFour(board)) {
				System.out.println("The " + (activePlayer == 1 ? "red" : "yellow") + " player won");
				finish = true;
			} else {
				activePlayer = (activePlayer == 1 ? 2: 1);					
				if (isDraw(board)) {
					System.out.println("It is a draw");
					finish = true;
				}
			}
		}
	}

	public static boolean isDraw(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] > 2) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean putDisk(int[][] board, int player, int column) {
		if (board[0][column] <= 2) {
			return false;
		}
		for (int i = 0; i < board.length - 1; i++) {
			if (board[i + 1][column] <= 2) {
				board[i][column] = player;
				return true;
			};
		}
		board[board.length - 1][column] = player;
		return true;
	}
	
	
	public static int getDisk(int[][] board, int player) {
		System.out.print("Drop a " + (player == 1 ? "red" : "yellow") + " disk at column (0-6): ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}
	
	public static void printBoard(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			System.out.print("|");
			for (int j = 0; j < board[i].length; j++) {
				char place;
				if (board[i][j] == 1) {
					place = 'R';		
				} else if (board[i][j] == 2) {
					place = 'Y';		
				} else {
					place = ' ';
				}
				System.out.print(place + "|");	
			}			
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean isConsecutiveFour(int[][] values) {
		return isRowConsecutiveFour(values)||isColumnConsecutiveFour(values)||isDiagonal1ConsecutiveFour(values)||isDiagonal2ConsecutiveFour(values);
	}
	
	
	public static boolean isDiagonal2ConsecutiveFour(int[][] values) {
		for (int i = 0; i < values[0].length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j <= i; j++) {
				if ((i - j) >= values[0].length) {
					break;
				}
				if ((values.length - j - 1) < 0) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[values.length - j - 1][i - j];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[values.length - j - 1][i - j]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[values.length - j - 1][i - j];
					consecutiveCount = 1;
				}
			}
		}
		
		for (int i = 1; i < values.length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j < values.length - i; j++) {
				if ((values[0].length - j - 1) < 0) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[values.length - j - i - 1][values[0].length - j - 1];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[values.length - j - i - 1][values[0].length - j - 1]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[values.length - j - i - 1][values[0].length - j - 1];
					consecutiveCount = 1;
				}
			}
		}
		return false;
	}
	
	
	
	public static boolean isDiagonal1ConsecutiveFour(int[][] values) {
		for (int i = 0; i < values.length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j <= i; j++) {
				if (j >= values[0].length) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[i - j][j];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[i - j][j]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[i - j][j];
					consecutiveCount = 1;
				}
			}
		}
		for (int i = 1; i < values[0].length; i++) {
			int consecutiveCount = 0;
			int consecutiveNumber = 0;
			for (int j = 0; j < values.length; j++) {
				if ((i + j) >= values[0].length) {
					break;
				}
				if (consecutiveCount == 0) {
					consecutiveNumber = values[values.length - j - 1][i + j];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[values.length - j - 1][i + j]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[values.length - j - 1][i + j];
					consecutiveCount = 1;
				}
			}
		}
		return false;
	}


	
	public static boolean isRowConsecutiveFour(int[][] values) {
		int consecutiveCount = 0;
		int consecutiveNumber = 0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (consecutiveCount == 0) {
					consecutiveNumber = values[i][j];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[i][j]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[i][j];
					consecutiveCount = 1;
				}				
			}
			consecutiveCount = 0;
		}
		return false;
	}
	
	
	public static boolean isColumnConsecutiveFour(int[][] values) {
		int consecutiveCount = 0;
		int consecutiveNumber = 0;
		for (int i = 0; i < values[0].length; i++) {
			for (int j = 0; j < values.length; j++) {
				if (consecutiveCount == 0) {
					consecutiveNumber = values[j][i];
					consecutiveCount++;					
				} else if (consecutiveNumber == values[j][i]) {
					consecutiveCount++;
					if (consecutiveCount == 4) {
						return true;
					}
				} else {
					consecutiveNumber = values[j][i];
					consecutiveCount = 1;
				}				
			}
			consecutiveCount = 0;
		}
		return false;
	}
	
	
}
