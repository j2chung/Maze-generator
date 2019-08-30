package cs21as06;

import java.util.Arrays;
import java.util.Random;
import java.util.ArrayList;

/**
 * CS21 Assignment 06 p6
 * filename: MazeGen3.java
 * Maze Generation for assignment 06
 *
 * @author Joseph Chung, materialstuff@live.com jjchung
 * @version 1.0
 */

public class MazeGen3 {
	private DisjointSet dj;
	private int initial;
	private int[][] maze;
	
    /**
     * Constructor for the maze with size n x n
     *
     * @param n size of side of maze
     * @version 1.0
     */
	
	public MazeGen3(int n) {
		maze = new int[n][n];
		dj = new DisjointSet(n * n);
		for (int[] row: maze)
		    Arrays.fill(row, 15);
		maze[0][0] = 11;
		maze[n - 1][n - 1] = 14;
		initial = n;
	}
	
    /**
     * Generates the maze
     *
     * @version 1.0
     */
	
	public void mazeGen() {
		Random rand = new Random();
		int randomRow;
		int randomCol;
		int choice;	
		int select;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		while (dj.size > 1) {
			randomRow = rand.nextInt(initial);
			randomCol = rand.nextInt(initial);
			for (int i = 1; i < 5; i++) arr.add(i);
			while(arr.size() > 0) {
				choice = rand.nextInt(arr.size());
				select = arr.get(choice);
				switch(select) {
				case 1:
					if (checkPos(randomRow, randomCol) != 0 &&
						checkPos(randomRow, randomCol) != 1 &&
						checkPos(randomRow, randomCol) != 2 &&
						!inDS(randomRow, randomCol, randomRow - 1, randomCol)) {
						maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
						maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
						arr.clear();
					}
					break;
				case 2:
					if (checkPos(randomRow, randomCol) != 2 && 
						checkPos(randomRow, randomCol) != 3 && 
						checkPos(randomRow, randomCol) != 4 && 
						!inDS(randomRow, randomCol, randomRow, randomCol + 1)) {
						maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
						maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
						arr.clear();
					}
					break;
				case 3:
					if (checkPos(randomRow, randomCol) != 4 && 
						checkPos(randomRow, randomCol) != 5 && 
						checkPos(randomRow, randomCol) != 6 && 
						!inDS(randomRow, randomCol, randomRow + 1, randomCol)) {
						maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
						maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
						arr.clear();
					}
					break;
				case 4:
					if (checkPos(randomRow, randomCol) != 6 &&
						checkPos(randomRow, randomCol) != 7 &&
						checkPos(randomRow, randomCol) != 0 &&
						!inDS(randomRow, randomCol, randomRow, randomCol - 1)) {
						maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
						maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
						arr.clear();
					}
					break;
				}
				if (arr.size() > 0) arr.remove(choice);
			}
		}
	}
	
    /**
     * Checks if coordinates (x1,y1) and (x1,y1) are in the same disjointed set
     *
     * @param x1 first x coordinate
     * @param y1 first y coordinate
     * @param x2 second x coordinate
     * @param y2 second y coordinate
     * @version 1.0
     */
	
	private boolean inDS(int x1, int y1, int x2, int y2) {
		if (dj.find(toIndex(x1, y1)) == dj.find(toIndex(x2,y2))) {
			return true;
		}
		return false;	
	}
	
	private int breakTop(int i) {
		return i - 8;
	}
	
	private int breakRight(int i) {
		return i - 1;
	}
	
	private int breakBottom(int i) {
		return  i - 2;
	}
	
	private int breakLeft(int i) {
		return i - 4;
	}
		
	/** checks which position the square is in
	 * return value meaning:
	 * 0 = top left corner
	 * 1 = top
	 * 2 = top right corner
	 * 3 = right
	 * 4 = bottom right corner
	 * 5 = bottom
	 * 6 = bottom left corner
	 * 7 = left
	 * 8 = free
	 * 
	 * @param int row x
	 * @param int col y
	 * @return int position
	 */

	private int checkPos(int row, int col) {
		if (row == 0 && col == 0) return 0;
		if (row == 0 && col != 0 && col != initial - 1) return 1;
		if (row == 0 && col == initial - 1) return 2;
		if (row != initial - 1 && row != 0 && col == initial - 1) return 3;
		if (row == initial - 1 && col == initial - 1) return 4;
		if (row == initial - 1 && col != initial - 1 && col != 0) return 5;
		if (row == initial - 1 && col == 0) return 6;
		if (row != 0 && row != initial - 1 && col == 0) return 7;
		return 8;
	}
	
    /**
     * The index of the 2d array into the index of 1d array
     *
     * @param int row x
     * @param int col y
     * @version 1.0
     */
	
	private int toIndex(int row, int col) {
		return (initial * row) + col;
	}
	
	public int[][] getMaze() {
		return maze;
	}
	
}
