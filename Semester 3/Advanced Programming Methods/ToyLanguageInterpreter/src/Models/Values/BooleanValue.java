package Models.Values;

import Models.Types.BooleanType;
import Models.Types.IType;

public class BooleanValue implements IValue{
    boolean value;


    public BooleanValue(boolean value){
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof BooleanValue){
            if(((BooleanValue) another).value == value){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public boolean getValue(){
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public IType getType() {
        return new BooleanType();
    }
}
