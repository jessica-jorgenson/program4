package pro4;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	public BufferedWriter out = null;
	public Node root;
	public ArrayList<Node> nodes = new ArrayList<Node>();
	public int[][] matrix;// Edges will be represented as adjacency Matrix
	int size;

	public Graph() throws IOException {
		try {
			out = new BufferedWriter(new FileWriter("output.txt"));
		} catch (FileNotFoundException ex) {
			System.out.println("ERROR");
		}
	}

	public void setRoot(Node n) {
		this.root = n;
	}

	public Node getRoot() {
		return this.root;
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	// This method will be called to make connect two nodes
	public void connectNode(Node start, Node end) {
		if (matrix == null) {
			size = nodes.size();
			matrix = new int[size][size];
		}

		int startIndex = nodes.indexOf(start);
		int endIndex = nodes.indexOf(end);
		matrix[startIndex][endIndex] = 1;
		matrix[endIndex][startIndex] = 1;
	}

	private Node getUnvisitedChildNode(Node n) {

		int index = nodes.indexOf(n);
		int j = 0;
		while (j < size) {
			if (matrix[index][j] == 1 && ((Node) nodes.get(j)).visited == false) {
				return (Node) nodes.get(j);
			}
			j++;
		}
		return null;
	}

	// BFS traversal of a tree
	public void BFS() throws IOException {

		// BFS uses Queue data structure
		Queue<Node> q = new LinkedList<Node>();
		q.add(this.root);
		out.write("BFS: " + root.data + " ");
		root.visited = true;
		while (!q.isEmpty()) {
			Node n = (Node) q.remove();
			Node child = null;
			while ((child = getUnvisitedChildNode(n)) != null) {
				child.visited = true;
				out.write(n.data + " ");
				q.add(child);
			}
		}
		// Clear visited property of nodes for other method
		out.newLine();
		clearNodes();
	}

	// DFS traversal of a tree
	public void DFS() throws IOException {
		// DFS uses Stack data structure
		Stack<Node> s = new Stack<Node>();
		s.push(this.root);
		root.visited = true;
		out.write("DFS: " + root.data + " ");
		while (!s.isEmpty()) {
			Node n = (Node) s.peek();
			Node child = getUnvisitedChildNode(n);
			if (child != null) {
				child.visited = true;
				out.write(n.data + " ");
				s.push(child);
			} else {
				s.pop();
			}
		}
		// Clear visited property of nodes
		out.newLine();
		clearNodes();
	}

	// Utility methods for clearing visited property of node
	private void clearNodes() {
		int i = 0;
		while (i < size) {
			Node n = (Node) nodes.get(i);
			n.visited = false;
			i++;
		}
	}

}
