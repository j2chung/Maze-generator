package cs21as06;

import java.util.Arrays;
import java.util.Random;

public class MazeGen2 {
	private DisjointSet dj;
	private int initial;
	private int[][] maze;
	boolean[] checked;
	
	
	public MazeGen2(int n) {
		maze = new int[n][n];
		dj = new DisjointSet(n * n);
		for (int[] row: maze)
		    Arrays.fill(row, 15);
		maze[0][0] = 11;
		maze[n - 1][n - 1] = 14;
		initial = n;
		checked = new boolean[n * n] ;
		Arrays.fill(checked, false);
	}
	
	public void mazeGen() {
		Random rand = new Random();
		int randomRow;
		int randomCol;
		int position;
		int choice;	
		while (dj.size > 1) {
			randomRow = rand.nextInt(initial);
			randomCol = rand.nextInt(initial);
			position = checkPos(randomRow, randomCol);
			switch (position) {	
			case 0: // top left corner
				choice = rand.nextInt(2);				
				if (!inDS(randomRow, randomCol, randomRow + 1, randomCol) && 
						!inDS(randomRow, randomCol, randomRow, randomCol + 1)) { // if both right and bottom are valid
					if (choice == 0) { // break bottom
						maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
						maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
					} else if (choice == 1) { // break right
						maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
						maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
					}
				} else if (!inDS(randomRow, randomCol, randomRow + 1, randomCol)) { //only bottom is valid
					maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
					maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
				} else if (!inDS(randomRow, randomCol, randomRow, randomCol + 1)) { //only right is valid
					maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
					maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
				}
				break;
								
			case 1: // top
				choice = rand.nextInt(3);
				if (!inDS(randomRow, randomCol, randomRow + 1, randomCol) &&
						!inDS(randomRow, randomCol, randomRow, randomCol - 1) &&
						!inDS(randomRow, randomCol, randomRow, randomCol + 1)) {
					if (choice == 0) { // break bottom
						maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
						maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
					} else if (choice == 1) { // break left
						maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
						maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
					} else if (choice == 2) { // break right
						maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
						maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
					}
				} else if (!inDS(randomRow, randomCol, randomRow + 1, randomCol)) { //bottom is valid
					maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
					maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
				} else if(!inDS(randomRow, randomCol, randomRow, randomCol - 1)) { //left is valid
					maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
					maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
				} else if (!inDS(randomRow, randomCol, randomRow, randomCol + 1)) { // right is valid
					maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
					maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
				}		
				break;
			
			case 2: 
				choice = rand.nextInt(2);				
				if (!inDS(randomRow, randomCol, randomRow + 1, randomCol) && 
						!inDS(randomRow, randomCol, randomRow, randomCol - 1)) { // if both left and bottom are valid
					if (choice == 0) { // break bottom
						maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
						maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
					} else if (choice == 1) { // break left
						maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
						maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
					}
				} else if (!inDS(randomRow, randomCol, randomRow + 1, randomCol)) { //only bottom is valid
					maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
					maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
				} else if (!inDS(randomRow, randomCol, randomRow, randomCol - 1)) { //only left is valid
					maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
					maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
				}
				break;
				
			case 3:
				choice = rand.nextInt(3);
				if (!inDS(randomRow, randomCol, randomRow + 1, randomCol) &&
						!inDS(randomRow, randomCol, randomRow, randomCol - 1) &&
						!inDS(randomRow, randomCol, randomRow - 1, randomCol)) {
					if (choice == 0) { // break bottom
						maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
						maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
					} else if (choice == 1) { // break left
						maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
						maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
					} else if (choice == 2) { // break top
						maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
						maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
					}
				} else if (!inDS(randomRow, randomCol, randomRow + 1, randomCol)) { //bottom is valid
					maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
					maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
				} else if(!inDS(randomRow, randomCol, randomRow, randomCol - 1)) { //left is valid
					maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
					maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
				} else if (!inDS(randomRow, randomCol, randomRow - 1, randomCol)) { // top is valid
					maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
					maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
				}		
				break;
				
			case 4:
				choice = rand.nextInt(2);				
				if (!inDS(randomRow, randomCol, randomRow - 1, randomCol) && 
						!inDS(randomRow, randomCol, randomRow, randomCol - 1)) { // if both left and top are valid
					if (choice == 0) { // break top
						maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
						maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
					} else if (choice == 1) { // break left
						maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
						maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
					}
				} else if (!inDS(randomRow, randomCol, randomRow - 1, randomCol)) { //only top is valid
					maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
					maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
				} else if (!inDS(randomRow, randomCol, randomRow, randomCol - 1)) { //only left is valid
					maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
					maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
				}
				break;
				
			case 5:
				choice = rand.nextInt(3);
				if (!inDS(randomRow, randomCol, randomRow - 1, randomCol) &&
						!inDS(randomRow, randomCol, randomRow, randomCol - 1) &&
						!inDS(randomRow, randomCol, randomRow, randomCol + 1)) {
					if (choice == 0) { // break top
						maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
						maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
					} else if (choice == 1) { // break left
						maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
						maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
					} else if (choice == 2) { // break right
						maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
						maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
					}
				} else if (!inDS(randomRow, randomCol, randomRow - 1, randomCol)) { //top is valid
					maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
					maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
				} else if(!inDS(randomRow, randomCol, randomRow, randomCol - 1)) { //left is valid
					maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
					maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
				} else if (!inDS(randomRow, randomCol, randomRow, randomCol + 1)) { // right is valid
					maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
					maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
				}		
				break;
			
			case 6:
				choice = rand.nextInt(2);				
				if (!inDS(randomRow, randomCol, randomRow - 1, randomCol) && 
						!inDS(randomRow, randomCol, randomRow, randomCol + 1)) { // if both right and top are valid
					if (choice == 0) { // break top
						maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
						maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
					} else if (choice == 1) { // break right
						maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
						maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
					}
				} else if (!inDS(randomRow, randomCol, randomRow - 1, randomCol)) { //only top is valid
					maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
					maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
				} else if (!inDS(randomRow, randomCol, randomRow, randomCol + 1)) { //only right is valid
					maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
					maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
				}
				break;
				
			case 7:
				choice = rand.nextInt(3);
				if (!inDS(randomRow, randomCol, randomRow + 1, randomCol) &&
						!inDS(randomRow, randomCol, randomRow, randomCol + 1) &&
						!inDS(randomRow, randomCol, randomRow - 1, randomCol)) {
					if (choice == 0) { // break bottom
						maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
						maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
					} else if (choice == 1) { // break right
						maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
						maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
					} else if (choice == 2) { // break top
						maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
						maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
						dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
					}
				} else if (!inDS(randomRow, randomCol, randomRow + 1, randomCol)) { //bottom is valid
					maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
					maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
				} else if(!inDS(randomRow, randomCol, randomRow, randomCol + 1)) { //right is valid
					maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
					maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
				} else if (!inDS(randomRow, randomCol, randomRow - 1, randomCol)) { // top is valid
					maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
					maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
				}		
				break;
				
			case 8:
				if (!inDS(randomRow, randomCol, randomRow, randomCol - 1)) { //left is valid
					maze[randomRow][randomCol] = breakLeft(maze[randomRow][randomCol]);
					maze[randomRow][randomCol - 1] = breakRight(maze[randomRow][randomCol - 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol - 1));
				} else if (!inDS(randomRow, randomCol, randomRow - 1, randomCol)) { //top is valid
					maze[randomRow][randomCol] = breakTop(maze[randomRow][randomCol]);
					maze[randomRow - 1][randomCol] = breakBottom(maze[randomRow - 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow - 1, randomCol));
				} else if(!inDS(randomRow, randomCol, randomRow, randomCol + 1)) { //right is valid
					maze[randomRow][randomCol] = breakRight(maze[randomRow][randomCol]);
					maze[randomRow][randomCol + 1] = breakLeft(maze[randomRow][randomCol + 1]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow, randomCol + 1));
				} else if (!inDS(randomRow, randomCol, randomRow + 1, randomCol)) { // bottom is valid
					maze[randomRow][randomCol] = breakBottom(maze[randomRow][randomCol]);
					maze[randomRow + 1][randomCol] = breakTop(maze[randomRow + 1][randomCol]);
					dj.union(toIndex(randomRow, randomCol), toIndex(randomRow + 1, randomCol));
				}
			}
		}
	}
	
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
	 * @param row 
	 * @param col
	 * @return 
	 */
	
	private int checkPos(int row, int col) {
		if (row == 0 && col == 0) {
			return 0;
		} else if (row == 0 && col != 0 && col != initial - 1) {
			return 1;
		} else if (row == 0 && col == initial - 1) {
			return 2;
		} else if (row != initial - 1 && row != 0 && col == initial - 1) {
			return 3;
		} else if (row == initial - 1 && col == initial - 1) {
			return 4;
		} else if (row == initial - 1 && col != initial - 1 && col != 0) {
			return 5;
		} else if (row == initial - 1 && col == 0) {
			return 6;
		} else if (row != 0 && row != initial - 1 && col == 0) {
			return 7;
		} else return 8;
	}
	
	public int[][] getMaze() {
		return maze;
	}
	
	private int toIndex(int row, int col) {
		return (initial * row) + col;
	}
	
}
