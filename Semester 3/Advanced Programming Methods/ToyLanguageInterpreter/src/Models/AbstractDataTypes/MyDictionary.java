package Models.AbstractDataTypes;

import java.util.*;

public class MyDictionary<TKey, TValue> implements MyIDictionary<TKey, TValue>{
    private Hashtable<TKey, TValue> dictionary;

    public MyDictionary(){
        this.dictionary = new Hashtable<TKey, TValue>();
    }


    @Override
    public boolean isDefined(TKey ID) {
        return dictionary.contains(ID);
    }

    @Override
    public TValue lookup(TKey ID) {
        return dictionary.get(ID);
    }

    @Override
    public void update(TKey ID, TValue value) {
        dictionary.put(ID, value);
    }

    @Override
    public void addToDictionary(TKey ID, TValue value) {
        dictionary.put(ID, value);
    }

    @Override
    public MyIDictionary<TKey, TValue> clone() {
        MyIDictionary<TKey, TValue> output = new MyDictionary<TKey, TValue>();
        for(TKey key: this.dictionary.keySet()){
            output.addToDictionary(key, this.lookup(key));
        }
        return output;
    }

    @Override
    public Map<TKey, TValue> getContent() {
        return dictionary;
    }

    @Override
    public Hashtable<TKey, TValue> getAll() {
        return dictionary;
    }
}
