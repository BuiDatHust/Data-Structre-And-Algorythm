package Stack;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    Queue<Integer> tempQueue = new LinkedList<>();
    Queue<Integer> mainQueue = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        while(!mainQueue.isEmpty()){
            tempQueue.add(mainQueue.poll());
        }

        mainQueue.add(x);

        while (!tempQueue.isEmpty()){
            mainQueue.add(tempQueue.poll());
        }
    }

    public int pop() {
        return mainQueue.poll();
    }

    public int top() {
        return mainQueue.peek();
    }

    public boolean empty() {
        return mainQueue.isEmpty();
    }
}
