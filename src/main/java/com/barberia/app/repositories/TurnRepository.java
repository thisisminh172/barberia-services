package com.barberia.app.repositories;

import com.barberia.app.models.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnRepository  extends JpaRepository<Turn, Long> {
    List<Turn> findAllByStatus(String status);
    List<Turn> findAllByStatusAndEmployeeId(String status, long id);
}
