public class MyStack<T> {
    private class Node {
        T value;
        Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public MyStack() {
        top = null;
        size = 0;
    }

    public void push(T value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            top = top.next;
        } else {
            Node prevNode = getNodeAtIndex(index - 1);
            prevNode.next = prevNode.next.next;
        }
        size--;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (top == null) {
            return null;
        }
        return top.value;
    }

    public T pop() {
        if (top == null) {
            return null;
        }
        T value = top.value;
        top = top.next;
        size--;
        return value;
    }

    private Node getNodeAtIndex(int index) {
        Node currentNode = top;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }
}
