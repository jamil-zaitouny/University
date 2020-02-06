package Models.Expressions;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Structures.MyIHeap;
import Models.Types.IType;
import Models.Values.IValue;

public interface IExpression {
    IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap heap) throws MyException;
    IType typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException;
}
