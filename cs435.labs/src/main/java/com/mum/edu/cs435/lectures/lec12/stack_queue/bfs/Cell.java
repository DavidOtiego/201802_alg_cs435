package com.mum.edu.cs435.lectures.lec12.stack_queue.bfs;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	static final int SIZE = Board.SIZE;
	String value;
	Position position;
	boolean visited = false;
	
	Cell(String value, Position position){
		this.value = value;
		this.position = position;
	}
	
	List<Position> adjacentPositions() {
		List<Position> list = new ArrayList<Position>();
		int row = position.row;
		int col = position.col;
		if(row > 0){
			list.add(new Position(row-1,col));
		}
		if(col>0){
			list.add(new Position(row,col-1));
		}
		if(row < SIZE-1){
			list.add(new Position(row+1,col));
		}
		if(col < SIZE-1){
			list.add(new Position(row,col+1));
		}
		return list;
	}

}
