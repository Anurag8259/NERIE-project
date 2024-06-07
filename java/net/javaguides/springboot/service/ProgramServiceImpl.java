package net.javaguides.springboot.service;

import net.javaguides.springboot.model.mt_programdetails;
import net.javaguides.springboot.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    private ProgramRepository programRepository; // Assuming you have a ProgramRepository to interact with the database

    @Override
    public List<mt_programdetails> getPendingPrograms() {
//        return programRepository.findByProgramState("Pending"); // Query the repository for programs with state "Pending"
    return null;
    }
}

