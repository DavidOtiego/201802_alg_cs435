package com.mum.edu.cs435.lectures.lec12.stack_queue.bfs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JButton;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui extends JApplet {
	final static int SIZE = Board.SIZE;
	JButton[][] values = new JButton[SIZE][SIZE];
	JTextArea lowerText;
	public void init(){
		for(int i = 0; i < SIZE; ++i){
			for(int j= 0; j < SIZE; ++j){
				values[i][j]= new JButton("");
			}
		}
		buildGui();
	}
	
	private void buildGui() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JPanel upper = makeUpperPanel();
		JPanel middle = makeMiddlePanel();
		JPanel lower = makeLowerPanel();
		mainPanel.add(upper,BorderLayout.NORTH);
		mainPanel.add(middle,BorderLayout.CENTER);
		mainPanel.add(lower,BorderLayout.SOUTH);
		add(mainPanel);
	}
	
	private JPanel makeUpperPanel() {
		JPanel outer = new JPanel();
		outer.setLayout(new GridLayout(SIZE,SIZE));
		for(int i = 0; i < SIZE; ++i){
			for(int j = 0; j < SIZE; ++j){
				outer.add(values[i][j]);
			}
		}
		return outer;
		
		
		
	}
	private JPanel makeMiddlePanel() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton resetButn = new JButton("New Game");
		resetButn.addActionListener(new NewGameListener());
		JButton computeButn = new JButton("Compute Components");
		computeButn.addActionListener(new ComputeListener());
		p.add(resetButn);
		p.add(computeButn);
		return p;
		
	}
	private JPanel makeLowerPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		lowerText = new JTextArea(8,54);
		JScrollPane scroll = new JScrollPane(lowerText);
		panel.add(scroll);
		return panel;
		
	}
	void clearCells() {
		for(int i = 0; i < SIZE; ++i){
			for(int j = 0; j < SIZE; ++j){
				values[i][j].setText("");
			}
		}
	}
	class ComputeListener implements ActionListener {
		public void actionPerformed(ActionEvent evt){
			//implement
		}
	}
	class NewGameListener implements ActionListener {
		
		public void actionPerformed(ActionEvent evt){
			clearCells();
			Board board = new Board();
			List<Cell> cells = board.newBoard();
			Position pos = null;
			String val = null;
			for(Cell cell : cells){
				pos = cell.position;
				val = cell.value;
				values[pos.row][pos.col].setText(val);
			}
			repaint();
			
		}
	}
	public static void main(String[] args){
		//(new Gui()).init();
	}
	
	private static final long serialVersionUID = 3978993180127801395L;

}
