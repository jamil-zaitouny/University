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

import java.sql.Ref;

public class WriteHeap implements IStatement{
    String variableName;
    IExpression expression;

    public WriteHeap(String variableName, IExpression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();
        if(symbolTable.isDefined(variableName)){
            IValue value = symbolTable.lookup(variableName);
            if(value.getType() instanceof ReferenceType){
                ReferenceValue referenceValue = (ReferenceValue) value;
                if(heap.isKey(referenceValue.getAddress())){
                    IValue expressionValue = expression.evaluate(symbolTable, heap);
                    if(expressionValue.getType().equals(((ReferenceType) referenceValue.getType()).getInner())){
                        heap.update(referenceValue.getAddress(), expressionValue);
                        return null;
                    } else{
                        throw new MyException("Invalid type!");
                    }
                }else{
                    throw new MyException("Invalid address!");
                }
            }else{
                throw new MyException("Invalid type!");
            }
        }else{
            throw new MyException("Variable not defined!");
        }
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType variableType = typeEnv.lookup(variableName);
        IType expressionType = expression.typeCheck(typeEnv);
        if(variableType.equals(new ReferenceType(expressionType))){
            return typeEnv;
        }else{
            throw new MyException("WriteHeap statement: different types");
        }
    }

    @Override
    public String toString() {
        return "WriteHeap(" + variableName + ", " + expression.toString() + ")";
    }
}
