package es.datastructur.synthesizer;
import java.util.Iterator;


public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;

    /* Index for the next enqueue. */
    private int last;

    /* Variable for the fillCount. */
    private int fillCount;

    /* Array for storing the buffer data. */
    private T[] rb;


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) (new Object[capacity]);
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    // return size of the buffer
    public int capacity() {
        return rb.length;
    }

    @Override
    // return number of items currently in the buffer
    public int fillCount() {
        return fillCount;
    }


    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last = move(last);
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T returnValue = rb[first];
        first = move(first);
        fillCount = fillCount - 1;
        return returnValue;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return  rb[first];
    }

    private int move(int cursor) {
        if (cursor == capacity() - 1) {
            return 0;
        }
        int returnV = cursor += 1;
        return returnV;
    }

    @Override
    public Iterator<T> iterator() {
        return new Bqiterator<T>();
    }

    private class Bqiterator<T> implements Iterator<T> {
        private int wizPos;

        Bqiterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < fillCount;
        }

        public T next() {
            T returnItem = (T) rb[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer arbo = (ArrayRingBuffer) o;

        if (this.fillCount() != arbo.fillCount()) {
            return false;
        }

        int thiscur = this.first;
        int ocur = arbo.first;

        for (int i = 0; i < this.fillCount(); i++) {

            if (this.rb[thiscur] != arbo.rb[ocur]) {
                return false;
            }
            thiscur = move(thiscur);
            ocur = move(ocur);

        }
        return true;
    }
}

