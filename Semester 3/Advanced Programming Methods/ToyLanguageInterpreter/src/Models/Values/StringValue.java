package Models.Values;

import Models.Types.IType;
import Models.Types.StringType;

public class StringValue implements IValue {
    String string;

    public StringValue(String string){
        this.string = string;
    }

    public String getValue(){
        return string;
    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof StringValue){
            if(((StringValue) another).getValue() == string){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public IType getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return string;
    }
}
