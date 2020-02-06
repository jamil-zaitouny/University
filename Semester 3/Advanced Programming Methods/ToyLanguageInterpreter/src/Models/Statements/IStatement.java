package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Structures.ProgramState;
import Models.Types.IType;

public interface IStatement {
        ProgramState execute(ProgramState state) throws MyException;
        MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException;
}
