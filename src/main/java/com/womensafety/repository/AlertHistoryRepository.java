package com.womensafety.repository;

import com.womensafety.model.AlertHistory;
import com.womensafety.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertHistoryRepository extends JpaRepository<AlertHistory, Long> {
    List<AlertHistory> findByUserOrderByArchivedAtDesc(User user);
}
