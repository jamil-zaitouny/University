package Models.Structures;

import Models.Values.IValue;

import java.util.HashMap;
import java.util.Map;

public class MyHeap implements MyIHeap {
    HashMap<Integer, IValue> heap;
    int key;

    public MyHeap(){
        heap = new HashMap<Integer, IValue>();
        key = 0;
    }

    @Override
    public String toString() {
        String result = "";
        for(Integer key: heap.keySet()){
            result += "\t" + String.valueOf(key) + heap.get(key) + "\n";
        }
        return result;
    }

    @Override
    public void add(IValue value) {
        key++;
        heap.put(key, value);
    }

    @Override
    public int getCurrentKey() {
        return key;
    }

    @Override
    public boolean isKey(int address) {
        return heap.containsKey(address);
    }

    @Override
    public IValue getValue(int address) {
        return heap.get(address);
    }

    @Override
    public void update(int address, IValue value) {
        heap.put(address, value);
    }

    @Override
    public void setContent(Map<Integer, IValue> map) {
        heap.clear();
        for(Integer key: map.keySet()){
            heap.put(key, map.get(key));
        }
    }

    @Override
    public Map<Integer, IValue> getContent() {
        return heap;
    }

    @Override
    public HashMap<Integer, IValue> getAll() {
        return heap;
    }
}
