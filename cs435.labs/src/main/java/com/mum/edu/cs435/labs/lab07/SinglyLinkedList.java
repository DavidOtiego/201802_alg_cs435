package com.mum.edu.cs435.labs.lab07;

public class SinglyLinkedList {
		private Node head;  //zeroth element
		SinglyLinkedList(){
		}
		
		/** Returns the input item wrapped in a Node */
		public Node add(String item){
			if(head == null) {
				head = new Node(null, item);
			}
			else {
				head = new Node(head, item);
			}
				
			
			return head;
		}
		private Node getNode(int pos){
			if (pos >= size()) throw new IndexOutOfBoundsException();
			int pointer = 0;
			Node node = head;
			while(++pointer <= pos) {
				node = node.next;
			}
			
			//node is the node we are seeking
			return node;
		}
		
		public String get(int pos){
			Node node = getNode(pos);
			return (node != null) ? node.value : null;
		}
		/** returns the index of the String s, if found; 
		 * -1 otherwise 
		 */
		public int find(String s){
			if(s == null) return -1;
			Node currentNode = head;
			int i = -1;
			while(currentNode != null){
				++i;
				if(s.equals(currentNode.value)) return i;	
				currentNode = currentNode.next;
				
			}
			return -1;
		}
		
		
		public void insert(String s, int pos) {
			//corrected to throw exception
			if(pos > size()) {
				throw new IndexOutOfBoundsException("pos = "+pos +" but size = "+size());
			}
			if(pos == 0) {
				add(s);
			}
			else {
				Node next = head;
				Node previous = null;
				for(int i = 0; i < pos; ++i){
					previous = next;
					next = next.next;
				}
				Node insertNode = new Node(next,s);
				previous.next = insertNode;
			}
			
		}

		/** remove object at specified index */
		public boolean remove(int index){
			Node toBeRemoved = getNode(index);
			Node previous = null;
			if(index > 0) {
				previous = getNode(index-1);
			}
			if(toBeRemoved == null) return false;
			Node next = toBeRemoved.next;
			if(previous != null)
                previous.next = toBeRemoved.next;
			toBeRemoved = null;
			return true;
		}
		/** remove by specifying object -- removes
		 *  first occurrence of s
		 */
		public boolean remove(String s){
			int pos = find(s);
			if(pos == -1) return false;
			return remove(pos);
			
		}	
		
		public int size(){
			int count = 0;
			Node next = head;
			while(next != null){
				++count;
				next = next.next;
			}
			return count;
		}
		
		public String toString(){
			StringBuilder sb = new StringBuilder("[");
			Node next = head;
			while(next != null){
				sb.append(next.value+", ");
				next = next.next;
			}
			String ret = sb.toString();
			ret = ret.substring(0,ret.length()-2);
			return (ret +"]");
		}
				
		
		
		class Node  {
			String value;
			Node next;
			Node(){}
			Node(Node next, String value){
				this.next = next;
				this.value = value;
			}
			public String toString(){
				return value;
			}
		}

		public static void main(String[]args){
					}
		
    }


