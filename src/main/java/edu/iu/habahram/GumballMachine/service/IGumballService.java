package edu.iu.habahram.GumballMachine.service;

import edu.iu.habahram.GumballMachine.model.GumballMachineRecord;
import edu.iu.habahram.GumballMachine.model.TransitionResult;

import java.io.IOException;
import java.util.List;

public interface IGumballService {
    TransitionResult insertQuarter(String id) throws IOException;
    TransitionResult ejectQuarter(String id) throws IOException;
    TransitionResult turnCrank(String id) throws IOException;
    
    List<GumballMachineRecord> findAll() throws IOException;

    GumballMachineRecord findById(String id) throws IOException;

    String save(GumballMachineRecord gumballMachineRecord) throws IOException;

}
