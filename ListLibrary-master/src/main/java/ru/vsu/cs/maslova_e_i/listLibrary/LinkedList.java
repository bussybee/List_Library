package ru.vsu.cs.maslova_e_i.listLibrary;

import java.util.NoSuchElementException;

public class LinkedList<E>{

    /* внутренний класс, необходим для того, чтобы
    объявить ссылки на близлежащие элементы списка
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    int size = 0;
    Node<E> first;
    Node<E> last;
    int modCount = 0;

    public LinkedList(){
        this.first = null;
        this.last = null;
    }

    //Добавляет указанный элемент в конец этого списка
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    //добавление элемента в конкретную ячейку по индексу
    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(element);
        else
            linkBefore(element, node(index));
    }

    //Вставляет указанный элемент в начало этого списка
    public void addFirst(E e) {
        linkFirst(e);
    }

    //Вставляет указанный элемент в конец этого списка
    public void addLast(E e) {
        linkLast(e);
    }

    //Удаляет элемент в указанной позиции в этом списке
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    //Извлекает и удаляет первый элемент этого списка
    public E remove() {
        return removeFirst();
    }

    //удаляет и возвращает первый элемент данного списка
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    //удаляет и возвращает последний элемент данного списка
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    //возвращает индекс первого появления указанного элемента
    //или -1, если список не содержит элемента
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;
    }

    //удаляет все элементы данного листа
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.element = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }

    //Возвращает значение, если этот список содержит указанный элемент
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    //Возвращает количество элементов в этом списке
    public int size() {
        return size;
    }

    //Возвращает массив, содержащий все элементы этого списка
    //в правильной последовательности (от первого до последнего элемента)
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.element;
        return result;
    }

    //удаляет элемент стека, представленного этим списком
    public E pop() {
        return removeFirst();
    }

    //Помещает элемент в стек, представленный этим списком
    public void push(E e) {
        addFirst(e);
    }

    //Возвращает элемент в указанной позиции в этом списке
    public E get(int index) {
        checkElementIndex(index);
        return node(index).element;
    }

    //Заменяет элемент в указанной позиции указанным элементом
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVal = x.element;
        x.element = element;
        return oldVal;
    }

    //Указывает, является ли аргумент индексом существующего элемента
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    //Указывает, является ли аргумент индексом допустимой позиции
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    //Ссылка на объект e в качестве первого элемента
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }

    //Ссылка на объект e в качестве последнего элемента
    void linkLast(E e) {
        final Node<E> l = last;
        //создается объект-обертка, туда кладется добавляемый элемент
        final Node<E> newNode = new Node<>(l, e, null);
        //Node становится последним
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    //метод проверяет, что индекс, по которому происходит вставка, не выходит за границы списка
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    //возвращает сообщение, если индекс выходит за границы допустимых значений
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    //возвращает элемент с заданной позицией
    private Node<E> node(int index) {
        Node<E> x;
        if (index < (size >> 1)) {
            x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
        }
        return x;
    }

    //добавляет элемент e перед ненулевым узлом succ
    private void linkBefore(E e, Node<E> succ) {
        final Node<E> pred = succ.prev;
        final Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    //отсоединяет ненулевой узел
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.element;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.element = null;
        size--;
        modCount++;
        return element;
    }

    //отсоединяет ненулевой первый узел
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.element;
        final Node<E> next = f.next;
        f.element = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }

    //отсоединяет ненулевой последний узел
    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.element;
        final Node<E> prev = l.prev;
        l.element = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }


}
