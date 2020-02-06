package Models.AbstractDataTypes;

import java.util.Stack;

public class MyStack<TElem> implements MyIStack<TElem>{
    Stack<TElem> stack;

    public MyStack(){
        stack = new Stack<TElem>();
    }
    public MyStack(Stack<TElem> stack){
        this.stack = stack;
    }

    @Override
    public TElem pop() {
        return stack.pop();
    }

    @Override
    public void push(TElem elem) {
        stack.push(elem);
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public TElem top() {
        return stack.peek();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Stack<TElem> getAll() {
        return this.stack;
    }
}
