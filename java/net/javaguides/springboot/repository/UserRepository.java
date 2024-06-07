package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.mt_userlogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<mt_userlogin,String> {
    mt_userlogin findByUserCode(String userCode);
}
