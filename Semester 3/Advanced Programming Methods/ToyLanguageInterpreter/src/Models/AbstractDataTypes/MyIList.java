package Models.AbstractDataTypes;

import java.util.ArrayList;

public interface MyIList<TElem> {
    void add(TElem element);
    void add(int position, TElem element);
    boolean contains(TElem element);
    int size();
    boolean isEmpty();
    TElem get(int position);
    TElem remove(int position);
    ArrayList<TElem> getAll();
}
