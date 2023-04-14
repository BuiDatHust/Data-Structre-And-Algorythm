package Heap;

public class MinHeap {
    int[] minHeap;
    int realSize = 0;
    int heapSize;

    public MinHeap(int heapSize) {
        this.heapSize = heapSize;
        this.minHeap = new int[heapSize + 1];
    }

    public int peek() {
        return this.minHeap[1];
    }

    public void add(int x) {
        realSize++;
        if (realSize > heapSize) {
            System.out.println("out bound of min heap!");
            realSize--;
            return;
        }

        minHeap[realSize] = x;
        int index = realSize;
        int parent = index / 2;
        while (index > 1 && minHeap[parent] > minHeap[index]) {
            int temp = minHeap[parent];
            minHeap[parent] = minHeap[index];
            minHeap[index] = temp;
            index = parent;
            parent = index / 2;
        }
    }

    public int pop() {
        if (realSize < 1) {
            System.out.println("min heap is empty, can not pop");
            return -1;
        }

        int removeElement = minHeap[1];
        minHeap[1] = minHeap[realSize];
        minHeap[realSize] =0;
        realSize--;
        int index = 1;

        while (index <= realSize / 2) {
            int left = index * 2;
            int right = index * 2 + 1;

            if (minHeap[index] > minHeap[left] || minHeap[index] > minHeap[right]) {
                int temp = minHeap[index];
                if (minHeap[left] < minHeap[right]) {
                    minHeap[index] = minHeap[left];
                    minHeap[left] = temp;
                    index = left;
                } else {
                    minHeap[index] = minHeap[right];
                    minHeap[right] = temp;
                    index = right;
                }
            } else {
                break;
            }
        }

        return removeElement;
    }

    public int size() {
        return realSize;
    }

    @Override
    public String toString() {
        String s = "";

        for (int i=1; i<=realSize ;i++) {
                s += minHeap[i];
        }

        return s;
    }

    public static void main(String[] args) {
        MinHeap mh = new MinHeap(6);
        mh.add(1);
        mh.add(2);
        mh.add(3);
        mh.add(4);
        mh.add(5);
        System.out.println(mh.pop());
        System.out.println(mh.size());
        System.out.println(mh.toString());
    }
}
