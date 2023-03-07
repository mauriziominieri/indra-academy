package com.academy.repository;

import com.academy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findBySurnameIgnoreCase(String surname);
    List<User> findByYearsGreaterThanOrderByYearsDesc(Long anni);
}
