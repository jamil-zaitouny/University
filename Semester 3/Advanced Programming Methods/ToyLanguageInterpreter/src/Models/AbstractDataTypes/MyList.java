package Models.AbstractDataTypes;

import java.util.ArrayList;

public class MyList<TElem> implements MyIList<TElem>{
    ArrayList<TElem> list;

    public MyList(ArrayList<TElem> list){
        this.list = list;
    }
    public MyList(){
        list = new ArrayList<TElem>();
    }

    @Override
    public void add(TElem element) {
        list.add(element);
    }

    @Override
    public void add(int position, TElem element) {
        list.add(position, element);
    }

    @Override
    public boolean contains(TElem element) {
        return list.contains(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public TElem get(int position) {
        return list.get(position);
    }

    @Override
    public TElem remove(int position) {
        return remove(position);
    }

    @Override
    public ArrayList<TElem> getAll() {
        return list;
    }

    @Override
    public String toString() {
        String result = "";
        for(TElem element: list){
            result += "\t" + element.toString() + "\n";
        }
        return result;
    }
}
