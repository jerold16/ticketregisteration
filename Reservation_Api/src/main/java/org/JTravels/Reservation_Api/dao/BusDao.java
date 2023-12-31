package org.JTravels.Reservation_Api.dao;

import java.util.List;
import java.util.Optional;

import org.JTravels.Reservation_Api.Dto.Bus;
import org.JTravels.Reservation_Api.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	@Autowired
	private BusRepository rep;
	
	public Bus save(Bus bus) {
		return rep.save(bus);
	}
	public Bus update(Bus bus) {
		return rep.save(bus);
	}
	public Optional<Bus> findbyID(int id){
		return rep.findById(id);
	}
    public List<Bus> FindByroutes(String from,String to){
    	return rep.Busroutes(from, to);
    }

}
