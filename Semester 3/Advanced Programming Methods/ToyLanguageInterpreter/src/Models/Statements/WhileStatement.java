package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Expressions.IExpression;
import Models.Structures.MyIHeap;
import Models.Structures.ProgramState;
import Models.Types.BooleanType;
import Models.Types.IType;
import Models.Values.BooleanValue;
import Models.Values.IValue;


public class WhileStatement implements IStatement {
    IExpression expression;
    IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement){
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();
        IValue value = expression.evaluate(symbolTable, heap);
        if(value.getType().equals(new BooleanType())){
            BooleanValue booleanValue = (BooleanValue) value;
            if(booleanValue.getValue() == true){
                state.getExecutionStack().push(new WhileStatement(expression, statement));
                state.getExecutionStack().push(statement);
            }
            return null;
        }else{
            throw new MyException("Invalid type");
        }
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType expressionType = expression.typeCheck(typeEnv);
        if(expressionType instanceof BooleanType){
            statement.typeCheck(typeEnv.clone());
            return typeEnv;
        }else{
            throw new MyException("While condition not a boolean expression");
        }
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ")(" + statement.toString() + ")";
    }
}
