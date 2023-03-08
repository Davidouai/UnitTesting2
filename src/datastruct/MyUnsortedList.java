package datastruct;

import java.util.Arrays;

public class MyUnsortedList<E> implements UnsortedList<E> {

    private static class Link<E> {
        final E element;
        Link<E> next;
        Link<E> prev;

        private Link(E element) {
            this.element = element;
        }
    }

    private Link<E> head;
    private Link<E> tail;
    private int size;

    private MyUnsortedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @SafeVarargs
    public static <E> MyUnsortedList<E> of(E... elements) {
        return of(Arrays.asList(elements));
    }

    public static <E> MyUnsortedList<E> of(Iterable<E> elements) {
        MyUnsortedList<E> list = new MyUnsortedList<>();
        for (E element : elements) {
            list.append(element);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void prepend(E element) {
        Link<E> newHead = new Link<>(element);
        if (isEmpty()) {
        	tail = newHead;
        }
        size++;
        newHead.next = head;
        head = newHead;
    }

    @Override
    public void append(E element) {
        insert(element, size);
    }

    @Override
    public void insert(E elem, int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        
        
        if (pos == 0) {
            prepend(elem);
        } else if (pos == size) {
            size++;
            Link<E> inserted = new Link<>(elem);
            Link<E> oldTail = tail;
        	tail.next = inserted;
        	tail = inserted;
        	tail.prev = oldTail;
        } else {
            Link<E> prevLink = head;
            while (pos-- > 1) {
                prevLink = prevLink.next;
            }

            size++;
            Link<E> inserted = new Link<>(elem);
            Link<E> nextLink = prevLink.next;
            prevLink.next = inserted;
            inserted.next = nextLink;
        }
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new EmptyListException();
        } 
        
        if (size == 1) {
        	tail = null;
        }

        size--;
        Link<E> oldHead = head;
        head = oldHead.next;

        return oldHead.element;
    }

    @Override
    public E popLast() {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        
    	return remove(size - 1);
    }

    @Override
    public E remove(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (pos == 0) {
            return pop();
        } else if (pos == size - 1) {
	        size--;
	        E elt = tail.element;
        	if (tail.prev != null) {
        		tail = tail.prev;
        	} else {
        		tail = head;
        	}
	        return elt;
        } else {
	        Link<E> prevLink = head;
	        while (--pos > 0) {
	            prevLink = prevLink.next;
	        }
	
	        Link<E> removed = prevLink.next;
	        prevLink.next = removed.next;

	        size--;
	        return removed.element;
        }
    }

    @Override
    public E show(int pos) throws IndexOutOfBoundsException {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (pos == 0) {
        	return head.element;
        }
        
        Link<E> prevLink = head;
        while (--pos > 0) {
            prevLink = prevLink.next;
        }
        
        return prevLink.next.element;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyUnsortedList)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        MyUnsortedList<E> that = (MyUnsortedList<E>) obj;
        if (this.size != that.size) {
            return false;
        }

        Link<E> thisLink = this.head;
        Link<E> thatLink = that.head;
        while (thisLink != null) {
            if (thatLink == null || !thisLink.element.equals(thatLink.element)) {
                return false;
            }
            thisLink = thisLink.next;
            thatLink = thatLink.next;
        }

        return thatLink == null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("MyUnsortedList { size = ");
        builder.append(size);
        builder.append(", [");

        MyUnsortedList.Link<E> link = head;
        while (link != null) {
            builder.append(link.element);
            link = link.next;
            if (link != null) {
                builder.append(", ");
            }
        }

        return builder.append("] }").toString();
    }
}
