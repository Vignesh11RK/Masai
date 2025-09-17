package com.vig.BookMyshowClone.repository;


import com.vig.BookMyshowClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
