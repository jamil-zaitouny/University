package Models.Types;

import Models.Values.IValue;

public class IntType implements IType{
    public boolean equals(Object another){
        if(another instanceof IntType){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Integer";
    }

    @Override
    public IValue defaultValue() {
        return null;
    }
}
