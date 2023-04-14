package Queue;

import java.util.Stack;

public class MyQueue {
    Stack<Integer> tempStack = new Stack<>();
    Stack<Integer> mainStack = new Stack<>();

    public MyQueue() {

    }

    public void push(int x) {
        while (!mainStack.isEmpty()) {
            tempStack.push(mainStack.pop());
        }

        mainStack.push(x);

        while (!tempStack.isEmpty()) {
            mainStack.push(tempStack.pop());
        }
    }

    public int pop() {
        return mainStack.pop();
    }

    public int peek() {
        return mainStack.peek();
    }

    public boolean empty() {
        return mainStack.isEmpty();
    }
}
