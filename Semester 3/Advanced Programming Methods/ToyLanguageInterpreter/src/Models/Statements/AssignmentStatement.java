package Models.Statements;

import Models.AbstractDataTypes.MyIDictionary;
import Models.Exceptions.MyException;
import Models.Expressions.IExpression;
import Models.Structures.MyIHeap;
import Models.Structures.ProgramState;
import Models.Types.IType;
import Models.Values.IValue;
import Models.AbstractDataTypes.MyIStack;

public class AssignmentStatement implements IStatement{
    String ID;
    IExpression expression;

    public AssignmentStatement(String ID, IExpression expression){
        this.ID = ID;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return ID + "=" + expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, IValue> symbolTable = state.getSymbolTable();
        MyIHeap heap = state.getHeap();

        if(symbolTable.isDefined(ID)){
            IValue value = expression.evaluate(symbolTable, heap);
            IType typeID = (symbolTable.lookup(ID)).getType();
            if(value.getType().equals(typeID)){
                symbolTable.update(ID, value);
            }
            else{
                throw new MyException("declared type of variable " + ID + " and type of the assigned expression do not match.");
            }
        }else{
            throw new MyException("The used variable " + ID + " was not declared before.");
        }
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws MyException {
        IType typeVar = typeEnv.lookup(ID);
        IType typeExpression = expression.typeCheck(typeEnv);
        if(typeVar.equals(typeExpression)){
            return typeEnv;
        }else{
            throw new MyException("Assignment: right hand side and left hand side have different types");
        }
    }

}
