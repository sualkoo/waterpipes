package sk.stuba.fei.uim.oop.board;

import sk.stuba.fei.uim.oop.tile.State;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Board extends JPanel{
    private Node[][] board;

    public Board(int dimension) {
        generatePath(dimension);
    }

    private void generateNeighbors(int dimension) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (i != 0)
                    this.board[i][j].addNeighbour(Direction.UP,this.board[i-1][j]);
                if (i != dimension-1)
                    this.board[i][j].addNeighbour(Direction.DOWN,this.board[i+1][j]);
                if (j != 0)
                    this.board[i][j].addNeighbour(Direction.LEFT,this.board[i][j-1]);
                if (j != dimension-1)
                    this.board[i][j].addNeighbour(Direction.RIGHT ,this.board[i][j+1]);
            }
        }
    }

    private void initializeBoard(int dimension) {
        this.board = new Node[dimension][dimension];
        this.setLayout(new GridLayout(dimension,dimension));
        this.setBackground(Color.GRAY);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.board[i][j] = new Node(j,i);
                this.add(this.board[i][j]);
            }
        }
        this.generateNeighbors(dimension);
    }

    private void generatePath(int dimension) {
        initializeBoard(dimension);

        HashSet<Node> visitedNodes = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        Random random = new Random();
        ArrayList<Node> unvisitedNeighbors;

        Node start = this.board[0][random.nextInt(dimension)];
        Node underStart = this.board[1][start.getPosX()];
        Node end = this.board[dimension-1][random.nextInt(dimension)];
        Node aboveEnd = this.board[dimension-2][end.getPosX()];

        visitedNodes.add(underStart);
        visitedNodes.add(start);
        visitedNodes.add(end);

        stack.push(underStart);
        start.setState(State.TAIL);
        end.setFinish(true);

        while (!stack.isEmpty()) {
            Node current = stack.peek();
            Node previous;

            unvisitedNeighbors = getUnvisitedNeighbors(current, visitedNodes, dimension);

            if (!unvisitedNeighbors.isEmpty()) {
                Node next = unvisitedNeighbors.get(random.nextInt(unvisitedNeighbors.size()));

                if (stack.size() > 1) {
                    previous = stack.get(stack.indexOf(current)-1);
                }
                else {
                    previous = start;
                }

                straightOrBent(current,next,previous);
                visitedNodes.add(next);
                stack.push(next);

                randomRotation(random,current);

                if (next.equals(aboveEnd)) {
                    straightOrBent(next,end,current);
                    visitedNodes.add(aboveEnd);
                    end.setState(State.TAIL);
                    break;
                }
            } else {
                stack.pop();
            }
        }
    }

    private void straightOrBent(Node current, Node next, Node previous) {
        int dx = Math.abs(previous.getPosX() - next.getPosX());
        int dy = Math.abs(previous.getPosY() - next.getPosY());

        if (dx == 1 && dy == 1) {
            current.setState(State.BENT);
        } else {
            current.setState(State.STRAIGHT);
        }
    }

    private ArrayList<Node> getUnvisitedNeighbors(Node cell, HashSet<Node> visited, int dimension) {
        ArrayList<Node> neighbors = new ArrayList<>();

        if (cell.getPosX() > 0) {
            Node left = this.board[cell.getPosY()][cell.getPosX() - 1];
            if (!visited.contains(left)) {
                neighbors.add(left);
            }
        }

        if (cell.getPosY() > 0) {
            Node up = this.board[cell.getPosY() - 1][cell.getPosX()];
            if (!visited.contains(up)) {
                neighbors.add(up);
            }
        }

        if (cell.getPosX() < dimension - 1) {
            Node right = this.board[cell.getPosY()][cell.getPosX() + 1];
            if (!visited.contains(right)) {
                neighbors.add(right);
            }
        }

        if (cell.getPosY() < dimension - 1) {
            Node down = this.board[cell.getPosY() + 1][cell.getPosX()];
            if (!visited.contains(down)) {
                neighbors.add(down);
            }
        }
        return neighbors;
    }

    private void randomRotation(Random random, Node node) {
        int rotation = random.nextInt(4);
        for (int i = 0; i < rotation; i++) {
            node.rotate();
        }
        this.repaint();
    }
}