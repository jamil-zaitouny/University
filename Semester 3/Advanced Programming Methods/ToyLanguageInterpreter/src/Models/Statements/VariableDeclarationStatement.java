package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Structures.ProgramState;
import Models.Types.IType;
import Models.Values.IValue;

public class VariableDeclarationStatement implements IStatement{
    String ID;
    IType type;

    public VariableDeclarationStatement(String ID, IType type){
        this.ID = ID;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        if(symbolTable.isDefined(ID)){
            throw new MyException(ID + " is already defined");
        }
        symbolTable.addToDictionary(ID, type.defaultValue());
        return null;
    }


    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        typeEnv.addToDictionary(ID, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type.toString() + " " + ID;
    }
}
