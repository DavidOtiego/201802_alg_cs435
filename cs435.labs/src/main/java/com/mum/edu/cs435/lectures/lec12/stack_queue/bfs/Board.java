package com.mum.edu.cs435.lectures.lec12.stack_queue.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
	final static int SIZE = 15;
	final static int ALPHABET_SIZE = 26;
	final static int NUM_LABELS = ALPHABET_SIZE*ALPHABET_SIZE;
	static String[] labels = new String[NUM_LABELS];
	static {
		for(int i = 0; i < ALPHABET_SIZE; ++i){
			for(int j= 0; j < ALPHABET_SIZE; ++j){
				labels[(26*i + j)] =  (char)(((int)'A')+i) + "" + (char)(((int)'A')+j);
			}
		}
		//System.out.println(Arrays.toString(labels));
	}
	/** randomly generate cells as follows
	 *  pick a random number r between SIZE^2/4 and SIZE^2/2
	 *  for each i < r, pick a label at random and pick a position at random,
	 *  without duplication of either
	 * @return
	 */
	List<Cell> newBoard(){
		List<Cell> theBoard = new ArrayList<Cell>();
		int r = RandomNumbers.getRandomInt(SIZE*SIZE/4,SIZE*SIZE/2);
		HashMap<Integer,Integer> usedLabels = new HashMap<Integer,Integer>();
		HashMap<Position,Position> usedPositions = new HashMap<Position,Position>();
		String nextLabel = null;
		Position nextPosition = null;
		Cell nextCell = null;
		for(int i = 0; i<r; ++i){
			//get a label index
			int randLabelIndex = -1;
			do {
				randLabelIndex = RandomNumbers.getRandomInt(0,NUM_LABELS-1);
			}
			while(usedLabels.containsKey(new Integer(randLabelIndex)));
			usedLabels.put(randLabelIndex,randLabelIndex);
		
			//get a free position
			Position nextpos = null;
			do {
				nextpos = 
					new Position(RandomNumbers.getRandomInt(0,SIZE-1),
							RandomNumbers.getRandomInt(0,SIZE-1));
			}
			while(usedPositions.containsKey(nextpos));
			usedPositions.put(nextpos,nextpos);
			nextCell = new Cell(labels[randLabelIndex], nextpos);
			theBoard.add(nextCell);
		}
		return theBoard;
		
	}
	
	public static void main(String[] args){}
}
