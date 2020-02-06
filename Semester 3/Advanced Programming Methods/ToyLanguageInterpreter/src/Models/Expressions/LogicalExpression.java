package Models.Expressions;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Structures.MyIHeap;
import Models.Types.BooleanType;
import Models.Types.IType;
import Models.Values.BooleanValue;
import Models.Values.IValue;


public class LogicalExpression implements IExpression{
    IExpression expression1, expression2;
    String operation;

    public LogicalExpression(String operation, IExpression expression1, IExpression expression2){
        this.operation = operation;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap heap) throws MyException {
        IValue value1, value2;
        value1 = expression1.evaluate(table, heap);
        if(value1.getType().equals(new BooleanType())){
            value2 = expression2.evaluate(table, heap);

            if(value2.getType().equals(new BooleanType())){
                BooleanValue booleanValue1 = (BooleanValue) value1;
                BooleanValue booleanValue2 = (BooleanValue) value2;

                Boolean boolean1, boolean2;
                boolean1 = booleanValue1.getValue();
                boolean2 = booleanValue2.getValue();

                if(operation.equals("&&")){
                    return new BooleanValue(boolean1 && boolean2);
                }else if(operation.equals("||")){
                    return new BooleanValue(boolean1 || boolean2);
                }else{
                    throw new MyException("Invalid operand");
                }
            }else{
                throw new MyException("The second operand is not a boolean");
            }
        }else{
            throw new MyException("The first operand is not a boolean");
        }
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeExpression1 = expression1.typeCheck(typeEnv);
        IType typeExpression2 = expression2.typeCheck(typeEnv);

        if(typeExpression1 instanceof BooleanType){
            if(typeExpression2 instanceof BooleanType){
                return new BooleanType();
            }else{
                throw new MyException("The second operand is not a boolean");
            }
        }else{
            throw new MyException("The first operand is not an exception");
        }

    }
}
