package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.mt_programdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<mt_programdetails,String> {
    mt_programdetails findByProgramDetailId(long programId);
    List<mt_programdetails> findByHasPhases(String hasPhases);
    List<mt_programdetails> findByProgramState(String programState);

}
