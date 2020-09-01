package com.cg.flight.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.flight.entity.Flight;

@Repository
public interface FlightDao extends JpaRepository<Flight, String>{

	
	@Query("from Flight where flightCode=:flightid")
	public Flight getFlight(@Param("flightid") String flight);
	
}
