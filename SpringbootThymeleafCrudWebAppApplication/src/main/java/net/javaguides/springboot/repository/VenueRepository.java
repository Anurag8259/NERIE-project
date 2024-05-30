package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.m_venues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VenueRepository extends JpaRepository<m_venues,String> {
    m_venues findByVenueCode(String venueCode);
}
