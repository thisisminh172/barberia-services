package com.barberia.app.repositories;

import com.barberia.app.models.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository  extends JpaRepository<Turn, Long> {
}
