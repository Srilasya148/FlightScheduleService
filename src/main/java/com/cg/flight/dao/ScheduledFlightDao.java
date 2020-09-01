package com.cg.flight.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.flight.entity.ScheduledFlight;

@Repository
public interface ScheduledFlightDao extends JpaRepository<ScheduledFlight, Integer>{



}
