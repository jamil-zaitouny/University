package Models.Expressions;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Structures.MyIHeap;
import Models.Types.IType;
import Models.Types.IntType;
import Models.Values.IValue;
import Models.Values.IntValue;

public class ArithmeticExpression implements  IExpression{
    IExpression expression1, expression2;
    String operation;

    public ArithmeticExpression(String operation,
                                IExpression expression1,
                                IExpression expression2){
        this.operation = operation;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap heap) throws MyException {
        IValue value1, value2;
        value1 = expression1.evaluate(table, heap);

        if(value1.getType().equals(new IntType())){
            value2 = expression2.evaluate(table, heap);
            if(value2.getType().equals(new IntType())){
                IntValue intValue1 = (IntValue)value1;
                IntValue intValue2= (IntValue) value2;

                int number1, number2;

                number1 = intValue1.getValue();
                number2 = intValue2.getValue();
                if(operation.equals("+")){
                    return new IntValue(number1 + number2);
                }else if(operation.equals("-")){
                    return new IntValue(number1 - number2);
                }else if(operation.equals("*")){
                    return new IntValue(number1 * number2);
                }else if(operation.equals("/")){

                    if(number2 == 0){
                        throw new MyException("Division by zero");
                    }else{
                        return new IntValue(number1/number2);
                    }

                }
            }else{
                throw new MyException("Second operand is not an integer");
            }
        }
        throw new MyException("first operand is not an integer");
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeExpression1 = expression1.typeCheck(typeEnv);
        IType typeExpression2 = expression2.typeCheck(typeEnv);
        if(typeExpression1 instanceof IntType){
            if(typeExpression2 instanceof  IntType){
                return new IntType();
            }else{
                throw new MyException("The second operand is not an integer");
            }
        }else{
            throw new MyException("The first operand is not an integer");
        }
    }

    @Override
    public String toString() {
        return expression1.toString() + operation + expression2.toString();
    }
}
