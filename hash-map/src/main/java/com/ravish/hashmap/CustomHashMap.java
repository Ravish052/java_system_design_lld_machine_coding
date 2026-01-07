package com.ravish.hashmap;


class Node<K, V> {
    K key;
    V val;

    Node<K,V> next;
    Node<K,V> prev;

    Node(K key, V val){
        this.key = key;
        this.val = val;
    }
}


public class CustomHashMap<K, V> {
    private final int INITIAL_SIZE = 4;
    private final int MAX_CAPACITY = 1 << 30;
    private final float LOAD_FACTOR = 0.75f;
    private int countOfNodes = 0;

    private Node<K, V>[] map;

    public CustomHashMap(){
        map = new Node[INITIAL_SIZE];
        for (int i = 0; i < INITIAL_SIZE; i++){
            map[i] = new Node<>( null,  null);
            map[i].next = new Node<>( null, null);
            map[i].next.prev = map[i];
        }
    }

    // main functions
    public V get(K key) {
        Node node = findNode(key);
        return node == null ? null : (V) node.val;
    }

    public void put(K key, V val) {
        Node node = findNode(key);
        if (node != null) {
            node.val = val;
            return;
        }

        int bucket = key.hashCode() % map.length;

        Node head = map[bucket];

        Node newNode = new Node(key, val);
        Node old_first = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = old_first;
        old_first.prev = newNode;

        countOfNodes++;

        if (countOfNodes > LOAD_FACTOR * map.length){
            rehash( map.length * 2);
        }

    }

    public void remove(K key) {
        Node nodeToRemove = findNode(key);
        if (nodeToRemove == null ) return;

        Node prevNode = nodeToRemove.prev;
        Node nextNode = nodeToRemove.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        countOfNodes --;
    }

    //helper functions
    public int getSize() { return countOfNodes;}

    private void rehash(int newSize) {
        if (newSize > MAX_CAPACITY) {
            System.out.println(("HASHMAP EXCEEDING MAX CAPACITY"));
        }

        Node[] newMap = new Node[newSize];
        for(int i = 0; i< newSize; i++){
            newMap[i] = new Node<>( null,  null);
            newMap[i].next = new Node<>(null, null);
            newMap[i].next.prev = newMap[i];
        }

        for (Node<K,V> curr : map) {
            while(curr != null) {
                if(curr.key == null) {
                    curr = curr.next;
                    continue;
                }

                int newBucket = curr.key.hashCode() % newSize;
                Node newHead = newMap[newBucket];
                Node oldFirst = newHead.next;

                Node<K, V> newNode = new Node<>(curr.key, curr.val);
                newHead.next = newNode;
                newNode.prev = newHead;
                newNode.next = oldFirst;
                oldFirst.prev = newNode;

                curr = curr.next;
            }
        }
    }

    private Node findNode(K key) {
        int bucket = key.hashCode() % map.length;
        Node head = map[bucket];

        while(head != null) {
            if(head.key != null && head.key.equals(key)){
                return head;
            }

            head= head.next;
        }
        return null;
    }


}
