package Models.Structures;

import java.util.Hashtable;

public interface MyIFileTable<TFileName, TDescriptor> {
    boolean isDefined(TFileName name);

    void add(TFileName name, TDescriptor fileDescriptor);

    TDescriptor get(TFileName name);

    void delete(TFileName name);

    Hashtable<TFileName, TDescriptor> getAll();
}
