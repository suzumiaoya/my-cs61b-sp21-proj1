package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    Comparator<T> com;

    public MaxArrayDeque (Comparator<T> c){
        super();
        com = c;
    }

    public T max(){
        if(size == 0){
            return null;
        }
        else{
            T max = array[0];
            for(T item : array){
                if(com.compare(max,item) < 0){
                    max = item;
                }
            }
            return max;
        }
    }

    public T max(Comparator<T> c){
        if(size == 0){
            return null;
        }
        else{
            T max = array[0];
            for(T item : array){
                if(c.compare(max, item) < 0){
                    max = item;
                }
            }
            return max;
        }
    }
}
