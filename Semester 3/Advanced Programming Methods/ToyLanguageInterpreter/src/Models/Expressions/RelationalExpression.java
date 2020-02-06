package Models.Expressions;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Structures.MyIHeap;
import Models.Types.IType;
import Models.Types.IntType;
import Models.Values.BooleanValue;
import Models.Values.IValue;
import Models.Values.IntValue;

import java.beans.Expression;

public class RelationalExpression implements IExpression {
    IExpression expression1, expression2;
    String operation;

    public RelationalExpression(IExpression expression1, IExpression expression2, String operation){
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap heap) throws MyException {
        IValue value1, value2;

        value1 = expression1.evaluate(table, heap);
        if(value1.getType().equals(new IntType())){
            value2 = expression2.evaluate(table, heap);
            if(value2.getType().equals(new IntType())){
                int number1 = ((IntValue) value1).getValue();
                int number2 = ((IntValue) value2).getValue();

                switch (operation){
                    case "<":
                        return new BooleanValue(number1 < number2);
                    case "<=":
                        return new BooleanValue(number1 <= number2);
                    case "==":
                        return new BooleanValue(number1 == number2);
                    case ">":
                        return new BooleanValue(number1 > number2);
                    case ">=":
                        return new BooleanValue(number1 > number1);
                }
            }else{
                throw new MyException("invalid expression");
            }
        }else{
            throw new MyException("invalid expression");
        }
        return null;
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return null;
    }
}
