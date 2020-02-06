package Models.AbstractDataTypes;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<TKey, TValue> {
    boolean isDefined(TKey ID);

    TValue lookup(TKey ID);

    void update(TKey ID, TValue value);

    void addToDictionary(TKey ID, TValue value);

    MyIDictionary<TKey, TValue> clone();

    Map<TKey, TValue> getContent();

    Hashtable<TKey, TValue> getAll();

}
