package com.womensafety.repository;

import com.womensafety.model.SosAlert;
import com.womensafety.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SosAlertRepository extends JpaRepository<SosAlert, Long> {
    List<SosAlert> findByUserOrderByTriggeredAtDesc(User user);
    List<SosAlert> findByStatus(SosAlert.Status status);
}
