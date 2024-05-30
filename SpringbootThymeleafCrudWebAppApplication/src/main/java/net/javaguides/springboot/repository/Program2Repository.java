package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.m_programs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Program2Repository extends JpaRepository<m_programs,String > {

    m_programs findByProgramCode(String programCode);
}
