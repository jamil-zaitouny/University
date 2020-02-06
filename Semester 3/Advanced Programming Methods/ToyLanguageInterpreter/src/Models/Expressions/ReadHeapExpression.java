package Models.Expressions;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Structures.MyIHeap;
import Models.Types.IType;
import Models.Types.ReferenceType;
import Models.Values.IValue;
import Models.Values.ReferenceValue;

public class ReadHeapExpression implements IExpression{
    IExpression expression;
    public ReadHeapExpression(IExpression expression){
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "ReadHeap(" + expression.toString() + ")";
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap heap) throws MyException {
        IValue value = expression.evaluate(table, heap);
        if(value instanceof ReferenceValue){
            ReferenceValue referenceValue = (ReferenceValue) value;
            int address = referenceValue.getAddress();
            if(heap.isKey(address)){
                return heap.getValue(address);
            }else{
                throw new MyException("Invalid address");
            }
        }else{
            throw new MyException("Not a reference value");
        }
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType type = expression.typeCheck(typeEnv);
        if(type instanceof ReferenceType){
            ReferenceType referenceType = (ReferenceType) type;
            return referenceType.getInner();
        }else{
            throw new MyException("ReadHeap argument not a reference type");
        }
    }
}
