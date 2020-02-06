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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenReadFileStatement implements IStatement {
    IExpression expression;

    public OpenReadFileStatement(IExpression expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIFileTable fileTable = state.getFileTable();
        MyIHeap heap = state.getHeap();
        IValue value = expression.evaluate(symbolTable, heap);
        if(value.getType().equals(new StringType())){
            StringValue name = (StringValue) value;
            if(!fileTable.isDefined(name)){
                try{
                    BufferedReader fileDescriptor = new BufferedReader(new FileReader((String) name.getValue()));
                    fileTable.add(name, fileDescriptor);
                }catch (FileNotFoundException exception){
                    throw new MyException("file not found");
                }
            }else{
                throw new MyException("name already defined!");
            }
        }else{
            throw new MyException("invalid filename");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        if(expression.typeCheck(typeEnv).equals(new StringType())){
            return typeEnv;
        }else{
            throw new MyException("Open file not a string");
        }
    }

    @Override
    public String toString() {
        return "OpenReadFileStatement(" + expression.toString() + ")";
    }
}
