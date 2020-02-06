package Models.Values;

import Models.Types.IType;
import Models.Types.ReferenceType;

public class ReferenceValue implements IValue{
    int address;
    IType locationType;

    public ReferenceValue(IType locationType, int address){
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress(){
        return address;
    }

    @Override
    public String toString() {
        return "("+ String.valueOf(address) + ", " + locationType.toString() + ")";
    }

    @Override
    public IType getType() {
        return new ReferenceType(locationType);
    }
}
