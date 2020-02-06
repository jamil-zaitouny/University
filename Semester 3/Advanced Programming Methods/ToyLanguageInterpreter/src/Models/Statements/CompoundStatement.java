package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.AbstractDataTypes.MyIStack;
import Models.Exceptions.MyException;
import Models.Structures.ProgramState;
import Models.Types.IType;

public class CompoundStatement implements IStatement{
    IStatement first, second;

    public CompoundStatement(IStatement first, IStatement second){
        this.first = first;
        this.second = second;
    }


    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public String toString() {
        return first.toString() + ";" + second.toString();
    }
}
