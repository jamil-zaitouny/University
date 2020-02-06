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


public class IfStatement implements IStatement{
    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression expression,
                       IStatement thenStatement,
                       IStatement elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();
        IValue value = expression.evaluate(symbolTable, heap);
        if(!value.getType().equals(new BooleanType())){
            throw new MyException("Wrong expression!");
        }
        BooleanValue booleanValue = (BooleanValue)value;
        Boolean bool = booleanValue.getValue();
        if(bool){
            state.getExecutionStack().push(thenStatement);
        }else{
            state.getExecutionStack().push(elseStatement);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        if(expression.typeCheck(typeEnv).equals(new BooleanType())){
            thenStatement.typeCheck(typeEnv.clone());
            elseStatement.typeCheck(typeEnv.clone());
            return typeEnv;
        }else{
            throw new MyException("Condition is not a boolean expression");
        }
    }

    @Override
    public String toString() {
        return "IF (" + expression.toString()
                + ") then (" + thenStatement.toString()
                + ") else (" + elseStatement.toString()
                + ")";
    }
}
