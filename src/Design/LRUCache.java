package Design;

import java.util.*;

class LRUCache {
    public int capacity;
    public int size = 0;
    Map<Integer, Integer> stores = new HashMap<>();
    Deque<Integer> q = new LinkedList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }


    public int get(int key) {
        if (!stores.containsKey(key)) {
            return -1;
        }

        q.remove(key);
        q.addLast(key);
        return stores.get(key);
    }

    public void put(int key, int value) {
        if (!stores.containsKey(key)) {
            if (size == capacity - 1) {
                stores.remove(q.pollFirst());
            } else {
                size++;
            }
            q.add(key);
        } else {
            q.remove(key);
            q.addLast(key);
        }
        stores.put(key, value);
    }
}
