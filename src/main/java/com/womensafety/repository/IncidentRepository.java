package com.womensafety.repository;

import com.womensafety.model.Incident;
import com.womensafety.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByUserOrderByCreatedAtDesc(User user);
}
