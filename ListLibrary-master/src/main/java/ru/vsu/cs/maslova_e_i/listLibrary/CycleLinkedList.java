package ru.vsu.cs.maslova_e_i.listLibrary;

public class CycleLinkedList {

    private static class Node {
        int value;
        Node nextNode;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node head = null;
    private Node tail = null;

    public void addNode(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        }

        tail = newNode;
        tail.nextNode = head;
    }

    public boolean containsNode(int searchValue) {
        Node currentNode = head;

        if (head != null) {
            do {
                if (currentNode.value == searchValue) {
                    return true;
                }
                currentNode = currentNode.nextNode;
            } while (currentNode != head);
        }
        return false;
    }

    public void deleteNode(int valueToDelete) {
        Node currentNode = head;
        if (head == null) {
            return;
        }
        do {
            Node nextNode = currentNode.nextNode;
            if (nextNode.value == valueToDelete) {
                if (tail == head) {
                    head = null;
                    tail = null;
                } else {
                    currentNode.nextNode = nextNode.nextNode;
                    if (head == nextNode) {
                        head = head.nextNode;
                    }
                    if (tail == nextNode) {
                        tail = currentNode;
                    }
                }
                break;
            }
            currentNode = nextNode;
        } while (currentNode != head);
    }

}
