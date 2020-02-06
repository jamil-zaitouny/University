package Models.Structures;

import java.util.Hashtable;

public class MyFileTable<TFileName, TDescriptor> implements MyIFileTable<TFileName, TDescriptor>{
    private Hashtable<TFileName, TDescriptor> fileTable;
    public MyFileTable(){
        fileTable = new Hashtable<TFileName, TDescriptor>();
    }

    @Override
    public String toString() {
        String result = "";
        for(TFileName name: fileTable.keySet()){
            result += "\t" + name.toString() + "\n";
        }
        return result;
    }

    @Override
    public boolean isDefined(TFileName name) {
        return fileTable.contains(name);
    }

    @Override
    public void add(TFileName name, TDescriptor fileDescriptor) {
        fileTable.put(name, fileDescriptor);
    }

    @Override
    public TDescriptor get(TFileName name) {
        return fileTable.get(name);
    }

    @Override
    public void delete(TFileName name) {
        fileTable.remove(name);
    }

    @Override
    public Hashtable getAll() {
        return fileTable;
    }
}
