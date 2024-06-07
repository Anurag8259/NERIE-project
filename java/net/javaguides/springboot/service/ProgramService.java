package net.javaguides.springboot.service;

import net.javaguides.springboot.model.mt_programdetails;

import java.util.List;

public interface ProgramService {
    List<mt_programdetails> getPendingPrograms();
}
