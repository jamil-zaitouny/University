package Models.Expressions;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Structures.MyIHeap;
import Models.Types.IType;
import Models.Values.IValue;

public class VariableExpression implements IExpression{
    String ID;

    public VariableExpression(String ID){
        this.ID = ID;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap heap) throws MyException {
        return table.lookup(ID);
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return typeEnv.lookup(ID);
    }

    @Override
    public String toString() {
        return ID;
    }
}
