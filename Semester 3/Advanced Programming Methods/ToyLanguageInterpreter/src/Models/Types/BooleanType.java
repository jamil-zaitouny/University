package Models.Types;

import Models.Values.IValue;

public class BooleanType implements IType{
    public boolean equals(Object another){
        if(another instanceof  BooleanType){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Boolean";
    }

    @Override
    public IValue defaultValue() {
        return null;
    }
}
