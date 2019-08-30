package cs21as06;

/**
 * CS21 Assignment 06 p6
 * filename: p6.java
 * main file for assignment 06
 *
 * @author Joseph Chung, materialstuff@live.com jjchung
 * @version 1.0
 */

public class p6 {
	public static void main(String[] args) {
		MazeGen3 maze;
		if (args.length > 0) {
			int input = Integer.valueOf(args[0]);
			if (input > 2) {
				maze = new MazeGen3(input);
				maze.mazeGen();
				for (int[] row : maze.getMaze()) {
					for (int elem : row) {
						if (elem > 9) {
							System.out.print((char)(elem + 87));
						} else {
							System.out.print(elem);
						}
					}
					System.out.println();
				}
				System.out.println();
			} else {
				System.out.println("Maze size must be at least 3!");
			}
		}
	}	
}
