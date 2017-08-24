package com.example.android.tetris.game_entities.TetronimoDispenser;

import com.example.android.tetris.game_entities.GridCellView;
import com.example.android.tetris.game_entities.Tetronimoes.Tetronimo;

/**
 * A circular, singly linked list that tracks the tetronimo in play at the head
 * and upcoming tetronimos in connected nodes.
 */
public class TetronimoLinkedList {

    private Node head;
    private int size;

    public TetronimoLinkedList() {
        head = null;
        size = 0;
    }

    public Node getHead() {return head;}

    public int getSize() {return size;}

    /**
     * Inserts a node containing data into the circular list
     * @param data the data to be stored on the node
     */
    public void insertNode(Tetronimo data) {

        Node insertNode = new Node();
        insertNode.setData(data);

        if(head == null) {
            head = insertNode;
        }
        else {
            Node node = head;
            for(int i = 1; i < size; i++) {
                node = node.next;
            }

            insertNode.next = head;
            node.next = insertNode;
        }

        size++;
    }

    /**
     * Advance head to next node in circle
     * @return new head node
     */
    public Node getNextHead() {
        head = head.next;
        return head;
    }

    private class Node {
        private Tetronimo data;
        private Node next;

        public Node(Tetronimo data, Node next) {
            this.data = data;
            this.next = next;
        }

        Node() {
            data = null;
            next = null;
        }

        public Tetronimo getData() { return data; }

        public Node getNext() { return next; }

        public void setData(Tetronimo tetronimo) { data = tetronimo; }

        public void setNext(Node next) { this.next = next; }
    }
}
