package Models.Types;

import Models.Values.IValue;
import Models.Values.StringValue;

public class StringType implements IType{

    public StringType(){

    }

    @Override
    public boolean equals(Object another) {
        if(another instanceof StringType){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "String";
    }

    @Override
    public IValue defaultValue() {
        return new StringValue("\"\"");
    }
}
