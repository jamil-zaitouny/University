package View.Utilities;

import Models.Values.IValue;

public class SymbolTableObject {

    String Name;
    IValue Value;

    public SymbolTableObject(String name, IValue value) {
        this.Name = name;
        this.Value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public IValue getValue() {
        return Value;
    }

    public void setValue(IValue value) {
        this.Value = value;
    }
}
