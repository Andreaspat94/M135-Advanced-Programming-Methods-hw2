public class Main {
    private static final Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long startTime = System.currentTimeMillis();

        int count = 200000000;
        MyDoublyLinkedList list = new MyDoublyLinkedList();
        System.out.println("--------------\nStart adding nodes...");
        for (int i = 0; i < count; i++) {
            MyDoublyLinkedList.Node newNode = new MyDoublyLinkedList.Node();
            int variable = i * 3;
            newNode.data = variable;
            list.add(newNode);
        }

        System.out.println("Finish adding nodes\n--------------");
        long stopTime= System.currentTimeMillis();
        System.out.println("Total adding time: " + (stopTime - startTime)+ " milliseconds");

        int counter = count;
        MyDoublyLinkedList.Node itr;
        itr = list.head;
        startTime = System.currentTimeMillis();
        System.out.println("--------------\nStart deleting nodes..." );
        // delete nodes from list
        for (int i = 1; i < count; i++) {
            if (i % 10 != 0 && i > 2) {
                if (itr.prev.prev != null) {
                    list.del(itr.prev);
                }
                counter--;
            }
            itr = itr.next;
        }
        itr = null;

        System.out.println("Finish deleting nodes...\n--------------");
        stopTime = System.currentTimeMillis();
        System.out.println("Total deleting time: " + (stopTime - startTime)+ " milliseconds");
        //size of list
        System.out.println("Size of list: " + counter);

        //mix node pointers in list.
        startTime = System.currentTimeMillis();
        list.mixPointers(counter);
        stopTime = System.currentTimeMillis();
        System.out.println("Total mixing time: " + (stopTime - startTime) + " milliseconds");

		System.out.println("--------------\nStart iterating the list...");
        startTime = System.currentTimeMillis();
        for (int i = 0 ; i < 5; i++) {
			list.traverse();
		}
        stopTime = System.currentTimeMillis();
        System.out.println("Traversal time: " + (stopTime - startTime));

        System.out.println("Total execution time: " + (stopTime - start));
    }
}

class MyDoublyLinkedList {
    static class Node {
        int data;
        Node prev;
        Node next;

        public Node() {
        }
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

    void del(MyDoublyLinkedList.Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node = null;
    }

    void traverse() {
        
        int counter = 1;
        MyDoublyLinkedList.Node ptr = head;

        while (ptr != null) {
            ptr = ptr.next;
            counter++;
        }

        ptr = tail;
        while(ptr != null) {
            ptr = ptr.prev;
        }
    }

    void mixPointers(int size) {
        System.out.println("--------------\nStart mixing the list...");
        Node i1 = head;
        Node temp = head.next;
        Node i2 = tail;
        int counter = 0;

        while (counter < size/2) {
            i1.next = i2;
            i2.next = temp;

            temp = temp.next;
            i2 = i2.prev;
            i1 = i1.next.next;
            counter++;
            if (counter == size/2) {
                i1.next = null;
            }
        }
        counter = 0;
        i1 = tail;
        temp = tail.prev;
        i2 = head;
        while(counter < size/2) {
            i1.prev = i2;
            i2.prev = temp;

            i1 = temp;
            temp = temp.prev;
            i2 = i2.next.next;

            counter++;
            if (counter == size/2){
                i1.prev = null;
            }
        }

        i1 = null;
        i2 = null;
        temp = null;

        System.out.println("Finish mixing the list...\n--------------");
    }
}