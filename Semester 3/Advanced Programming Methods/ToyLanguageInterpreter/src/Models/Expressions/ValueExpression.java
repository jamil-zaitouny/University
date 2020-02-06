package Models.Expressions;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Structures.MyIHeap;
import Models.Types.IType;
import Models.Values.IValue;

public class ValueExpression implements IExpression{
    IValue value;

    public ValueExpression(IValue value){
        this.value = value;
    }
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap heap) throws MyException {
        return value;
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return value.getType();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
