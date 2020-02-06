package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Expressions.IExpression;
import Models.Structures.MyIFileTable;
import Models.Structures.MyIHeap;
import Models.Structures.ProgramState;
import Models.Types.IType;
import Models.Types.StringType;
import Models.Values.IValue;
import Models.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements IStatement {
    IExpression expression;

    public CloseReadFileStatement(IExpression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIFileTable<StringValue, BufferedReader> fileTable = state.getFileTable();
        MyIHeap heap = state.getHeap();
        IValue value = expression.evaluate(symbolTable, heap);
        if(value.getType().equals(new StringType())){
            if(fileTable.isDefined((StringValue) value)){
                BufferedReader fileDescriptor = fileTable.get((StringValue) value);
                try{
                    fileDescriptor.close();
                    fileTable.delete((StringValue) value);
                }catch (IOException exception){
                    throw new MyException("invalid close[");
                }
            }else{
                throw new MyException("invalid filename");
            }
        }else {
            throw new MyException("invalid type");
        }
        return null;
    }


    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        if(expression.typeCheck(typeEnv).equals(new StringType())){
            return  typeEnv;
        }else{
            throw new MyException("wrong type of argument");
        }
    }

    @Override
    public String toString() {
        return "closeReadFile(" + expression.toString() + ")";
    }
}
