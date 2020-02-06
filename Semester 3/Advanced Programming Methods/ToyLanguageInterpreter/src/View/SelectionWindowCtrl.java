package View;


import Models.AbstractDataTypes.MyDictionary;
import Models.Exceptions.MyException;
import Models.Expressions.*;
import Models.Statements.*;
import Models.Types.BooleanType;
import Models.Types.IntType;
import Models.Types.ReferenceType;
import Models.Types.StringType;
import Models.Values.BooleanValue;
import Models.Values.IntValue;
import Models.Values.StringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectionWindowCtrl {

    @FXML
    ListView<String> stmtString;

    List<IStatement> stmtList = new ArrayList<>();

    @FXML
    public void initialize(){
        //int v; v=2; print (v)
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));

        IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BooleanType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(3)))),
                                        new PrintStatement(new
                                                VariableExpression("v"))))));

        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("s",new StringType()),
                new CompoundStatement(new AssignmentStatement("s",new ValueExpression(new StringValue("ana"))),
                        new PrintStatement(new VariableExpression("s"))));
        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new readFileStatement(new VariableExpression("varf"), "varc"),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new readFileStatement(new VariableExpression("varf"),"varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new readFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFileStatement(new VariableExpression("varf"))))))))));

        IStatement ex6 = new IfStatement(new RelationalExpression(new ValueExpression(new IntValue(40)), new ValueExpression(new IntValue(30)),
                "=="),new PrintStatement(new ValueExpression(new StringValue("ana"))),
                new PrintStatement(new ValueExpression(new StringValue("ion"))));

        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewHeapStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewHeapStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new VariableExpression("a")))))));


        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewHeapStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewHeapStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression("+",new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))),new ValueExpression(new IntValue(5)))))))));


        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewHeapStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeap("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression("+", new ReadHeapExpression(new VariableExpression("v")),new ValueExpression(new IntValue(5))))))));


        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(ReadHeapExpression()(ReadHeapExpression()(a)))
        IStatement ex10 = new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new NewHeapStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewHeapStatement("a",new VariableExpression("v")),
                                        new CompoundStatement(new NewHeapStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a")))))))));
        // int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex11 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",new ArithmeticExpression("-", new VariableExpression("v"), new ValueExpression(new IntValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));


        //int v; Ref int a; v=10;new(a,22);
        // fork(wH(a,30);v=32;print(v);print(ReadHeapExpression()(a)));
        // print(v);print(ReadHeapExpression()(a))

        IStatement inFork=new CompoundStatement(new WriteHeap("a",new ValueExpression(new IntValue(30))),
                new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))));

        IStatement inFork2=new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                new PrintStatement(new VariableExpression("v")));


        IStatement ex12 = new CompoundStatement(new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                                new CompoundStatement(new NewHeapStatement("a",new ValueExpression(new IntValue(22))),
                                        new CompoundStatement(new ForkStatement(inFork),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));

        //Wrong type check
        //bool a;a=13;
        IStatement ex13 = new CompoundStatement(new VariableDeclarationStatement("a", new BooleanType()), new AssignmentStatement("a", new ValueExpression(new IntValue(13))));

        //Wrong type check
        //Ref Int a; new(a,'ana')
        IStatement ex14 = new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())), new NewHeapStatement("a", new ValueExpression(new StringValue("ana"))));

        stmtList.add(ex1);
        stmtList.add(ex2);
        stmtList.add(ex3);
        stmtList.add(ex4);
        stmtList.add(ex5);
        stmtList.add(ex6);
        stmtList.add(ex7);
        stmtList.add(ex8);
        stmtList.add(ex9);
        stmtList.add(ex10);
        stmtList.add(ex11);
        stmtList.add(ex12);
        stmtList.add(ex13);
        stmtList.add(ex14);

        ObservableList<String> observableList = FXCollections.observableArrayList();
        
        for(IStatement st : stmtList)
        {
            observableList.add(st.toString());
        }
        stmtString.setItems(observableList);
        stmtString.getSelectionModel().selectFirst();

    }

    @FXML
    public void executeButton(Event evt) throws IOException {
        IStatement stmt = stmtList.get(stmtString.getSelectionModel().getSelectedIndex());

        try {
            stmt.typeCheck(new MyDictionary<>());

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("StatementExecutionWindow.fxml"));
            loader.setController(new StatementExecutionWindow(stmt));
            Stage stage = new Stage();
            Parent root = loader.load();
            stage.setTitle("Running program");
            stage.setScene(new Scene(root, 800, 630));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
        catch (MyException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Typecheck failed");
            alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(e.toString())));
            alert.showAndWait();

        }
        //System.out.println(stmt.toString());
    }


}
