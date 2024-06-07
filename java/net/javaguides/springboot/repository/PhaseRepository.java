package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.m_phases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhaseRepository extends JpaRepository<m_phases,String> {
    List<m_phases> findByProgramProgramCode(String programCode);
}
