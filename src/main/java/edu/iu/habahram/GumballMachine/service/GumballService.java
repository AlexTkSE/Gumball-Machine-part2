package edu.iu.habahram.GumballMachine.service;

import edu.iu.habahram.GumballMachine.model.GumballMachine;
import edu.iu.habahram.GumballMachine.model.GumballMachineRecord;
import edu.iu.habahram.GumballMachine.model.IGumballMachine;
import edu.iu.habahram.GumballMachine.model.TransitionResult;
import edu.iu.habahram.GumballMachine.repository.IGumballRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Service
public class GumballService implements IGumballService{

    IGumballRepository gumballRepository;

    public GumballService(IGumballRepository gumballRepository) {
        this.gumballRepository = gumballRepository;
    }

    @Override
    public TransitionResult insertQuarter(String id) throws IOException {
        return performTransition(id, GumballMachine::insertQuarter);
    }

    @Override
    public TransitionResult ejectQuarter(String id) throws IOException {
        return performTransition(id, GumballMachine::ejectQuarter);
    }

    @Override
    public TransitionResult turnCrank(String id) throws IOException {
        return performTransition(id, GumballMachine::turnCrank);
    }

    private TransitionResult performTransition(String id, Function<GumballMachine, TransitionResult> transitionFunction) throws IOException {
        GumballMachineRecord record = gumballRepository.findById(id);
        GumballMachine machine = new GumballMachine(record.getId(), record.getState(), record.getCount());
        TransitionResult result = transitionFunction.apply(machine);
        if (result.succeeded()) {
            record.setState(result.stateAfter());
            record.setCount(result.countAfter());
            save(record);
        }
        return result;
    }

    @Override
    public List<GumballMachineRecord> findAll() throws IOException {
        return gumballRepository.findAll();
    }

    @Override
    public GumballMachineRecord findById(String id) throws IOException {
        return gumballRepository.findById(id);
    }

    @Override
    public String save(GumballMachineRecord gumballMachineRecord) throws IOException {
        return gumballRepository.save(gumballMachineRecord);
    }
}
