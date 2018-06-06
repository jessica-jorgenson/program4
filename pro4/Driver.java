package pro4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Jessica Jorgenson
 * CSCI 232
 * This program retrieves a matrix from a file, and displays the BFS and DFS of the matrix
 * */

public class Driver {

	public static void main(String[] args) {
		int[][] theMatrix = getMatrix();
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i < theMatrix[0].length; i++) {
			Node n = new Node(i);
			nodeList.add(n);
		}

		// Create the graph, add nodes, create edges between nodes
		Graph g = new Graph();
		for (Node node : nodeList) {
			g.addNode(node);
		}
		g.setRoot(nodeList.get(0));

		for (int i = 0; i < theMatrix[0].length; i++) {
			for (int j = 0; j < theMatrix[1].length; j++) {
				if (theMatrix[i][j] == 1) {
					g.connectNode(nodeList.get(i), nodeList.get(j));
				}
			}
		}

		// Perform the traversal of the graph
		System.out.println("DFS Traversal of a tree is:");
		g.DFS();

		System.out.println("\nBFS Traversal of a tree is:");
		g.BFS();

	}

	public static int[][] getMatrix() {
		int[][] matrix = null;
		int row = 0;
		boolean beginning = true;
		Scanner fileInput = getFile();
		while (fileInput.hasNextLine()) {
			String[] le = fileInput.nextLine().split("\\s+");
			if (beginning) {
				matrix = new int[le.length][le.length];
				beginning = false;
			}
			for (int i = 0; i < le.length; i++) {
				int num = Integer.parseInt(le[i]);
				matrix[row][i] = num;
			}
			row++;
		}
		return matrix;
	}

	public static Scanner getFile() {
		try {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter location of file: ");
			String input = scan.nextLine();
			System.out.println();
			Scanner leFile = new Scanner(new File(input));
			return leFile;

		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(0);
			return null;

		}

	}

}
