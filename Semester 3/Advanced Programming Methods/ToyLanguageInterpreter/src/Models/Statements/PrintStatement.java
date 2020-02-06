package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.AbstractDataTypes.MyIList;
import Models.Exceptions.MyException;
import Models.Expressions.IExpression;
import Models.Structures.MyIHeap;
import Models.Structures.ProgramState;
import Models.Types.IType;
import Models.Values.IValue;

public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression){
        this.expression = expression;
    }


    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIList<IValue> output = state.getOutput();
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();
        output.add(expression.evaluate(symbolTable, heap));
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print (" + expression.toString() + ")";
    }
}
