package Repository;

import Models.Exceptions.MyException;
import Models.Structures.ProgramState;

import java.util.List;

public interface IRepository {
    void addState(ProgramState state);

    void logProgramStateExecution(ProgramState state) throws MyException;

    List<ProgramState> getProgramList();

    void setProgramList(List<ProgramState> listState);

    List<Integer> getListOfIds();

    ProgramState getProgramStateByID(int ID);
}
