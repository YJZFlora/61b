

public class ArrayDequeTest {

    public static void testAddAndSize() {
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        a1.addFirst(4);
        a1.addFirst(3);
        a1.addFirst(2);
        a1.addFirst(1);
        a1.addFirst(0);
        a1.addFirst(7);

        a1.addLast(5);
        a1.addLast(6); // actually: { 0, 1, 2, 3, 4, 5, 6(nF), 7(nL)}, while in user's mind, it is { 7, 0, 1, 2, 3, 4, 5, 6} )

        a1.addFirst(15);
        a1.addLast(8);
        /*         0  1  2  3 4 5 6 7 8   9    10 11 12 13 14    15                           0  1  2 3 4 5 6 7 8 9
        Actually: {7, 0, 1, 2,3,4,5,6,8,/(nL), /, /, /, /, /(nF), 15}, while in user's mind: {15, 7,0,1,2,3,4,5,6,8}
         */
        a1.printDeque();// {15, 7,0,1,2,3,4,5,6,8}

        a1.addFirst(4);
        //   0   1   2  3  4  5  6  7  8  9   10   11  12  13  14   15
        // {7,  0, 1, 2, 3, 4, 5, 6, 8, /nl, ...          /nf, 4,  15}
        System.out.println("4 VS " + a1.get(0));
        System.out.println("11 VS " +a1.size());


        a1.removeLast();
        //   0   1   2  3  4  5  6  7  8  9   10   11  12  13  14   15           0  1   2   3 4 5 6 7 8 9
        // {7,  0, 1, 2, 3, 4, 5, 6,/nl, ...               /nf, 4,  15}  user: { 4, 15, 7, 0,1,2,3,4,5,6}
        System.out.println("10 VS " +a1.size());
        System.out.println("4 VS " + a1.get(0));
        for(int j = 0; j < 8; j++) {
            a1.removeLast();
        }
        System.out.println("2 VS " +a1.size());
        System.out.println("4 VS " + a1.get(0));
        System.out.println("15 VS " + a1.get(1));
        System.out.println("null VS " + a1.get(2));
        //a1.printDeque();// { 4, 15}

        ArrayDeque<Integer> b2 = new ArrayDeque<>(a1);
        b2.printDeque(); // { 4, 15}

        ArrayDeque<Integer> c = new ArrayDeque<>();
        c.addFirst(0);
        c.addFirst(1);
        c.addLast(2);
        c.addFirst(3);
        c.addFirst(4);
        c.addFirst(5);
        c.removeLast();
        c.addLast(7);
        c.addLast(8);
        c.addFirst(9);
        c.addLast(10);
        c.removeFirst();
        c.addLast(12);
        c.removeLast();
        c.removeLast();
        c.get(6); //8
        c.get(0); //5
        c.removeFirst();     //==> 5
        c.removeLast();    //==> 8
        c.removeFirst();     //==> 4
        c.removeFirst() ;    //==> 3
        c.removeLast(); //7

    }


    public static void main(String[] args){
        System.out.println("Running tests.\n");
        testAddAndSize();
    }

}