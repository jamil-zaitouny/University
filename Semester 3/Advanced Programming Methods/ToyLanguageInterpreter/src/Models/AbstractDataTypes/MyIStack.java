package Models.AbstractDataTypes;

import java.util.Stack;

public interface MyIStack<TElem>{
    TElem pop();
    void push(TElem elem);
    int size();
    TElem top();
    boolean isEmpty();

    Stack<TElem> getAll();
}
