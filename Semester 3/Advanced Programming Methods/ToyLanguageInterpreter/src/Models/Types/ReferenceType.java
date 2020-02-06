package Models.Types;

import Models.Values.IValue;
import Models.Values.ReferenceValue;

public class ReferenceType implements IType{
    IType inner;

    public ReferenceType(IType inner){
        this.inner = inner;
    }
    public IType getInner(){
        return inner;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof ReferenceType){
            return inner.equals(((ReferenceType) inner).getInner());
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public IValue defaultValue() {
        return new ReferenceValue(inner, 0);
    }
}
