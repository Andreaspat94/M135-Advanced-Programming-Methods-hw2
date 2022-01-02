package myhw2.gc;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        final int count = 1000000000;

        for (int i = 0; i < count; i++) {
            Node newNode = new Node();
            newNode.x = i * 3;
            newNode.y = i * 6;
            newNode.z = i * 4;
            if (10550 % (i + 1) > 50) {
                newNode.z = i * 4;
            }
        }

        for (int i = 0; i <count * 0.2; i++) {
            Node newNode = new Node();
            newNode.z = i+4;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("The execution took: " + (endTime - startTime) + " milliseconds");
    }
}

class Node {
    int x;
    int y;
    int z;

    public Node() {
    }
}