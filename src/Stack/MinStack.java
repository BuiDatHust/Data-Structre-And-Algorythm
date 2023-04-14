package Stack;

import java.util.ArrayList;
import java.util.List;

// every operation has complexity is O(1)
class MinStack {
    List<Integer> arr = new ArrayList<>();
    List<Integer> minArr = new ArrayList<>();
    int min = Integer.MAX_VALUE;
    int size = 0;

    public MinStack() {

    }

    public void push(int val) {
        if (min > val) {
            min = val;
        }

        arr.add(val);
        minArr.add(min);
        size++;
    }

    public void pop() {
        if (arr.isEmpty()) {
            return;
        }

        arr.remove(size - 1);
        minArr.remove(size - 1);
        size--;

        if (arr.isEmpty()) {
            min = Integer.MAX_VALUE;
        } else {
            min = minArr.get(size - 1);
        }
    }

    public int top() {
        return arr.get(size - 1);
    }

    public int getMin() {
        return min;
    }
}
