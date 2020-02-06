package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Expressions.IExpression;
import Models.Structures.MyIFileTable;
import Models.Structures.MyIHeap;
import Models.Structures.ProgramState;
import Models.Types.IType;
import Models.Types.IntType;
import Models.Types.StringType;
import Models.Values.IValue;
import Models.Values.IntValue;
import Models.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class readFileStatement implements IStatement{
    IExpression expression;
    String variableName;

    public readFileStatement(IExpression expression, String variableName){
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        if(!symbolTable.lookup(variableName).getType().equals(new IntType())){
            throw new MyException("invalid type");
        }
        if (symbolTable.isDefined(variableName)) {
            MyIFileTable<StringValue, BufferedReader> fileTable = state.getFileTable();
            MyIHeap heap = state.getHeap();
            IValue value = expression.evaluate(symbolTable, heap);
            if(value.getType().equals(new StringType())){
                BufferedReader fileDescriptor = fileTable.get((StringValue) value);
                if(fileDescriptor == null)  {
                    throw new MyException("Invalid fileDescriptor");
                }
                try{
                    String read = fileDescriptor.readLine();
                    IntValue number;
                    if(read == null){
                        number = new IntValue(0);
                    }else{
                        number = new IntValue(Integer.parseInt(read));
                    }
                    symbolTable.addToDictionary(variableName, number);
                }catch (IOException exception){
                    throw new MyException("invalid reading");
                }
            }else{
                throw new MyException("invalid filename");
            }
        }else{
            throw new MyException("invalid varName");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType expressionType = expression.typeCheck(typeEnv);
        if(expressionType.equals(new StringType())){
            return typeEnv;
        }
        else{
            throw new MyException("ReadFile: not a string");
        }
    }

    @Override
    public String toString() {
            return "readFile(" + expression.toString() + ")";
    }
}
