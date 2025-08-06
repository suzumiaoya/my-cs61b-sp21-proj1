package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque <T> implements Deque<T>{
    T[] array;
    int size,head,tail, capacity;
    /*
    * head: the index of the head_item in the deque
    * tail: the index of the tail_item in the deque
    * */

    public ArrayDeque(){
        array = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
        capacity = 8;
    }

    public ArrayDeque(int cap){
        array = (T[]) new Object[cap];
        size = 0;
        head = 0;
        tail = 0;
        capacity = cap;
    }

    /**
     * Considering to use loop structure to make the addFirst more efficient
     *
     *
     */
    private T[] resize(){
        T[] a = (T[]) new Object[array.length*2];
        if(head < tail){
            System.arraycopy(array, 0, a, 0, array.length);
            head = 0;
            tail = array.length - 1;
            return a;
        }
        else{
            System.arraycopy(array, head, a, 0, array.length - head);
            System.arraycopy(array, 0, a, array.length - head, tail + 1);
            head = 0;
            tail = array.length - 1;
            return a;
        }
    }

    @Override
    public void addFirst(T item){
        if(size == 0){
            array[head] = item;
            size++;
        }
        else if(size == array.length) {
            T[] a = resize();
            head = (head + a.length - 1) % a.length;
            a[head] = item;
            size++;
            array = a;
        }
        else {
            head = (head + array.length - 1) % array.length;
            array[head]=item;
            size++;
        }
    }

    @Override
    public void addLast(T item){
        if(size == array.length){
            T[]a = resize();
            tail = (tail + 1) % a.length;
            a[tail] = item;
            size++;
            array = a;
        }
        else if(size == 0){
            array[tail] = item;
            size++;
        }
        else {
            tail = (tail + 1) % array.length;
            array[tail] = item;
            size++;
        }
    }

    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        else {
            T item = array[head];
            array[head] = null;
            head = (head + 1) % array.length;
            size--;
            return item;
        }
    }

    @Override
    public T removeLast(){
        if(size == 0){
            return null;
        }
        else {
            T item = array[tail];
            array[tail] = null;
            tail = (tail - 1 + array.length) % array.length;
            size--;
            return item;
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public T get(int index){
        if(size == 0 || index < 0 || index >= size){
            return null;
        }
        else {
            return array[(head + index) % array.length];
        }
    }

    @Override
    public void printDeque(){
        for(int i = 0; i <= size - 1; i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator(){
        return new ArrayDeque.dequeIterator();
    }

    private class dequeIterator implements Iterator<T>{
        int pos = 0;

        @Override
        public boolean hasNext(){
            return pos < size;
        }

        @Override
        public T next(){
            if(!(hasNext())){
                throw new NoSuchElementException("No more elements in the deque.");
            }
            T item = get(pos);
            pos++;
            return item;
        }
    }
}
