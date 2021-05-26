package 알고리즘스터디;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class List클래스 {

    public static void main(String[] args) {
    }

    public static class myArrayList<T> implements List<T>{

        int size;
        private T[] arr;

        public myArrayList(){
            size = 0;
            arr = (T[])new Object[10];
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            if(size == 0 ) return true;
            else return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public boolean add(T element) {
            if(size >= arr.length){
                T[] bigger = (T[]) new Object[arr.length * 2];
                System.arraycopy(arr,0, bigger, 0, arr.length);
                arr = bigger;
            }
            arr[size] = element;
            size++;
            return false;
        }

        @Override
        public boolean remove(Object o) {
            int removeIndex = indexOf(o);
            if(removeIndex == -1) return false;
            remove(removeIndex);
            return true;
        }

        @Override
        public boolean addAll(Collection c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public T get(int index) {
            if(index < 0 || index >= size){
                throw new IndexOutOfBoundsException();
            }
            return arr[index];
        }

        @Override
        public T set(int index, T element) {
            T old = get(index);
            arr[index] = element;
            return old;
        }

        @Override
        public void add(int index, Object element) {

        }

        @Override
        public T remove(int index) {
            T removeElement = get(index);
            for(int i=index; i<size-1; i++){
                arr[i] = arr[i+1];
            }
            size--;
            return removeElement;
        }

        @Override
        public int indexOf(Object element) {
            for(int i=0; i<size; i++){
                if(equals(arr[i], element)){
                    return i;
                }
            }
            return -1;
        }

        private boolean equals(Object target, Object element) {
           if(target == null){
               return element == null;
           }
           return target.equals(element);
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator listIterator() {
            return null;
        }

        @Override
        public ListIterator listIterator(int index) {
            return null;
        }

        @Override
        public List subList(int fromIndex, int toIndex) {
            return null;
        }

        @Override
        public boolean retainAll(Collection c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection c) {
            return false;
        }

        @Override
        public boolean containsAll(Collection c) {
            return false;
        }

        @Override
        public Object[] toArray(Object[] a) {
            return new Object[0];
        }


    }
}
