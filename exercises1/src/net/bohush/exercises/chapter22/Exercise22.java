package net.bohush.exercises.chapter22;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise22 {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.print("Enter number of disks: ");
		int n = input.nextInt();
		System.out.println("The moves are:");
		System.out.println("Number of moves: " + moveDisks(n, 'A', 'B', 'C'));
	}
	
	public static int moveDisks(int n, char fromTower, char toTower, char auxTower) {
		ArrayList<OneMove> moves = new ArrayList<>();
		
		OneMove oneMove = new OneMove(n, fromTower, toTower, auxTower, false);
		moves.add(oneMove);
		
		for (int i = 1; i < n; i++) {
			ArrayList<OneMove> tmpMoves = new ArrayList<>();
			for (int j = 0; j < moves.size(); j++) {		
				if( moves.get(j).isText) {
					tmpMoves.add(moves.get(j));
				} else {
					tmpMoves.add(new OneMove(n - i, moves.get(j).fromTower, moves.get(j).auxTower, moves.get(j).toTower, false));
					tmpMoves.add(new OneMove(n - i + 1, moves.get(j).fromTower, moves.get(j).toTower, moves.get(j).auxTower, true));
					tmpMoves.add(new OneMove(n - i, moves.get(j).auxTower, moves.get(j).toTower, moves.get(j).fromTower, false));
				}
			}
			moves = tmpMoves;
		}
		for (OneMove StringMove : moves) {
			System.out.println(StringMove);
		}
		return  moves.size();
	}
}


class OneMove {
	public char fromTower;
	public char toTower;
	public char auxTower;
	public int n;
	public boolean isText;
	
	public OneMove(int n, char fromTower, char toTower, char auxTower, boolean isText) {
		super();
		this.fromTower = fromTower;
		this.toTower = toTower;
		this.auxTower = auxTower;
		this.n = n;
		this.isText = isText;
	}
	
	@Override
	public String toString() {
		return "Move disk " + n + " from " + fromTower + " to "	+ toTower;
	}
	
}