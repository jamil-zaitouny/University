package Models.Values;

import Models.Types.IType;
import Models.Types.IntType;

public class IntValue implements IValue {
    int value;
    public IntValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof IntValue){
            if(value == ((IntValue) another).getValue()){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public IType getType() {
        return new IntType();
    }
}
