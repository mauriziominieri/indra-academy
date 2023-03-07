package com.academy.repository;

import com.academy.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findAllByOrderByLogDateDesc();
    List<Log> findByLogDateAfterOrderByLogDateDesc(Date fromDate);
    List<Log> findByLogDateBeforeOrderByLogDateDesc(Date toDate);
    List<Log> findByLogDateBetweenOrderByLogDateDesc(Date fromDate, Date toDate);
}
