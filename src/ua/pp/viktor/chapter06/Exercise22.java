package ua.pp.viktor.chapter06;

public class Exercise22 {

	public static void main(String[] args) {
		int chessboardSize = 8;
		int[] chessboard = new int[chessboardSize];
		for (int i = 0; i < 077777777; i++) {
			int tmp = i;
			for (int j = chessboardSize - 1; j >= 0; j--, tmp /= chessboardSize)
				chessboard[j] = tmp % chessboardSize;
			if (!isHorizonalFine(chessboard))
				continue;			
			if (!isDiagonal1Fine(chessboard))
				continue;			
			printChessboard(chessboard);
			break;
		}
	}
	
	public static boolean isDiagonal1Fine(int[] chessboard) {
		for (int i = 0; i < chessboard.length; i++) {
			for (int j = i + 1; j < chessboard.length; j++) {
				if (chessboard[j] - chessboard[i] == j - i) 
					return false;
				if (chessboard[i] - chessboard[j] == j - i) 
					return false;
			}
		}
		return true;	
	}
	
	public static void printChessboard(int[] chessboard) {
		for (int i = 0; i < chessboard.length; i++) {
			for (int j = 0; j < chessboard.length; j++)
				System.out.print(chessboard[i] == j ? "|Q" : "| ");
			System.out.println("|");
		}
		System.out.println();
	}
	
	public static boolean isHorizonalFine(int[] chessboard) {
		boolean[] numbers = new boolean[chessboard.length];
		for (int i = 0; i < chessboard.length; i++)
			numbers[chessboard[i]] = true;
		for (int i = 0; i < numbers.length; i++)
			if (!numbers[i])
				return false;
		return true;	
	}

}
