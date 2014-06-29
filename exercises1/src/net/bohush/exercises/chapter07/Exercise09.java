package net.bohush.exercises.chapter07;

import java.util.Scanner;

public class Exercise09 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
		
		boolean isFinish = false;
		char player = 'X';
		while (!isFinish) {
			printBoard(board);
			setPlayer(input, player, board);
			if (isDraw(board)) {
				isFinish = true;
				printBoard(board);
				System.out.println("It is a draw");
			} else if (isPlayerWin(player, board)) {
				isFinish = true;
				printBoard(board);
				System.out.println(player + " player won");
			} else {
				player = (player == 'X' ? '0' : 'X');
			}
		}
		
		input.close();
		
	}
	
	public static boolean isDraw(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == ' ') {
					return false;					
				}
			}
		}
		
		return true;
	}
	
	public static boolean isPlayerWin(char player, char[][] board) {
		for (int i = 0; i < board.length; i++) {
			if ((board[i][0] == player) && (board[i][1] == player) && (board[i][2] == player)) {
				return true;
			}
			if ((board[0][i] == player) && (board[1][i] == player) && (board[2][i] == player)) {
				return true;
			}
		}
		if ((board[0][0] == player) && (board[1][1] == player) && (board[2][2] == player)) {
			return true;
		}
		if ((board[2][0] == player) && (board[1][1] == player) && (board[0][2] == player)) {
			return true;
		}		
		return false;
	}
	
	public static void setPlayer(Scanner input, char player, char[][] board) {
		System.out.print("Enter a row (0, 1, or 2) for player " + player + ": ");
		int row = input.nextInt();
		System.out.print("Enter a column (0, 1, or 2) for player " + player + ": ");
		int column = input.nextInt();
		board[row][column] = player;			
	}
	
	public static void printBoard(char[][] board) {
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("|");
			for (int j = 0; j < board[i].length; j++ ) {
				System.out.print(" " + board[i][j] + " |");
			}
			System.out.println("\n-------------");
		}	
	}

}
