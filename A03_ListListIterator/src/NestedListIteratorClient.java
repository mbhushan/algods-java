import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 Given an array of arrays, implement an iterator class to allow the client to
 traverse and remove elements in the array list.

 This iterator should provide three public class member functions:

 boolean hasNext() return true if there is another element in the set
 int next() return the value of the next element in the array
 void remove() remove the last element returned by the iterator. T
 hat is, remove the element that the previous next() returned.
 This method can be called only once per call to next(), otherwise an exception will be thrown.
 */
public class NestedListIteratorClient {

    public static void main(String[] args) {
        NestedListIteratorClient nc = new NestedListIteratorClient();

        List<Integer> A = Arrays.asList(new Integer[]{3, 6, 9});
        List<Integer> B = Arrays.asList(new Integer[]{5, 10, 20});
        List<Integer> C = Arrays.asList(new Integer[]{2, 4, 6, 8});

        List<List<Integer>> list = new ArrayList<>();
        list.add(A);
        list.add(B);
        list.add(C);


        List<List<Integer>> test = new ArrayList<>();
        test.add(new ArrayList<Integer>() {{
            add(1);
            add(2);
        }});
        test.add(new ArrayList<Integer>() {{
        }});
        test.add(new ArrayList<Integer>() {{
            add(4);
            add(5);
            add(6);
        }});

        test.add(new ArrayList<Integer>(){{
            add(8);
            add(10);
        }});



        nc.demoIter2(test);
    }

    public void demoIter2(List<List<Integer>> list) {
        NestedListIterator nl = new NestedListIterator(list);

        System.out.println("input list: " + list);
        while (nl.hasNext()) {
            System.out.println(nl.next());
            nl.remove();
            System.out.println("input list: " + list);
        }
    }

    public void demoIter1(List<List<Integer>> list) {
        ListIterator li = new ListIterator(list);

        while(li.hasNext()) {
            System.out.print(li.next()+ " ");
        }
        System.out.println();
    }
}

class NestedListIterator {

    Iterator<List<Integer>> rowIter;
    Iterator<Integer> colIter;

    public NestedListIterator(List<List<Integer>> input) {
        rowIter = input.iterator();
        colIter = Collections.emptyIterator();
    }

    public Integer next() {
        return (Integer) colIter.next();
    }

    public boolean hasNext() {
        while (!colIter.hasNext() && rowIter.hasNext()) {
            colIter = rowIter.next().iterator();
        }

        return colIter.hasNext();
    }

    public void remove() {
        colIter.remove();
    }
}

class ListIterator {

    Deque<Integer> deque;
    Iterator iter;
    Iterator colIter;

    public ListIterator(List<List<Integer>> input) {
        deque = new ArrayDeque<>();
        iter = input.iterator();
        colIter = Collections.emptyIterator();

    }

    public boolean hasNext() {
        while (deque.isEmpty()) {
            if (iter.hasNext()) {
                List<Integer> list = (List<Integer>) iter.next();
                colIter = list.iterator();
                if (list.size() > 0) {
                    System.out.println();
                    deque.addAll(list);
                }
            } else {
                break;
            }
        }

        return !deque.isEmpty();
    }

    public void remove() {
        iter.remove();
    }

    public Integer next() {
        return deque.isEmpty() ? null: deque.removeFirst();
    }

}
