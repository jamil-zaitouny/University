package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.AbstractDataTypes.MyStack;
import Models.Exceptions.MyException;
import Models.Structures.ProgramState;
import Models.Types.IType;

public class ForkStatement implements IStatement {
    IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ProgramState.setID();
        ProgramState newState = new ProgramState( new MyStack<IStatement>(),
                                    state.cloneSymbolTable(),
                                    state.getOutput(),
                                    state.getFileTable(),
                                    state.getHeap(),
                                    statement);
        return newState;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        statement.typeCheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
    }
}
