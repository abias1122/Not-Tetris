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

    public void insertNode(Node insertNode) {

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
    private class Node {
        private Tetronimo data;
        private Node next;

        public Node(Tetronimo data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node() {
            data = null;
            next = null;
        }

        public Tetronimo getData() { return data; }

        public Node getNext() { return next; }

        public void setData(Tetronimo tetronimo) { data = tetronimo; }

        public  void setNext(Node next) { this.next = next; }
    }
}
