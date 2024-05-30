package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.m_offices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<m_offices,String> {

    m_offices findByOfficeCode(String officeCode);
}
