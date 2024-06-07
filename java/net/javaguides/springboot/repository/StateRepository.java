package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.m_states;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<m_states,String> {
    m_states findByStateCode(String stateCode);
}
