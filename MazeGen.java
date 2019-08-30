package cs21as06;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class MazeGen {
	private int[] right = {1, 3, 5, 7, 9, 11, 13, 15};
	private int[] left = {4, 5, 7, 9, 11, 13, 15};
	private int[] top = {8, 9, 10, 11, 12, 13, 14, 15};
	private int[] bottom = {2, 3, 6, 7, 10, 11, 14, 15};
	
	private DisjointSet dj;
	private int[][] maze;
	private int initial;
	
	public MazeGen(int n) {
		maze = new int[n][n];
		dj = new DisjointSet(n * n);
		Arrays.fill(maze, 15);
		maze[0][0] = 11;
		maze[n][n] = 14;
		initial = n;
	}
	
	public void generateMaze() {
		int select;
		Random rand = new Random();
		int first = rand.nextInt(initial);
		int second = rand.nextInt(initial);
		for(int i = second; i < maze.length; i++) {
			if (find(first))
				if (!isEdg(first, second)) {
				
				}
		}

	}
	
	private boolean isEdg(int x, int y) {
		if (x == 0 || y == 0 || x == initial - 1 || y == inital - 1) {
			return true;
		}
		return false;
	}
	
	private int toDJ(int x, int y) {
		return x * initial + y;
	}

	
	private boolean isAdjacent(int x, int y) { // x has to be origin and nxn matrix
		if (x + 1 == y || x - 1 == y || x + initial == y || x - initial == y) {
			return true;
		}
		return false;
	}
	
	
	private int whichCorner(int n) {
		if (n == 0) {
			return 1; // topleft
		} else if (n == initial - 1) {
			return 2; // top right
		} else if (n == initial * (initial - 1)) {
			return 3; // bottom left
		} else if (n == (initial * initial) - 1) {
			return 4; // bottom right
		}
		return 0; // not a corner
	}

	private int whichEdge(int n) {
		if (n < initial) {
			return 1; //top
		} else if (n % initial == 0) {
			return 2; //left
		} else if ((n + 1) % initial == 0) {
			return 3; // right
		} else if (n >= initial * (initial - 1)) {
			return 4; // bottom
		}
		return 0; //free 
	}

}

