package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.m_programs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Program2Repository extends JpaRepository<m_programs,String > {

    m_programs findByProgramCode(String programCode);

    List<m_programs> findByHasPhases(String hasPhases);
}
