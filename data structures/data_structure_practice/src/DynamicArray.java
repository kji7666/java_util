public class DynamicArray <T> implements Iterable <T> {
    private T[] array;
    private int capacity = 0;
    private int len = 0;

    public DynamicArray(int capacity) {
        if(capacity <= 0) throw new IllegalArgumentException("Illegal capacity: " + capacity);
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }
    public DynamicArray() {this(16);}

    public int size() { return len; }
    public boolean isEmpty() { return size() == 0; }
    public T get(int index) { return array[index]; }
    public void set(int index, T elem) { array[index] = elem; }

    public void clear() {
        for(int i=0; i<size(); i++) {
            array[i] = null;
        }
        len = 0;
    }

    public void add(T elem) {
        if(len+1 >= capacity) {
            if(capacity == 0) { capacity = 1; }
            else { capacity *= 2; }
            T[] newArray = (T[]) new Object[capacity];
            for(int i=0; i<len; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[len++] = elem;
    }

    public T removeAt(int rmIndex) {
        if (rmIndex >= len || rmIndex < 0) throw new IndexOutOfBoundsException();
        T data = array[rmIndex];
        T[] newArray = (T[]) new Object[len-1];
        for(int i=0, j=0; i<len; i++, j++) {
            if(i == rmIndex) j--;
            else newArray[j] = array[j];
        }
        array = newArray;
        --len;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i=0; i<len; i++) {
            if(array[i].equals(obj)) {
                removeAt(i); return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i=0; i<len; i++) {
            if(array[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator <T> iterator() {
        return new java.util.Iterator<T> () {
            int index = 0;
            public boolean hasNext() { return index < len; }
            public T next() { return array[index++]; }
        };
    }

    @Override 
    public String toString() {
        if(len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for(int i=0; i<len-1; i++) { sb.append(array[i] + ", "); }
            return sb.append(array[len-1] + "]").toString();
        }
    }
}
