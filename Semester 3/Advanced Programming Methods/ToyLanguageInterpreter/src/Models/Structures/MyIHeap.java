package Models.Structures;

import Models.Values.IValue;

import java.util.HashMap;
import java.util.Map;

public interface MyIHeap {
    void add(IValue value);

    int getCurrentKey();

    boolean isKey(int address);

    IValue getValue(int address);

    void update(int address, IValue value);

    public void setContent(Map<Integer, IValue> map);

    Map<Integer, IValue> getContent();

    HashMap<Integer, IValue> getAll();
}
