package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Expressions.IExpression;
import Models.Structures.MyIHeap;
import Models.Structures.ProgramState;
import Models.Types.IType;
import Models.Types.ReferenceType;
import Models.Values.IValue;
import Models.Values.ReferenceValue;

import java.beans.Expression;
import java.net.IDN;


public class NewHeapStatement implements IStatement{
    String variableName;
    IExpression expression;

    public NewHeapStatement(String variableName, IExpression expression){
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        if(symbolTable.isDefined(variableName)){
            if(symbolTable.lookup(variableName).getType() instanceof ReferenceType){
                IValue value = expression.evaluate(symbolTable, heap);
                ReferenceType referenceType = (ReferenceType) symbolTable.lookup(variableName).getType();
                if(value.getType().equals(referenceType.getInner())){
                    heap.add(value);
                    symbolTable.addToDictionary(variableName, new ReferenceValue(value.getType(), heap.getCurrentKey()));
                    return null;
                }else{
                    throw new MyException("invalid type");
                }
            }else{
                throw new MyException("not a reference type");
            }
        }else{
            throw new MyException("variable is not defined");
        }
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType variableType = typeEnv.lookup(variableName);
        IType expressionType = expression.typeCheck(typeEnv);
        if(variableType.equals(new ReferenceType(expressionType))){
            return typeEnv;
        }else{
            throw new MyException("New Statement: different types");
        }
    }

    @Override
    public String toString() {
        return "NewHeapStatement(" + variableName + "," + expression.toString() + ")";
    }
}
