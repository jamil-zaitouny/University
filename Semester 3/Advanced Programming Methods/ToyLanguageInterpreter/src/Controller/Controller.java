package Controller;

import Models.Exceptions.MyException;
import Models.Structures.ProgramState;
import Models.Types.ReferenceType;
import Models.Values.IValue;
import Models.Values.ReferenceValue;
import Repository.IRepository;

import java.sql.Ref;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    private IRepository repository;
    private ExecutorService executorService;

    public List<ProgramState> getProgramStateList(){
        return repository.getProgramList();
    }

    private List<ProgramState> programStateList;

    public Controller(IRepository repository){
        this.repository = repository;
        executorService = Executors.newFixedThreadPool(10);
    }

    public ProgramState getProgramStateByID(int ID){
        return repository.getProgramStateByID(ID);
    }

    Map<Integer, IValue> safeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer, IValue> heap){
        return heap.entrySet()
                .stream()
                .filter(element -> addressesFromSymbolTable.contains(element.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> getAddressFromSymbolTable(Collection<IValue> symbolTableValues, Collection<IValue> heap){
        List<Integer> list = Stream.concat(
                heap.stream()
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> {ReferenceValue value1 = (ReferenceValue) value; return value1.getAddress();}),
                symbolTableValues.stream()
                .filter(value -> value instanceof ReferenceValue)
                .map(value -> {ReferenceValue value1 = (ReferenceValue) value; return value1.getAddress();})
        ).collect(Collectors.toList());
        return list;
    }

    List<ProgramState> removeCompleteProgram(List<ProgramState> inProgramList){
        return inProgramList
                .stream()
                .filter(value -> value.isNotComplete())
                .collect(Collectors.toList());
    }

    public void oneStepForAllProgram(List<ProgramState> programStateList) throws MyException {
        programStateList
                .forEach(program -> {
                    try{
                        repository.logProgramStateExecution(program);
                        System.out.println(program.toString());
                    }catch (MyException exception){
                        System.out.println(exception.getMessage());
                    }
                });
        List<Callable<ProgramState>> callableList =
                programStateList
                .stream()
                .map((ProgramState program) ->(Callable<ProgramState>)(()-> {return program.oneStep();}))
                .collect(Collectors.toList());

        try{
            List<ProgramState> newProgramList = executorService
                    .invokeAll(callableList)
                    .stream()
                    .map(programStateFuture -> {
                        try{
                            return programStateFuture.get();
                        }catch (ExecutionException exception){
                            throw new RuntimeException("Thread exception" + exception.getMessage());
                        }catch(InterruptedException exception){
                            throw new RuntimeException("Thread Exception" + exception.getMessage());
                        }
                    })
                    .filter(programState -> programState !=null)
                    .collect(Collectors.toList());
            programStateList.addAll(newProgramList);
            programStateList.forEach(
                    program ->{
                        try{
                            repository.logProgramStateExecution(program);
                            System.out.println(program.toString());
                        }catch(MyException exception){
                            System.out.println(exception.getMessage());
                        }
                    }
            );
            repository.setProgramList(programStateList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean oneStepGUI() throws  MyException{
        List<ProgramState> programStateList = removeCompleteProgram(repository.getProgramList());
        if(programStateList.size() > 0){
            oneStepForAllProgram(programStateList);
            removeCompleteProgram(repository.getProgramList());
            return true;
        }else{
            executorService.shutdownNow();
            repository.setProgramList(programStateList);
            return false;
        }
    }


    public List<Integer> getIdList() {
        return repository.getListOfIds();
    }
}
