package myhw2.gc;

public class Object {
    private static final Runtime runtime = Runtime.getRuntime();

    public static void main(String[] args) {
//        runtime.gc();
        long heap1 = usedMemory();
        System.out.println(heap1);

        final int count = 10000;
        double[] objects = new double[count];

        for (int i = 0; i < count ; i++) {

            int object = i;
            objects [i] = (double) object;

        }
//        runtime.gc();
        long heap2 = usedMemory();
        System.out.println(heap2);
        System.out.println("Size of object Point: " + Math.round((float) (heap2 - heap1))/count + " bytes");
    }


    public static long usedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }
}

