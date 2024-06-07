package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.m_coursecategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<m_coursecategories,String> {
    m_coursecategories findByCourseCategoryCode(String programCategory);
}
