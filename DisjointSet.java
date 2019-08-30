package cs21as06;

import java.util.Arrays;

/**
 * CS21 Assignment 06 p6
 * filename: DisjointSet.java
 * Disjoint Set for assignment 06
 *
 * @author Joseph Chung, materialstuff@live.com jjchung
 * @version 1.0
 */

public class DisjointSet {
	
	private int[] parent;
	private int[] rank;
	public int size;
	
	public DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];
		size = n;
		makeSet(n);
	}
	
	public void makeSet(int x) {
		for(int i = 0; i < x; i++) {
			parent[i] = i;
		}
		Arrays.fill(rank, 0);
		
	}
	
	public void union(int x, int y) {
		if (x < parent.length && y < parent.length && x >= 0 && y >= 0 ) {
			link(find(x), find(y));
		}
	}
	
	public int find(int x) {
		if (x < parent.length && x >= 0) {
			if (x != parent[x]) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}
		return x;
	}
	
	private void link(int x, int y) {
		if (x == y) return;
		if (rank[x] > rank[y]) {
			parent[y] = x;
			size--;
		} else {
			parent[x] = y;
			if (rank [x] == rank[y]) {
				rank[y]++;
			}
			size--;
		}
	}

}
