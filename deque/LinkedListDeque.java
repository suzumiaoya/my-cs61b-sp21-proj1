package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T> {

    class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item, Node<Item> prev, Node<Item> next){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private final Node<T> head;
    private final Node<T> tail;
    private int size;

    public LinkedListDeque(){
        this.head = new Node<>(null, null, null);
        this.tail = new Node<>(null, head, null);
        this.head.next = tail;
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node<T> addItem = new Node<>(item,head,head.next);
        head.next.prev = addItem;
        head.next = addItem;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> addItem = new Node<>(item,tail.prev,tail);
        tail.prev.next = addItem;
        tail.prev = addItem;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(isEmpty())
            return null;

        T item = head.next.item;
        head.next.item = null;
        head.next.next.prev = head;
        head.next = head.next.next;
        size--;
        return item;
    }

    @Override
    public T removeLast() {
        if(isEmpty())
            return null;
        T item = tail.prev.item;
        tail.prev.item = null;
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
        size--;
        return item;
    }

    @Override
    public T get(int index){
        Node<T> node = head;
        if(index < 0 || index >= size)
            return null;
        for(int i = 0; i <= index; i++){
            node = node.next;
        }
        return node.item;
    }

    private Node<T> getNodeRecursive(int index){
        Node<T> node;
        if(index == 0)
            return head.next;
        else
            node = getNodeRecursive(index - 1).next; return node;
    }

    public T getRecursive(int index){
        if(index < 0 || index >= size)
            return null;

        return getNodeRecursive(index).item;
    }

    @Override
    public void printDeque() {
        Node<T> node = head.next;
        while(node.item != null){
            System.out.println(node.item);
            node = node.next;
        }
        System.out.println();
    }

    public boolean equals(Object o){
        if(!(o instanceof Deque<?>) || (((Deque<?>) o).size() != size)){
            return false;
        }
        for(int i = 0; i < size; i++){
            if(!(get(i).equals((Deque<?>)((Deque<?>) o).get(i)))){
                return false;
            }
        }
        return true;
    }

    public Iterator<T> iterator(){
        return new dequeIterator();
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
