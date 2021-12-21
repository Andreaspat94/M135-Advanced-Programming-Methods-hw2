package myhw2.gc;

public class Main {
    private static final Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Total memory: ... " + runtime.totalMemory() + " bytes (currently available)");
        System.out.println("Max memory:   ... " + runtime.maxMemory() + " bytes");
        System.out.println("Free memory:  ... " + runtime.freeMemory() + " bytes");

        long heap1 = usedMemory();
        final int count = 100;

        // create my custom Doubly linked list.
        MyDoublyLinkedList list = new MyDoublyLinkedList();
        for (int i = 0; i < count; i++) {
            list.add(i*3);
        }

        long heap2 = usedMemory();
        System.out.println("Size of object list: " + Math.round((float) (heap2 - heap1)) / count + " bytes");

        long endTime = System.currentTimeMillis();
        System.out.println("The execution took: " + (endTime - startTime) + " milliseconds");
    }

    public static long usedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }
}

class MyDoublyLinkedList {

    class Node {
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    Node head, tail = null;

    public void add(int data) {
        Node newNode = new Node(data);
        // if list is empty, head and tail points to newNode.
        if (head == null) {
            head = tail = newNode;
            head.prev = null;
            tail.next = null;
        // else add new node to the end of the list.
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = null;
        }
    }
}
