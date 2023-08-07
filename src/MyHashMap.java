public class MyHashMap<K, V> {
    private class Node {
        K key;
        V value;
        Node next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node[] buckets;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public MyHashMap() {
        capacity = DEFAULT_CAPACITY;
        buckets = new Node[capacity];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private void rehash() {
        Node[] oldBuckets = buckets;
        capacity *= 2;
        buckets = new Node[capacity];
        size = 0;

        for (Node oldNode : oldBuckets) {
            Node currentNode = oldNode;
            while (currentNode != null) {
                put(currentNode.key, currentNode.value);
                currentNode = currentNode.next;
            }
        }
    }

    public void put(K key, V value) {
        if ((double) size / capacity >= LOAD_FACTOR) {
            rehash();
        }

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

    public void remove(K key) {
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
        buckets = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
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
