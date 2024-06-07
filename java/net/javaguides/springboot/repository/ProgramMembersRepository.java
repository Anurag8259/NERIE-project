package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.mt_program_members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramMembersRepository extends JpaRepository<mt_program_members,String> {
}
