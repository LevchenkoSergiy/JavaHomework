public class MyHashMap {
    private class Node {
        Object key;
        Object value;
        Node next;

        Node(Object key, Object value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node[] buckets;
    private int capacity;
    private int size;

    public MyHashMap() {
        capacity = 16; // Initial capacity
        buckets = new Node[capacity];
        size = 0;
    }

    private int hash(Object key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(Object key, Object value) {
        int index = hash(key);
        Node newNode = new Node(key, value);

        if (buckets[index] == null) {
            buckets[index] = newNode;
        } else {
            Node currentNode = buckets[index];
            while (currentNode.next != null) {
                if (currentNode.key.equals(key)) {
                    currentNode.value = value;
                    return;
                }
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
        size++;
    }

    public void remove(Object key) {
        int index = hash(key);

        if (buckets[index] == null) {
            return;
        }

        if (buckets[index].key.equals(key)) {
            buckets[index] = buckets[index].next;
            size--;
            return;
        }

        Node prevNode = buckets[index];
        Node currentNode = prevNode.next;

        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                prevNode.next = currentNode.next;
                size--;
                return;
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }

    public Object get(Object key) {
        int index = hash(key);
        Node currentNode = buckets[index];

        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
}
