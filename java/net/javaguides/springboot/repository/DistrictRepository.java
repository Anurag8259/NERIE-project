package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.m_districts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DistrictRepository extends JpaRepository<m_districts,String> {
    m_districts findByDistrictCode(String districtCode);

    List<m_districts> findByState_StateCode(String stateCode);
}
