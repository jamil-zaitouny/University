package Models.Structures;

import Models.AbstractDataTypes.MyDictionary;
import Models.AbstractDataTypes.MyIDictionary;
import Models.AbstractDataTypes.MyIList;
import Models.AbstractDataTypes.MyIStack;
import Models.Exceptions.MyException;
import Models.Statements.IStatement;
import Models.Values.IValue;
import Models.Values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    MyIStack<IStatement> executionStack;
    MyIDictionary<String, IValue> symbolTable;
    MyIList<IValue> output;
    MyIFileTable<StringValue, BufferedReader> fileTable;
    MyIHeap heap;
    static int ID = 1;
    int programID;


    //Constructor
    public ProgramState(MyIStack<IStatement> stack,
                        MyIDictionary<String, IValue> table,
                        MyIList<IValue> output,
                        MyIFileTable<StringValue, BufferedReader> fileTable,
                        MyIHeap heap,
                        IStatement statement){

        this.executionStack = stack;
        this.symbolTable = table;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
        this.programID = ID;

        stack.push(statement);
    }


    //Getters and Setters
    public MyIStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(MyIStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public MyIDictionary<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(MyIDictionary<String, IValue> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public MyIList<IValue> getOutput() {
        return output;
    }

    public void setOutput(MyIList<IValue> output) {
        this.output = output;
    }

    public MyIFileTable<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIFileTable<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIHeap getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        ProgramState.ID = ID;
    }

    public int getProgramID() {
        return programID;
    }

    public void setProgramID(int programID) {
        this.programID = programID;
    }

    @Override
    public String toString() {
        return
                "ID = " + String.valueOf(programID)
                + "\nExecution Stack:\n" + executionStack.toString()
                + "Symbol Table nr: " + String.valueOf(programID) + symbolTable.toString()
                + "Output\n" +  output.toString()
                + "File Table\n" + fileTable.toString()
                + "Heap:\n" + heap.toString()
                + "-------------------------------\n";
    }
    public boolean isNotComplete(){
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws MyException{
        if(executionStack.isEmpty()){
            throw new MyException("The execution stack is empty!");
        }
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }
    public static synchronized void setID(){
        ID++;
    }
    public ProgramState get(){
        return this;
    }
    public MyIDictionary<String, IValue> cloneSymbolTable(){
        MyIDictionary<String, IValue> symbolTableClone = new MyDictionary<>();
        for(String key: symbolTable.getContent().keySet()){
            symbolTableClone.addToDictionary(key, symbolTable.lookup(key));
        }
        return symbolTableClone;
    }
}
