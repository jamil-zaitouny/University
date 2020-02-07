package View;

import Controller.Controller;
import Models.AbstractDataTypes.*;
import Models.Exceptions.MyException;
import Models.Statements.IStatement;
import Models.Structures.*;
import Models.Values.IValue;
import Models.Values.StringValue;
import Repository.IRepository;
import Repository.Repository;
import View.Utilities.HeapObject;
import View.Utilities.SymbolTableObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.util.ArrayList;

public class StatementExecutionWindow {
    IStatement statement;
    @FXML
    ListView<String> programView;
    @FXML
    ListView<String> executionStackView;
    @FXML
    ListView<String> outView;
    @FXML
    ListView<String> fileTableView;
    @FXML
    TableView<HeapObject> heapView;
    @FXML
    TableColumn<HeapObject, Integer> heapAddress;
    @FXML
    TableColumn<HeapObject, IValue> heapValue;
    @FXML
    TableView<SymbolTableObject> symbolTableView;
    @FXML
    TableColumn<SymbolTableObject, String> symbolName;
    @FXML
    TableColumn<SymbolTableObject, IValue> symbolValue;
    @FXML
    Label statementName;
    @FXML
    Label numberProgramStates;
    @FXML
    Button runButton;


    MyIDictionary<String, IValue> symbolTable;
    MyIHeap heap;
    MyIStack<IStatement> executionStack;
    MyIList<IValue> out;
    MyIFileTable<StringValue, BufferedReader> fileTable;
    ArrayList<Integer> programListIDs;
    private Controller controller;
    int size;

    public StatementExecutionWindow(IStatement statement)
    {
        this.statement= statement;
    }

    @FXML
    public void initialize() {
        statementName.setText(statement.toString());
        ProgramState state = new ProgramState(new MyStack<IStatement>(), new MyDictionary<String, IValue>(), new MyList<IValue>(), new MyFileTable<>(), new MyHeap(), statement);
        IRepository repo = new Repository("..\\repo.txt");
        repo.addState(state);
        controller= new Controller(repo);
        initPrgState();
        programView.getSelectionModel().selectFirst();
        runButton.setOnMouseClicked(event -> {
            try {
                if(controller.oneStepGUI()) {
                    initPrgState();
                    setAll();
                }
            }
            catch (MyException exp)
            {
                exp.printStackTrace();
            }
        });
        programView.setOnMouseClicked(event -> setAll());
        heapAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        heapValue.setCellValueFactory(new PropertyValueFactory<>("Value"));

        symbolName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        symbolValue.setCellValueFactory(new PropertyValueFactory<>("Value"));


        setAll();
    }

    public void initPrgState()
    {
        ObservableList<String> prgListId = FXCollections.observableArrayList();
        programListIDs = (ArrayList<Integer>) controller.getIdList();

        for(int id : programListIDs)
            prgListId.add(String.valueOf(id));

        programView.setItems(prgListId);
        numberProgramStates.setText("No of Prg States: "+ String.valueOf(programListIDs.size()));
        if(size != prgListId.size())
        {
            programView.getSelectionModel().selectFirst();
            size = prgListId.size();
        }
    }

    public void setAll()
    {
        ProgramState state = controller.getProgramStateByID(Integer.parseInt(programView.getSelectionModel().getSelectedItem()));
        symbolTable = state.getSymbolTable();
        executionStack= state.getExecutionStack();
        heap = state.getHeap();
        out = state.getOutput();
        fileTable = state.getFileTable();

        ObservableList<String> exeStackObs = FXCollections.observableArrayList();
        int index = executionStack.getAll().size()-1;
        while (index >= 0)
        {
            exeStackObs.add(executionStack.getAll().get(index).toString());
            index--;
        }
        executionStackView.setItems(exeStackObs);

        ObservableList<String> outObs = FXCollections.observableArrayList();
        for(IValue v : out.getAll())
            outObs.add(v.toString());
        outView.setItems(outObs);

        ObservableList<String> fileObs = FXCollections.observableArrayList();
        for(StringValue sv : fileTable.getAll().keySet())
            fileObs.add(sv.toString());
        fileTableView.setItems(fileObs);

        ObservableList<HeapObject> heapObs = FXCollections.observableArrayList();
        for(int key : heap.getAll().keySet())
            heapObs.add(new HeapObject(key, heap.getValue(key)));
        heapView.setItems(heapObs);

        ObservableList<SymbolTableObject> symTblObs = FXCollections.observableArrayList();
        for(String name : symbolTable.getAll().keySet())
            symTblObs.add(new SymbolTableObject(name, symbolTable.lookup(name)));
        symbolTableView.setItems(symTblObs);
    }

}
