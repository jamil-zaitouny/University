package View.Utilities;

import Models.Values.IValue;

public class HeapObject {
    int Address;
    IValue Value;

    public int getAddress() {
        return Address;
    }

    public void setAddress(int address) {
        this.Address = address;
    }

    public IValue getValue() {
        return Value;
    }

    public void setValue(IValue value) {
        this.Value = value;
    }

    public HeapObject(int address, IValue value) {
        this.Address = address;
        this.Value = value;
    }
}
