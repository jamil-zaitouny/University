package Repository;

import Models.Exceptions.MyException;
import Models.Structures.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> states;
    private String logFilePath;

    public Repository(String logFilePath){
        states = new ArrayList<ProgramState>();
        this.logFilePath = logFilePath;
    }

    @Override
    public void addState(ProgramState state) {
        states.add(state);
    }

    @Override
    public void logProgramStateExecution(ProgramState state) throws MyException {
        try{
            PrintWriter logFile = new PrintWriter(
                    new BufferedWriter(new FileWriter(logFilePath, true))
            );
            logFile.append(state.toString());
            logFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProgramState> getProgramList() {
        return states;
    }

    @Override
    public void setProgramList(List<ProgramState> listState) {
        states = listState;
    }

    @Override
    public List<Integer> getListOfIds() {
        List<Integer> IDs = new ArrayList<>();;
        for(ProgramState state: states){
            IDs.add(state.getProgramID());
        }
        return IDs;
    }


    @Override
    public ProgramState getProgramStateByID(int ID) {
        for(ProgramState state: states){
            if(state.getProgramID() == ID){
                return state;
            }
        }
        return null;
    }
}
