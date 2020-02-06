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
    ListView<String> prgView;
    @FXML
    ListView<String> exeStackView;
    @FXML
    ListView<String> outView;
    @FXML
    ListView<String> fileTblView;
    @FXML
    TableView<HeapObject> heapView;
    @FXML
    TableColumn<HeapObject, Integer> heapAddr;
    @FXML
    TableColumn<HeapObject, IValue> heapValue;
    @FXML
    TableView<SymbolTableObject> symTblView;
    @FXML
    TableColumn<SymbolTableObject, String> symName;
    @FXML
    TableColumn<SymbolTableObject, IValue> symValue;
    @FXML
    Label statementName;
    @FXML
    Label numberPageStates;
    @FXML
    Button runButton;


    MyIDictionary<String, IValue> symTbl;
    MyIHeap heap;
    MyIStack<IStatement> executionStack;
    MyIList<IValue> out;
    MyIFileTable<StringValue, BufferedReader> fileTable;
    ArrayList<Integer> prgListIds;
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
        prgView.getSelectionModel().selectFirst();
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
        prgView.setOnMouseClicked(event -> setAll());
        heapAddr.setCellValueFactory(new PropertyValueFactory<>("Address"));
        heapValue.setCellValueFactory(new PropertyValueFactory<>("Value"));

        symName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        symValue.setCellValueFactory(new PropertyValueFactory<>("Value"));


        setAll();
    }

    public void initPrgState()
    {
        ObservableList<String> prgListId = FXCollections.observableArrayList();
        prgListIds = (ArrayList<Integer>) controller.getIdList();

        for(int id : prgListIds)
            prgListId.add(String.valueOf(id));

        prgView.setItems(prgListId);
        numberPageStates.setText("No of Prg States: "+ String.valueOf(prgListIds.size()));
        if(size != prgListId.size())
        {
            prgView.getSelectionModel().selectFirst();
            size = prgListId.size();
        }
    }

    public void setAll()
    {
        ProgramState state = controller.getProgramStateByID(Integer.parseInt(prgView.getSelectionModel().getSelectedItem()));
        symTbl = state.getSymbolTable();
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
        exeStackView.setItems(exeStackObs);

        ObservableList<String> outObs = FXCollections.observableArrayList();
        for(IValue v : out.getAll())
            outObs.add(v.toString());
        outView.setItems(outObs);

        ObservableList<String> fileObs = FXCollections.observableArrayList();
        for(StringValue sv : fileTable.getAll().keySet())
            fileObs.add(sv.toString());
        fileTblView.setItems(fileObs);

        ObservableList<HeapObject> heapObs = FXCollections.observableArrayList();
        for(int key : heap.getAll().keySet())
            heapObs.add(new HeapObject(key, heap.getValue(key)));
        heapView.setItems(heapObs);

        ObservableList<SymbolTableObject> symTblObs = FXCollections.observableArrayList();
        for(String name : symTbl.getAll().keySet())
            symTblObs.add(new SymbolTableObject(name, symTbl.lookup(name)));
        symTblView.setItems(symTblObs);
    }

}
