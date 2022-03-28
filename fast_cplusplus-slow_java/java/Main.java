public class Main {
    private static final Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        final int count = 250000000;

        // create my custom Doubly linked list.
        MyDoublyLinkedList list = new MyDoublyLinkedList();
        System.out.println("Start filling the list...");
        for (int i = 0; i < count; i++) {
            MyDoublyLinkedList.Node newNode = new MyDoublyLinkedList.Node();
            // trying to restrain VM's optimization processes.
			int variable = i * 3;
            newNode.data = variable;
            if (10550 % (i+1) > 500) {
				variable = i *4;
                newNode.data = variable;
            }

            list.add(newNode);
        }
     
       long endTime = System.currentTimeMillis();
        System.out.println("The execution took: " + (endTime - startTime) + " milliseconds");
    }
}

class MyDoublyLinkedList {
    static class Node {
        int data;
        Node prev;
        Node next;

        public Node() {}
    }

    Node head, tail = null;

    public void add(Node newNode) {

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